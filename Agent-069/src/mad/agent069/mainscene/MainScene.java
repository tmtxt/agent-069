package mad.agent069.mainscene;

import mad.agent069.music.MyMusic;
import mad.agent069.switchscene.DisplayStageScene;
import mad.agent069.tween.MainSceneAccessor;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainScene implements Screen{
	
	//Properties
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;
	private Music background_music;
	private Stage stage;
	private BitmapFont font;
	private TextureAtlas atlas;
	private Skin skin;
	private TextButton playButton;
	private TextButton loadButton;
	private TextButton settingButton;
	private TextButton scoreButton;
	private TextButtonStyle style;
	
	TweenManager manager;
	AgentMain agentMain;
	
	public MainScene(AgentMain mainscene){
		this.agentMain = mainscene;
	}
	
	@Override
	public void render(float delta) {

		//Settings GL color
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		manager.update(delta);
		
		batch.setProjectionMatrix(camera.combined);
		stage.act(delta);
		
		/*
		 * BEGIN DRAWING BATCHS
		 */
		
		//Draw the main scene
		batch.begin();
		sprite.draw(batch);
		batch.end();
		
		//Draw buttons
		batch.begin();
		stage.draw();
		batch.end();
		/*
		 * END DRAWING BATCHS
		 */
		
		//If main scene background full appears. All buttons are displayed.
		if(sprite.getColor().toString().equals("fffffffe")){
			playButton.setColor(1, 1, 1, 1);
			settingButton.setColor(1, 1, 1, 1);
			loadButton.setColor(1, 1, 1, 1);
			scoreButton.setColor(1, 1, 1, 1);
		}
		
	}

	@Override
	public void resize(int width, int height) {
		if(stage == null){
			stage = new Stage(width, height, true);
		}
		stage.clear();
		
		Gdx.input.setInputProcessor(stage);
		
		//Editing Play button
		playButton.setWidth(250);
		playButton.setHeight(55);
		playButton.setX(Gdx.graphics.getWidth()/10);
		playButton.setY(Gdx.graphics.getHeight()*6/16);
		
		//Editing Setting button
		settingButton.setWidth(250);
		settingButton.setHeight(55);
		settingButton.setX(Gdx.graphics.getWidth()/10);
		settingButton.setY(Gdx.graphics.getHeight()*5/16 - 20);
		
		//Editing Load button
		loadButton.setWidth(250);
		loadButton.setHeight(55);
		loadButton.setX(Gdx.graphics.getWidth()/10);
		loadButton.setY(Gdx.graphics.getHeight()*4/16 - 40);
		
		//Editing Score button
		scoreButton.setWidth(250);
		scoreButton.setHeight(55);
		scoreButton.setX(Gdx.graphics.getWidth()/10);
		scoreButton.setY(Gdx.graphics.getHeight()*3/16 - 60);
		
		//Add to Stage
		stage.addActor(playButton);
		stage.addActor(settingButton);
		stage.addActor(loadButton);
		stage.addActor(scoreButton);
	}

	@Override
	public void show() {

		//Camera settings
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1024, 512);
		
		//Create a batch to draw on
		batch = new SpriteBatch();
		
		//Create Tween Manager
		manager = new TweenManager();
		TweenCallback cb = new TweenCallback() {
			
			@Override
			public void onEvent(int arg0, BaseTween<?> arg1) {
				tweenCompleted();
			}
		};
		
		//Create atlas
		atlas = new TextureAtlas("mainscene/button_main_scene.atlas");
		
		//Create a skin
		skin = new Skin();
		skin.addRegions(atlas);
		
		//Create font
		font = new BitmapFont(Gdx.files.internal("mainscene/white_font.fnt"),false);
		
		//New Buttons
		style = new TextButtonStyle();
		style.up = skin.getDrawable("button_up");
		style.down = skin.getDrawable("button_down");
		style.font = font;
		playButton = new TextButton("New game", style);
		playButton.setColor(1, 1, 1, 0);
		playButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				agentMain.setScreen(new DisplayStageScene(agentMain, DisplayStageScene.STAGE_1, new SettingScene(agentMain)));
				background_music.stop();
			}
		});
		
		settingButton = new TextButton("Setting", style);
		settingButton.setColor(1, 1, 1, 0);
		settingButton.addListener((new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				agentMain.setScreen(new SettingScene(agentMain));
				
			}
		}));
		
		loadButton = new TextButton("Load", style);
		loadButton.setColor(1, 1, 1, 0);
		scoreButton = new TextButton("Score", style);
		scoreButton.setColor(1, 1, 1, 0);
		
		//Get texture from mainscene/
		texture = new Texture(Gdx.files.internal("mainscene/mainscene.png"));
		sprite = new Sprite(texture);
		sprite.setColor(1, 1, 1, 0);
		
		Tween.registerAccessor(Sprite.class, new MainSceneAccessor());
		Tween.to(sprite, MainSceneAccessor.ALPHA, 2.5f).target(1).ease(TweenEquations.easeInQuad).setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE).start(manager);

		//Will add Tween to fade mainscene away
//		Tween.to(sprite, MainSceneAccessor.ALPHA, 3f).target(1).ease(TweenEquations.easeInQuad).repeatYoyo(1, 2.5f).setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE).start(manager);
		
		//Get music from music/ and loop forever until it's told to stop
		background_music = MyMusic.musicMainScene();
		background_music.setLooping(true);
		background_music.setVolume(MyMusic.music_volume);
		background_music.play();
	}

	/*
	 * When screen completely fading out - Set a new Screen
	 */
	private void tweenCompleted(){
		//Will add message here
		Gdx.app.log("Finished", "Finished scene");
		
	};
	
	@Override
	public void hide() {
		background_music.stop();
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		background_music.play();
	}

	@Override
	public void dispose() {
		background_music.stop();
	}

}

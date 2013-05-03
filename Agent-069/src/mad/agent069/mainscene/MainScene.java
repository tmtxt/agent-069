package mad.agent069.mainscene;

import mad.agent069.Scene1;
import mad.agent069.Scene2;
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

public class MainScene implements Screen {

	// Properties
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
	private TextButton quitButton;
	private TextButton settingButton;
	private TextButton scoreButton;
	private TextButton introButton;
	private TextButton howtoButton;
	private TextButtonStyle style;

	TweenManager manager;
	AgentMain agentMain;

	public MainScene(AgentMain mainscene) {
		this.agentMain = mainscene;
	}

	@Override
	public void render(float delta) {

		// Settings GL color
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		manager.update(delta);

		batch.setProjectionMatrix(camera.combined);
		stage.act(delta);

		/*
		 * BEGIN DRAWING BATCHS
		 */

		// Draw the main scene
		batch.begin();
		sprite.draw(batch);
		batch.end();

		// Draw buttons
		batch.begin();
		stage.draw();
		batch.end();
		/*
		 * END DRAWING BATCHS
		 */

		// If main scene background full appears. All buttons are displayed.
		if (sprite.getColor().toString().equals("fffffffe")) {
			playButton.setColor(1, 1, 1, 1);
			settingButton.setColor(1, 1, 1, 1);
			quitButton.setColor(1, 1, 1, 1);
			scoreButton.setColor(1, 1, 1, 1);
			introButton.setColor(1, 1, 1, 1);
			howtoButton.setColor(1, 1, 1, 1);
		}

	}

	@Override
	public void resize(int width, int height) {
		if (stage == null) {
			stage = new Stage(width, height, true);
		}
		stage.clear();

		Gdx.input.setInputProcessor(stage);

		// Editing Play button
		playButton.setWidth(250);
		playButton.setHeight(55);
		playButton.setX(Gdx.graphics.getWidth() / 10);
		playButton.setY(Gdx.graphics.getHeight() * 7 / 16 + 10);

		// Editing Setting button
		settingButton.setWidth(250);
		settingButton.setHeight(55);
		settingButton.setX(Gdx.graphics.getWidth() / 10);
		settingButton.setY(Gdx.graphics.getHeight() * 6 / 16 - 10);

		// Editing Score button
		scoreButton.setWidth(250);
		scoreButton.setHeight(55);
		scoreButton.setX(Gdx.graphics.getWidth() / 10);
		scoreButton.setY(Gdx.graphics.getHeight() * 5 / 16 - 30);

		// Editing Intro button
		introButton.setWidth(250);
		introButton.setHeight(55);
		introButton.setX(Gdx.graphics.getWidth() / 10);
		introButton.setY(Gdx.graphics.getHeight() * 4 / 16 - 50);

		// Editing How to play button
		howtoButton.setWidth(250);
		howtoButton.setHeight(55);
		howtoButton.setX(Gdx.graphics.getWidth() / 10);
		howtoButton.setY(Gdx.graphics.getHeight() * 3 / 16 - 70);
		
		// Editing Quit button
		quitButton.setWidth(250);
		quitButton.setHeight(55);
		quitButton.setX(Gdx.graphics.getWidth() / 10);
		quitButton.setY(Gdx.graphics.getHeight() * 2 / 16 - 90);
		
		// Add to Stage
		stage.addActor(playButton);
		stage.addActor(settingButton);
		stage.addActor(quitButton);
		stage.addActor(scoreButton);
		stage.addActor(howtoButton);
		stage.addActor(introButton);
	}

	@Override
	public void show() {

		// Camera settings
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1024, 512);

		// Create a batch to draw on
		batch = new SpriteBatch();

		// Create Tween Manager
		manager = new TweenManager();
		TweenCallback cb = new TweenCallback() {

			@Override
			public void onEvent(int arg0, BaseTween<?> arg1) {
				tweenCompleted();
			}
		};

		// Create atlas
		atlas = new TextureAtlas("mainscene/button_main_scene.atlas");

		// Create a skin
		skin = new Skin();
		skin.addRegions(atlas);

		// Create font
		font = new BitmapFont(Gdx.files.internal("mainscene/white_font.fnt"),
				false);

		// New Buttons
		style = new TextButtonStyle();
		style.up = skin.getDrawable("button_up");
		style.down = skin.getDrawable("button_down");
		style.font = font;

		// ===== Play Button =====
		playButton = new TextButton("New game", style);
		playButton.setColor(1, 1, 1, 0);
		playButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				agentMain.setScreen(new DisplayStageScene(agentMain,
						DisplayStageScene.STAGE_1, new Scene1(agentMain)));
				background_music.stop();
			}
		});

		// ===== Setting Button =====
		settingButton = new TextButton("Setting", style);
		settingButton.setColor(1, 1, 1, 0);
		settingButton.addListener((new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				agentMain.setScreen(new SettingScene(agentMain));

			}
		}));

		// ===== Quit Button =====
		quitButton = new TextButton("Quit", style);
		quitButton.setColor(1, 1, 1, 0);
		quitButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});

		// ===== Score Button =====
		scoreButton = new TextButton("Score", style);
		scoreButton.setColor(1, 1, 1, 0);
		scoreButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {

			}
		});

		// ===== Intro Button =====
		introButton = new TextButton("Story", style);
		introButton.setColor(1, 1, 1, 0);
		introButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {

			}
		});

		// ===== Howto Button =====
		howtoButton = new TextButton("How to play", style);
		howtoButton.setColor(1, 1, 1, 0);
		howtoButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {

			}
		});

		// Get texture from mainscene/
		texture = new Texture(Gdx.files.internal("mainscene/mainscene.png"));
		sprite = new Sprite(texture);
		sprite.setColor(1, 1, 1, 0);

		Tween.registerAccessor(Sprite.class, new MainSceneAccessor());
		Tween.to(sprite, MainSceneAccessor.ALPHA, 1.5f).target(1)
				.ease(TweenEquations.easeInQuad).setCallback(cb)
				.setCallbackTriggers(TweenCallback.COMPLETE).start(manager);

		// Get music from music/ and loop forever until it's told to stop
		background_music = MyMusic.musicMainScene();
		background_music.setLooping(true);
		background_music.setVolume(MyMusic.music_volume);
		background_music.play();
	}

	/*
	 * When screen completely fading out - Set a new Screen
	 */
	private void tweenCompleted() {
		// Will add message here
		Gdx.app.log("Finished", "Finished scene");

	};

	@Override
	public void hide() {
		// background_music.stop();
		dispose();
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
		Gdx.app.log("Dispose", "Logged in dispose");
		background_music.stop();
		stage.clear();
	}

}

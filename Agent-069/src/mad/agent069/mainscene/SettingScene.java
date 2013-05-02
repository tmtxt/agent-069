package mad.agent069.mainscene;

import mad.agent069.music.MyMusic;
import mad.agent069.sound.MySound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class SettingScene implements Screen {

	// All Properties
	AgentMain agent;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Sprite sprite;
	private Texture backgroundTexture;
	private Music background_music;
	private TextureAtlas atlas;
	private Skin skin;
	private Skin sliderSkin;
	private BitmapFont font;
	private TextButtonStyle style;
	private Label soundLabel;
	private Label musicLabel;
	private Slider soundSlide;
	private Slider musicSlide;
	private TextButton apply;
	private TextButton cancel;

	private Stage stage;

	private int button_width = 250;
	private int button_height = 55;

	public SettingScene(AgentMain am) {
		this.agent = am;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		camera.setToOrtho(false, 1024, 512);

		batch.setProjectionMatrix(camera.combined);

		/*
		 * BEGIN DRAWING BATCHS
		 */
		
		//Draw sprite
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

		background_music.setVolume(musicSlide.getValue());
	}

	@Override
	public void resize(int width, int height) {
		if (stage == null) {
			stage = new Stage(width, height, true);
		}
		stage.clear();

		Gdx.input.setInputProcessor(stage);

		// Editing Apply button
		apply.setWidth(button_width);
		apply.setHeight(button_height);
//		apply.setX(Gdx.graphics.getWidth() * 3 / 10 - 20);
		apply.setX((Gdx.graphics.getWidth() / 2 / 2));
		apply.setY(Gdx.graphics.getHeight() * 1 / 16);

		// Editing Cancel button
		cancel.setWidth(button_width);
		cancel.setHeight(button_height);
//		cancel.setX(Gdx.graphics.getWidth() * 6 / 10 - 20);
		cancel.setX((Gdx.graphics.getWidth() / 2) + ((Gdx.graphics.getWidth() / 2) *1/4));
		cancel.setY(Gdx.graphics.getHeight() * 1 / 16);
		
		//Slider sound
		soundSlide.setWidth(400);
		soundSlide.setHeight(100);
		soundSlide.setX(Gdx.graphics.getWidth()*3/6);
		soundSlide.setY(Gdx.graphics.getHeight()*4/6);
		
		//Slider music
		musicSlide.setWidth(400);
		musicSlide.setHeight(100);
		musicSlide.setX(Gdx.graphics.getWidth()*3/6);
		musicSlide.setY(Gdx.graphics.getHeight()*2/6);
		
		//sound label
		soundLabel.setWidth(300);
		soundLabel.setHeight(100);
		soundLabel.setX(Gdx.graphics.getWidth()*2/7);
		soundLabel.setY(Gdx.graphics.getHeight()*4/6);
		
		//music label
		musicLabel.setWidth(300);
		musicLabel.setHeight(100);
		musicLabel.setX(Gdx.graphics.getWidth()*2/7);
		musicLabel.setY(Gdx.graphics.getHeight()*2/6);

		// Stage add
		stage.addActor(apply);
		stage.addActor(cancel);
		stage.addActor(soundSlide);
		stage.addActor(musicSlide);
		stage.addActor(soundLabel);
		stage.addActor(musicLabel);
	}

	@Override
	public void show() {
		// Camera settings
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// Create new batch
		batch = new SpriteBatch();

		// Create background texture
		backgroundTexture = new Texture(
				Gdx.files.internal("settingscene/settingscene.png"));

		// Create new Sprite
		sprite = new Sprite(backgroundTexture);
		sprite.setColor(1, 1, 1, 1);

		// Create atlas
		atlas = new TextureAtlas("mainscene/button_main_scene.atlas");

		// Create a skin
		skin = new Skin();
		skin.addRegions(atlas);
		atlas = new TextureAtlas("uiskin/uiskin.atlas");
		sliderSkin = new Skin();
		sliderSkin.addRegions(atlas);

		// Create font
		font = new BitmapFont(Gdx.files.internal("mainscene/white_font.fnt"),
				false);

		// Style
		style = new TextButtonStyle();
		style.up = skin.getDrawable("button_up");
		style.down = skin.getDrawable("button_down");
		style.font = font;

		// Create new Buttons
		apply = new TextButton("Apply", style);
		apply.setColor(1, 1, 1, 1);
		apply.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				MySound.sound_volume = soundSlide.getValue();
				MyMusic.music_volume = musicSlide.getValue();
				agent.setScreen(new MainScene(agent));
				AgentMain.prefs.putFloat("musicVolume", MyMusic.music_volume);
				AgentMain.prefs.flush();
				AgentMain.prefs.putFloat("soundVolume", MySound.sound_volume);
				AgentMain.prefs.flush();
				background_music.stop();
			}
		});
		
		cancel = new TextButton("Cancel", style);
		cancel.setColor(1, 1, 1, 1);
		cancel.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				agent.setScreen(new MainScene(agent));
				background_music.stop();
			}
		});
		
		//Create new Sliders
		SliderStyle slistyle = new SliderStyle();
		slistyle.background = sliderSkin.getDrawable("uiskin_bakcground");
		slistyle.knob = sliderSkin.getDrawable("uiskin_slider");
		soundSlide = new Slider(0f, 1f, 0.1f, false, slistyle);
		soundSlide.setColor(1, 1, 1, 1);
		soundSlide.setValue(MySound.sound_volume);
		
		musicSlide = new Slider(0f,1f,0.1f,false,slistyle);
		musicSlide.setColor(1, 1, 1, 1);
		musicSlide.setValue(MyMusic.music_volume);
		
		//Create slider labels
		LabelStyle ls = new LabelStyle(font, Color.WHITE);
		soundLabel = new Label("Sound: ", ls);
		musicLabel = new Label("Music: ", ls);

		// Get music from music/ and loop forever until it's told to stop
		background_music = MyMusic.musicSettingScene();
		background_music.setLooping(true);
		background_music.play();
		
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

}

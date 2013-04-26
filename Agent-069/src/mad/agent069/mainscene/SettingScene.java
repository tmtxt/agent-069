package mad.agent069.mainscene;

import mad.agent069.music.MyMusic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class SettingScene implements Screen{

	//All Properties
	AgentMain agent;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Sprite sprite;
	private Texture backgroundTexture;
	private Music background_music;
	
	private Label soundLabel;
	private Label musicLabel;
	private Slider soundSlide;
	private Slider musicSlide;
	
	private TextButton apply;
	private TextButton cancel;
	
	private Stage stage;
	
	public SettingScene(AgentMain am){
		this.agent = am;
	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		sprite.draw(batch);
		batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		//Camera settings
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1024, 512);
		
		//Create new batch
		batch = new SpriteBatch();
		
		//Create background texture
		backgroundTexture = new Texture(Gdx.files.internal("settingscene/settingscene.png"));
		
		
		//Create new Sprite
		sprite = new Sprite(backgroundTexture);
		sprite.setColor(1, 1, 1, 1);
		
		//Get music from music/ and loop forever until it's told to stop
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

package mad.agent069;

import java.util.ArrayList;
import java.util.List;

import mad.agent069.mainscene.AgentMain;
import mad.agent069.mainscene.MainScene;
import mad.agent069.music.MyMusic;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;

public class GameIntroScene implements ApplicationListener, Screen {

	// The list of all textures will be drawn
	private List<Texture> textureList;

	// Camera
	private OrthographicCamera camera;
	private SpriteBatch batch;

	// The current texture to be drawn
	private int currentTexture;
	
	// The background music
	private Music background_music;

	// The main scene
	private AgentMain agentMain;

	public GameIntroScene(AgentMain agentMain) {
		super();
		this.agentMain = agentMain;
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		// Init the texture list
		this.textureList = new ArrayList<Texture>();

		// Init the camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Scene.SCENE_WIDTH, Scene.SCENE_HEIGHT);

		// Create a batch to draw on
		batch = new SpriteBatch();

		// Init the current texture to be drawn
		this.currentTexture = 0;

		// Init the image to draw
		for (int i = 0; i < 3; i++) {
			Texture texture = new Texture(Gdx.files.internal("gameintro" + i
					+ ".png"));
			this.textureList.add(texture);
		}

		// Action listener
		Gdx.input.setInputProcessor(new GestureDetector(new GestureListener() {

			@Override
			public boolean zoom(float initialDistance, float distance) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean touchDown(float x, float y, int pointer, int button) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean tap(float x, float y, int count, int button) {
				// TODO Auto-generated method stub
				currentTexture++;
				Gdx.graphics.requestRendering();
				return false;
			}

			@Override
			public boolean pinch(Vector2 initialPointer1,
					Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean pan(float x, float y, float deltaX, float deltaY) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean longPress(float x, float y) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean fling(float velocityX, float velocityY, int button) {
				// TODO Auto-generated method stub
				return false;
			}
		}));
		
		// Music for the game
		background_music = MyMusic.musicMainScene();
		background_music.setLooping(true);
		background_music.setVolume(MyMusic.music_volume);
		background_music.play();

		// Non continuous rendering
		Gdx.graphics.setContinuousRendering(false);
		Gdx.graphics.requestRendering();
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		this.render();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		this.create();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);

		batch.begin();

		if (this.currentTexture < this.textureList.size()) {
			// Draw the
			batch.draw(this.textureList.get(currentTexture), 0, 0);
		} else {
			// Quit the scene
			Gdx.graphics.setContinuousRendering(true);
			background_music.stop();
			agentMain.setScreen(new MainScene(agentMain));
		}

		batch.end();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		Gdx.graphics.setContinuousRendering(true);
		Gdx.input.setInputProcessor(null);
	}

}

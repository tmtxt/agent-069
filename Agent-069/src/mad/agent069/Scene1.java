package mad.agent069;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class Scene1 extends Scene {

	// The track texture
	private Texture trackTexture;
	// The track texture width
	private int trackTextureWidth;
	// Current track position in X axis
	private float currentTrackX;
	// Constant track position in Y axis
	private final float trackY = 0;
	// Last time draw track
	private long lastTimeTrack;
	// Track moving time
	private long trackMovingTime;
	// Track moving distance (pixel)
	private float trackMovingDistance = 20;

	// The sky texture
	private Texture skyTexture;
	// The sky texture width
	private int skyTextureWidth;
	// Current sky position in Y axis
	private float currentSkyX;
	// Constant sky position in Y axis
	private final float skyY = 150;
	// Last time draw sky
	private long lastTimeSky;
	// Sky moving time
	private long skyMovingTime;
	// Sky moving distance (pixel)
	private final float skyMovingDistance = 10;

	@Override
	public void create() {
		// TODO Auto-generated method stub
		super.create();

		// The speed of this scene, used for calculating the moving time of the
		// background, main character and obstacles
		this.sceneSpeed = 1;

		// The main character
		this.mainCharacter = new MainCharacter(this);

		// Init the camera
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, Scene.SCENE_WIDTH, Scene.SCENE_HEIGHT);

		// Init the sprite batch
		this.batch = new SpriteBatch();

		// Init the texture to draw the track
		this.trackTexture = new Texture(Gdx.files.internal("scene1/track.png"));
		this.currentTrackX = Scene.SCENE_WIDTH;
		this.trackTextureWidth = this.trackTexture.getWidth();
		this.trackMovingTime = (long) (Scene.SCENE_MOVING_TIME / this.sceneSpeed);

		// Init the texture to draw the sky
		this.skyTexture = new Texture(Gdx.files.internal("scene1/sky.png"));
		this.currentSkyX = Scene.SCENE_WIDTH;
		this.skyTextureWidth = this.skyTexture.getWidth();
		this.skyMovingTime = (long) (Scene.SCENE_MOVING_TIME / this.sceneSpeed);

		// Last time draw
		long currentTime = TimeUtils.nanoTime();
		this.lastTimeSky = currentTime;
		this.lastTimeTrack = currentTime;
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		super.resize(width, height);
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		super.render();

		// Clear color
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		// Set the camera
		batch.setProjectionMatrix(this.camera.combined);

		// Current time
		long currentTime = TimeUtils.nanoTime();
		
		// Begin drawing
		batch.begin();

		// Draw the sky
		batch.draw(skyTexture, currentSkyX - this.skyTextureWidth, this.skyY);
		batch.draw(skyTexture, currentSkyX, this.skyY);

		// Draw the track
		batch.draw(trackTexture, currentTrackX - this.trackTextureWidth, this.trackY);
		batch.draw(trackTexture, currentTrackX, this.trackY);
		
		// Draw the main character
		this.mainCharacter.drawMainCharacter(batch, currentTime);

		batch.end();
		// End drawing

		// Move the sky backward
		if (currentTime - this.lastTimeSky > this.skyMovingTime) {
			this.currentSkyX -= this.skyMovingDistance;
			this.lastTimeSky = currentTime;
		}

		// Move the track backward
		if (currentTime - this.lastTimeTrack > this.trackMovingTime) {
			this.currentTrackX -= this.trackMovingDistance;
			this.lastTimeTrack = currentTime;
		}

		// Move the sky back to starting position if it reach the end
		if (this.currentSkyX <= Scene.SCENE_WIDTH - this.skyTextureWidth) {
			currentSkyX = Scene.SCENE_WIDTH;
		}

		// Move the track back to starting position if it reach the end
		if (this.currentTrackX <= Scene.SCENE_WIDTH - this.trackTextureWidth) {
			currentTrackX = Scene.SCENE_WIDTH;
		}
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		super.pause();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		super.resume();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}

}

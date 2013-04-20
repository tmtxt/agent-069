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
	
	// Current track position
	private float currentTrackX;
	
	// Last time draw track
	private long lastTimeTrack;
	
	// The sky texture
	private Texture skyTexture;
	
	// Current sky position
	private float currentSkyX;
	
	// Last time draw sky
	private long lastTimeSky;

	@Override
	public void create() {
		// TODO Auto-generated method stub
		super.create();
		
		// The speed of this scene, used for calculating the moving time of the background, main character and obstacles
		this.sceneSpeed = 1;
		
		// The main character
		this.mainCharacter = new MainCharacter(this);
		
		// Init the camera
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, Scene.SCENE_WIDTH, Scene.SCENE_HEIGHT);
		
		// Init the sprite batch
		this.batch = new SpriteBatch();
		
		// Init the texture to draw the track
		this.trackTexture = new Texture(Gdx.files.internal("scene1track.png"));
		this.currentTrackX = Scene.SCENE_WIDTH;
		
		// Init the texture to draw the sky
		this.skyTexture = new Texture(Gdx.files.internal("scene1sky.png"));
		this.currentSkyX = Scene.SCENE_HEIGHT;
		
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
		
		// Begin drawing
		batch.begin();
		
		// Draw the sky
		batch.draw(skyTexture, currentSkyX - 2048, 150);
		batch.draw(skyTexture, currentSkyX, 150);
		
		// Draw the track
		batch.draw(trackTexture, currentTrackX - 2048, 0);
		batch.draw(trackTexture, currentTrackX, 0);
		
		batch.end();
		// End drawing
		
		// Current time
		long currentTime = TimeUtils.nanoTime();
		
		// Move the sky backward
		if(currentTime - this.lastTimeSky > 30000000){
			this.currentSkyX -= 10;
			this.lastTimeSky = currentTime;
		}
		
		// Move the track backward
		if(currentTime - this.lastTimeTrack > 30000000){
			this.currentTrackX -= 20;
			this.lastTimeTrack = currentTime;
		}
		
		// Move the sky back to starting position if it reach the end
		if(this.currentSkyX <= 800 - 2048){
			currentSkyX = 800;
		}
		if(this.currentTrackX <= 800 - 2048){
			currentTrackX = 800;
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

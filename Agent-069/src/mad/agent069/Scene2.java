package mad.agent069;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;

public class Scene2 extends Scene {

	// The track texture
	private Texture trackTexture;
	// The track texture width
	private int trackTextureWidth;
	// Current track position in X axis
	private float trackCurrentX;
	// Constant track position in Y axis
	private final float trackY = 0;
	// Last time draw track
	private long trackLastTimeDraw;
	// Track moving time
	private long trackMovingTime;
	// Track moving distance (pixel)
	private float trackMovingDistance = Scene.BACKGROUND_MOVING_DISTANCE;

	// The sky texture
	private Texture skyTexture;
	// The sky texture width
	private int skyTextureWidth;
	// Current sky position in Y axis
	private float skyCurrentX;
	// Constant sky position in Y axis
	private final float skyY = 150;
	// Last time draw sky
	private long skyLastTimeDraw;
	// Sky moving time
	private long skyMovingTime;

	@Override
	protected void swipeRightHandler() {
		// TODO Auto-generated method stub
	}

	// Sky moving distance (pixel)
	private final float skyMovingDistance = 10;

	@Override
	public void create() {
		// TODO Auto-generated method stub
		super.create();

		// Only allow these types of obstacle
		Scene.OBSTACLE_CLASS_NAMES = new String[] { "RockObstacle",
				"ThornsObstacle", "HeliObstacle", "FireObstacle",
				"RocketObstacle", "BombObstacle" };

		// The speed of this scene, used for calculating the moving time of the
		// background, main character and obstacles
		this.speed = 1;

		// Init the texture to draw the track
		this.trackTexture = new Texture(Gdx.files.internal("scene2/track.png"));
		this.trackCurrentX = Scene.SCENE_WIDTH;
		this.trackTextureWidth = this.trackTexture.getWidth();
		this.trackMovingTime = (long) (Scene.SCENE_MOVING_TIME / this.speed);

		// Init the actual scene moving time
		this.actualMovingTime = this.trackMovingTime;

		// Init the texture to draw the sky
		this.skyTexture = new Texture(Gdx.files.internal("scene2/sky.png"));
		this.skyCurrentX = Scene.SCENE_WIDTH;
		this.skyTextureWidth = this.skyTexture.getWidth();
		this.skyMovingTime = (long) (Scene.SCENE_MOVING_TIME / this.speed);

		// The current time
		long currentTime;
		
		// Last time draw
		currentTime = TimeUtils.nanoTime();
		this.skyLastTimeDraw = currentTime;
		this.trackLastTimeDraw = currentTime;

		// Init the scene floor position
		Scene.SCENE_FLOOR_POSITION_Y = 20;

		// Init some other properties
		this.initAfterCreate(currentTime);
		
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

		// Current time
		long currentTime = TimeUtils.nanoTime();

		// Prepare for main character drawing
		this.mainCharacter.prepareForDrawing(currentTime);

		// Begin drawing
		batch.begin();

		// Draw the sky
		batch.draw(skyTexture, skyCurrentX - this.skyTextureWidth, this.skyY);
		batch.draw(skyTexture, skyCurrentX, this.skyY);

		// Draw the track
		batch.draw(trackTexture, trackCurrentX - this.trackTextureWidth,
				this.trackY);
		batch.draw(trackTexture, trackCurrentX, this.trackY);

		this.drawGeneral(batch, currentTime);

		batch.end();
		// End drawing

		// Move the sky backward
		if (currentTime - this.skyLastTimeDraw > this.skyMovingTime) {
			this.skyCurrentX -= this.skyMovingDistance;
			this.skyLastTimeDraw = currentTime;
		}

		// Move the track backward
		if (currentTime - this.trackLastTimeDraw > this.trackMovingTime) {
			this.trackCurrentX -= this.trackMovingDistance;
			this.trackLastTimeDraw = currentTime;
		}

		// Move the sky back to starting position if it reach the end
		if (this.skyCurrentX <= Scene.SCENE_WIDTH - this.skyTextureWidth) {
			skyCurrentX = Scene.SCENE_WIDTH;
		}

		// Move the track back to starting position if it reach the end
		if (this.trackCurrentX <= Scene.SCENE_WIDTH - this.trackTextureWidth) {
			trackCurrentX = Scene.SCENE_WIDTH;
		}

		// Handler for obstacle
		this.obstacleHandler(currentTime);

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

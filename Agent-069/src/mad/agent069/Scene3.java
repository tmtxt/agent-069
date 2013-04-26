package mad.agent069;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;

public class Scene3 extends Scene {
	// The background texture
	private Texture backgroundTexture;
	// The background texture width
	private int backgroundTextureWidth;
	// Current background position in X axis
	private float backgroundCurrentX;
	// Constant background position in Y axis
	private final float backgroundY = 0;
	// Last time draw background
	private long backgroundLastTimeDraw;
	// Background moving time
	private long backgroundMovingTime;
	// Background moving distance (in pixel)
	private float backgroundMovingDistance = Scene.BACKGROUND_MOVING_DISTANCE;

	@Override
	public void create() {
		// TODO Auto-generated method stub
		super.create();

		// Init scene speed
		this.speed = 1;

		// Init the texture to draw the background
		this.backgroundTexture = new Texture(Gdx.files.internal("forest.png"));
		this.backgroundCurrentX = Scene.SCENE_WIDTH;
		this.backgroundTextureWidth = this.backgroundTexture.getWidth();
		this.backgroundMovingTime = (long) (Scene.SCENE_MOVING_TIME / this.speed);

		// Init the actual scene moving time
		this.actualMovingTime = this.backgroundMovingTime;

		// Last time draw
		long currentTime = TimeUtils.nanoTime();
		this.backgroundLastTimeDraw = currentTime;

		// Init the scene floor position
		Scene.SCENE_FLOOR_POSITION_Y = 45;

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

		// The current time
		long currentTime = TimeUtils.nanoTime();

		// Begin drawing
		batch.begin();

		// Draw the background
		batch.draw(this.backgroundTexture, this.backgroundCurrentX,
				this.backgroundY);
		batch.draw(this.backgroundTexture, this.backgroundCurrentX
				- this.backgroundTextureWidth, this.backgroundY);

		// Draw the main character
		this.mainCharacter.drawMainCharacter(batch, currentTime);

		// Draw the obstacle
		this.obstacle.drawObstacle(batch, currentTime);

		batch.end();
		// End drawing

		// Move the background backward
		if (currentTime - this.backgroundLastTimeDraw > this.backgroundMovingTime) {
			this.backgroundCurrentX -= this.backgroundMovingDistance;
			this.backgroundLastTimeDraw = currentTime;
		}

		// Move the background back to the start position if it reach the end
		if (this.backgroundCurrentX <= Scene.SCENE_WIDTH
				- this.backgroundTextureWidth) {
			this.backgroundCurrentX = Scene.SCENE_WIDTH;
		}

		// Create new obstacle if the last obstacle disappear
		if (this.obstacle.getCurrentX() <= 0 - this.obstacle.getWidth()) {
			this.obstacle = this.createNewObstacle(currentTime);
		}

		// Check if the main character overlap the obstacle
		this.obstacleOverlap();
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
}

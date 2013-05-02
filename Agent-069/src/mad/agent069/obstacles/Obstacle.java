package mad.agent069.obstacles;

import mad.agent069.Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

// The Obstacles used in the game
public abstract class Obstacle {
	// The speed of the obstacle, just a number for calculating the obstacle
	// moving time
	protected float speed;

	// The current position of the obstacle
	protected float currentX;
	protected float currentY;
	protected Rectangle currentPosition;

	// The time for the obstacle to move backward
	protected long movingSpeed;

	// The current scene displaying this obstacle
	protected Scene currentScene;

	// Last time draw obstacle
	protected long lastTimeObstacle;

	// The obstacle texture width
	protected int width;

	// Allow being shot
	protected boolean allowShot;

	// Allow overlap
	public boolean allowOverlap;

	// Obstacle's blood
	protected int blood;

	// The explosion sound
	protected Sound explosionSound;

	public int getBlood() {
		return blood;
	}

	public void decreaseBlood() {
		this.blood--;
	}

	public boolean isAllowOverlap() {
		return allowOverlap;
	}

	public void setAllowOverlap(boolean allowOverlap) {
		this.allowOverlap = allowOverlap;
	}

	/**
	 * Get the width of the this obstacle texture
	 * 
	 * @return int Obstacle width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Draw this obstacle
	 * 
	 * @param batch
	 *            The SpriteBatch to draw this obstacle
	 * @param currentTime
	 *            Current time in nanosecond
	 */
	public void drawObstacle(SpriteBatch batch, long currentTime) {
		this.drawSpecificObstacle(batch, currentTime);
	}

	public boolean isAllowShot() {
		return allowShot;
	}

	/**
	 * Draw the specific obstacle texture
	 * 
	 * @param batch
	 *            The SpriteBatch to draw this obstacle
	 * @param currentTime
	 *            Current time in nanosecond
	 */
	protected abstract void drawSpecificObstacle(SpriteBatch batch,
			long currentTime);

	/**
	 * 
	 * 
	 * @param currentScene
	 *            The scene this obstacle belongs to
	 * @param lastTimeObstacle
	 *            The current time in nanosecond
	 */
	public Obstacle(Scene currentScene, long lastTimeObstacle) {
		this.currentScene = currentScene;
		this.lastTimeObstacle = lastTimeObstacle;

		// Init the current position of the obstacle
		this.currentX = Scene.SCENE_WIDTH;
		this.currentY = Scene.SCENE_FLOOR_POSITION_Y;

		// Allow overlap
		this.allowOverlap = true;

	}

	/**
	 * Calculate the moving speed of the obstacle
	 */
	protected void calculateMovingSpeed() {
		this.movingSpeed = (long) (this.currentScene.getActualMovingTime() / this.speed);
	}

	/**
	 * Move the obstacle backward
	 * 
	 * @param long The current time in nanoseconds
	 */
	public void moveObstacleBackward(long currentTime) {

		if (currentTime - lastTimeObstacle >= movingSpeed) {
			this.currentPosition.setX(this.currentPosition.getX()
					- Scene.BACKGROUND_MOVING_DISTANCE);
			this.lastTimeObstacle = currentTime;
		}

	}

	/**
	 * Get the current position in X axis of this obstacle
	 * 
	 * @return float Current obstacle X position
	 */
	public float getCurrentX() {
		return this.currentPosition.getX();
	}

	public Rectangle getCurrentPosition() {
		return currentPosition;
	}

	public void playExplosionSound() {
		this.explosionSound.play();
	}

	/**
	 * Init the current position, should be called in constructor of derived
	 * classes, after finishing initializing the texture
	 * 
	 * @param obstacleTexture
	 *            The obstacle texture
	 */
	protected void initCurrentPosition(Texture obstacleTexture) {
		// Set the current position
		this.currentPosition = new Rectangle(Scene.SCENE_WIDTH,
				Scene.SCENE_FLOOR_POSITION_Y, obstacleTexture.getWidth(),
				obstacleTexture.getHeight());

		// Set the obstacle width
		this.width = obstacleTexture.getWidth();
	}

	/**
	 * Init for the obstacle that allows to be shot Should be called at the end
	 * of the constructor of that obstacle
	 */
	protected void initAllowBeShot(int blood) {
		// Allow to be shot
		this.allowShot = true;

		// Obstacle blood (explode after 3 shot)
		this.blood = blood;

		// Obstacle explosion sound
		this.explosionSound = Gdx.audio.newSound(Gdx.files
				.internal("explosion.mp3"));
	}

	/**
	 * Set this obstacle moving time to the scene moving time
	 */
	public void setMovingTimeToSceneMovingTime() {
		this.movingSpeed = this.currentScene.getActualMovingTime();
	}

	public abstract void changeToCollapseTexture();

}

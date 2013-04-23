package mad.agent069;

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
	 * @param batch The SpriteBatch to draw this obstacle
	 * @param currentTime Current time in nanosecond
	 */
	public void drawObstacle(SpriteBatch batch, long currentTime) {
		this.drawSpecificObstacle(batch, currentTime);

		// move it backward
		if(currentTime - lastTimeObstacle >= movingSpeed){
			this.moveObstacleBackward();
			this.lastTimeObstacle = currentTime;
		}
		
	}

	/**
	 * Draw the specific obstacle texture
	 * 
	 * @param batch The SpriteBatch to draw this obstacle
	 * @param currentTime Current time in nanosecond
	 */
	protected abstract void drawSpecificObstacle(SpriteBatch batch,
			long currentTime);

	/**
	 * Call when this obstacle hit the main character
	 */
	public abstract void collapse();

	/**
	 * 
	 * 
	 * @param currentScene The scene this obstacle belongs to
	 * @param lastTimeObstacle The current time in nanosecond
	 */
	public Obstacle(Scene currentScene, long lastTimeObstacle) {
		this.currentScene = currentScene;
		
		this.lastTimeObstacle = lastTimeObstacle;

		// Init the current position of the obstacle
		this.currentX = Scene.SCENE_WIDTH;
		this.currentY = Scene.SCENE_FLOOR_POSITION_Y;
		
	}

	/**
	 * Calculate the moving speed of the obstacle
	 */
	protected void calculateMovingSpeed() {
		this.movingSpeed = (long) (this.currentScene.getActualMovingTime() / this.speed );
	}

	/**
	 * Move the obstacle backward
	 */
	protected void moveObstacleBackward() {
		// this.currentX -= Scene.BACKGROUND_MOVING_DISTANCE;
		this.currentPosition.setX(this.currentPosition.getX() - Scene.BACKGROUND_MOVING_DISTANCE);
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
	
}

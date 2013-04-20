package mad.agent069;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// The Obstacles used in the game
public abstract class Obstacle {
	// The speed of the obstacle, just a number for calculating the obstacle
	// moving time
	protected float obstacleSpeed;

	// The current position of the obstacle
	protected float currentX;
	protected float currentY;

	// The time for the obstacle to move backward
	protected long obstacleMovingSpeed;

	// The current scene displaying this obstacle
	protected Scene currentScene;
	
	// The obstacle texture width
	protected int obstacleWidth;
	
	public int getObstacleWidth() {
		return obstacleWidth;
	}

	public void setObstacleWidth(int obstacleWidth) {
		this.obstacleWidth = obstacleWidth;
	}

	// Last time draw obstacle
	protected long lastTimeObstacle;

	public long getLastTimeObstacle() {
		return lastTimeObstacle;
	}

	public void setLastTimeObstacle(long lastTimeObstacle) {
		this.lastTimeObstacle = lastTimeObstacle;
	}

	// Draw this obstacle
	public void drawObstacle(SpriteBatch batch, long currentTime) {
		this.drawSpecificObstacle(batch, currentTime);

		// move it backward
		if(currentTime - lastTimeObstacle >= obstacleMovingSpeed){
			this.moveObstacleBackward();
			this.lastTimeObstacle = currentTime;
		}
		
	}

	// Draw specific obstacle image
	protected abstract void drawSpecificObstacle(SpriteBatch batch,
			long currentTime);

	// What to do when this obstacle collapse with the main character
	public abstract void collapse();

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
		this.obstacleMovingSpeed = (long) (Scene.SCENE_MOVING_TIME / this.obstacleSpeed );
	}

	/**
	 * Move the obstacle backward
	 */
	protected void moveObstacleBackward() {
		this.currentX -= this.currentScene.getBackgroundMovingDistance();
	}

	public float getObstacleSpeed() {
		return obstacleSpeed;
	}

	public void setObstacleSpeed(float obstacleSpeed) {
		this.obstacleSpeed = obstacleSpeed;
	}

	public float getCurrentX() {
		return currentX;
	}

	public void setCurrentX(float currentX) {
		this.currentX = currentX;
	}

	public float getCurrentY() {
		return currentY;
	}

	public void setCurrentY(float currentY) {
		this.currentY = currentY;
	}

	public long getObstacleMovingSpeed() {
		return obstacleMovingSpeed;
	}

	public void setObstacleMovingSpeed(long obstacleMovingSpeed) {
		this.obstacleMovingSpeed = obstacleMovingSpeed;
	}

	public Scene getCurrentScene() {
		return currentScene;
	}

	public void setCurrentScene(Scene currentScene) {
		this.currentScene = currentScene;
	}
}

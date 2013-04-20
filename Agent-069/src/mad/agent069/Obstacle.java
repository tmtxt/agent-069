package mad.agent069;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// The Obstacles used in the game
public abstract class Obstacle {
	// The speed of the obstacle, just a number for calculating the obstacle moving time
	protected float obstacleSpeed;
	
	// The current position of the obstacle
	protected float currentX;
	protected float currentY;
	
	// The time for the obstacle to move backward
	protected long obstacleMovingSpeed;
	
	// The current scene displaying this obstacle
	protected Scene currentScene;
	
	// Draw this obstacle
	public abstract void drawObstacle(SpriteBatch batch, long currentTime);
	
	// What to do when this obstacle collapse with the main character
	public abstract void collapse();
	
	public Obstacle(Scene currentScene){
		this.currentScene = currentScene;
		
		// Init the current position of the obstacle
		this.currentX = Scene.SCENE_WIDTH - 64;
		this.currentY = Scene.SCENE_FLOOR_POSITION_Y;
	}
	
	/**
	 * Calculate the moving speed of the obstacle
	 */
	protected void calculateMovingSpeed(){
		this.obstacleMovingSpeed = (long)(this.currentScene.getSceneSpeed() / 1);
	}
}

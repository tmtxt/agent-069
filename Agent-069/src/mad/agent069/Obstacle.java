package mad.agent069;

// The Obstacles used in the game
public class Obstacle {
	// The speed of the obstacle, just a number for calculating the obstacle moving time
	protected float obstacleSpeed;
	
	// The time for the obstacle to move backward
	protected long obstacleMovingTime;
	
	// The current scene displaying this obstacle
	protected Scene currentScene;
	
	// What to do when this obstacle collapse with the main character
	public void collapse(){
		
	}
	
	public Obstacle(Scene currentScene){
		this.currentScene = currentScene;
		
		// calculate the obstacle moving time based on the current scene speed
		
	}
}

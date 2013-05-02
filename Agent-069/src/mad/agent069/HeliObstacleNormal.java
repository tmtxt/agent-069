package mad.agent069;

public class HeliObstacleNormal extends HeliObstacle {

	public HeliObstacleNormal(Scene currentScene, long lastTimeObstacle) {
		super(currentScene, lastTimeObstacle);
		// TODO Auto-generated constructor stub

		// The speed of this obstacle
		this.speed = (float) 1.4;

		// Calculate the actual obstacle moving speed
		this.calculateMovingSpeed();
	}

}

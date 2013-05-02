package mad.agent069;

public class RocketObstacleNormal extends RocketObstacle {

	public RocketObstacleNormal(Scene currentScene, long lastTimeObstacle) {
		super(currentScene, lastTimeObstacle);
		// TODO Auto-generated constructor stub

		// The speed
		this.speed = (float) 1.5;

		// Calculate the actual obstacle moving speed
		this.calculateMovingSpeed();
	}

}

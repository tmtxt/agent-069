package mad.agent069;

public class RocketObstacleFast extends RocketObstacle {

	public RocketObstacleFast(Scene currentScene, long lastTimeObstacle) {
		super(currentScene, lastTimeObstacle);
		// TODO Auto-generated constructor stub

		// The speed
		this.speed = (float) 1.75;

		// Calculate the actual obstacle moving speed
		this.calculateMovingSpeed();
	}

}

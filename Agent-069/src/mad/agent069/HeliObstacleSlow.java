package mad.agent069;

public class HeliObstacleSlow extends HeliObstacle {

	public HeliObstacleSlow(Scene currentScene, long lastTimeObstacle) {
		super(currentScene, lastTimeObstacle);
		// TODO Auto-generated constructor stub

		// The speed of this obstacle
		this.speed = (float) 1.2;

		// Calculate the actual obstacle moving speed
		this.calculateMovingSpeed();
	}

}

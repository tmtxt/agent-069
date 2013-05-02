package mad.agent069;

public class HeliObstacleFast extends HeliObstacle {

	public HeliObstacleFast(Scene currentScene, long lastTimeObstacle) {

		super(currentScene, lastTimeObstacle);
		// TODO Auto-generated constructor stub

		// The speed of this obstacle
		this.speed = (float) 1.75;

		// Calculate the actual obstacle moving speed
		this.calculateMovingSpeed();
	}

}

package mad.agent069.obstacles;

import mad.agent069.Scene;

public class RocketObstacleNormal extends RocketObstacle {

	public RocketObstacleNormal(Scene currentScene, long lastTimeObstacle) {
		super(currentScene, lastTimeObstacle);
		// TODO Auto-generated constructor stub

		// The speed
		this.speed = (float) 1.55;

		// Calculate the actual obstacle moving speed
		this.calculateMovingSpeed();
	}

}

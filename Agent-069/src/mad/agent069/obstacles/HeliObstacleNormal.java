package mad.agent069.obstacles;

import mad.agent069.Scene;

public class HeliObstacleNormal extends HeliObstacle {

	public HeliObstacleNormal(Scene currentScene, long lastTimeObstacle) {
		super(currentScene, lastTimeObstacle);
		// TODO Auto-generated constructor stub

		// The speed of this obstacle
		this.speed = (float) 1.5;

		// Calculate the actual obstacle moving speed
		this.calculateMovingSpeed();
	}

}

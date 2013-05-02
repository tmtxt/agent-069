package mad.agent069;

public class RocketObstacleLow extends RocketObstacle {

	public RocketObstacleLow(Scene currentScene, long lastTimeObstacle) {
		super(currentScene, lastTimeObstacle);
		// TODO Auto-generated constructor stub

		this.currentPosition.setY(Scene.SCENE_FLOOR_POSITION_Y + 10);

		// The speed
		this.speed = (float) 1.65;

		// Calculate the actual obstacle moving speed
		this.calculateMovingSpeed();
	}

}

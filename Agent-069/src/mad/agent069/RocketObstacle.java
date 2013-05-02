package mad.agent069;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class RocketObstacle extends Obstacle {

	// The texture to draw
	private Texture rocketTexture;

	// The position of the texture from the scene floor
	private final int distanceFromFloor = 65;

	public RocketObstacle(Scene currentScene, long lastTimeObstacle) {
		super(currentScene, lastTimeObstacle);
		// TODO Auto-generated constructor stub

		// Init the texture
		this.rocketTexture = ObstacleTexture.ROCKET_OBSTACLE_TEXTURE;

		// Init the current position
		this.currentPosition = new Rectangle(Scene.SCENE_WIDTH,
				Scene.SCENE_FLOOR_POSITION_Y + this.distanceFromFloor,
				this.rocketTexture.getWidth(), this.rocketTexture.getHeight());

		// Set the obstacle width
		this.width = this.rocketTexture.getWidth();

		// Not allow to be shot
		this.allowShot = false;
	}

	@Override
	protected void drawSpecificObstacle(SpriteBatch batch, long currentTime) {
		// TODO Auto-generated method stub

		// draw the obstacle
		batch.draw(this.rocketTexture, this.currentPosition.getX(),
				this.currentPosition.getY());

	}

	@Override
	public void changeToCollapseTexture() {
		// TODO Auto-generated method stub

	}

}

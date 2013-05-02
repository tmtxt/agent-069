package mad.agent069;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class BombObstacle extends Obstacle {

	// The texture to draw this obstacle
	private Texture bombTexture;

	public BombObstacle(Scene currentScene, long lastTimeObstacle) {
		super(currentScene, lastTimeObstacle);
		// TODO Auto-generated constructor stub

		// The speed of this obstacle
		this.speed = 1;

		// Calculate the actual obstacle moving speed
		this.calculateMovingSpeed();

		// Init the texture
		this.bombTexture = ObstacleTexture.BOMB_OBSTACLE_TEXTURE;

		// Set the current position
		this.currentPosition = new Rectangle(Scene.SCENE_WIDTH,
				Scene.SCENE_FLOOR_POSITION_Y, this.bombTexture.getWidth(),
				this.bombTexture.getHeight());

		// Set the obstacle width
		this.width = this.bombTexture.getWidth();

		// Not allow to be shot
		this.allowShot = false;
	}

	@Override
	protected void drawSpecificObstacle(SpriteBatch batch, long currentTime) {
		// TODO Auto-generated method stub

		// draw the obstacle
		batch.draw(this.bombTexture, this.currentPosition.getX(),
				this.currentPosition.getY());

	}

	@Override
	public void changeToCollapseTexture() {
		// TODO Auto-generated method stub

	}

}

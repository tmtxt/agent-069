package mad.agent069.obstacles;

import mad.agent069.Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class ThornsObstacle extends Obstacle {

	// The texture to draw this obstacle
	private Texture tankTexture;

	public ThornsObstacle(Scene currentScene, long lastTimeObstacle) {
		super(currentScene, lastTimeObstacle);
		// TODO Auto-generated constructor stub

		// The speed of this obstacle
		this.speed = 1;

		// Calculate the actual obstacle moving speed
		this.calculateMovingSpeed();

		// Init the texture
		this.tankTexture = ObstacleTexture.THORNS_OBSTACLE_TEXTURE;

		// Set the current position
		this.currentPosition = new Rectangle(Scene.SCENE_WIDTH,
				Scene.SCENE_FLOOR_POSITION_Y, this.tankTexture.getWidth(),
				this.tankTexture.getHeight());

		// Set the obstacle width
		this.width = tankTexture.getWidth();

		// Not allow to be shot
		this.allowShot = false;
	}

	@Override
	protected void drawSpecificObstacle(SpriteBatch batch, long currentTime) {
		// TODO Auto-generated method stub
		// draw the obstacle
		batch.draw(this.tankTexture, this.currentPosition.getX(),
				this.currentPosition.getY());

	}

	@Override
	public void changeToCollapseTexture() {
		// TODO Auto-generated method stub

	}

}

package mad.agent069.obstacles;

import mad.agent069.Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class FireObstacle extends Obstacle {

	public FireObstacle(Scene currentScene, long lastTimeObstacle) {
		super(currentScene, lastTimeObstacle);
		// TODO Auto-generated constructor stub

		// Speed = 1 (equal to speed of the background)
		this.speed = 1;

		// Calculate the moving speed
		this.calculateMovingSpeed();

		// Set the current position
		this.currentPosition = new Rectangle(Scene.SCENE_WIDTH,
				Scene.SCENE_FLOOR_POSITION_Y,
				ObstacleTexture.FIRE_OBSTACLE_TEXTURE.getWidth(),
				ObstacleTexture.FIRE_OBSTACLE_TEXTURE.getHeight());

		// Set the obstacle width
		this.width = ObstacleTexture.FIRE_OBSTACLE_TEXTURE.getWidth();

		// Not allow to be shot
		this.allowShot = false;
	}

	@Override
	protected void drawSpecificObstacle(SpriteBatch batch, long currentTime) {
		// TODO Auto-generated method stub
		batch.draw(ObstacleTexture.FIRE_OBSTACLE_TEXTURE,
				this.currentPosition.getX(), this.currentPosition.getY());
	}


	@Override
	public void changeToCollapseTexture() {
		// TODO Auto-generated method stub

	}

}

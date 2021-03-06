package mad.agent069.obstacles;

import mad.agent069.Scene;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TankObstacleSlow extends Obstacle {

	// The texture to draw this obstacle
	private Texture tankTexture;

	// The collapse texture
	private Texture collapseTexture;

	// The obstacle blood
	private final int tankBlood = 10;

	public TankObstacleSlow(Scene currentScene, long lastTimeObstacle) {
		super(currentScene, lastTimeObstacle);
		// TODO Auto-generated constructor stub

		// Init the speed
		this.speed = (float) 0.2;

		// Calculate the actual obstacle moving speed
		this.calculateMovingSpeed();

		// Init the texture
		this.tankTexture = ObstacleTexture.TANK_OBSTACLE_SLOW_TEXTURE;
		this.collapseTexture = ObstacleTexture.TANK_COLLAPSE_OBSTACLE_TEXTURE;

		// Init the current position
		this.initCurrentPosition(tankTexture);

		// Init allow to be shot
		this.initAllowBeShot(this.tankBlood);
	}

	@Override
	protected void drawSpecificObstacle(SpriteBatch batch, long currentTime) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		batch.draw(this.tankTexture, this.currentPosition.getX(),
				this.currentPosition.getY());
	}

	@Override
	public void changeToCollapseTexture() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		this.tankTexture = this.collapseTexture;
	}

}

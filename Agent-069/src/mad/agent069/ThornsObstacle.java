package mad.agent069;

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
		this.tankTexture = new Texture(Gdx.files.internal("thorns.png"));

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
	public void collapse() {
		// TODO Auto-generated method stub

	}

}

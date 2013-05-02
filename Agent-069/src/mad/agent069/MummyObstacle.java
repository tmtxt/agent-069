package mad.agent069;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class MummyObstacle extends Obstacle {

	// The texture to draw this obstacle
	private Texture mummyTexture;
	
	// The collapse texture
	private Texture collapseTexture;

	// The position of the texture from the scene floor
	private final int distanceFromFloor = 65;
	
	// This obstacle's blood
	private final int blood = 1;

	public MummyObstacle(Scene currentScene, long lastTimeObstacle) {
		super(currentScene, lastTimeObstacle);
		// TODO Auto-generated constructor stub

		// Speed
		this.speed = (float) 1.5;

		// Calculate the actual obstacle moving speed
		this.calculateMovingSpeed();

		// Init the texture
		this.mummyTexture = ObstacleTexture.MUMMY_OBSTACLE_TEXTURE;
		this.collapseTexture = ObstacleTexture.TANK_COLLAPSE_OBSTACLE_TEXTURE;

		// Current position
		this.currentPosition = new Rectangle(Scene.SCENE_WIDTH,
				Scene.SCENE_FLOOR_POSITION_Y + this.distanceFromFloor,
				this.mummyTexture.getWidth(), this.mummyTexture.getHeight());

		// Set the obstacle width
		this.width = mummyTexture.getWidth();

		// Not allow to be shot
		this.initAllowBeShot(this.blood);
	}

	@Override
	protected void drawSpecificObstacle(SpriteBatch batch, long currentTime) {
		// TODO Auto-generated method stub
		// draw the obstacle
		batch.draw(this.mummyTexture, this.currentPosition.getX(),
				this.currentPosition.getY());
	}

	@Override
	public void changeToCollapseTexture() {
		// TODO Auto-generated method stub
		this.mummyTexture = this.collapseTexture;

	}

}

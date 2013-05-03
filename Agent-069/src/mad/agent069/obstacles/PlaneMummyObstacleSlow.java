package mad.agent069.obstacles;

import mad.agent069.Scene;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class PlaneMummyObstacleSlow extends Obstacle {

	// The plane with mummy texture
	private Texture planeMummyTexture;

	// Collapse texture
	private Texture collapseTexture;

	// The health
	private final int blood = 2;

	// The distance from the floor to the texture
	private final int distanceFromFloor = 90;

	// Initial Y
	private final int initialY = (int) (Scene.SCENE_FLOOR_POSITION_Y + distanceFromFloor);

	// The mummy texture
	private Texture mummyTexture;

	// The plane texture
	private Texture planeTexture;

	// Is shot?
	private boolean isShot;

	public PlaneMummyObstacleSlow(Scene currentScene, long lastTimeObstacle) {
		super(currentScene, lastTimeObstacle);
		// TODO Auto-generated constructor stub

		// Init the texture
		this.planeMummyTexture = ObstacleTexture.PLANE_MUMMY_OBSTACLE_TEXTURE;
		this.collapseTexture = ObstacleTexture.MUMMY_COLLAPSE_OBSTACLE_TEXTURE;
		this.mummyTexture = ObstacleTexture.MUMMY_DROP_OBSTACLE_TEXTURE;
		this.planeTexture = ObstacleTexture.PLANE_OBSTACLE_TEXTURE;

		// Init the speed
		this.speed = (float) 0.3;

		// Calculate moving speed
		this.calculateMovingSpeed();

		// Has not been shot yet
		this.isShot = false;

		// Init the current position
		this.currentPosition = new Rectangle(Scene.SCENE_WIDTH,
				Scene.SCENE_FLOOR_POSITION_Y + this.distanceFromFloor,
				this.planeMummyTexture.getWidth(),
				this.planeMummyTexture.getHeight());

		this.initAllowBeShot(this.blood);
	}

	@Override
	protected void drawSpecificObstacle(SpriteBatch batch, long currentTime) {
		// TODO Auto-generated method stub
		if (isShot) {
			if (this.currentPosition.getY() >= Scene.SCENE_FLOOR_POSITION_Y + 75) {
				batch.draw(planeTexture, this.currentPosition.getX(),
						this.currentPosition.getY() + 75 + 118);
				batch.draw(mummyTexture, this.currentPosition.getX(),
						this.currentPosition.getY());
				this.currentPosition.setY(Scene.SCENE_FLOOR_POSITION_Y + 60);
			} else if (this.currentPosition.getY() >= Scene.SCENE_FLOOR_POSITION_Y + 60) {
				batch.draw(planeTexture, this.currentPosition.getX(),
						this.currentPosition.getY() + 60 + 118);
				batch.draw(mummyTexture, this.currentPosition.getX(),
						this.currentPosition.getY());
				this.currentPosition.setY(Scene.SCENE_FLOOR_POSITION_Y + 45);
			} else if (this.currentPosition.getY() >= Scene.SCENE_FLOOR_POSITION_Y + 45) {
				batch.draw(planeTexture, this.currentPosition.getX(),
						this.currentPosition.getY() + 45 + 118);
				batch.draw(mummyTexture, this.currentPosition.getX(),
						this.currentPosition.getY());
				this.currentPosition.setY(Scene.SCENE_FLOOR_POSITION_Y + 30);
			} else if (this.currentPosition.getY() >= Scene.SCENE_FLOOR_POSITION_Y + 30) {
				batch.draw(planeTexture, this.currentPosition.getX(),
						this.currentPosition.getY() + 30 + 118);
				batch.draw(mummyTexture, this.currentPosition.getX(),
						this.currentPosition.getY());
				this.currentPosition.setY(Scene.SCENE_FLOOR_POSITION_Y + 15);
			} else if (this.currentPosition.getY() >= Scene.SCENE_FLOOR_POSITION_Y + 15) {
				batch.draw(planeTexture, this.currentPosition.getX(),
						this.currentPosition.getY() + 15 + 118);
				batch.draw(mummyTexture, this.currentPosition.getX(),
						this.currentPosition.getY());
				this.currentPosition.setY(Scene.SCENE_FLOOR_POSITION_Y);
			} else {
				batch.draw(this.planeTexture, this.currentPosition.getX(),
						Scene.SCENE_FLOOR_POSITION_Y + this.distanceFromFloor
								+ 118);
				batch.draw(collapseTexture, currentPosition.getX(),
						currentPosition.getY());
			}

			// batch.draw(this.planeTexture, this.currentPosition.getX(),
			// Scene.SCENE_FLOOR_POSITION_Y + this.distanceFromFloor + 118);
			// batch.draw(collapseTexture, currentPosition.getX(),
			// currentPosition.getY());
		} else {
			if (this.currentPosition.getX() > 220) {
				batch.draw(this.planeMummyTexture, this.currentPosition.getX(),
						this.currentPosition.getY());
			} else if(this.currentPosition.getX() > 165){
				if (this.currentPosition.getY() != (this.initialY - 15))
					this.currentPosition.setY(this.initialY - 15);
				batch.draw(this.planeTexture, this.currentPosition.getX(),
						this.currentPosition.getY() + 15 + 118);
				batch.draw(this.mummyTexture, this.currentPosition.getX(),
						this.currentPosition.getY());
			} else if (this.currentPosition.getX() > 110) {
				if (this.currentPosition.getY() != (this.initialY - 30))
					this.currentPosition.setY(this.initialY - 30);
				batch.draw(this.planeTexture, this.currentPosition.getX(),
						this.currentPosition.getY() + 30 + 118);
				batch.draw(this.mummyTexture, this.currentPosition.getX(),
						this.currentPosition.getY());
			} else {
				if (this.currentPosition.getY() != (this.initialY - 60))
					this.currentPosition.setY(this.initialY - 60);
				batch.draw(this.planeTexture, this.currentPosition.getX(),
						this.currentPosition.getY() + 60 + 118);
				batch.draw(this.mummyTexture, this.currentPosition.getX(),
						this.currentPosition.getY());
			}

		}

	}

	@Override
	public void changeToCollapseTexture() {
		// TODO Auto-generated method stub
		this.isShot = true;
		this.currentPosition.setY(Scene.SCENE_FLOOR_POSITION_Y + 75);
		// this.currentPosition.setY(Scene.SCENE_FLOOR_POSITION_Y);
	}

}

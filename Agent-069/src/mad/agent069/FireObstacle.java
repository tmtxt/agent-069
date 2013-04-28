package mad.agent069;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class FireObstacle extends Obstacle {
	
	// The texture to draw this obstacle
	private Texture fireTexture;

	public FireObstacle(Scene currentScene, long lastTimeObstacle) {
		super(currentScene, lastTimeObstacle);
		// TODO Auto-generated constructor stub
		
		// Speed = 1 (equal to speed of the background)
		this.speed = 1;
		
		// Calculate the moving speed
		this.calculateMovingSpeed();
		
		// Init the texture
		this.fireTexture = new Texture(Gdx.files.internal("fire.png"));
		
		// Set the current position
		this.currentPosition = new Rectangle(Scene.SCENE_WIDTH, Scene.SCENE_FLOOR_POSITION_Y,
				this.fireTexture.getWidth(), this.fireTexture.getHeight());
		
		// Set the obstacle width
		this.width = this.fireTexture.getWidth();
		
		// Not allow to be shot
		this.allowShot = false;
	}

	@Override
	protected void drawSpecificObstacle(SpriteBatch batch, long currentTime) {
		// TODO Auto-generated method stub
		batch.draw(this.fireTexture, this.currentPosition.getX(), this.currentPosition.getY());
	}

	@Override
	public void collapse() {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeToCollapseTexture() {
		// TODO Auto-generated method stub
		
	}

}

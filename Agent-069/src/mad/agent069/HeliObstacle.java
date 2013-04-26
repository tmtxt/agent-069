package mad.agent069;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class HeliObstacle extends Obstacle {
	
	// The texture to draw this obstacle
	private Texture heliTexture;
	
	// The position of the texture from the scene floor
	private final int distanceFromFloor = 65;

	public HeliObstacle(Scene currentScene, long lastTimeObstacle) {
		super(currentScene, lastTimeObstacle);
		// TODO Auto-generated constructor stub
		
		// The speed of this obstacle
		this.speed = 1;
		
		// Calculate the actual obstacle moving speed
		this.calculateMovingSpeed();
		
		// Init the texture
		this.heliTexture = new Texture(Gdx.files.internal("obstacles/heli.png"));
		
		// Set the current position
		this.currentPosition = new Rectangle(Scene.SCENE_WIDTH, Scene.SCENE_FLOOR_POSITION_Y + this.distanceFromFloor,
				this.heliTexture.getWidth(), this.heliTexture.getHeight());
		
		// Set the obstacle width
		this.width = heliTexture.getWidth();
	}

	@Override
	protected void drawSpecificObstacle(SpriteBatch batch, long currentTime) {
		// TODO Auto-generated method stub
		
		// draw the obstacle
		batch.draw(this.heliTexture, this.currentPosition.getX(), this.currentPosition.getY());
		
	}

	@Override
	public void collapse() {
		// TODO Auto-generated method stub

	}

}

package mad.agent069;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TankObstacle extends Obstacle {
	
	// The texture to draw this obstacle
	private Texture tankTexture;
	
	

	public TankObstacle(Scene currentScene) {
		super(currentScene);
		// TODO Auto-generated constructor stub
		
		// The speed of this obstacle
		this.obstacleSpeed = 1;
		
		// Calculate the actual obstacle moving speed
		this.calculateMovingSpeed();
		
		// Init the texture
		this.tankTexture = new Texture(Gdx.files.internal("obstacles/tank.png"));
	}

	@Override
	public void drawObstacle(SpriteBatch batch, long currentTime) {
		// TODO Auto-generated method stub
		batch.draw(this.tankTexture, this.currentX, this.currentY);
	}

	@Override
	public void collapse() {
		// TODO Auto-generated method stub

	}

}

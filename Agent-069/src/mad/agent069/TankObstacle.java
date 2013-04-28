package mad.agent069;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class TankObstacle extends Obstacle {

	// The texture to draw this obstacle
	private Texture tankTexture;
	
	// The collapse texture
	private Texture collapseTexture;

	public TankObstacle(Scene currentScene, long lastTimeObstacle) {
		super(currentScene, lastTimeObstacle);
		// TODO Auto-generated constructor stub

		// Init the speed
		this.speed = (float) 0.2;

		// Calculate the actual obstacle moving speed
		this.calculateMovingSpeed();

		// Init the texture
		this.tankTexture = new Texture(Gdx.files.internal("tank.png"));
		this.collapseTexture = new Texture(Gdx.files.internal("tankcollapse.png"));

		// Set the current position
		this.currentPosition = new Rectangle(Scene.SCENE_WIDTH,
				Scene.SCENE_FLOOR_POSITION_Y, this.tankTexture.getWidth(),
				this.tankTexture.getHeight());

		// Set the obstacle width
		this.width = this.tankTexture.getWidth();

		// Allow to be shot
		this.allowShot = true;
		
		// Obstacle blood (explode after 3 shot)
		this.blood = 3;

		// Obstacle explosion sound
		this.explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosion.mp3"));
	}

	@Override
	protected void drawSpecificObstacle(SpriteBatch batch, long currentTime) {
		// TODO Auto-generated method stub
		batch.draw(this.tankTexture, this.currentPosition.getX(),
				this.currentPosition.getY());
	}

	@Override
	public void collapse() {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeToCollapseTexture() {
		// TODO Auto-generated method stub
		this.tankTexture = this.collapseTexture;
	}

}

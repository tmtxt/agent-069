package mad.agent069;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Bullet {
	// The texture to draw this bullet
	private Texture bulletTexture;

	// The constant distance from the bottom of main character to the bullet
	private final float distanceFromMainCharacterBottom = 52;
	
	// The position in Y axis (in pixel)
	private float positionY;

	// The current position of the bullet
	private Rectangle currentPosition;
		
	// The distance for each time the bullet move (pixel)
	private final int movingDistance = 40;

	public Bullet(Scene currentScene, MainCharacter mainCharacter) {
		// Init the texture
		this.bulletTexture = new Texture(Gdx.files.internal("bullet.png"));
		
		// Init the position in Y axis
		this.positionY = mainCharacter.getCurrentPosition().getY() + this.distanceFromMainCharacterBottom;

		// Init the current position
		this.currentPosition = new Rectangle(mainCharacter.getCurrentPosition()
				.getX() + mainCharacter.getCurrentPosition().getWidth(),
				this.positionY, this.bulletTexture.getWidth(),
				this.bulletTexture.getHeight());
	}
	
	/**
	 * Draw this bullet
	 * 
	 * @param batch The scene SpriteBatch
	 */
	public void drawBullet(SpriteBatch batch, List<Bullet> bulletList){
		batch.draw(this.bulletTexture, this.currentPosition.getX(), this.currentPosition.getY());
		this.currentPosition.setX(this.currentPosition.getX() + this.movingDistance);
	}

	public Rectangle getCurrentPosition() {
		return currentPosition;
	}
}

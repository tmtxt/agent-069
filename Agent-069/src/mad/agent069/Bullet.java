package mad.agent069;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Bullet {
	// The texture to draw this bullet
	private static final Texture bulletTexture = new Texture(
			Gdx.files.internal("bullet.png"));

	// The constant distance from the bottom of main character to the bullet
	private final float distanceFromMainCharacterBottom = 45;

	// The position in Y axis (in pixel)
	private float positionY;

	// The current position of the bullet
	private Rectangle currentPosition;

	// The distance for each time the bullet move (pixel)
	private final int movingDistance = 40;

	// The sound of the bullet
	private Sound shootingSound;

	public Bullet(Scene currentScene, MainCharacter mainCharacter) {
		// Init the position in Y axis
		this.positionY = mainCharacter.getCurrentPosition().getY()
				+ this.distanceFromMainCharacterBottom;

		// Init the current position
		this.currentPosition = new Rectangle(mainCharacter.getCurrentPosition()
				.getX() + mainCharacter.getCurrentPosition().getWidth(),
				this.positionY, Bullet.bulletTexture.getWidth(),
				Bullet.bulletTexture.getHeight());

		// Init the sound
		this.shootingSound = Gdx.audio.newSound(Gdx.files.internal("gun.mp3"));
	}

	public void playShootingSound() {
		this.shootingSound.play();
	}

	/**
	 * Draw this bullet
	 * 
	 * @param batch
	 *            The scene SpriteBatch
	 */
	public void drawBullet(SpriteBatch batch, List<Bullet> bulletList) {
		// Draw this bullet
		batch.draw(Bullet.bulletTexture, this.currentPosition.getX(),
				this.currentPosition.getY());

		// Set the current position
		this.currentPosition.setX(this.currentPosition.getX()
				+ this.movingDistance);

	}

	public Rectangle getCurrentPosition() {
		return currentPosition;
	}
}

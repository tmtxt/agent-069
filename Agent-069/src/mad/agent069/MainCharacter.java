package mad.agent069;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainCharacter {
	// The initial position of the main character
	public static final float ORIGINAL_X = 20;
	public static final float ORIGINAL_Y = 20;

	// The current position of the main character
	private float currentX;
	private float currentY;

	// The current status: Normal, Jumping, Lowering head
	private int currentStatus;
	public static final int CURRENT_STATUS_NORMAL = 1;
	public static final int CURRENT_STATUS_JUMPING = 2;
	public static final int CURRENT_STATUS_LOWERHEAD = 3;

	// The current texture of the main character
	private Texture currentTexture;

	// The texture to draw for normal status
	private Texture normalTexture;

	// The texture to draw for lower head status
	private Texture lowerheadTexture;

	// The current scene this main character belongs to
	private Scene currentScene;

	public MainCharacter(Scene currentScene) {
		// The current scene
		this.currentScene = currentScene;

		// Init the normal texture
		this.normalTexture = new Texture(
				Gdx.files.internal("maincharacter/player-normal.png"));

		// Init the lowerhead texture
		this.lowerheadTexture = new Texture(
				Gdx.files.internal("maincharacter/player-low.png"));

		// Set the current status and the current texture
		this.currentStatus = MainCharacter.CURRENT_STATUS_NORMAL;
		this.currentTexture = this.normalTexture;

		// Set the current position of the main character
		this.currentX = MainCharacter.ORIGINAL_X;
		this.currentY = MainCharacter.ORIGINAL_Y;
	}

	/**
	 * Draw the main character
	 * 
	 * @param batch
	 *            The SpriteBatch of the current Scene
	 */
	public void drawMainCharacter(SpriteBatch batch) {
		switch (this.currentStatus) {
		case MainCharacter.CURRENT_STATUS_NORMAL:
			this.currentStatusNormalHandler();
			break;
			
		case MainCharacter.CURRENT_STATUS_LOWERHEAD:
			this.currentStatusLowerheadHandler();
			break;
			
		default:
			this.currentStatusJumpingHandler();
			break;
		}
		batch.draw(this.currentTexture, this.currentX, this.currentY);
	}
	
	/**
	 * Handler for normal status
	 */
	private void currentStatusNormalHandler(){
		this.currentTexture = this.normalTexture;
	}
	
	/**
	 * Handler for jumping status
	 */
	private void currentStatusJumpingHandler(){
		this.currentTexture = this.normalTexture;
	}
	
	/**
	 * Handler for lower head status
	 */
	private void currentStatusLowerheadHandler(){
		this.currentTexture = this.lowerheadTexture;
	}

	public int getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(int currentStatus) {
		// Only allow to change status when the current status is normal status
		// If the current status is not normal status (jumping or lowerhead), continue to draw
		// the texture until it come back to normal status
		if(this.currentStatus == MainCharacter.CURRENT_STATUS_NORMAL){
			this.currentStatus = currentStatus;
		}
	}

	public Texture getCurrentTexture() {
		return currentTexture;
	}

	public void setCurrentTexture(Texture currentTexture) {
		this.currentTexture = currentTexture;
	}

	public Texture getNormalTexture() {
		return normalTexture;
	}

	public void setNormalTexture(Texture normalTexture) {
		this.normalTexture = normalTexture;
	}

	public Texture getLowerheadTexture() {
		return lowerheadTexture;
	}

	public void setLowerheadTexture(Texture lowerheadTexture) {
		this.lowerheadTexture = lowerheadTexture;
	}

	public float getCurrentX() {
		return currentX;
	}

	public void setCurrentX(float currentX) {
		this.currentX = currentX;
	}

	public float getCurrentY() {
		return currentY;
	}

	public void setCurrentY(float currentY) {
		this.currentY = currentY;
	}
}

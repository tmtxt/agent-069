package mad.agent069;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class MainCharacter {
	// The initial position of the main character
	public static final float ORIGINAL_X = 20;
	public static float ORIGINAL_Y = Scene.SCENE_FLOOR_POSITION_Y;

	// The current position of the main character
	private float currentX;
	private float currentY;
	private Rectangle currentPosition;
	
	// The actual position of the main character
	private Rectangle currentPositionCrop;
	
	// The gap from the texture edge to the real image
	public static final float TEXTURE_GAP_X = 33;
	public static final float TEXTURE_GAP_Y = 42;

	public Rectangle getCurrentPositionCrop() {
		return currentPositionCrop;
	}

	public Rectangle getCurrentPosition() {
		return currentPosition;
	}

	// The current status: Normal, Jumping, Lowering head
	private int currentStatus;
	public static final int CURRENT_STATUS_NORMAL = 1;
	public static final int CURRENT_STATUS_JUMPING = 2;
	public static final int CURRENT_STATUS_LOWERHEAD = 3;
	
	// The lowerhead time
	// This is not the actual lower head time, just a number for used with current scene speed
	// to calculate the real lower head time in nanosecond
	private final long lowerheadTime = 1000000000;
	// The actual lowerhead time
	private long lowerheadActualTime;
	// Start time draw lowerhead
	private long lowerheadStartTime;
	
	// The jumping status properties
	// This is not the actual jumping time, just a number for used with current scene speed
	// to calculate the real jumping time in nanosecond
	private final long jumpingTime = 50000000;
	// The actual jumping time
	private long jumpingActualTime;
	// Last time draw jumping time
	private long jumpingLastTime;
	// The peak position of the texture when jump in Y axis
	private final float jumpingPeakPositionY = Scene.SCENE_FLOOR_POSITION_Y + 100;
	// The jumping distance for each jump, measured in pixel
	private final float jumpingDistance = 20;
	// The current orientation of jump status (go up/down)
	private boolean jumpingCurrentOrientation;
	// The constants to determine jumping orientation
	private final boolean jumpingOrientationUp = true;
	private final boolean jumpingOrientaionDown = false;

	// The current texture of the main character
	private Texture currentTexture;
	// The texture to draw for normal status
	private Texture normalTexture;
	// The texture to draw for lower head status
	private Texture lowerheadTexture;

	// The current scene this main character belongs to
	private Scene currentScene;

	/**
	 * 
	 * 
	 * @param currentScene The scene this main character belongs to
	 */
	public MainCharacter(Scene currentScene) {
		// The current scene
		this.currentScene = currentScene;

		// Init the normal texture
		this.normalTexture = new Texture(
				Gdx.files.internal("player-normal.png"));

		// Init the lowerhead texture
		this.lowerheadTexture = new Texture(
				Gdx.files.internal("player-low.png"));

		// Set the current status and the current texture
		this.currentStatus = MainCharacter.CURRENT_STATUS_NORMAL;
		this.currentTexture = this.normalTexture;

		// Set the current position of the main character
		this.currentX = MainCharacter.ORIGINAL_X;
		this.currentY = MainCharacter.ORIGINAL_Y;
		this.currentPosition = new Rectangle(MainCharacter.ORIGINAL_X, MainCharacter.ORIGINAL_Y,
				this.currentTexture.getWidth(), this.currentTexture.getHeight());
		this.currentPositionCrop = new Rectangle(this.currentPosition.getX() + MainCharacter.TEXTURE_GAP_X,
				this.currentPosition.getY(),
				this.currentPosition.getWidth() - MainCharacter.TEXTURE_GAP_X,
				this.currentPosition.getHeight() - MainCharacter.TEXTURE_GAP_Y);
		
		// Calculate the actual lowerhead time
		this.lowerheadActualTime = (long)(this.lowerheadTime / this.currentScene.getSpeed());
		
		// Calculate the actual jumping time
		this.jumpingActualTime = (long)(this.jumpingTime / this.currentScene.getSpeed());
	}

	/**
	 * Draw the main character
	 * 
	 * @param batch The SpriteBatch of the current Scene
	 * @param currentTime The current time measure in nanosecond
	 */
	public void drawMainCharacter(SpriteBatch batch, long currentTime) {
		switch (this.currentStatus) {
		case MainCharacter.CURRENT_STATUS_NORMAL:
			this.currentStatusNormalHandler();
			break;
			
		case MainCharacter.CURRENT_STATUS_LOWERHEAD:
			this.currentStatusLowerheadHandler(currentTime);
			break;
			
		default:
			
			this.currentStatusJumpingHandler(currentTime);
			break;
		}
		batch.draw(this.currentTexture, this.currentPosition.getX(), this.currentPosition.getY());
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
	private void currentStatusJumpingHandler(long currentTime){
		
		if(currentTime - this.jumpingLastTime > this.jumpingActualTime){
			
			// For up orientation
			if(this.jumpingCurrentOrientation == this.jumpingOrientationUp){
				// Increase the current position in Y axis of the texture
				// this.currentY += this.jumpingDistance;
				this.currentPosition.setY(this.currentPosition.getY() + this.jumpingDistance);
				this.currentPositionCrop.setY(this.currentPosition.getY() + this.jumpingDistance);
				
				if(this.currentPosition.getY() >= this.jumpingPeakPositionY){
					// reach the peak, change orientation to down
					this.jumpingCurrentOrientation = this.jumpingOrientaionDown;
				}
				
			} // For down orientation
			else {
				// Decrease the current position in Y axis of the texture
				// this.currentY -= this.jumpingDistance;
				this.currentPosition.setY(this.currentPosition.getY() - this.jumpingDistance);
				this.currentPositionCrop.setY(this.currentPosition.getY() - this.jumpingDistance);
				
				if(this.currentPosition.getY() <= MainCharacter.ORIGINAL_Y){
					// reach the bottom, back to normal status
					this.currentStatus = MainCharacter.CURRENT_STATUS_NORMAL;
				}
				
			}
			
			// Set the last time jumping to current time
			this.jumpingLastTime = currentTime;
			
			// Used the normal texture for jumping, can change later
			this.currentTexture = this.normalTexture;
		}
		
	}
	
	/**
	 * Handler for lower head status
	 */
	private void currentStatusLowerheadHandler(long currentTime){
		if(currentTime - this.lowerheadStartTime >= this.lowerheadActualTime){
			// back to normal status
			this.currentStatus = MainCharacter.CURRENT_STATUS_NORMAL;
			this.currentTexture = this.normalTexture;
		} else {
			// set the texture to lowerhead
			this.currentTexture = this.lowerheadTexture;
		}
		
		// set the current position
		this.currentPosition.setHeight(this.currentTexture.getHeight());
		this.currentPositionCrop.setHeight(this.currentTexture.getHeight());
	}

	/**
	 * Get the current status of main character
	 * 
	 * @return int The current status of main character
	 */
	public int getCurrentStatus() {
		return currentStatus;
	}

	/**
	 * Set the current status of the main character
	 * 
	 * @param currentStatus The status to set
	 * @param currentTime The current time measures in nanosecond (TimeUtils.nanoTime())
	 */
	public void setCurrentStatus(int currentStatus, long currentTime) {
		// Only allow to change status when the current status is normal status
		// If the current status is not normal status (jumping or lowerhead), continue to draw
		// the texture until it come back to normal status
		if(this.currentStatus == MainCharacter.CURRENT_STATUS_NORMAL){
			if(currentStatus == MainCharacter.CURRENT_STATUS_LOWERHEAD){
				// Set the start time lowerhead
				this.lowerheadStartTime = currentTime;
			} else if(currentStatus == MainCharacter.CURRENT_STATUS_JUMPING){
				// Set the last time jumping
				this.jumpingLastTime = currentTime;
				// Set the jumping orientation
				this.jumpingCurrentOrientation = this.jumpingOrientationUp;
			}
			// Set the current status
			this.currentStatus = currentStatus;
		}
	}
	
}

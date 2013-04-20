package mad.agent069;

import com.badlogic.gdx.graphics.Texture;

public class MainCharacter {
	// The initial position of the main character
	private final float originalX = 20;
	private final float originalY = 20;
	
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
	
	public MainCharacter(Scene currentScene){
		// The current scene
		this.currentScene = currentScene;
		
		// Init the texture here
		
		// Set the current status and the current texture
		this.currentStatus = MainCharacter.CURRENT_STATUS_NORMAL;
		this.currentTexture = this.normalTexture;
	}
	
	public int getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(int currentStatus) {
		this.currentStatus = currentStatus;
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
	public float getOriginalX() {
		return originalX;
	}
	public float getOriginalY() {
		return originalY;
	}
}

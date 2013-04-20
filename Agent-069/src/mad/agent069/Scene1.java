package mad.agent069;

import com.badlogic.gdx.graphics.Texture;

public class Scene1 extends Scene {
	
	// The track texture
	private Texture trackTexture;
	
	// The sky texture
	private Texture skyTexture;

	@Override
	public void create() {
		// TODO Auto-generated method stub
		super.create();
		
		// The speed of this scene, used for calculating the moving time of the background, main character and obstacles
		this.sceneSpeed = 1;
		
		// The main character
		this.mainCharacter = new MainCharacter(this);
		
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		super.resize(width, height);
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		super.render();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		super.pause();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		super.resume();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}
	
}

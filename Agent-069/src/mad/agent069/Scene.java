package mad.agent069;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class Scene implements ApplicationListener{
	
	// The screen size of the game, measured in pixel
	public static final int SCENE_WIDTH = 800;
	public static final int SCENE_HEIGHT = 400;
	
	// The camera of the game
	protected OrthographicCamera camera;
	
	// Sprite batch
	protected SpriteBatch batch;

	// Determine the speed of the current scene
	// This speed is used for calculating background speed, obstacle speed,...
	protected float sceneSpeed;
	
	// Time for the background to move backward
	protected long backgroundMovingTime;
	
	// The length of the scene
	protected long length;
	
	public float getSceneSpeed() {
		return sceneSpeed;
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}

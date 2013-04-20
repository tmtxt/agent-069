package mad.agent069;

import mad.agent069.DirectionGestureDetector.DirectionListener;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Scene implements ApplicationListener {

	// The screen size of the game, measured in pixel
	public static final int SCENE_WIDTH = 800;
	public static final int SCENE_HEIGHT = 400;

	// The camera of the game
	protected OrthographicCamera camera;

	// Sprite batch
	protected SpriteBatch batch;

	// Main character
	protected MainCharacter mainCharacter;

	// Determine the speed of the current scene
	// This speed is used for calculating background speed, obstacle speed,...
	protected float sceneSpeed;

	// This is not the real scene moving time, it's just a number for used with
	// sceneSpeed
	// to calculate the real moving time
	public static final long SCENE_MOVING_TIME = 30000000;

	// The length of the scene
	protected long length;

	public float getSceneSpeed() {
		return sceneSpeed;
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		// Gesture Detector for the game
		this.setGestureDetector();
	}
	
	/**
	 * Handler for swipe up gesture
	 */
	protected void swipeUpHandler(){
		this.mainCharacter.setCurrentStatus(MainCharacter.CURRENT_STATUS_JUMPING);
	}
	
	/**
	 * Handler for swipe down gesture
	 */
	protected void swipeDownHandler(){
		this.mainCharacter.setCurrentStatus(MainCharacter.CURRENT_STATUS_LOWERHEAD);
	}

	/**
	 * Set the gesture detector for the game
	 */
	protected void setGestureDetector() {
		// Gesture detector for the game
		Gdx.input.setInputProcessor(new DirectionGestureDetector(
				new DirectionListener() {

					@Override
					public void onUp() {
						// TODO Auto-generated method stub
						swipeUpHandler();
					}

					@Override
					public void onRight() {
						// TODO Auto-generated method stub

					}

					@Override
					public void onLeft() {
						// TODO Auto-generated method stub

					}

					@Override
					public void onDown() {
						// TODO Auto-generated method stub
						swipeDownHandler();
					}
				}));
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

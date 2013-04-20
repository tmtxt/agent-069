package mad.agent069;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import mad.agent069.DirectionGestureDetector.DirectionListener;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class Scene implements ApplicationListener {

	// The screen size of the game, measured in pixel
	public static final int SCENE_WIDTH = 800;
	public static final int SCENE_HEIGHT = 400;

	// The position of the floor
	public static final float SCENE_FLOOR_POSITION_Y = 20;

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

	// The list of obstacle class names
	String[] obstacleClassNames = { "RockObstacle", "TankObstacle" };

	// Create a new obstacle
	protected Obstacle createNewObstacle() {
		Random random = new Random();
		Obstacle obstacle = null;
		
		// Get the random obstacle class name
		String obstacleClassName = "mad.agent069."
				+ this.obstacleClassNames[random.nextInt(this.obstacleClassNames.length)];

		try {
			Constructor c = Class.forName(obstacleClassName)
					.getConstructor(Scene.class);
			obstacle = (Obstacle) c.newInstance(this);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return obstacle;
	}

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
	protected void swipeUpHandler() {
		this.mainCharacter.setCurrentStatus(
				MainCharacter.CURRENT_STATUS_JUMPING, TimeUtils.nanoTime());
	}

	/**
	 * Handler for swipe down gesture
	 */
	protected void swipeDownHandler() {
		this.mainCharacter.setCurrentStatus(
				MainCharacter.CURRENT_STATUS_LOWERHEAD, TimeUtils.nanoTime());
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

package mad.agent069;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import mad.agent069.*;
import mad.agent069.DirectionGestureDetector.DirectionListener;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class Scene implements ApplicationListener {

	// The screen size of the game, measured in pixel
	public static final int SCENE_WIDTH = 800;
	public static final int SCENE_HEIGHT = 400;

	// The position of the floor
	public static float SCENE_FLOOR_POSITION_Y = 20;

	// The camera of the game
	protected OrthographicCamera camera;

	// Sprite batch
	protected SpriteBatch batch;

	// Main character
	protected MainCharacter mainCharacter;

	// Determine the speed of the current scene
	// This speed is used for calculating background speed, obstacle speed,...
	protected float speed;

	// This is not the real scene moving time, it's just a number for used with
	// sceneSpeed
	// to calculate the real moving time
	public static final long SCENE_MOVING_TIME = 30000000;

	// The actual scene moving time
	protected long actualMovingTime;

	public long getActualMovingTime() {
		return actualMovingTime;
	}

	// The length of the scene
	protected long length;

	// The distance each time the background move
	public static final float BACKGROUND_MOVING_DISTANCE = 20;

	// The list of obstacle class names
	public static final String[] OBSTACLE_CLASS_NAMES = { "RockObstacle", "TankObstacle", "HeliObstacle" };

	// The obstacle
	protected Obstacle obstacle;
	
	// The texture to draw when main character overlap obstacle
	protected Texture explosionTexture;
	
	// The explosion sound
	protected Sound explosionSound;

	/**
	 * Generate a random obstacle
	 * 
	 * @param currentTime
	 *            The current time in nanosecond
	 * @return Obstacle The newly created obstacle object
	 */
	protected Obstacle createNewObstacle(long currentTime) {
		Random random = new Random();
		Obstacle obstacle = null;

		// Get the random obstacle class name
		String obstacleClassName = "mad.agent069."
				+ Scene.OBSTACLE_CLASS_NAMES[random
						.nextInt(Scene.OBSTACLE_CLASS_NAMES.length)];

		try {
			Constructor c = Class.forName(obstacleClassName).getConstructor(
					Scene.class, long.class);
			obstacle = (Obstacle) c.newInstance(this, currentTime);
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

	/**
	 * Check if the main character and the obstacle overlap
	 */
	protected void obstacleOverlap(SpriteBatch batch) {
		if (this.mainCharacter.getCurrentPositionCrop().overlaps(
				this.obstacle.getCurrentPosition())) {
			// Code goes here
			// Gdx.input.vibrate(100);
			// Draw the explosion image
			batch.draw(this.explosionTexture, this.mainCharacter.getCurrentPosition().getX(), this.mainCharacter.getCurrentPosition().getY());
			// Play the explosion sound
			this.explosionSound.play();
			
			Gdx.graphics.setContinuousRendering(false);
		}
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		// Gesture Detector for the game
		this.setGestureDetector();

		// Init the camera
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, Scene.SCENE_WIDTH, Scene.SCENE_HEIGHT);

		// Init the sprite batch
		this.batch = new SpriteBatch();
		
		// Init the explosion texture
		this.explosionTexture = new Texture(Gdx.files.internal("explosion.png"));
		
		// Init the explosion sound
		this.explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosion.mp3"));
	}

	/**
	 * Init some properties after the create() function is called but before the
	 * render(). This means this function will be called at the end of the
	 * create() method in the derived class
	 * 
	 * @param long The current time in nanosecond
	 */
	protected void initAfterCreate(long currentTime) {
		// The main character
		this.mainCharacter = new MainCharacter(this);

		// Init the obstacle
		this.obstacle = this.createNewObstacle(currentTime);
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
		// Clear color
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		// Set the camera
		batch.setProjectionMatrix(this.camera.combined);
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

	// GENERATED METHODS

	public long getLength() {
		return length;
	}

	public float getSpeed() {
		return speed;
	}

}

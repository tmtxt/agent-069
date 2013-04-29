package mad.agent069;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
	public static String[] OBSTACLE_CLASS_NAMES = { "RockObstacle",
			"ThornsObstacle", "HeliObstacle", "FireObstacle", "TankObstacle" };

	// The obstacle
	protected Obstacle obstacle;

	// The texture to draw when main character overlap obstacle
	protected Texture explosionTexture;

	// The explosion sound
	protected Sound explosionSound;

	// The list of bullets
	List<Bullet> bulletList;

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

		if (this.obstacle.allowOverlap) {
			if (this.mainCharacter.getCurrentPositionCrop().overlaps(
					this.obstacle.getCurrentPosition())) {

				// Draw the explosion image
				batch.draw(this.explosionTexture, this.mainCharacter
						.getCurrentPosition().getX(), this.mainCharacter
						.getCurrentPosition().getY());
				// Play the explosion sound
				this.explosionSound.play();
				Gdx.input.vibrate(100);

				// Stop rendering
				Gdx.graphics.setContinuousRendering(false);
				
				// Disable gesture detection
				Gdx.input.setInputProcessor(null);
				
				// Calculate the score
				long currentTime = TimeUtils.millis();
				Score.finishCurrentScene(currentTime);
				System.out.println(Score.getScore());
			}
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
		this.explosionSound = Gdx.audio.newSound(Gdx.files
				.internal("explosion.mp3"));

		// Init the list of bullet
		this.bulletList = new ArrayList<Bullet>();

		// Init the score
		long currentTime = TimeUtils.millis();
		Score.startNewScene(currentTime);
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
		// Play the jumping sound
		this.mainCharacter.playJumpSound();

		// Change the status
		this.mainCharacter.setCurrentStatus(
				MainCharacter.CURRENT_STATUS_JUMPING, TimeUtils.nanoTime());
	}

	/**
	 * Handler for swipe down gesture
	 */
	protected void swipeDownHandler() {
		// Play the lowerhead sound
		this.mainCharacter.playLowerheadSound();

		// Change the status
		this.mainCharacter.setCurrentStatus(
				MainCharacter.CURRENT_STATUS_LOWERHEAD, TimeUtils.nanoTime());
	}

	/**
	 * Handler for swipe right (shoot)
	 */
	protected void swipeRightHandler() {
		Bullet bullet = new Bullet(this, this.mainCharacter);

		// Play the sound
		// bullet.playShootingSound();

		// Create a new bullet
		this.bulletList.add(bullet);

	}

	/**
	 * A general draw function that all scene should call after drawing the
	 * background
	 * 
	 * @param batch
	 *            The scene SpriteBatch
	 * @param currentTime
	 *            The current time in nanosecond
	 */
	protected void drawGeneral(SpriteBatch batch, long currentTime) {
		// Draw the main character
		this.mainCharacter.drawMainCharacter(batch, currentTime);

		// Draw the obstacle
		this.obstacle.drawObstacle(batch, currentTime);

		// Draw the bullet
		for (Bullet bullet : this.bulletList) {
			bullet.drawBullet(batch, this.bulletList);
		}

		// Check if the main character overlap the obstacle
		this.obstacleOverlap(batch);
	}

	/**
	 * Remove the bullet from the list if it goes out of the screen. Should be
	 * called in the render function before drawing
	 */
	protected void removeBullet() {
		if (this.bulletList.size() > 0) {
			// If the first bullet in the list goes out of the screen, remove it
			if (this.bulletList.get(0).getCurrentPosition().getX() >= Scene.SCENE_WIDTH) {
				this.bulletList.remove(0);
			}
		}
	}

	/**
	 * A handler function for obstacle Should be at the end of render function
	 * in derived classes
	 * 
	 * @param long The current time in nanosecond
	 */
	protected void obstacleHandler(long currentTime) {
		// Move the obstacle backward
		this.obstacle.moveObstacleBackward(currentTime);

		// If the obstacle allows to be shot
		if (this.obstacle.isAllowShot()) {
			if (this.bulletList.size() > 0) {
				if (this.obstacle.getCurrentPosition().overlaps(
						this.bulletList.get(0).getCurrentPosition())) {
					// Remove the bullet first
					this.bulletList.remove(0);

					// Decrease this obstacle's blood
					this.obstacle.decreaseBlood();

					// If the obstacle blood is 0
					if (this.obstacle.getBlood() <= 0) {
						// Not allow this obstacle to be overlap
						this.obstacle.setAllowOverlap(false);
						this.obstacle.changeToCollapseTexture();

						// Set the obstacle moving time to the scene moving time
						this.obstacle.setMovingTimeToSceneMovingTime();

						// Play the explosion sound
						this.obstacle.playExplosionSound();
					}
				}
			}
		}

		// Create new obstacle if the last obstacle disappear
		if (this.obstacle.getCurrentX() <= 0 - this.obstacle.getWidth()) {
			this.obstacle = this.createNewObstacle(currentTime);
		}

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
						swipeRightHandler();
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

		// Remove the bullet if it goes out of the screen
		this.removeBullet();
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

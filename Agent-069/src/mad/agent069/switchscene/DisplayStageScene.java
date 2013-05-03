package mad.agent069.switchscene;

import mad.agent069.mainscene.AgentMain;
import mad.agent069.tween.MainSceneAccessor;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class DisplayStageScene implements Screen {

	//Constant
	private static final String STAGE_ATLAS = "switchingscene/Stage.atlas";
	public static final String STAGE_1 = "Stage_1";
	public static final String STAGE_2 = "Stage_2";
	public static final String STAGE_3 = "Stage_3";
	
	//Properties
	private AgentMain agent;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Sprite sprite;
	private TweenManager manager;
	private BitmapFont font;
	private Texture stageTexture;
	private TextureAtlas atlas;
	private Skin skin;

	private String titleName;
	private Screen newScreen;

	/*
	 * Constructor that display the stage name and change to the newScreen
	 * directly
	 */
	public DisplayStageScene(AgentMain agent, String screenNumber, Screen newScreen) {
		this.agent = agent;
		
		titleName = screenNumber;
		this.newScreen = newScreen;
	}

	@Override
	public void render(float delta) {
		//Settings GL color
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		manager.update(delta);
		
		batch.setProjectionMatrix(camera.combined);
		
		/*
		 * BEGIN DRAWING BATCHS
		 */
		
		//Draw the main scene
		batch.begin();
		sprite.draw(batch);
		batch.end();

		/*
		 * END DRAWING BATCHS
		 */
	}

	@Override
	public void resize(int width, int height) {
		
		sprite.setPosition(Gdx.graphics.getWidth()/2 - sprite.getWidth()/2, Gdx.graphics.getHeight()/2 - sprite.getHeight()/2);

	}

	@Override
	public void show() {
		// Camera settings
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// New Batch
		batch = new SpriteBatch();

		// Create font
		font = new BitmapFont(Gdx.files.internal("mainscene/white_font.fnt"), false);
		
		//Create new Atlas and Skin
		atlas = new TextureAtlas(STAGE_ATLAS);
		skin = new Skin();
		skin.addRegions(atlas);
		
		//Create Stage texture
		sprite = skin.getSprite(titleName);
		sprite.setColor(1, 1, 1, 0);
		
		// Calling Tween
		manager = new TweenManager();
		TweenCallback cb = new TweenCallback() {

			@Override
			public void onEvent(int arg0, BaseTween<?> arg1) {
				tweenCompleted();
			}
		};
		
		Tween.registerAccessor(Sprite.class, new MainSceneAccessor());
		Tween.to(sprite, MainSceneAccessor.ALPHA, 1f).target(1).ease(TweenEquations.easeInQuad).repeatYoyo(1, 1f).setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE).start(manager);
		
	}

	private void tweenCompleted() {
		Gdx.app.log("Done", "DONE");
		this.agent.setScreen(this.newScreen);
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

}

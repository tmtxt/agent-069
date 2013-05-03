package mad.agent069.switchscene;

import mad.agent069.Score;
import mad.agent069.cloud.MinHighScore;
import mad.agent069.mainscene.AgentMain;
import mad.agent069.mainscene.MainScene;
import mad.agent069.tween.MainSceneAccessor;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class SwitchScene implements Screen {

	// Constant
	private static final String LOSE_BACKGROUND_PATH = "switchingscene/switch_scene.png";
	private static final String WIN_BACKGROUND_PATH = "switchingscene/switch_scene2.png";
	private static final String UPLOADED_PATH = "switchingscene/uploaded_text.png";
	public static final String WIN_SCENE = "winscene";
	public static final String LOSE_SCENE = "losescene";
	public static String userInput = "";

	private AgentMain agent;
	private OrthographicCamera camera;
	private Stage stage;

	private boolean isUploadded = false;
	private Label scoreTitle;
	private Label scoreLabel;

	private TextButton backMenu;
	private TextButton uploadScore;
	private TextButtonStyle style;
	private String score;
	private String endScene;

	private TweenManager manager;
	private TweenCallback cb;
	private Screen newScreen;
	private TextButton nextLevel;
	private SpriteBatch batch;
	private Sprite backgroundSprite;
	private Sprite uploadedTextSprite;
	private Texture background;
	private Texture uploaded_text;
	private TextureAtlas atlas;
	private Skin skin;
	private BitmapFont normalFont;
	private BitmapFont bigFont;
	private MyTextInputListener listener = new MyTextInputListener();
	private ClickListener uplis;

	public SwitchScene(AgentMain agent, String score, String determineScene,
			Screen newScreen) {
		this.agent = agent;
		this.score = score;
		this.newScreen = newScreen;
		this.endScene = determineScene;
	}

	@Override
	public void render(float delta) {
		// Clear color
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		manager.update(delta);
		batch.setProjectionMatrix(camera.combined);
		stage.act(delta);

		if(isUploadded){
			uploadScore.removeListener(uplis);
			stage.addActor(uploadScore);
			isUploadded = false;
		}
		
		// Begin drawing batch
		batch.begin();
		backgroundSprite.draw(batch);
		batch.end();

		batch.begin();
		stage.draw();
		batch.end();

		batch.begin();
		uploadedTextSprite.draw(batch);
		batch.end();

	}

	@Override
	public void resize(int width, int height) {
		// Create new stage
		if (stage == null) {
			stage = new Stage(width, height, true);
		}
		stage.clear();

		Gdx.input.setInputProcessor(stage);

		// ScoreTitle setting
		scoreTitle
				.setX(Gdx.graphics.getWidth() / 2 - scoreTitle.getWidth() / 2);
		scoreTitle.setY(Gdx.graphics.getHeight() * 8 / 9
				- scoreTitle.getHeight() / 2);

		scoreLabel
				.setX((Gdx.graphics.getWidth() / 2 - scoreLabel.getWidth() / 2));
		scoreLabel.setY(Gdx.graphics.getHeight() * 7 / 9
				- scoreLabel.getHeight() / 2);

		// Back button settings
		backMenu.setX((Gdx.graphics.getWidth() * 1 / 4 - backMenu.getWidth() / 2));
		backMenu.setY(Gdx.graphics.getHeight() * 3 / 9 - backMenu.getHeight()
				/ 2);

		// Upload score
		uploadScore
				.setX((Gdx.graphics.getWidth() * 2 / 4 - backMenu.getWidth() / 2));
		uploadScore.setY(Gdx.graphics.getHeight() * 3 / 9
				- backMenu.getHeight() / 2);

		// Next Level
		nextLevel
				.setX((Gdx.graphics.getWidth() * 3 / 4 - backMenu.getWidth() / 2));
		nextLevel.setY(Gdx.graphics.getHeight() * 3 / 9 - backMenu.getHeight()
				/ 2);

		// Uploaded Text
		// uploadedTextSprite.setSize(512, 32);

		uploadedTextSprite.setX(1024 / 2 - uploadedTextSprite.getWidth() / 2);
		uploadedTextSprite.setY(70);

		// Add to stage
		stage.addActor(nextLevel);
		stage.addActor(scoreTitle);
		stage.addActor(scoreLabel);
		stage.addActor(backMenu);
		stage.addActor(uploadScore);

	}

	@Override
	public void show() {
		// Camera settings
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1024, 512);

		// New batch
		batch = new SpriteBatch();

		// Create atlas
		atlas = new TextureAtlas("mainscene/button_main_scene.atlas");

		// Create a skin
		skin = new Skin();
		skin.addRegions(atlas);

		// New Texture including BACKGROUND and UPLOADED_TEXT
		if(endScene.equals(WIN_SCENE)){
			background = new Texture(WIN_BACKGROUND_PATH);
		}else if(endScene.equals(LOSE_SCENE)){
			background = new Texture(LOSE_BACKGROUND_PATH);
		}
		uploaded_text = new Texture(UPLOADED_PATH);

		// Create font
		bigFont = new BitmapFont(
				Gdx.files.internal("switchingscene/big_white_font.fnt"), false);
		normalFont = new BitmapFont(
				Gdx.files.internal("mainscene/white_font.fnt"), false);

		// New Sprite
		backgroundSprite = new Sprite(background);
		backgroundSprite.setColor(1, 1, 1, 1);
		uploadedTextSprite = new Sprite(uploaded_text);
		uploadedTextSprite.setColor(1, 1, 1, 0);

		// Create score title
		LabelStyle ls_normal = new LabelStyle(normalFont, Color.WHITE);
		scoreTitle = new Label("Your Score", ls_normal);
		scoreTitle.setColor(1, 1, 1, 1);

		LabelStyle ls_big = new LabelStyle(bigFont, Color.RED);
		scoreLabel = new Label(score, ls_big);
		scoreLabel.setColor(1, 1, 1, 1);

		// Create button style
		style = new TextButtonStyle();
		style.up = skin.getDrawable("button_up");
		style.down = skin.getDrawable("button_down");
		style.font = normalFont;

		// Create back button
		backMenu = new TextButton("Back to Menu", style);
		backMenu.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				agent.setScreen(new MainScene(agent));
			}
		});

		// Calling Tween
		manager = new TweenManager();
		cb = new TweenCallback() {

			@Override
			public void onEvent(int arg0, BaseTween<?> arg1) {
				tweenCompleted();
			}
		};

		Tween.registerAccessor(Sprite.class, new MainSceneAccessor());

		uploadScore = new TextButton("Upload Score", style);
		uplis = new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.input.getTextInput(listener, "Enter your username", "");
			}
		};
		
		uploadScore.addListener(uplis);

		// Set The right button
		if (endScene.equals(WIN_SCENE)) {
			nextLevel = new TextButton("Next level", style);
		} else if (endScene.equals(LOSE_SCENE)) {
			nextLevel = new TextButton("Retry", style);
		}
		nextLevel.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				agent.setScreen(newScreen);
			}
		});

	}

	private void tweenCompleted() {
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		stage.clear();

		// this.batch.dispose();
		// this.skin.dispose();
		// this.atlas.dispose();
		// this.normalFont.dispose();
		// this.bigFont.dispose();
		// this.stage.dispose();
		// this.background.dispose();
	}

	private void showUploadedText() {
		Tween.to(uploadedTextSprite, MainSceneAccessor.ALPHA, 1f)
		.target(1).ease(TweenEquations.easeInQuad)
		.repeatYoyo(1, 1f).setCallback(cb)
		.setCallbackTriggers(TweenCallback.COMPLETE)
		.start(manager);	
	}

	class MyTextInputListener implements TextInputListener {

		@Override
		public void input(String text) {
			userInput = text;
			showUploadedText();
			isUploadded = true;
			
			String username = userInput;
			int score = Integer.parseInt(Score.getScore()+"");

			MinHighScore.processHighscore(username, score);
			
//			if (!result.equals("0")) {
//				System.out.println("NHAN: " + result);
//			}
			
		}

		@Override
		public void canceled() {

		}

	}
}

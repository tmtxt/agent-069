package mad.agent069.switchscene;

import mad.agent069.Scene;
import mad.agent069.mainscene.AgentMain;
import mad.agent069.mainscene.MainScene;

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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class SwitchScene implements Screen {

	// Constant
	private static final String BACKGROUND_PATH = "switchingscene/switch_scene.png";
	public static final String WIN_SCENE = "winscene";
	public static final String LOSE_SCENE = "losescene";

	AgentMain agent;
	OrthographicCamera camera;
	Stage stage;

	Label scoreTitle;
	Label scoreLabel;

	TextButton backMenu;
	TextButton uploadScore;
	TextButtonStyle style;
	String score;
	String endScene;

	Screen newScreen;
	TextButton nextLevel;
	private SpriteBatch batch;
	private Sprite sprite;
	private Texture background;
	private TextureAtlas atlas;
	private Skin skin;
	private BitmapFont normalFont;
	private BitmapFont bigFont;

	public SwitchScene(AgentMain agent, String score, String determineScene, Screen newScreen) {
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

		batch.setProjectionMatrix(camera.combined);
		stage.act(delta);

		// Begin drawing batch
		batch.begin();
		sprite.draw(batch);
		batch.end();

		batch.begin();
		stage.draw();
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

		// Add to stage
		stage.addActor(nextLevel);

		// Add to stage
		stage.addActor(scoreTitle);
		stage.addActor(scoreLabel);
		stage.addActor(backMenu);
		stage.addActor(uploadScore);

	}

	@Override
	public void show() {
		// Camera settings
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		// New batch
		batch = new SpriteBatch();

		// Create atlas
		atlas = new TextureAtlas("mainscene/button_main_scene.atlas");

		// Create a skin
		skin = new Skin();
		skin.addRegions(atlas);

		// New background
		background = new Texture(BACKGROUND_PATH);

		// Create font
		bigFont = new BitmapFont(
				Gdx.files.internal("switchingscene/big_white_font.fnt"), false);
		normalFont = new BitmapFont(
				Gdx.files.internal("mainscene/white_font.fnt"), false);

		// New Sprite
		sprite = new Sprite(background);
		sprite.setColor(1, 1, 1, 1);

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

		uploadScore = new TextButton("Upload Score", style);
		if(endScene.equals(WIN_SCENE)){
			nextLevel = new TextButton("Next level", style);
		}else if(endScene.equals(LOSE_SCENE)){
			nextLevel = new TextButton("Retry", style);
		}
		nextLevel.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				agent.setScreen(newScreen);
			}
		});

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
//		this.batch.dispose();
//		this.skin.dispose();
//		this.atlas.dispose();
//		this.normalFont.dispose();
//		this.bigFont.dispose();
//		this.stage.dispose();
//		this.background.dispose();
	}

}

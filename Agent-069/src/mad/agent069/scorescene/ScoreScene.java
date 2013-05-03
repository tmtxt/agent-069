package mad.agent069.scorescene;

import java.util.ArrayList;

import mad.agent069.cloud.MinHighScore;
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
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ScoreScene implements Screen {

	// Constant
	private static final String BACKGROUND_PATH = "scorescene/score_scene.png";

	//Properties
	private AgentMain agent;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Stage stage;
	private Label scoreTitle;
	private TextButtonStyle style;
	private TextButton backMenu;
	private Screen newScreen;
	private Sprite sprite;
	private Texture background;
	private TextureAtlas atlas;
	private Skin skin;
	private LabelStyle ls_normal_left;
	private LabelStyle ls_normal_right;
	private LabelStyle ls_big;
	private LabelStyle ls_big_title;
	private BitmapFont normalFont;
	private BitmapFont bigFont;
	private Table table;
	
	
	ArrayList<ScoreCoordinate> scoreCor;

	public ScoreScene(AgentMain agent) {
		this.agent = agent;
	}

	@Override
	public void render(float delta) {
		// Clear color
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		stage.act(delta);

		/*
		 * BEGIN DRAWING BATCH AND STAGE
		 */
		batch.begin();
		sprite.draw(batch);
		batch.end();

		batch.begin();
		stage.draw();
//		Table.drawDebug(stage);
		batch.end();

		/*
		 * END DRAWING BATCH AND STAGE
		 */
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
		
		
		//Table setting

		Gdx.app.log("Appear", "Appear");
		table.center();
		table.debug();
		table.setFillParent(true);
		
		//Back menu
		backMenu.setX((Gdx.graphics.getWidth() * 2 / 4 - backMenu.getWidth() / 2));
		backMenu.setY(table.getHeight());
		
		//Add Stage
		stage.addActor(scoreTitle);
		stage.addActor(table);
		stage.addActor(backMenu);

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

		// New background
		background = new Texture(BACKGROUND_PATH);

		// Create font
		normalFont = new BitmapFont(
				Gdx.files.internal("mainscene/white_font.fnt"), false);
		bigFont = new BitmapFont(
				Gdx.files.internal("switchingscene/big_white_font.fnt"), false);

		// New Sprite
		sprite = new Sprite(background);
		sprite.setColor(1, 1, 1, 1);

		// Create score title
		ls_normal_left = new LabelStyle(normalFont, Color.WHITE);
		ls_normal_right = new LabelStyle(normalFont, Color.YELLOW);
		ls_big = new LabelStyle(bigFont, Color.MAGENTA);
		ls_big_title = new LabelStyle(bigFont, Color.GREEN);
		scoreTitle = new Label("Score Table", ls_big);
		scoreTitle.setColor(1, 1, 1, 1);

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
		
		//Create table
		table = new Table();
		showTable();
	}

	private void showTable(){
		ArrayList<String[]> allScore = MinHighScore.getHighscoreList();
		table.columnDefaults(0).pad(5);
		table.columnDefaults(1).pad(5);
		table.add(new Label("Name", ls_big_title));
		table.add(new Label("Score", ls_big_title));
		table.row();
		for(int i =0; i < allScore.size(); i++){
			table.add(new Label(allScore.get(i)[1], ls_normal_left));
			table.add(new Label(allScore.get(i)[0], ls_normal_right));
			table.row();
		}
		
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
	}

	class ScoreCoordinate {
		public int xCor;
		public int yCor;
	}
}

package mad.agent069.scorescene;

import mad.agent069.mainscene.AgentMain;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScoreScene implements Screen {

	AgentMain agent;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	public ScoreScene(AgentMain agent) {
		
	}
	
	@Override
	public void render(float delta) {

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		// Camera settings
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		// New batch
		batch = new SpriteBatch();
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

	}

}

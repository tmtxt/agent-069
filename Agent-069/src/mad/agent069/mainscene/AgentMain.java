package mad.agent069.mainscene;

import mad.agent069.switchscene.SwitchScene;

import com.badlogic.gdx.Game;

public class AgentMain extends Game {
	
	@Override
	public void create() {		
		setScreen(new MainScene(this));
//		setScreen(new SwitchScene(this, "2020", SwitchScene.WIN_SCENE, new SettingScene(this)));
	}

	@Override
	public void dispose() {
		super.dispose();
//		batch.dispose();
//		texture.dispose();
	}

	@Override
	public void render() {		
		super.render();
		
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.pause();
	}
}

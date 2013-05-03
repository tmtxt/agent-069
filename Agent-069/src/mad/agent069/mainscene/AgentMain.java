package mad.agent069.mainscene;

import mad.agent069.music.MyMusic;
import mad.agent069.scorescene.ScoreScene;
import mad.agent069.sound.MySound;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class AgentMain extends Game {

	static Preferences prefs;

	@Override
	public void create() {
		prefs = Gdx.app.getPreferences("Agent_preferences");

		// Get or create preferences to sound volume
		if (prefs.contains("soundVolume")) {
			MySound.sound_volume = prefs.getFloat("soundVolume");
		} else {
			MySound.sound_volume = 0.5f;
			prefs.putFloat("soundVolume", MySound.sound_volume);
			prefs.flush();
		}

		// Get or create preferences to music volume
		if (prefs.contains("musicVolume")) {
			MyMusic.music_volume = prefs.getFloat("musicVolume");
		} else {
			MyMusic.music_volume = 0.5f;
			prefs.putFloat("musicVolume", MyMusic.music_volume);
			prefs.flush();
		}
		
		Gdx.app.log("Music", MyMusic.music_volume + "");

		setScreen(new MainScene(this));
//		setScreen(new ScoreScene(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		// batch.dispose();
		// texture.dispose();
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

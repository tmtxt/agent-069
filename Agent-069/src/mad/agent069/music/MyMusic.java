package mad.agent069.music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MyMusic {

	//Properties
	private static boolean music_enabled = true;
	private static int music_volume = 50;
	
	/*
	 * MUSIC FOR MAIN SCENE
	 */
	public static Music musicMainScene(){
		Music temp = Gdx.audio.newMusic(Gdx.files.internal("music/mainscene_music.mp3"));
		return temp;
	}

	/*
	 * GETTERS - SETTERS
	 */
	public static boolean isMusic_enabled() {
		return music_enabled;
	}

	public static void setMusic_enabled(boolean music_enabled) {
		MyMusic.music_enabled = music_enabled;
	}

	public static int getMusic_volume() {
		return music_volume;
	}

	public static void setMusic_volume(int music_volume) {
		MyMusic.music_volume = music_volume;
	}
	
	
}

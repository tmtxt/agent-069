package mad.agent069.music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MyMusic {

	//Properties
	public static float music_volume = 0.5f;
	
	/*
	 * MUSIC FOR MAIN SCENE
	 */
	public static Music musicMainScene(){
		Music temp = Gdx.audio.newMusic(Gdx.files.internal("music/mainscene_music.mp3"));
		return temp;
	}
	
	/*
	 * MUSIC FOR SETTING SCENE
	 */
	public static Music musicSettingScene(){
		Music temp = Gdx.audio.newMusic(Gdx.files.internal("music/setting_background_music.mp3"));
		return temp;
	}
	
}

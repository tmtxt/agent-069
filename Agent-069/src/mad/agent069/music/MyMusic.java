package mad.agent069.music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MyMusic {

	//Properties
	public static float music_volume;
	
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
	
	
	public static Music musicScene1(){
		Music temp = Gdx.audio.newMusic(Gdx.files.internal("bg1.mp3"));
		return temp;
	}
	
	public static Music musicScene2(){
		Music temp = Gdx.audio.newMusic(Gdx.files.internal("bg1.mp3"));
		return temp;
	}
	
	public static Music musicScene3(){
		Music temp = Gdx.audio.newMusic(Gdx.files.internal("bg1.mp3"));
		return temp;
	}
}

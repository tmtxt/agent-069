package mad.agent069.sound;

public class MySound {

	//Properties
	private static boolean sound_enabled = true;
	private static int sound_volume = 50;
	
	/*
	 * GETTERS - SETTERS
	 */
	public static boolean isSound_enabled() {
		return sound_enabled;
	}
	public static void setSound_enabled(boolean sound_enabled) {
		MySound.sound_enabled = sound_enabled;
	}
	public static int getSound_volume() {
		return sound_volume;
	}
	public static void setSound_volume(int sound_volume) {
		MySound.sound_volume = sound_volume;
	}
	
	
}

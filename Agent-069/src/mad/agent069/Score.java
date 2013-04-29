package mad.agent069;

// Store the score of user
public class Score {
	// The score of the user
	private static long SCORE = 0;
	
	// The start time of the current scene (in millisecond)
	private static long SCENE_START_TIME = 0;
	
	/**
	 * Get the score of the user
	 * @return The score of the user
	 */
	public static long getScore(){
		return Score.SCORE;
	}
	
	/**
	 * Call when a user finish a scene or die
	 * @param currentTime The current time in millisecond
	 */
	public static void finishCurrentScene(long currentTime){
		long gainScore = currentTime - Score.SCENE_START_TIME;
		Score.SCORE += gainScore;
	}
	
	/**
	 * Call when user pause game
	 * @param currentTime The current time in millisecond
	 */
	public static void pauseCurrentScene(long currentTime){
		Score.finishCurrentScene(currentTime);
	}
	
	/**
	 * Call when use resume from pause
	 * @param currentTime The current time in millisecond
	 */
	public static void resumeCurrentScene(long currentTime){
		Score.startNewScene(currentTime);
	}

	/**
	 * Call when user start a new scene
	 * @param currentTime The current time in millisecond
	 */
	public static void startNewScene(long currentTime){
		Score.SCENE_START_TIME = currentTime;
	}
	
	/**
	 * Call to reset the score, should be called when start the first scene
	 */
	public static void resetScore(){
		Score.SCORE = 0;
	}
}

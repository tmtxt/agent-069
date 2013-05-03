package mad.agent069.cloud;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class UploadScoreListener extends ClickListener {
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		String username = getUsername();
		int score = getScore();

		String result = MinHighScore.processHighscore(username, score);
		if (!result.equals("0")) {
			System.out.println("NHAN: " + result);
		}

	}

	public String getUsername() {
		return "Nhan";
	}

	private int getScore() {
		return 3002;
	}

	
}

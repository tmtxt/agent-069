package mad.agent069;

import mad.agent069.mainscene.MainScene;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Agent-069";
		cfg.useGL20 = false;
		cfg.width = Scene.SCENE_WIDTH;
		cfg.height = Scene.SCENE_HEIGHT;
		
		new LwjglApplication(new MainScene(), cfg);
	}
}

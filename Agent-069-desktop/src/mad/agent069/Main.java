package mad.agent069;

import mad.agent069.mainscene.AgentMain;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Agent-069";
		cfg.useGL20 = false;
		cfg.width = 1000;
		cfg.height = 700;
		
		new LwjglApplication(new AgentMain(), cfg);
	}
}

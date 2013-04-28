package mad.agent069;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

// Class for storing obstacle texture
public class ObstacleTexture {
	public static final Texture FIRE_OBSTACLE_TEXTURE = new Texture(Gdx.files.internal("fire.png"));
	public static final Texture HELI_OBSTACLE_TEXTURE = new Texture(Gdx.files.internal("heli.png"));
	public static final Texture ROCK_OBSTACLE_TEXTURE = new Texture(Gdx.files.internal("stone.png"));
	public static final Texture TANK_OBSTACLE_TEXTURE = new Texture(Gdx.files.internal("tank.png"));
	public static final Texture TANK_COLLAPSE_OBSTACLE_TEXTURE = new Texture(Gdx.files.internal("tankcollapse.png"));
	public static final Texture THORNS_OBSTACLE_TEXTURE = new Texture(Gdx.files.internal("thorns.png"));
}

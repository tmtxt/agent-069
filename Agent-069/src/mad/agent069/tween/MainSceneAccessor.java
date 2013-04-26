package mad.agent069.tween;

import aurelienribon.tweenengine.TweenAccessor;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class MainSceneAccessor implements TweenAccessor<Sprite>{

	public static final int ALPHA = 1;
	
	@Override
	public int getValues(Sprite arg0, int arg1, float[] arg2) {
		switch(arg1){
		case ALPHA:
			arg2[0] = arg0.getColor().a;
			return 1;
		default:
			return 0;
		}
	}

	@Override
	public void setValues(Sprite arg0, int arg1, float[] arg2) {
		switch (arg1){
		case ALPHA:
			arg0.setColor(1,1,1,arg2[0]);
			break;
		}
		
	}

}

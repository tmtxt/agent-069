package com.me.mygdxgame;

import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;

public class CopyOfRepeatedBackground2 implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture background;
	private Texture bucketImage;
	private Texture sky;
	
	private float currentBgX;
	private float currentBucketY;
	private long lastTimeBg;
	
	private float currentSkyX;
	private long lastTimeSky;
	
	private Sprite sprite1, sprite2;
	
	@Override
	public void create() {		
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();
		
		background = new Texture(Gdx.files.internal("factory.png"));
		currentBgX = 800;
		currentBucketY = 20;
		lastTimeBg = TimeUtils.nanoTime();
		
		sprite1 = new Sprite(background);
		sprite1.setSize(480, 1920);
		sprite1.setPosition(currentBgX, 0);
		
		sprite2 = new Sprite(background);
		sprite2.setSize(480, 1920);
		sprite2.setPosition(currentBgX - 1920, 0);
		
		sky = new Texture(Gdx.files.internal("bluesky.png"));
		currentSkyX = 800;
		lastTimeSky = TimeUtils.nanoTime();
		
		bucketImage = new Texture(Gdx.files.internal("bucket.png"));
		
	}

	@Override
	public void dispose() {
		batch.dispose();
		background.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		sprite1.draw(batch);
		sprite2.draw(batch);
//		batch.draw(bucketImage, 20, currentBucketY);
		batch.end();
		
		long nanoTime = TimeUtils.nanoTime();
		
		if(nanoTime - lastTimeSky > 30000000){
			currentSkyX -= 5;
			lastTimeSky = nanoTime;
		}
		
		if(nanoTime - lastTimeBg > 10000000){
			currentBgX -= 10;
			sprite1.setPosition(currentBgX, 0);
			sprite2.setPosition(currentBgX - 1920, 0);
			lastTimeBg = nanoTime;
//			if(currentBucketY == 20)
//				currentBucketY += 10;
//			else
//				currentBucketY -= 10;
		}
		
		if(currentSkyX <= 800-1920){
			currentSkyX = 800;
		}
		
		if(currentBgX <= 800-1920){
			currentBgX = 800;
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}

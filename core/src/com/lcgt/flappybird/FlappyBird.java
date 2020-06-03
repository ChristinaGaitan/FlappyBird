package com.lcgt.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlappyBird extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;

	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("bg.png");
	}

	@Override
	public void render () {
		Integer screenWidth = Gdx.graphics.getWidth();
		Integer screenHeight = Gdx.graphics.getHeight();

		batch.begin();
		batch.draw(background, 0, 0, screenWidth, screenHeight);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
	}
}

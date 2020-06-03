package com.lcgt.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlappyBird extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture bird;

	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("bg.png");
		bird = new Texture("bird.png");
	}

	@Override
	public void render () {
		Integer screenwidth = Gdx.graphics.getWidth();
		Integer height = Gdx.graphics.getHeight();

		batch.begin();
		batch.draw(background, 0, 0, width, height);
		batch.draw(background, width / 2 , height / 2);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
		bird.dispose();
	}
}

package com.lcgt.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlappyBird extends ApplicationAdapter {
    SpriteBatch batch;
    Texture background;

    Texture[] birds;
    int flpstate = 0;

    @Override
    public void create () {
        batch = new SpriteBatch();
        background = new Texture("bg.png");
        birds = new Texture[2];
        birds[0] = new Texture("bird.png");
        birds[1] = new Texture("bird2.png");
    }

    @Override
    public void render () {

        if (flpstate == 0) {
            flpstate = 1;
        } else {
            flpstate = 0;
        }

        Integer screenWidth = Gdx.graphics.getWidth();
        Integer screenHeight = Gdx.graphics.getHeight();
        Integer birdWidth = birds[flpstate].getWidth();
        Integer birdHeight = birds[flpstate].getHeight();

        batch.begin();
        batch.draw(background, 0, 0, screenWidth, screenHeight);
        batch.draw(birds[flpstate], screenWidth / 2 - birdWidth / 2, screenHeight / 2 - birdHeight / 2);
        batch.end();
    }

    @Override
    public void dispose () {
        batch.dispose();
        background.dispose();
        birds[0].dispose();
        birds[1].dispose();
    }
}

package com.lcgt.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlappyBird extends ApplicationAdapter {
    SpriteBatch batch;
    Texture background;
    float screenWidth = 0;
    float screenHeight = 0;

    Texture[] birds;
    int flpstate = 0;
    float birdY = 0;
    float birdX = 0;
    float velocity = 0;

    int gameState = 0;

    @Override
    public void create () {
        batch = new SpriteBatch();
        background = new Texture("bg.png");
        birds = new Texture[2];
        birds[0] = new Texture("bird.png");
        birds[1] = new Texture("bird2.png");

        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        float birdWidth = birds[0].getWidth();
        float birdHeight = birds[0].getHeight();
        birdX = screenWidth / 2 - birdWidth / 2;
        birdY = screenHeight / 2 - birdHeight / 2;
    }

    @Override
    public void render () {
        if (Gdx.input.justTouched()) {
            gameState = 1;
            Gdx.app.log("Touched", "Yes");
        }

        if (gameState != 0) {
            velocity++;
            birdY -= velocity;
        } else {
            if (Gdx.input.justTouched()) {
                gameState = 1;
                Gdx.app.log("Touched", "Yes");
            }
        }

        if (flpstate == 0) {
            flpstate = 1;
        } else {
            flpstate = 0;
        }

        batch.begin();
        batch.draw(background, 0, 0, screenWidth, screenHeight);
        batch.draw(birds[flpstate], birdX, birdY);
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

package com.lcgt.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

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

    Texture topTube;
    Texture bottomTube;
    float topTubeX = 0;
    float topTubeY = 0;
    float bottomTubeX = 0;
    float bottomTubeY = 0;

    Random randomGenerator;
    float gap = 400;
    float maxTubeOffset = 0;
    float tubeOffset = 0;

    float tubeVelocity = 4;
    float tubeX;

    @Override
    public void create () {
        batch = new SpriteBatch();
        background = new Texture("bg.png");
        birds = new Texture[2];
        birds[0] = new Texture("bird.png");
        birds[1] = new Texture("bird2.png");

        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");

        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        float birdWidth = birds[0].getWidth();
        float birdHeight = birds[0].getHeight();
        birdX = screenWidth / 2 - birdWidth / 2;
        birdY = screenHeight / 2 - birdHeight / 2;


        topTubeY = screenHeight / 2 + gap / 2;
        bottomTubeY = (screenHeight / 2) - (gap / 2) - bottomTube.getHeight();

        maxTubeOffset = screenHeight - (gap / 2) - 100;
        randomGenerator = new Random();
    }

    @Override
    public void render () {
        batch.begin();
        batch.draw(background, 0, 0, screenWidth, screenHeight);

        if (gameState != 0) {
            // Game has started
            if (Gdx.input.justTouched()) {
                velocity = -30;

                tubeOffset = (randomGenerator.nextFloat() - 0.5f) * (screenHeight - gap - 200);
                tubeX = screenWidth / 2 - topTube.getWidth() / 2;
            }
            tubeX = tubeX - 4;

            batch.draw(topTube, tubeX, topTubeY + tubeOffset);
            batch.draw(bottomTube, tubeX, bottomTubeY + tubeOffset);


            if (birdY > 0 || velocity < 0) {
                velocity++;
                birdY -= velocity;
            }
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

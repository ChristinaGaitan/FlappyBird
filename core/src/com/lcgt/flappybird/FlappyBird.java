package com.lcgt.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

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
    float topTubeY = 0;
    float bottomTubeY = 0;

    Random randomGenerator;
    float gap = 400;
    float maxTubeOffset = 0;


    int tubeVelocity = 4;

    int numberOfTubes = 4;
    float[] tubeX = new float[numberOfTubes];
    float[] tubeOffset = new float[numberOfTubes];
    float distanceBetweenTubes;

    Circle birdCircle;
//    ShapeRenderer shapeRenderer;
    Rectangle[] topTubeRectangles;
    Rectangle[] bottomTubeRectangles;

    int score = 0;
    int scoringTube = 0;

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

        distanceBetweenTubes = screenWidth * 3 / 4;

        birdCircle = new Circle();
//        shapeRenderer = new ShapeRenderer();
        topTubeRectangles = new Rectangle[numberOfTubes];
        bottomTubeRectangles = new Rectangle[numberOfTubes];

        for(int i=0; i < numberOfTubes; i++) {
            tubeOffset[i] = (randomGenerator.nextFloat() - 0.5f) * (screenHeight - gap - 200);
            tubeX[i] = screenWidth / 2 - topTube.getWidth() / 2 + screenWidth + i * distanceBetweenTubes;
            topTubeRectangles[i] = new Rectangle();
            bottomTubeRectangles[i] = new Rectangle();
        }
    }

    @Override
    public void render () {
        batch.begin();
        batch.draw(background, 0, 0, screenWidth, screenHeight);

        if(tubeX[scoringTube] < screenWidth / 2) {
            score++;
            Gdx.app.log("Score", String.valueOf(score));

            if(scoringTube < numberOfTubes-1) {
                scoringTube++;
            } else {
                scoringTube = 0;
            }
        }

        if (gameState != 0) {
            // Game has started
            if (Gdx.input.justTouched()) {
                velocity = -30;
            }

            for(int i=0; i < numberOfTubes; i++) {

                if(tubeX[i] < - topTube.getWidth()) {
                    // Last tube is at the edge of the screen
                    tubeOffset[i] = (randomGenerator.nextFloat() - 0.5f) * (screenHeight - gap - 200);
                    tubeX[i] = numberOfTubes * distanceBetweenTubes;
                } else {

                    tubeX[i] = tubeX[i] - tubeVelocity;
                }

                batch.draw(topTube, tubeX[i], topTubeY + tubeOffset[i]);
                batch.draw(bottomTube, tubeX[i], bottomTubeY + tubeOffset[i]);

                topTubeRectangles[i] = new Rectangle(tubeX[i], topTubeY + tubeOffset[i], topTube.getWidth(), topTube.getHeight());
                topTubeRectangles[i] = new Rectangle(tubeX[i], bottomTubeY + tubeOffset[i], bottomTube.getWidth(), bottomTube.getHeight());
            }

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

        birdCircle.set(screenWidth / 2, birdY + birds[flpstate].getHeight() / 2, birds[flpstate].getWidth() / 2);

//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        shapeRenderer.setColor(Color.RED);
//        shapeRenderer.circle(birdCircle.x, birdCircle.y, birdCircle.radius);

        for(int i=0; i < numberOfTubes; i++) {

//            shapeRenderer.rect(tubeX[i], topTubeY + tubeOffset[i], topTube.getWidth(), topTube.getHeight());
//            shapeRenderer.rect(tubeX[i], bottomTubeY + tubeOffset[i], bottomTube.getWidth(), bottomTube.getHeight());

            if(Intersector.overlaps(birdCircle, topTubeRectangles[i]) || Intersector.overlaps(birdCircle, bottomTubeRectangles[i])) {
                Gdx.app.log("Collision", "Yes");
            }

        }

//        shapeRenderer.end();

    }

    @Override
    public void dispose () {
        batch.dispose();
        background.dispose();
        birds[0].dispose();
        birds[1].dispose();
    }
}

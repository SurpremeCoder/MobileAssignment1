package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.badlogic.gdx.graphics.g3d.particles.ParticleChannels.TextureRegion;

public class GameScreen implements Screen {
    private MyGdxGame controller;
    Texture piecesTexture;
    Sprite ball;
    Sprite paddle;
    Sprite brick;
    Brick bricks[] = new Brick[20];
    // constructor to keep a reference to the main Game class
    public GameScreen(MyGdxGame game){

        this.controller = game;
        piecesTexture=new Texture("breakout_pieces.png");
        brick = new Sprite(piecesTexture, 0, 0, 40, 25);
        int height=Gdx.graphics.getHeight();
        int width=Gdx.graphics.getWidth();


        brick.setPosition(10, 10);
        controller.batch.begin();
        int brickY=16;
        for(int i=0; i<4; i++){
            int brickX=0;
            for (int b=0; b<20; b++){

                bricks[bricks.length-1]=new Brick(brickX, brickY, true);
                brick.draw(controller.batch);
                brickX+=40;

            }
            brickY+=25;

        }
        controller.batch.end();
        //ball.setRotation(45);
        /*
        ball = new Sprite(texture, 20, 20, 50, 50);
        ball.setPosition(10, 10);
        ball.setRotation(45);
        */
        //TextureRegion(pieces, int x, int y, int width, int height)

    }
    public void create() {
        Gdx.app.log("MenuScreen: ","menuScreen create");
    }
    public void render(float f) {
        Gdx.app.log("MenuScreen: ","menuScreen render");
        //Gdx.app.log("MenuScreen: ","About to call gameScreen");
       Gdx.gl.glClearColor(1,0,0,1);
       Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       controller.batch.begin();

      // controller.batch.draw(texture, 0,0);
       controller.batch.end();
        Gdx.app.log("MenuScreen: ","gameScreen started");
    }


    @Override
    public void dispose() { }
    @Override
    public void resize(int width, int height) { }
    @Override
    public void pause() { }
    @Override
    public void resume() { }
    @Override
    public void show() {
        Gdx.app.log("MenuScreen: ","menuScreen show called");
        create();
    }
    @Override
    public void hide() {
        Gdx.app.log("MenuScreen: ","menuScreen hide called");
    }
}
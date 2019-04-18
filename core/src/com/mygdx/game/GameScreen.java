package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {
    private MyGdxGame controller;
    Texture texture;
    // constructor to keep a reference to the main Game class
    public GameScreen(MyGdxGame game){

        this.controller = game;
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
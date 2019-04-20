package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MenuScreen implements Screen {
    MyGdxGame controller;
    // constructor to keep a reference to the main Game class

    private Skin skin;
    private Stage stage;

    private Texture playBtnTexture;
    private Texture exitBtnTexture;


    private final ImageButton playBtn;
    private final ImageButton exitBtn;

    /**
     * Constructor for the MenuScreen
     *
     * Displays the buttons and adds listeners to them
     */
    public MenuScreen(MyGdxGame game){
        this.controller = game;
        stage = new Stage();

        playBtnTexture=new Texture("playBtnActive.png");
        exitBtnTexture=new Texture("exitBtnActive.png");

        Drawable drawablePlayBtn = new TextureRegionDrawable(new TextureRegion(playBtnTexture));
        playBtn = new ImageButton(drawablePlayBtn);
        playBtn.setPosition(Gdx.graphics.getWidth() /2 - 100f, 200);
        playBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.changeScreen("play");
            }
        });

        stage.addActor(playBtn);
        Drawable drawableExitBtn = new TextureRegionDrawable(new TextureRegion(exitBtnTexture));
        exitBtn = new ImageButton(drawableExitBtn);
        exitBtn.setPosition(Gdx.graphics.getWidth() /2 - 100f, 100);
        exitBtn.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();

                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });
        stage.addActor(exitBtn);

    }
    public void create() {

        Gdx.input.setInputProcessor(stage);
    }
    public void render(float f) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        controller.batch.begin();
        stage.draw();
        controller.batch.end();
    }
    @Override
    public void dispose() {
        playBtnTexture.dispose();
        exitBtnTexture.dispose();
        stage.dispose();
        Gdx.input.setInputProcessor(null);
    }
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
        Gdx.input.setInputProcessor(null);
        Gdx.app.log("MenuScreen: ","menuScreen hide called");
    }
}
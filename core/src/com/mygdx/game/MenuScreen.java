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
    //OrthographicCamera camera;

    private Texture playBtnTexture;
    //private Texture playBtnInActiveTexture;
    private Texture exitBtnTexture;
    //private Texture exitBtnInactiveTexture;
    //private Button playBtn;
    //private Button exitBtn;

    private final ImageButton playBtn;
    private final ImageButton exitBtn;

    public MenuScreen(MyGdxGame game){
        this.controller = game;
        stage = new Stage();
        //float buttonSize = 0.2f * 0.2f;

        playBtnTexture=new Texture("playBtnActive.png");
        exitBtnTexture=new Texture("exitBtnActive.png");
        //playBtn=new Texture("playBtn.png");
        //exitBtn=new Texture("exitBtn.png");

        Drawable drawablePlayBtn = new TextureRegionDrawable(new TextureRegion(playBtnTexture));
        playBtn = new ImageButton(drawablePlayBtn);
        playBtn.setPosition(Gdx.graphics.getWidth() /2 - 100f, 200);
        playBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.changeScreen("play");
            }
        });
        /*
        playBtn.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //MenuScreen menuScreen = new MenuScreen(game);
                //game.setScreen(menuScreen);
                //game.gotoGameScreen();
                //super.g
                game.changeScreen("menu");

                //game.getScreen();
                Gdx.app.log("MenuScreen: ","bbbbb");
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        }); */
        stage.addActor(playBtn);
        Drawable drawableExitBtn = new TextureRegionDrawable(new TextureRegion(exitBtnTexture));
        exitBtn = new ImageButton(drawableExitBtn);
        exitBtn.setPosition(Gdx.graphics.getWidth() /2 - 100f, 100);
        exitBtn.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //MenuScreen menuScreen = new MenuScreen(game);
                //game.setScreen(menuScreen);
                //game.gotoGameScreen();
                //super.g

                Gdx.app.exit();
                //game.getScreen();

                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });
        stage.addActor(exitBtn);

        //playBtn=new ImageButton()
        //playBtn=new Button(10f,20f,30f,40f,playBtnActiveTexture, playBtnInActiveTexture);
        //playBtn=new TextButton()
        //playBtn=new Button(playBtnActiveTexture, playBtnInActiveTexture);
        //playBtn=new Button(0.0f,buttonSize,buttonSize,buttonSize,playBtnActiveTexture, playBtnInActiveTexture);
        //playBtn = new Button(100f, 20f, 100f, 100f, playBtnActiveTexture, playBtnInActiveTexture);
        //exitBtn = new Button(0.0f, 200, 100, 100, exitBtnActiveTexture, exitBtnInActiveTexture);


        /*
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 640, 480);
        Button startBtn = new TextButton("START GAME",mySkin,"small");
        startBtn.setSize(100,100);
        startBtn.setPosition(50,50);
        //startBtn.setPosition(GameConstants.centerX - startBtn.getWidth()/2,GameConstants.centerY);
        startBtn.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                MenuScreen menuScreen = new MenuScreen(game);
                setScreen(menuScreen);
                //game.gotoGameScreen();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });
        */
    }
    public void create() {
        /*
        skin = new Skin(Gdx.files.internal("gui/uiskin.json"));
        final TextButton button = new TextButton("Click me", skin, "default");
        button.setWidth(600f);
        button.setHeight(400f);
        button.setPosition(Gdx.graphics.getWidth() /2 - 100f, Gdx.graphics.getHeight()/2 - 10f);

        /*
        Gdx.app.log("MenuScreen: ","menuScreen create");
        batch = new SpriteBatch();
        skin = new Skin(Gdx.files.internal("gui/uiskin.json"));
        stage = new Stage();

        final TextButton playButton = new TextButton("Play", skin, "default");
        playButton.setWidth(100f);
        playButton.setHeight(100f);
        //playButton.setPosition(Gdx.graphics.getWidth() /2 - 100f, Gdx.graphics.getHeight()/2 - 10f);
        playButton.setPosition(Gdx.graphics.getWidth() /2 - 100f, Gdx.graphics.getHeight()/2 - 10f);
        playButton.getLabel().setFontScale(200,200);
        playButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen(game));
            }
        });

        final TextButton exitButton = new TextButton("Exit", skin, "default");
        exitButton.setWidth(100f);
        exitButton.setHeight(100f);
        exitButton.setPosition(Gdx.graphics.getWidth() /2 - 200f, Gdx.graphics.getHeight()/2 - 100f);
        exitButton.getLabel().setFontScale(200,200);

        stage.addActor(playButton);
        stage.addActor(exitButton);*/

        Gdx.input.setInputProcessor(stage);
    }
    public void render(float f) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        controller.batch.begin();
        stage.draw();
        //game.batch.draw(playBtn, Gdx.graphics.getWidth()/2-100, 300);
        //game.batch.draw(exitBtn,Gdx.graphics.getWidth()/2-100,200);
        controller.batch.end();
        /*


        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }*/

    }
    @Override
    public void dispose() {
        playBtnTexture.dispose();
        exitBtnTexture.dispose();
        stage.dispose();
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
        Gdx.app.log("MenuScreen: ","menuScreen hide called");
    }
}
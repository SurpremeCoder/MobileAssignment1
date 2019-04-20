package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

import static com.badlogic.gdx.graphics.g3d.particles.ParticleChannels.TextureRegion;

public class GameScreen implements Screen {
    private MyGdxGame controller;
    Texture piecesTexture;
    Sprite ball;
    Sprite paddle;
    Brick bricks[] = new Brick[80];
    OrthographicCamera camera;
    boolean gameStarted=false;
    Stage stage;
    float dt;
    boolean ballMoving=false;
    float movementFactor=2f;
    Vector2 ballVector;
    boolean firstMove=true;

    public GameScreen(MyGdxGame game){

        this.controller = game;
        int height=Gdx.graphics.getHeight();
        int width=Gdx.graphics.getWidth();
        stage = new Stage();
        piecesTexture=new Texture("breakout_pieces.png");

        //ball.setRotation(45);
        /*
        ball = new Sprite(texture, 20, 20, 50, 50);
        ball.setPosition(10, 10);
        ball.setRotation(45);
        */
        //TextureRegion(pieces, int x, int y, int width, int height)
        stage.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("GameScreenxxxx: ","game started");
                gameStarted=true;
                return true;
            }
        });

    }
    public void create() {
        Gdx.app.log("GameScreen: ","create");
        //Gdx.input.setInputProcessor();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 640, 480);
        paddle=new Sprite(piecesTexture, 40, 0, 80, 25);
        ball=new Sprite(piecesTexture, 40, 30, 40, 25);
        int brickY=380;
        int count=1;
        int brickX=0;
        for (int b=0; b<bricks.length; b++){
            bricks[b]=new Brick(brickX, brickY, new Sprite(piecesTexture, 0, 0, 39, 25),true);
            if(count==20){
                count=1;
                brickY+=15;
                brickX=0;
            }else{
                count++;
                brickX+=31;
            }
        }
        Gdx.input.setInputProcessor(stage);
       // stage.addActor(paddle);
    }

    public Vector2 ballPath(){
        double choice=Math.random();
        Vector2 vector;
        if(choice<0.5){
            vector=new Vector2(2,2);
        }else{
            vector=new Vector2(-2,2);
        }
        return vector;
    }

    public void render(float f) {
        Gdx.app.log("GameScreen: ","GameScreen render");

        dt = Gdx.graphics.getDeltaTime(); //delta time
       Gdx.gl.glClearColor(1,0,0,1);
       Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        controller.batch.setProjectionMatrix(camera.combined);
       controller.batch.begin();
        //Gdx.app.log("GameScreen: ","posX" + bricks[0].getPosX() + " posY" + bricks[0].getPosY());
       //Sprite gg=bricks[10].getSprite();
       //gg.draw(controller.batch);
        //bricks[0].getSprite().draw(controller.batch);

        /*
        @Override public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            // ignore if its not left mouse button or first touch pointer
            if (button != Input.Buttons.LEFT || pointer > 0) return false;
            camera.unproject(tp.set(screenX, screenY, 0));
           // dragging = true;
            return true;
        } */

        Gdx.app.log("GameScreen: ","mouseX" + Gdx.input.getX());
       for(int i=0; i<bricks.length;i++){
           if(bricks[i].getStatus()) {
               bricks[i].getSprite().draw(controller.batch);
           }
       }
        paddle.setPosition(Gdx.input.getX()-30, 32);
        paddle.draw(controller.batch);
       if(gameStarted){
           Gdx.app.log("GameScreen: ","STARTED");
           if(firstMove){
               ballVector=new Vector2(paddle.getX()+10,paddle.getY()+10);
               firstMove=false;
               ballMoving=true;
           }else {
               if (!ballMoving) {
                   ballVector = ballPath();
                   ball.setPosition(ballVector.x, ballVector.y);
                   ballMoving = true;
                   Gdx.app.log("GameScreen: ", "now moving" + ballVector.x + " y" + ballVector.y);
               } else {
                   float x = ballVector.x + movementFactor;
                   float y = ballVector.y + movementFactor;
                   ball.setPosition(x, y);
                   movementFactor += 2;
                   Gdx.app.log("GameScreen: ", "x" + x + " y " + y);
               }
           }

           /*
           if (playerSprite.getBoundingRectangle().overlaps(
                   goalSprite.getBoundingRectangle())) {
//Player has won!
               gameState = GameState.COMPLETE;
           }*/
       }else{
           ball.setPosition(paddle.getX()+1,paddle.getY()+1);
       }
        ball.draw(controller.batch);
       //ball.setPosition()
        //paddle.setPosition(Gdx.graphics.getWidth()/2-20,32);

        controller.batch.end();

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
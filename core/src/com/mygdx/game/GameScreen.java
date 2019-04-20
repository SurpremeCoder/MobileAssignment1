package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import static com.badlogic.gdx.graphics.g3d.particles.ParticleChannels.TextureRegion;

/**
 * This class handles and displays the game
 * @param firstMove - Whether this the first move of the ball from the paddle
 * @param xDirection - The x direction of the ball
 * @param xDirection - The y direction of the ball
 * @param score - the total score based on how many bricks have been broken
 * @param totalBricks - The amount of bricks left
 * @param bricks - An array containing all the bricks in the game
 *
 */
public class GameScreen implements Screen {
    private MyGdxGame controller;
    Texture piecesTexture;
    Texture gameOverTexture;
    Texture quitBtnTexture;
    Sprite ball;
    Sprite paddle;
    Sprite gameOver;
    Brick bricks[] = new Brick[80];
    OrthographicCamera camera;
    Stage stage;
    float dt;
    boolean ballMoving=false;
    float movementFactor=3f;
    Vector2 ballVector;
    boolean firstMove=true;
    char xDirection='+';
    char yDirection='+';
    BitmapFont scoreFont = new BitmapFont();
    int score=0;
    int totalBricks=80;

    ImageButton retryBtn;
    ImageButton quitBtn;


    GameStatus gameState;

    enum GameStatus {
        STARTED,
        NOT_STARTED,
        FINISHED,
        PAUSED
    }

    public GameScreen(MyGdxGame game){

        this.controller = game;
        int height=Gdx.graphics.getHeight();
        int width=Gdx.graphics.getWidth();
        stage = new Stage();
        piecesTexture=new Texture("breakout_pieces.png");
        gameOverTexture=new Texture("gameOver.png");
        quitBtnTexture=new Texture("quit.png");
        gameState=GameStatus.NOT_STARTED;

        stage.addListener(new InputListener(){ //Detects if mouse has been clicked
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("GameScreenxxxx: ","game started");
                gameState=GameStatus.STARTED;
                return true;
            }
        });

        Drawable drawableExitBtn = new TextureRegionDrawable(new TextureRegion(quitBtnTexture));
        quitBtn = new ImageButton(drawableExitBtn);
        quitBtn.setPosition(Gdx.graphics.getWidth() /2 - 100f, 100);
        quitBtn.addListener(new InputListener(){
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

    }

    /**
     * Sets up the game including add all the bricks
     */
    public void create() {
        Gdx.app.log("GameScreen: ","create");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 640, 480);
        paddle=new Sprite(piecesTexture, 40, 0, 80, 25);
        ball=new Sprite(piecesTexture, 40, 30, 40, 10);
        gameOver=new Sprite(gameOverTexture, 0, 0, 300, 100);
        gameOver.setPosition(200, 400);
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
    }

    /**
     *
     * @return The calculated vector of where the ball should randomly go.
     */
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

    /**
     * Detects any collisions of the ball with the walls, with bricks or with the paddles
     */
    public void calculateCollision(){
        boolean hitBrick=false;
        for(int i=0; i<bricks.length; i++){
            if(bricks[i].getStatus()==true){
                if (bricks[i].getSprite().getBoundingRectangle().overlaps(
                        ball.getBoundingRectangle())) {
                    bricks[i].setStatus(false);
                    hitBrick=true;
                    totalBricks--;
                    score++;
                    break;
                }
            }
        }
        if(hitBrick){
            xDirection = randomDirection();
            yDirection = '-';
            ballPath();
        }
        else{
            boolean hitPaddle=false;
            if (ball.getBoundingRectangle().overlaps(
                    paddle.getBoundingRectangle())) {
                Gdx.app.log("hit paddle: ", "overlaps");
                hitPaddle=true;
            }
            if (hitPaddle){
                xDirection=randomDirection();
                yDirection='+';
                ballPath();
            }else{
                if (ballVector.x < 0) {
                    Gdx.app.log("vvv: ", "ball outside left");
                    xDirection = '+';
                    yDirection = randomDirection();
                    ballPath();
                } else if (ballVector.x > 640) {
                    Gdx.app.log("vvv: ", "ball outside right");
                    xDirection = '-';
                    yDirection = randomDirection();
                    //double direction=Math.random();
                    ballPath();
                } else if (ballVector.y < 0) {
                    Gdx.app.log("vvv: ", "ball outside bottom");
                    gameState=GameStatus.FINISHED;
                   // paddle
                    //ballPath();
                } else if (ballVector.y > 480) {
                    Gdx.app.log("vvv: ", "ball outside top");
                    xDirection = randomDirection();
                    yDirection = '-';
                    ballPath();
                }
            }
        }
    }

    /**
     * Determines after a collision which direction the ball should travel
     *
     * @return A char that represents the direction of the axis
     */
    public char randomDirection(){
        double direction=Math.random();
        if(direction<0.5){
            return '+';
        }else{
            return '-';
        }
    }

    public void render(float f) {
        Gdx.app.log("GameScreen: ","GameScreen render");

        dt = Gdx.graphics.getDeltaTime(); //delta time
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        controller.batch.setProjectionMatrix(camera.combined);
        controller.batch.begin();

        for(int i=0; i<bricks.length;i++){
            Gdx.app.log("brick: " + i," status " + bricks[i].getStatus());
            if(bricks[i].getStatus()==true) {
                bricks[i].getSprite().draw(controller.batch);
            }else{
                Gdx.app.log("GameScreenJJJJJJJ: ","brick deleted!");
            }
        }

        if(gameState!=GameStatus.FINISHED || gameState!=GameStatus.PAUSED) { //make the paddle move with the mouse horizontally
            Gdx.app.log("GameScreenujjj: ","state is " + gameState);
            paddle.setPosition(Gdx.input.getX() - 30, 32);
        }
        paddle.draw(controller.batch);
        if(gameState==GameStatus.STARTED){
            Gdx.app.log("GameScreen: ","STARTED");
            if(firstMove){
                ballVector=new Vector2(paddle.getX(),paddle.getY()+10);
                xDirection=randomDirection();
                firstMove=false;
                ballMoving=true;
            }else {
                calculateCollision();
                if (!ballMoving) {
                    ballVector = ballPath();
                    ball.setPosition(ballVector.x, ballVector.y);
                    ballMoving = true;
                    Gdx.app.log("GameScreen: ", "now moving" + ballVector.x + " y" + ballVector.y);
                } else {
                    if(xDirection=='+'){
                        ballVector.x=ballVector.x + movementFactor ;
                    }else{
                        ballVector.x=ballVector.x - movementFactor ;
                    }
                    Gdx.app.log("tGameScreen: ", "y direction" + yDirection);
                    if(yDirection=='+'){
                        ballVector.y = ballVector.y + movementFactor;
                    }else{
                        ballVector.y = ballVector.y - movementFactor;
                    }

                    ball.setPosition(ballVector.x, ballVector.y);
                }
            }
            ball.draw(controller.batch);
        }else if(gameState==GameStatus.NOT_STARTED){
            ball.setPosition(paddle.getX()+1,paddle.getY()+1);
            ball.draw(controller.batch);
        }else if(gameState==GameStatus.PAUSED){

        }
        else{
            Gdx.app.log("tGameScreen: ", "GAME OVER");
            //controller.batch.draw(drawableExitBtn);
            //quitBtn.draw(controller.batch);
            gameOver.draw(controller.batch);

        }
        scoreFont.draw(controller.batch, "Score:" + score + " Bricks Remaining " +  totalBricks, 20, Gdx.graphics.getHeight()-15);
        controller.batch.end();
    }

    @Override
    public void dispose() {
        stage.dispose();
        gameOverTexture.dispose();
        piecesTexture.dispose();
        quitBtnTexture.dispose();
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
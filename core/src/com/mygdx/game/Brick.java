package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/** A class to represent a brick
 *Contains the x and y position of the brick, its status (whether its still alive or not) and its sprite
 */

public class Brick {
    private int posX;
    private int posY;
    private Sprite brickSprite;
    private boolean status;

    Brick(int posX, int posY, Sprite brickSprite, boolean status){
        this.posX=posX;
        this.posY=posY;
        this.brickSprite =brickSprite;
        brickSprite.setPosition(posX, posY);
        this.status=status;
    }

    public int getPosX(){
        return posX;
    }
    public int getPosY(){
        return posY;
    }
    public Sprite getSprite(){
        return brickSprite;
    }
    public boolean getStatus(){
        return status;
    }
    public void setStatus(boolean status){
        this.status=status;
    }
}

package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Brick {
    private int posX;
    private int posY;
    private Sprite brickSprite;
    private boolean status;

    Brick(int posX, int posY, Sprite brickSprite, boolean status){
        //this.posX=posX;
        //this.posY=posY;
        this.posY=50;
        this.posX=50;
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
        status=status;
    }
}

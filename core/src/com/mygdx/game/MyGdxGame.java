package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import sun.font.GraphicComponent;

public class MyGdxGame extends Game implements ApplicationListener {

	public SpriteBatch batch;
	private Game game;

	private MenuScreen menuScreen;
	private GameScreen gameScreen;
	public MyGdxGame(){
		game=this;
	}
	@Override
	public void create() {
		batch=new SpriteBatch();
		this.setScreen(new MenuScreen(this));
		Gdx.app.log("MyGdxGame: ","about to change screen to menuScreen");



		Gdx.app.log("MyGdxGame: "," create");
// Change screens to the menu
		//setScreen(new MenuScreen(this));
		//Gdx.app.log("MyGdxGame: ","changed screen to menuScreen");
	}

	/**
	 Changes the screen shown based on a specified value
	 @param screen the value of the screen that should be shown as a string
	 */
	public void changeScreen(String screen){
		if(screen=="menu"){
			if(menuScreen==null){
				menuScreen=new MenuScreen(this);
			}
			setScreen(menuScreen);
			//Gdx.input.setInputProcessor(menuScreen);
		}else{
			if(gameScreen==null){
				gameScreen=new GameScreen(this);
			}
			setScreen(gameScreen);
		}
	}
	@Override
	public void dispose() {super.dispose();}
	@Override
// this method calls the super class render
// which in turn calls the render of the actual screen being used
	public void render() {
		super.render();
	}
	@Override
	public void resize(int width, int height) { super.resize(width, height);}
	@Override
	public void pause() {
		super.pause();
	}
	@Override
	public void resume() {
		super.resume();
	}
}

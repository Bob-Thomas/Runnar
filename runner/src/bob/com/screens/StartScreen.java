package bob.com.screens;

import bob.com.player.Player;
import bob.com.runner.Runner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StartScreen implements  Screen {
	private SpriteBatch batch;
	private Texture splash;
	private Runner game;
	private float timer;
	public StartScreen(Runner game){
		this.game = game;

	}
	@Override
	public void render(float delta) {
		handleInput();
		
		batch.begin();
		batch.draw(this.splash,0,0, splash.getWidth(), splash.getHeight());
		batch.end();	
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {

		splash = new Texture("data/splash.png");
		batch = new SpriteBatch();
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		
	}
	 private void handleInput()
	    {
	        if(Gdx.input.justTouched())
	        {
	        	Gdx.app.log("ouch", "you hit me ");
	            game.setScreen(this.game.get_play());
	        }
	    }



}

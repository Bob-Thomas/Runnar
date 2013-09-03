package bob.com.screens;

import bob.com.atlas.TextureHelper;
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
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class StartScreen implements  Screen {
	private TextureHelper helper;
	private SpriteBatch batch;
	private AtlasRegion splash;
	private Runner game;
	private float timer;
	
	public StartScreen(Runner game){
		this.game = game;
		this.helper = this.game.getHelper();

	}
	@Override
	public void render(float delta) {
		handleInput();
		
		batch.begin();
		batch.draw(this.splash,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.end();	
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {

		splash = this.helper.getSplash();
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
	            game.setScreen(this.game.get_play());
	        }
	    }



}

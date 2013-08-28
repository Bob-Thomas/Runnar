package bob.com.screens;

import bob.com.atlas.TextureHelper;
import bob.com.player.Player;
import bob.com.runner.Runner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class GameOverScreen implements  Screen {
	private SpriteBatch batch;
	private TextureHelper helper;
	private AtlasRegion gameover;
	private Runner game;
	private float timer;
	public GameOverScreen(Runner game){
		this.game = game;
		this.helper = new TextureHelper();
	}
	@Override
	public void render(float delta) {
		handleInput();
		batch.begin();
		batch.draw(this.gameover,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {

		gameover = this.helper.getGameover();
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
	            game.setScreen(new GameScreen(game));
	        }
	    }



}

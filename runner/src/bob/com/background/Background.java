package bob.com.background;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;

import bob.com.atlas.TextureHelper;
import bob.com.level.WorldRenderer;
import bob.com.runner.Runner;

public class Background {
	private TextureHelper helper;
	private AtlasRegion texture;
	private Vector2 position,position2,position3;
	private Runner game;
	public TextureHelper getHelper() {
		return helper;
	}
	public void setHelper(TextureHelper helper) {
		this.helper = helper;
	}
	public Vector2 getPosition() {
		return position;
	}
	public void setPosition(Vector2 position) {
		this.position = position;
	}
	public Vector2 getPosition2() {
		return position2;
	}
	public void setPosition2(Vector2 position) {
		this.position2 = position;
	}
	public Vector2 getPosition3() {
		return position3;
	}
	public void setPosition3(Vector2 position) {
		this.position3 = position;
	}
	public Background(Vector2 position,Vector2 position2,Vector2 position3,Runner game){
		this.game = game;
		this.position = position;
		this.position2 = position2;
		this.position3 = position3;
		this.helper = this.game.getHelper();
		this.texture = this.helper.getBackground();
		
	}
	public void Draw(WorldRenderer render){
		render.getBatch().draw(texture,position.x,position.y, 500,500);
		render.getBatch().draw(texture,position2.x+500,position2.y, 500,500);
		render.getBatch().draw(texture,position3.x+1000,position3.y, 500,500);

	}
}

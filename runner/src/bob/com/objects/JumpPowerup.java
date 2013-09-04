package bob.com.objects;

import bob.com.atlas.TextureHelper;
import bob.com.level.WorldRenderer;
import bob.com.runner.Runner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class JumpPowerup {
	private AtlasRegion texture;
	private TextureHelper helper;
	private TextureRegion[] regions =new TextureRegion[4];
	private Vector2 position;
	private Rectangle bounds;
	private float timer;
	private int frame;
	private Runner game;
	public AtlasRegion getTexture() {
		return texture;
	}
	public TextureHelper getHelper() {
		return helper;
	}
	public TextureRegion[] getRegions() {
		return regions;
	}
	public Vector2 getPosition() {
		return position;
	}
	public Rectangle getBounds() {
		return bounds;
	}
	public float getTimer() {
		return timer;
	}
	public int getFrame() {
		return frame;
	}
	public JumpPowerup(Vector2 position,Runner game){
		this.game = game;
		this.helper = this.game.getHelper();
		this.texture = this.helper.getDoubleJump();
		this.position = position;
		this.bounds = new Rectangle(position.x,position.y,16,16);
		regions[0] = new TextureRegion(texture, 0,0,32,32);
		regions[1] = new TextureRegion(texture, 32,0,32,32);
		regions[2] = new TextureRegion(texture, 64,0,32,32);
		regions[3] = new TextureRegion(texture, 96,0,32,32);
	}	
	public void Update(float delta){
		timer += Gdx.graphics.getDeltaTime();
		if(timer > 0.11){
			timer = 0;
			frame ++;
		}
		if(frame > 3){
			frame = 0;
		}

	}
	public void Draw(SpriteBatch batch){
		batch.draw(this.regions[frame],position.x,position.y, 16, 16);
	}
}

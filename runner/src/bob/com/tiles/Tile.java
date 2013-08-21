package bob.com.tiles;
import java.util.Map;

import bob.com.level.WorldRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
public class Tile {
	
	public static final float SIZE = 32f;
	private AtlasRegion texture;
	Vector2 	position;
	Rectangle 	topBound,rightBound,box;
	private TextureRegion[]  regions =  new TextureRegion[4];
	private TextureAtlas atlas;
	private int texselect = 0;


	public int getTexselect() {
		return texselect;
	}

	public void setTexselect(int texselect) {
		this.texselect = texselect;
	}

	public TextureRegion[] getRegions() {
		return regions;
	}

	public void setRegions(TextureRegion[] regions) {
		this.regions = regions;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Rectangle get_top() {
		return topBound;
	}
	public Rectangle get_rightSide() {
		return rightBound;
	}
	public AtlasRegion get_Tex(){
		return this.texture;
	}
	public Rectangle get_box(){
		return this.box;
	}
    public void update(float delta){
    	this.topBound.x = this.position.x;
    	this.topBound.y = this.position.y-1;
    	this.rightBound.x = this.position.x;
    	this.rightBound.y = this.position.y-1;
    }
	public Tile(Vector2 pos,int texselect) {
		this.atlas = new TextureAtlas("images/textures/textures.atlas");
		this.texselect = texselect;
		this.texture = atlas.findRegion("blocks");
		this.position = pos;
		this.topBound = new Rectangle(this.position.x,this.position.y,SIZE,6);
		this.rightBound = new Rectangle(this.position.x,this.position.y-SIZE,1f,SIZE-10);
		this.box = new Rectangle(this.position.x,this.position.y,SIZE,SIZE);
		regions[0] = new TextureRegion(texture, 0,0,16,16);
		regions[1] = new TextureRegion(texture, 32,0,16,16);
		regions[2] = new TextureRegion(texture, 64,0,16,16);
		regions[3] = new TextureRegion(texture, 128,0,16,16);
		atlas.findRegion("blocks").setRegion(this.regions[texselect]);

	}
	public void Draw(WorldRenderer render){
		render.getBatch().draw(atlas.findRegion("blocks"),box.x,box.y-32, Tile.SIZE, Tile.SIZE);

	}

}

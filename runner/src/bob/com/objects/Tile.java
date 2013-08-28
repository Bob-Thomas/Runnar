package bob.com.objects;
import java.util.Map;

import bob.com.atlas.TextureHelper;
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
	private TextureHelper helper;
	Vector2 	position;
	Rectangle 	topBound,rightBound,box;
	private AtlasRegion texture;
	private TextureRegion[]  regions =  new TextureRegion[4];
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
		this.helper = new TextureHelper();
		this.texture = this.helper.getBlocks();
		this.texselect = texselect;
		this.position = pos;
		this.topBound = new Rectangle(this.position.x,this.position.y,SIZE,6);
		this.rightBound = new Rectangle(this.position.x,this.position.y-SIZE,1f,SIZE-10);
		this.box = new Rectangle(this.position.x,this.position.y-32,SIZE,SIZE);
		regions[0] = new TextureRegion(texture, 0,0,32,32);
		regions[1] = new TextureRegion(texture, 32,0,32,32);
		regions[2] = new TextureRegion(texture, 64,0,32,32);
		regions[3] = new TextureRegion(texture, 96,0,32,32);
		texture.setRegion(this.regions[texselect]);

	}
	public void Draw(WorldRenderer render){
		render.getBatch().draw(this.helper.getBlocks(),box.x,box.y, Tile.SIZE, Tile.SIZE);

	}

}

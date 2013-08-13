package bob.com.tiles;
import bob.com.level.WorldRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
public class Tile {
	
	public static final float SIZE = 1f;
	private Texture texture;
	Vector2 	position;
	Rectangle 	topBound,rightBound,box;
	

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
	public Texture get_Tex(){
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
	public Tile(Vector2 pos) {
		this.texture = new Texture("images/block.png");
		this.position = pos;
		this.topBound = new Rectangle(this.position.x,this.position.y-1,SIZE,0.1f);
		this.rightBound = new Rectangle(this.position.x,this.position.y-1,0.1f,0.65f);
		this.box = new Rectangle(this.position.x,this.position.y,1,1);

	}
	public void Draw(WorldRenderer render){
		render.getBatch().draw(this.texture,box.x,box.y-1, Tile.SIZE, Tile.SIZE);

	}

}

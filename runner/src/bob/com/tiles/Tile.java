package bob.com.tiles;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
public class Tile {
	
	public static final float SIZE = 1f;

	Vector2 	position;
	Rectangle 	bounds;

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
    public void update(float delta){
    	this.bounds.x = this.position.x;
    	this.bounds.y = this.position.y;
    }
	public Tile(Vector2 pos) {
		this.position = pos;
		this.bounds = new Rectangle(this.position.x,this.position.y,SIZE,SIZE);
	}
}

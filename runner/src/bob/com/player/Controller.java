package bob.com.player;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

import bob.com.level.World;
import bob.com.player.Player.State;
import bob.com.tiles.Tile;

public class Controller {

	enum Keys {
		LEFT, RIGHT, JUMP, FIRE
	}

	private World 	world;
	private Player 	player;
	private float jumptime;
	static Map<Keys, Boolean> keys = new HashMap<Controller.Keys, Boolean>();
	private static final long LONG_JUMP_PRESS 	= 100l;
	private static  float ACCELERATION 	= 1;
	private static final float GRAVITY 			= -250f;
	private static final float MAX_JUMP_SPEED	= 15f;
	private static final float DAMP 			= 0.99f;
	private static final float MAX_VEL 			= 100f;
	
	// these are temporary
	private static final float WIDTH = 10f;
	private long	jumpPressedTime;
	private boolean jumpingPressed;
	private boolean grounded = false;
	static {
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.JUMP, false);
		keys.put(Keys.FIRE, false);
	};

	public Controller(World world) {
		this.world = world;
		this.player = world.getPlayer();
	}

	// ** Key presses and touches **************** //

	public void leftPressed() {
		keys.get(keys.put(Keys.LEFT, true));
	}

	public void rightPressed() {
		keys.get(keys.put(Keys.RIGHT, true));
	}

	public void jumpPressed() {
		keys.get(keys.put(Keys.JUMP, true));
	}

	public void firePressed() {
		keys.get(keys.put(Keys.FIRE, false));
	}

	public void leftReleased() {
		keys.get(keys.put(Keys.LEFT, false));
	}

	public void rightReleased() {
		keys.get(keys.put(Keys.RIGHT, false));
	}

	public void jumpReleased() {
		keys.get(keys.put(Keys.JUMP, false));
	}

	public void fireReleased() {
		keys.get(keys.put(Keys.FIRE, false));
	}

	/** The main update method **/
	public void update(float delta) {
		processInput();
		player.update(delta);
		if(!UpCollision()){
		player.getAcceleration().y = GRAVITY;
		Gdx.app.log("hoi", "faggots ik raak niets");

		}
		else
		{
			player.getAcceleration().y = 0;
		}
		player.getAcceleration().mul(delta);
		player.getVelocity().x += 0.01f;
		player.getVelocity().add(2, player.getAcceleration().y);
		if (player.getAcceleration().x == 0) player.getVelocity().x *= DAMP;
		
		if (player.getVelocity().x > MAX_VEL) {
			player.getVelocity().x = MAX_VEL;
		}
		if (player.getVelocity().x < -MAX_VEL) {
			player.getVelocity().x = -MAX_VEL;
		}
		if(UpCollision()){
			player.getVelocity().add(player.getAcceleration().x, player.getAcceleration().y);
			player.getPosition().x = player.getPosition().x;
			player.getPosition().y =player.getPosition().y;
			player.getAcceleration().y += 1	;
			player.setState(State.IDLE);
			Gdx.app.log("hoi", "walking"
					);

		}
		if(RightCollision()){
			player.getVelocity().set(0, 0);
			Gdx.app.log("hoi", "side hit");


		}

//	if (player.getPosition().y < 1) {
//			player.getPosition().y = 1f;
//			player.setPosition(player.getPosition());
//			if (player.getState().equals(State.JUMPING)) {
//				player.setState(State.IDLE);
//			}
//		 }
	}

	private void processInput() {
		if (keys.get(Keys.JUMP)) {
			grounded = false;
			if (!player.getState().equals(State.JUMPING)) {
				jumpingPressed = true;
				jumpPressedTime = System.currentTimeMillis();
				player.setState(State.JUMPING);
				player.getVelocity().y = MAX_JUMP_SPEED; 
			} else {
				if (jumpingPressed && ((System.currentTimeMillis() - jumpPressedTime) >= LONG_JUMP_PRESS)) {
					jumpingPressed = false;
				} else {
					if (jumpingPressed) {
						player.getVelocity().y = MAX_JUMP_SPEED;
					}
				}
			}
		}
	}
	private  boolean UpCollision(){
		for(Tile tile:world.getTiles()){
			Rectangle rect = new Rectangle(tile.getPosition().x,tile.getPosition().y,tile.get_top().width,tile.get_top().height);
			if(player.getBounds().overlaps(rect)){	
				if(player.getPosition().y-1 < rect.y){

					return true;
				}
			}

		 }

		return false;

	}
	private  boolean RightCollision(){
		for(Tile tile:world.getTiles()){
			Rectangle rect = new Rectangle(tile.getPosition().x,tile.getPosition().y-1,tile.get_rightSide().width,tile.get_rightSide().height);
			if(player.getBounds().overlaps(rect)){	
				if(player.getPosition().x < rect.x){

					return true;
				}
			}

		 }

		return false;

	}
}
						

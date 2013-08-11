package bob.com.player;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;

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
	private static final long LONG_JUMP_PRESS 	= 200l;
	private static final float ACCELERATION 	= 20;
	private static final float GRAVITY 			= -20f;
	private static final float MAX_JUMP_SPEED	= 10f;
	private static final float DAMP 			= 2f;
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
		if(!Collision()){
		player.getAcceleration().y = GRAVITY;
		Gdx.app.log("hoi","ik word niet geraakt");
		}
		else
		{
			player.getAcceleration().y = 0;
		}
		player.getAcceleration().mul(delta);
		player.getVelocity().add(player.getAcceleration().x+1, player.getAcceleration().y);
		if (player.getAcceleration().x == 0) player.getVelocity().x *= DAMP;
		
		if (player.getVelocity().x > MAX_VEL) {
			player.getVelocity().x = MAX_VEL;
		}
		if (player.getVelocity().x < -MAX_VEL) {
			player.getVelocity().x = -MAX_VEL;
		}
		if(Collision()){
			
			Gdx.app.log("hoi", "faggots ik raak iets");
			player.getPosition().y = 1;
			player.getVelocity().add(player.getAcceleration().x, player.getAcceleration().y);
			player.setPosition(player.getPosition());
			player.getAcceleration().y = 0;
			player.setState(State.IDLE);
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
	private boolean Collision(){
		for(Tile tile:world.getTiles()){
			
			if(player.getBounds().x < tile.getBounds().x + tile.getBounds().width &&
					player.getBounds().x + player.getBounds().width > tile.getBounds().x &&
					player.getBounds().y < tile.getBounds().y + tile.getBounds().height &&           
					player.getBounds().y + player.getBounds().height > tile.getBounds().y){
					return true;
				}
			
			else
			{
				return false;
			}

		}
		return false;
	}
}
						

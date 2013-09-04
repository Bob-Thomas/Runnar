package bob.com.player;
import bob.com.atlas.TextureHelper;
import bob.com.level.WorldRenderer;
import bob.com.objects.Tile;
import bob.com.runner.Runner;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
public class Player {

		public enum State {
			IDLE, WALKING, JUMPING, DYING,FALLING
		}

		private float speed = 1f;	// unit per second
		private float jump_velocity = 8;
	    public static final float SIZE = 16f; // half a unit
		private Vector2 position = new Vector2();
		private Vector2 acceleration = new Vector2();
		private Vector2 velocity = new Vector2();
		private Rectangle bounds = new Rectangle();
		private State state = State.WALKING;
		private float	stateTime = 0;
		private AtlasRegion texture;
		private TextureRegion[]  regions =  new TextureRegion[6];
		private int frame = 1;
		private float timer;
		private TextureHelper helper;
		private Runner game;
		private boolean doubleJump = true;
		private boolean usedJump = false;
		public boolean isUsedJump() {
			return usedJump;
		}

		public void setUsedJump(boolean usedJump) {
			this.usedJump = usedJump;
		}

		public float getSpeed() {
			return speed;
		}

		public void setSpeed(float speed) {
			this.speed = speed;
		}

		public float getJump_velocity() {
			return jump_velocity;
		}

		public void setJump_velocity(float jump_velocity) {
			this.jump_velocity = jump_velocity;
		}


		public Vector2 getPosition() {
			return position;
		}

		public void setPosition(Vector2 position) {
			this.position = position;
		}

		public Vector2 getAcceleration() {
			return acceleration;
		}

		public void setAcceleration(Vector2 acceleration) {
			this.acceleration = acceleration;
		}

		public Vector2 getVelocity() {
			return velocity;
		}

		public void setVelocity(Vector2 velocity) {
			this.velocity = velocity;
		}

		public Rectangle getBounds() {
			return bounds;
		}

		public void setBounds(Rectangle bounds) {
			this.bounds = bounds;
		}

		public State getState() {
			return state;
		}

		public void setState(State state) {
			this.state = state;
		}



		public boolean isDoubleJump() {
			return doubleJump;
		}

		public void setDoubleJump(boolean doubleJump) {
			this.doubleJump = doubleJump;
		}

		public Player(Vector2 position,Runner game) {
			this.game = game;
			this.helper = this.game.getHelper();
			this.position = position;
			this.bounds.setHeight(SIZE);
			this.bounds.setWidth(SIZE);
			this.bounds.x = position.x;
			this.bounds.y = position.y;
			texture = this.helper.getPlayer();
			regions[0] = new TextureRegion(texture, 0,0,44,44);
			regions[1] = new TextureRegion(texture, 44, 0,44,44);
			regions[2] = new TextureRegion(texture, 88,0,44,44);
			regions[3] = new TextureRegion(texture, 132,0,44,44);
			regions[4] = new TextureRegion(texture, 176,0,44,44);
			regions[5] = new TextureRegion(texture, 220,0,44,44);			

		}
		

		public void update(float delta) {
			if(state == State.DYING){
				frame = 5;
			}
			if(state == State.JUMPING || state == State.FALLING){
				frame = 0;
			}
			if(state == State.WALKING){
			timer += Gdx.graphics.getDeltaTime();
			if(timer > 0.08){
				timer = 0;
				frame ++;
			}
			if(frame > 4){
				frame = 0;
			}
			}
			this.bounds.x = this.position.x;
			this.bounds.y = this.position.y;
			this.position.x += this.velocity.x;
			this.position.y = this.position.y  + this.velocity.y;
			}
		public void Draw(SpriteBatch batch){
			batch.draw(regions[frame],bounds.x,bounds.y, Player.SIZE, Player.SIZE);

		}

		public float getStateTime() {
			return stateTime;
		}

		public void setStateTime(float stateTime) {
			this.stateTime = stateTime;
		}
}


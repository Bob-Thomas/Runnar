package bob.com.player;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
public class Player {

		public enum State {
			IDLE, WALKING, JUMPING, DYING
		}

		private float speed = 1f;	// unit per second
		private float jump_velocity = 1f;
	    public static final float SIZE = 0.5f; // half a unit
		private Vector2 position = new Vector2();
		private Vector2 acceleration = new Vector2();
		private Vector2 velocity = new Vector2();
		private Rectangle bounds = new Rectangle();
		private State state = State.IDLE;
		private boolean	facingLeft = true;
		private float	stateTime = 0;

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

		public boolean isFacingLeft() {
			return facingLeft;
		}

		public void setFacingLeft(boolean facingLeft) {
			this.facingLeft = facingLeft;
		}

		public Player(Vector2 position) {
			this.position = position;
			this.bounds.setHeight(SIZE);
			this.bounds.setWidth(SIZE);
			this.bounds.x = position.x;
			this.bounds.y = position.y;

		}
		

		public void update(float delta) {
			this.bounds.x = this.position.x;
			this.bounds.y = this.position.y;
			if(Gdx.app.getType() == ApplicationType.Android)
			{
			position.add(velocity.mul(0.01f));
			}
			else
			{
			position.add(velocity.mul(delta));
			}
		}

		public float getStateTime() {
			return stateTime;
		}

		public void setStateTime(float stateTime) {
			this.stateTime = stateTime;
		}
}


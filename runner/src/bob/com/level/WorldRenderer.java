package bob.com.level;


import bob.com.background.Background;
import bob.com.objects.Coin;
import bob.com.objects.Tile;
import bob.com.player.Player;
import bob.com.player.Player.State;
import bob.com.player.Score;
import bob.com.runner.Runner;
import bob.com.screens.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class WorldRenderer {

	private Runner game;
	private static final float CAMERA_WIDTH = 10f;
	private static final float CAMERA_HEIGHT =7f;
    private SpriteBatch spriteBatch;
	private World world;
	private Score score;
	private OrthographicCamera cam;
	private Background background;
	ShapeRenderer debugRenderer = new ShapeRenderer();
	private boolean debug = false;
	private Player player;
	public OrthographicCamera getCam() {
		return cam;
	}
	public void setCam(OrthographicCamera cam) {
		this.cam = cam;
	}
	public SpriteBatch getBatch(){
		return game.getBatch();
	}
	public void setSize (int w, int h) {

	}
	public boolean isDebug() {
		return debug;
	}
	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public WorldRenderer(World world, boolean debug,Runner game) {
		this.game = game;
		this.world = world;
		this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
		this.cam.zoom = 40f;
		this.debug = debug;
		this.score = new Score();
		player = world.getPlayer();
		background = world.getBackground();
	}


	public void render(float delta) {
			this.game.getBatch().setProjectionMatrix(this.cam.combined);
			this.game.getBatch().enableBlending();
			background.Draw(this);
			score.Draw(this, player);
			for(Chunk chunk : world.getChunks()){
				chunk.render(this.game.getBatch());
			}
			player.Draw(this.game.getBatch());
			if(debug){
				drawDebug();
			}
			
			//drawDebug();
	}

	public void Update(float delta){
		for(Chunk chunk : world.getChunks()){
			chunk.Update(delta);
		}
		this.cam.update();
	    this.cam.position.set(player.getPosition().x,100,0);

		
	}


		
	private void drawDebug() {
		// render Bob
		debugRenderer.setProjectionMatrix(cam.combined);
		Rectangle rect = player.getBounds();
		debugRenderer.setColor(new Color(1,0, 0, 1));
		debugRenderer.begin(ShapeType.Line);
		debugRenderer.rect(rect.x, rect.y, Player.SIZE, Player.SIZE);
		for(Chunk c:world.getChunks()){
			for(Tile t:c.getTiles()){
				debugRenderer.rect(t.getPosition().x,t.getPosition().y, t.SIZE,t.SIZE);
				debugRenderer.setColor(new Color(1, 1, 0, 1));
				debugRenderer.rect(t.getPosition().x,t.getPosition().y,0.10f,32);
				debugRenderer.setColor(new Color(0, 1, 1, 1));
				debugRenderer.rect(t.getPosition().x,t.getPosition().y+32,32,0.1f);
			}
		}
		debugRenderer.end();
	}
}
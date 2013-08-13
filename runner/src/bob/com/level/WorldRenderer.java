package bob.com.level;


import bob.com.player.Player;
import bob.com.player.Player.State;
import bob.com.tiles.Tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class WorldRenderer {

	private static final float CAMERA_WIDTH = 10f;
	private static final float CAMERA_HEIGHT =7f;
	private static final float RUNNING_FRAME_DURATION = 0.06f;
    private SpriteBatch spriteBatch;
	private World world;
	private OrthographicCamera cam;
	private Texture playerTex,blockTex;
	/** for debug rendering **/
	ShapeRenderer debugRenderer = new ShapeRenderer();

	/** Textures **/
	
	private boolean debug = false;
	private int width;
	private int height;
	private float ppuX;	// pixels per unit on the X axis
	private float ppuY;	// pixels per unit on the Y axis

	public OrthographicCamera getCam() {
		return cam;
	}
	public void setCam(OrthographicCamera cam) {
		this.cam = cam;
	}
	public SpriteBatch getBatch(){
		return spriteBatch;
	}
	public void setSize (int w, int h) {
		this.width = w;
		this.height = h;
		ppuX = (float)width / CAMERA_WIDTH;
		ppuY = (float)height / CAMERA_HEIGHT;
	}
	public boolean isDebug() {
		return debug;
	}
	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public WorldRenderer(World world, boolean debug) {
		this.world = world;
		this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
		this.cam.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0);
		this.cam.update();
		this.spriteBatch = new SpriteBatch();
		this.debug = debug;
		loadTextures();
	}

	private void loadTextures() {
//		bobTexture = new  Texture(Gdx.files.internal("images/bob_01.png"));
		blockTex = new Texture(Gdx.files.internal("images/block.png"));
	}

	public void render(float delta) {
			spriteBatch.begin();
			spriteBatch.setProjectionMatrix(cam.combined);
			spriteBatch.disableBlending();
			for(Tile tile:world.getTiles()){
				tile.Draw(this);
			}
			spriteBatch.end();
			if(debug){
				drawDebug();
			}
			
			//drawDebug();
	}




		
	private void drawDebug() {
		// render blocks
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Line);
//		z
		// render Bob

		Player bob = world.getPlayer();
		Rectangle rect = bob.getBounds();
		debugRenderer.setColor(new Color(0, 1, 0, 1));
		debugRenderer.rect(rect.x, rect.y, 0.5f, 0.5f);
		debugRenderer.end();
	}
}
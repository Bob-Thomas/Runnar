package bob.com.level;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;



import bob.com.objects.Coin;
import bob.com.objects.JumpPowerup;
import bob.com.objects.Tile;
import bob.com.runner.Runner;
import bob.com.screens.GameScreen;

public class Chunk{
	private int x;
	private int y;
	private int blocksWidth;
	private int blocksHeight;
	private ArrayList<Tile> tiles;
	private ArrayList<Coin> coins;
	private ArrayList<JumpPowerup> jumpCoins;
	private Random rand = new Random();
	private Runner game;
	private Rectangle rectangle;
	public ArrayList<Tile> getTiles() {
		return tiles;
	}
	public ArrayList<Coin> getCoins() {
		return coins;
	}
	public void setTiles(ArrayList<Tile> tiles) {
		this.tiles = tiles;
	}
	public Rectangle getRectangle() {
		return rectangle;
	}
	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}
	public ArrayList<JumpPowerup> getJumpCoins() {
		return jumpCoins;
	}
	public void setJumpCoins(ArrayList<JumpPowerup> jumpCoins) {
		this.jumpCoins = jumpCoins;
	}
	public Chunk(int x, int y, int blocksWidth, int blocksHeight,int random,Runner game){
		this.game = game;
		this.x = x;
		this.y = y;
		this.blocksWidth = blocksWidth;
		this.blocksHeight = blocksHeight;
		this.setRectangle(new Rectangle(x,y,this.blocksWidth*32,this.blocksHeight*32));
		this.tiles = new ArrayList<Tile>();
		this.coins = new ArrayList<Coin>();
		this.jumpCoins = new ArrayList<JumpPowerup>();
		int randnr = rand.nextInt((100 - 0) + 1) + 0;
		if(blocksHeight == 1 && randnr == 50||randnr == 25||randnr == 75){
			this.jumpCoins.add(new JumpPowerup(new Vector2(this.x+(blocksWidth*16),this.y+(blocksHeight*32)),this.game));
		}
		if(blocksHeight == 3 && randnr > 50||randnr < 20||randnr == 100){
			this.coins.add(new Coin(new Vector2(this.x+(blocksWidth*16),this.y+(blocksHeight*32)),this.game));
		}
		for(int i = 0; i < blocksWidth * 32; i += 32){
			for(int o = 0; o < blocksHeight * 32; o += 32){
				int texselect = rand.nextInt((3 - 0) + 1) + 0;	
				this.tiles.add(new Tile(this.x + i, this.y + o,texselect,this.game));
			}
			Gdx.app.log("size", Integer.toString(randnr));

		}
		
	}
	public void render(SpriteBatch batch){
		for(Tile block : this.tiles){
			block.Draw(batch);
		}
		for(Coin c:this.coins){
			c.Draw(batch);
			
		}
		for(JumpPowerup c:this.jumpCoins){
			c.Draw(batch);			
		}
	}
	public void Update(float delta){
		for(Coin c:this.coins){
			c.Update(delta);			
		}
		for(JumpPowerup c:this.jumpCoins){
			c.Update(delta);			
		}

	}

	
}
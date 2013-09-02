package bob.com.level;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;



import bob.com.objects.Coin;
import bob.com.objects.Tile;
import bob.com.screens.GameScreen;

public class Chunk{
	private int x;
	private int y;
	private int blocksWidth;
	private int blocksHeight;
	private ArrayList<Tile> tiles;
	private ArrayList<Coin> coins;
	public ArrayList<Tile> getTiles() {
		return tiles;
	}
	public ArrayList<Coin> getCoins() {
		return coins;
	}
	public void setTiles(ArrayList<Tile> tiles) {
		this.tiles = tiles;
	}
	public Chunk(int x, int y, int blocksWidth, int blocksHeight,int random){
		this.x = x;
		this.y = y;
		this.blocksWidth = blocksWidth;
		this.blocksHeight = blocksHeight;
		this.tiles = new ArrayList<Tile>();
		this.coins = new ArrayList<Coin>();
		
		for(int i = 0; i < blocksWidth * 32; i += 32){
			for(int o = 0; o < blocksHeight * 32; o += 32){
				this.tiles.add(new Tile(this.x + i, this.y + o,random));
				if(o > 0*32){
				this.coins.add(new Coin(new Vector2(this.x+i,this.y+(o+32))));
				}
			}
		}
		
	}
	public void render(SpriteBatch batch){
		for(Tile block : this.tiles){
			block.Draw(batch);
		}
		for(Coin c:this.coins){
			c.Draw(batch);
			
		}
	}
	public void Update(float delta){
		for(Coin c:this.coins){
			c.Update(delta);			
		}
	}
	
}
package bob.com.level;


import java.util.ArrayList;
import java.util.Random;


import sun.rmi.runtime.Log;
import bob.com.player.Player;
import bob.com.player.Score;
import bob.com.background.Background;
import bob.com.objects.Coin;
import bob.com.objects.Tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class World {
	private int amount = 0;
	private Random random = new Random();
	private int randomnumber;
	private Background background;
	private Rectangle delRect;
	/** The blocks making up the world **/
	private ArrayList<Chunk> chunks = new ArrayList<Chunk>();
	ArrayList<Coin> coins = new ArrayList<Coin>();
	public ArrayList<Coin> getCoins() {
		return coins;
	}
	public void setCoins(ArrayList<Coin> coins) {
		this.coins = coins;
	}
	public ArrayList<Chunk> getChunks() {
		return chunks;
	}
	public Background getBackground(){
		return background;
	}
	/** Our player controlled hero **/
	Player player;

	// Getters -----------
	public Player getPlayer() {
		return player;
	}
	// --------------------

	public World() {
		player = new Player(new Vector2(0, 320));
		this.delRect = new Rectangle(0,-100,10,500);
		this.background = new Background(new Vector2(-500,-100),new Vector2(-500,-100),new Vector2(-1000,-100));
		generateLevel();
	}
	public void update(float delta)
	{
		this.delRect.x = this.player.getPosition().x-10*32;
		if(this.background.getPosition().x+1000 < this.player.getPosition().x){
			this.background.getPosition().x = this.player.getPosition().x-500;
		}
		if(this.background.getPosition2().x+1000  < this.player.getPosition().x){
			this.background.getPosition2().x = this.player.getPosition().x-500;
		}
		if(this.background.getPosition3().x+1000  < this.player.getPosition().x){
			this.background.getPosition3().x = this.player.getPosition().x-500;
		}	
		
		if( player.getPosition().x > (amount-(32*10)) ){
			randomnumber = random.nextInt((3 - 0) + 1) + 0;

			 generateRandom(randomnumber);
		}
		for(Chunk c:chunks){
			if(c.getTiles().get(0).get_box().overlaps(delRect))
			{
				chunks.remove(c);
				break;
			}

		}
		for(Chunk c:chunks){
			for(Coin co:c.getCoins()){
				if(co.getBounds().overlaps(delRect)){
					c.getCoins().remove(co);
					break;			
				}
				if(co.getBounds().overlaps(player.getBounds())){
					c.getCoins().remove(co);
					Score.setScore(Score.getScore() + 10);
					break;
				}
			}
		}
		

	}
	private void generateRandom(int random){

		switch(random)
		{
		case 1:
			 AddChunk(amount,0,1,1,3);
			break;
		case 2:
			 AddChunk(amount,0,1,2,1);
			break;
		case 3:
			 AddChunk(amount,0,1,2,2);
			break;
		default:
			break;
		}
		 
	}
	private void generateLevel() {
		 AddChunk(amount,0,10,1,1);

		
	
 			 			
	}
	private void AddChunk(int x,int y,int w,int h,int i){
		amount += w*32;
		this.chunks.add(new Chunk(x, y, w, h,i));
	}
	private void AddCoin(int x,int y){
		this.coins.add(new Coin(new Vector2(x,y)));
	}

}
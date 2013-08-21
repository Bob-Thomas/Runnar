package bob.com.level;


import java.util.Arrays;
import java.util.Random;

import sun.rmi.runtime.Log;
import bob.com.player.Player;
import bob.com.tiles.Tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class World {
	private int random = 0;
	private Random rng = new Random();
	/** The blocks making up the world **/
	Array<Tile> tiles = new Array<Tile>();
	
	public Array<Tile> getTiles() {
		return tiles;
	}
	public void setTiles(Array<Tile> tiles) {
		this.tiles = tiles;
	}
	/** Our player controlled hero **/
	Player player;

	// Getters -----------
	public Array<Tile> getBlocks() {
		return tiles;
	}
	public Player getPlayer() {
		return player;
	}
	// --------------------

	public World() {
		generateLevel();
	}
	@SuppressWarnings("unchecked")
	public void update(float delta)
	{
		random = rng.nextInt(3 - 0 + 1) + 0;
		if( player.getPosition().x > (tiles.size-6)*32 ){
			generateRandom(random);
		}
		Gdx.app.log("position",Integer.toString(tiles.size));

	}
	private void generateRandom(int random){

		switch(random)
		{
		case 1:
			  tiles.add(new Tile(new Vector2(tiles.size*Tile.SIZE, 0*Tile.SIZE),random));
			  tiles.add(new Tile(new Vector2(tiles.size*Tile.SIZE, 0*Tile.SIZE),random)); 	
			  tiles.add(new Tile(new Vector2(tiles.size*Tile.SIZE, 0*Tile.SIZE),random)); 	
			  tiles.add(new Tile(new Vector2(tiles.size*Tile.SIZE, 0*Tile.SIZE),random)); 	
			  tiles.add(new Tile(new Vector2(tiles.size*Tile.SIZE, 0*Tile.SIZE),random)); 	
			  tiles.add(new Tile(new Vector2(tiles.size*Tile.SIZE, 1*Tile.SIZE),random)); 	

			break;
		case 2:
			  tiles.add(new Tile(new Vector2(tiles.size*Tile.SIZE, 1*Tile.SIZE),random)); 	
			  tiles.add(new Tile(new Vector2(tiles.size*Tile.SIZE, 0*Tile.SIZE),random)); 	
			  tiles.add(new Tile(new Vector2(tiles.size*Tile.SIZE, 1*Tile.SIZE),random)); 	
			  tiles.add(new Tile(new Vector2(tiles.size*Tile.SIZE, 0*Tile.SIZE),random)); 	
			  tiles.add(new Tile(new Vector2(tiles.size*Tile.SIZE, 1*Tile.SIZE),random));
			  tiles.add(new Tile(new Vector2(tiles.size*Tile.SIZE, 0*Tile.SIZE),random)); 	


			break;
		case 3:
			  tiles.add(new Tile(new Vector2(tiles.size*Tile.SIZE, 1*Tile.SIZE),random)); 	
			  tiles.add(new Tile(new Vector2(tiles.size*Tile.SIZE, 2*Tile.SIZE),random)); 	
			  tiles.add(new Tile(new Vector2(tiles.size*Tile.SIZE, 2*Tile.SIZE),random)); 	
			  tiles.add(new Tile(new Vector2(tiles.size*Tile.SIZE, 3*Tile.SIZE),random)); 	
			  tiles.add(new Tile(new Vector2(tiles.size*Tile.SIZE, 3*Tile.SIZE),random)); 	
			  tiles.add(new Tile(new Vector2(tiles.size*Tile.SIZE, 3*Tile.SIZE),random)); 	

			break;
		}
	}
	private void generateLevel() {
  
		player = new Player(new Vector2(0, 2*Tile.SIZE));
		tiles.add(new Tile(new Vector2(tiles.size*Tile.SIZE, 0*Tile.SIZE),3));
		tiles.add(new Tile(new Vector2(tiles.size*Tile.SIZE, 0*Tile.SIZE),3));
		tiles.add(new Tile(new Vector2(tiles.size*Tile.SIZE, 0*Tile.SIZE),3));
		tiles.add(new Tile(new Vector2(tiles.size*Tile.SIZE, 0*Tile.SIZE),3));
		tiles.add(new Tile(new Vector2(tiles.size*Tile.SIZE, 0*Tile.SIZE),3));
		tiles.add(new Tile(new Vector2(tiles.size-1*Tile.SIZE, 0*Tile.SIZE),3));



 			 			
	}
}

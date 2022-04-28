package core.map;

public class Layer  {
	private int id = -1;
	private Tile[] tiles;
	private boolean walkable;
	
	public Layer() {
		id++;
	}
	
	public Layer Set(int maptiles) {
		tiles = new Tile[maptiles];
		return this;
	}

	public Tile getTile(int index) {
		if (index>tiles.length || index < 0) return null;
		return tiles[index];
	}

	public void setTiles(int index,Tile tile) {
		if (index>tiles.length || index < 0) return ;
		this.tiles[index] = tile;
	}


	public boolean isWalkable() {
		return walkable;
	}




	public void setWalkable(boolean w) {
		walkable = w;
	}

	@Override
	public String toString() {
		return "Layer [id=" + id + ", tiles=" + tiles.length +", walkable=" + walkable + "]";
	}

	
	
	

}

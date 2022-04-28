package core.map;

public class Layer {
	private int id = -1;
	private String name;
	private Tile[] tiles;
	private boolean visible;

	public Layer() {
		id++;
	}

	public Layer Set(String name, int maptiles) {
		setName(name);
		tiles = new Tile[maptiles];
		return this;
	}

	public String getName() {
		return name;
	}

	public Tile getTile(int index) {
		if (index > tiles.length || index < 0)
			return null;
		return tiles[index];
	}

	public void setTile(int index, Tile tile) {
		if (index > tiles.length || index < 0)
			return;
		tiles[index] = tile;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Layer [id=" + id + "name =" + name + ", tiles=" + tiles.length + ", visible=" + visible + "]";
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}

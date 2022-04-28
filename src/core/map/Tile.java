package core.map;

import java.awt.image.BufferedImage;

public class Tile extends MapObject {

	public static final Tile NULL = new Tile().Set(null,"",true);

	public Tile() {
		super();
	}
	public Tile Set(BufferedImage s, String t,boolean b) {
		super.Set(s, t,b);
		return this;
	}

	@Override
	public String toString() {
		return "Tile [id=" + id + ", type=" + type + ", walkable=" + walkable + ", position=" + position + "]";
	}

}

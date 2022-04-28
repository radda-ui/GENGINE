package core.map;

import java.awt.image.BufferedImage;

import core.gameObject.gameobject;
import core.utils.Position;

public class MapObject extends gameobject {
	protected int id = -1;
	protected BufferedImage sprite;
	protected String type; // TODO: define types;
	protected boolean walkable;
	protected Position position;

	public MapObject() {
		super();
		id++;
	}

	public MapObject Set(BufferedImage img, String t, boolean b) {
		sprite = img;
		type = t;
		walkable = b;
		return this;
	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public String getType() {
		return type;
	}

	public boolean isWalkable() {
		return walkable;
	}

	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}

	@Override
	public String toString() {
		return "MapObject [id=" + id + ", sprite=" + sprite + ", type=" + type + ", walkable=" + walkable
				+ ", position=" + position + "]";
	}

}

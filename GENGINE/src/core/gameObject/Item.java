package core.gameObject;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import core.gfx.Sprite;

public class Item extends gameobject {

	private Sprite sprite;
	private String name;
	private Map<String, String> properties = new HashMap<>();

	public Item() {
		super();
	}

	public Item Set(Sprite s, String n) {
		sprite = s;
		name = n;
		return this;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public String getName() {
		return name;
	}

	public Map<String, String> getProperties() {
		return properties;
	}
	public String getProperty(String key) {
		if (!properties.containsKey(key)) return "";
		return properties.get(key);
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProperty(String key, String value) {
		properties.put(key, value);
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
	
	
	

}

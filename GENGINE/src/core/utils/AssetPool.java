package core.utils;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import core.Main.Engine;
import core.Main.ImageUtils;
import core.map.GameMap;

public class AssetPool {
	public static AssetPool assets = new AssetPool();
	private HashMap<String, BufferedImage> images = new HashMap<>();
	private HashMap<String, GameMap> maps = new HashMap<>();

	private AssetPool() {
	}

	public void addGameMap(String name) {
		if (!maps.containsKey(name))
			maps.put(name, new GameMap().Set(name));
		else
			Engine.logger.log("asset is already Loaded!!!");
	}

	public void addImage(String name) {
		if (!images.containsKey(name))
			images.put(name, ImageUtils.loadImage("/images/" + name + ".png"));
		else
			Engine.logger.log("asset is already Loaded!!!");
	}

	public GameMap getGameMap(String name) {
		if (maps.containsKey(name))
			return maps.get(name);
		return null;
	}

	public BufferedImage getImage(String name) {
		if (images.containsKey(name))
			return images.get(name);
		return null;
	}

}

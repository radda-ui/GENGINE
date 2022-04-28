package core.gfx;

import java.awt.image.BufferedImage;

import core.Main.ImageUtils;

public class Tileset {

	private BufferedImage source;
	private BufferedImage[][] tiles;

	public Tileset() {
	}

	public Tileset Set(String path, int tilewidth, int tilehieght) {
		source = ImageUtils.loadImage(path);
		tiles = ImageUtils.sliceImage(source, tilewidth, tilehieght);
		return this;
	}

	public BufferedImage[][] getTiles() {
		return tiles;
	}

}

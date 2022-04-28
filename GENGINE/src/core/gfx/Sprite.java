package core.gfx;

import java.awt.image.BufferedImage;

import core.Main.ImageUtils;

public class Sprite {

	BufferedImage sprite;

	public Sprite Set(BufferedImage image) {
		sprite = image;
		return this;
	}

	public Sprite Set(String path) {
		sprite = ImageUtils.loadImage(path);
		return this;
	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}

}

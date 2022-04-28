package core.gui.components;

import java.awt.image.BufferedImage;

import core.gui.component;
import core.utils.Size;

public class image extends component {

	private BufferedImage image;

	public image() {
		size = new Size();
	}

	@Override
	public BufferedImage getSprite() {
		return image;
	}

	public image Set(BufferedImage image) {
		this.image = image;
		size = new Size(image.getWidth(), image.getHeight());
		return this;
	}

	@Override
	public void update() {
	}
}
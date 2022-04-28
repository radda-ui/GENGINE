package core.gui.components;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import core.Main.Engine;
import core.Main.ImageUtils;
import core.utils.Space;

public class slider extends clickable {

	private double max;
	private double min;
	private double value;

	public slider() {
		size = new core.utils.Size(360, 10);
		margin = new Space(0, 0, 15, 0);
	}

	private int getPixelsOfCurrentValue() {
		double range = max - min;
		double percentage = value - min;

		return (int) ((percentage / range) * size.getW());
	}

	@Override
	public BufferedImage getSprite() {
		BufferedImage sprite = ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_OPAQUE);
		Graphics2D graphics = sprite.createGraphics();

		graphics.setColor(Color.GRAY);
		graphics.fillRect(0, 0, size.getW(), size.getH());

		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, getPixelsOfCurrentValue(), size.getH());

		graphics.dispose();
		return sprite;
	}

	public double getValue() {
		return value;
	}

	private double getValueAt(double xPosition) {
		double positionOnSlider = xPosition - position.x;
		double percentage = positionOnSlider / size.getW();
		double range = max - min;

		return min + range * percentage;
	}

	@Override
	public void onClick() {
	}

	@Override
	public void onDrag() {
		value = getValueAt(Engine.mouse.getMouseX());
		value = Math.min(max, value);
		value = Math.max(min, value);
	}

	@Override
	protected void onFocus() {
	}

	@Override
	public void onRelease() {
	}

	public slider set(double min, double max) {
		this.min = min;
		this.max = max;
		value = max;
		return this;
	}

	public void setValue(double value) {
		this.value = value;
	}
}

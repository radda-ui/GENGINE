package core.gui;

import java.awt.image.BufferedImage;

import core.utils.Position;
import core.utils.Size;
import core.utils.Space;

public abstract class component {

	protected Space margin;
	protected Space padding;
	protected Position position;
	protected Size size;

	public component() {
		position = new Position(0, 0);
		size = new Size(1, 1);
		margin = new Space(0);
		padding = new Space(5);
	}

	public Space getMargin() {
		return margin;
	}

	public Space getPadding() {
		return padding;
	}

	public Position getPosition() {
		return position;
	}

	public Size getSize() {
		return size;
	}

	public abstract BufferedImage getSprite();

	public void setMargin(Space margin) {
		this.margin = margin;
	}

	public void setPadding(Space padding) {
		this.padding = padding;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public abstract void update();

}

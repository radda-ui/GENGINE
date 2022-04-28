package core.gui.components;

import java.awt.Rectangle;

import core.Main.Engine;
import core.gui.component;
import core.input.MouseConsumer;
import core.utils.Position;

public abstract class clickable extends component implements MouseConsumer {

	protected boolean hasFocus;
	protected boolean isPressed;

	private Rectangle getBounds() {
		return new Rectangle(position.x, position.y, size.getW(), size.getH());
	}

	protected abstract void onFocus();

	@Override
	public void update() {
		Position mousePosition = new Position(Engine.mouse.getMouseX(), Engine.mouse.getMouseY());
		boolean previousFocus = hasFocus;

		hasFocus = getBounds().contains(mousePosition.x, mousePosition.y);
		isPressed = hasFocus && Engine.mouse.isButtonUp(1);

		if (!previousFocus && hasFocus)
			onFocus();

		if (hasFocus)
			Engine.ui.getMouseHandler().setActiveConsumer(this);
	}
}

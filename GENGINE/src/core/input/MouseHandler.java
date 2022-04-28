package core.input;

import core.Main.Engine;
import core.gui.components.clickable;

public class MouseHandler {

	private clickable active;

	public void setActiveConsumer(clickable object) {
		active = object;
	}

	public void update() {
		if (active != null && Engine.mouse.isButtonUp(1))
			active.onClick();
	}
}
package core.Main;

import core.utils.Keys;
import core.utils.Position;
import core.utils.Size;

public class Camera {
	@SuppressWarnings("unused")
	private Object focus;
	private Position position;
	private Size size;

	public Camera(int width, int height) {
		size = new Size(width, height);
		position = new Position();
	}

	public Position getPosition() {
		return position;
	}

	public Size getSize() {
		return size;
	}

	public void setFocus(Object o) {
		focus = o;
	}

	public void setSize(int newWidth, int newHeight) {
		size = new Size(newWidth, newHeight);
	}

	public void update() {
		// if (focus != null) position = new Position(0, 0);
		int x = 0, y = 0;

		if (Engine.keyboard.isKey(Keys.VK_UP))
			y -= 10;
		if (Engine.keyboard.isKey(Keys.VK_DOWN))
			y += 10;
		if (Engine.keyboard.isKey(Keys.VK_LEFT))
			x -= 10;
		if (Engine.keyboard.isKey(Keys.VK_RIGHT))
			x += 10;
		setPosition(x, y);
	}

	public void setPosition(int x, int y) {
		int x2, y2;
		x2 = position.x + x;
		y2 = position.y + y;
		x = Math.max(x2, 0);
		y = Math.max(y2, 0);
		position.set(x, y);

	}
}

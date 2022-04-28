package core.utils;

public class Vector2i {
	public int x, y;

	public Vector2i() {
	}

	public Vector2i(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "x: " + x + " y: " + y;
	}

}
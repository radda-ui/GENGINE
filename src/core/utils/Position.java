package core.utils;

public class Position {

	public int x, y;

	public Position() {
		this(0, 0);
	}

	public Position(int i, int j) {
		x = i;
		y = j;
	}

	public Position add(int x2, int y2) {
		x += x2;
		y += y2;
		return this;
	}

	public Position set(int x2, int y2) {
		x = x2;
		y = y2;
		return this;
	}

}

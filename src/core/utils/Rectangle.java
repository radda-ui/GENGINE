package core.utils;

public class Rectangle {
	public int x, y, w, h;

	public Rectangle() {
		x = 0;
		y = 0;
		w = 0;
		h = 0;
	}

	public Rectangle(int w, int h) {
		x = 0;
		y = 0;
		this.w = w;
		this.h = h;
	}

	public Rectangle(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public Rectangle(Size s) {
		x = 0;
		y = 0;
		w = s.getW();
		h = s.getH();
	}

	// setters
	public final int getHeight() {
		return h;
	}

	public final int getWidth() {
		return w;
	}

	// getters
	public final int getX() {
		return x;
	}

	public final int getY() {
		return y;
	}

	public void setHeight(int h) {
		this.h = h;
	}

	public void setWidth(int w) {
		this.w = w;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

}

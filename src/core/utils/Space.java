package core.utils;

public class Space {
	private int left, top, right, bottom;

	public Space() {
		this(0);
	}

	public Space(int left) {
		this(left, left);
	}

	public Space(int left, int top) {
		super();
		this.left = left;
		this.top = top;
		right = left;
		bottom = top;
	}

	public Space(int left, int top, int right, int bottom) {
		super();
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}

	public final int getBottom() {
		return bottom;
	}

	public final int getHeight() {
		return bottom + top;
	}

	public final int getLeft() {
		return left;
	}

	public final int getRight() {
		return right;
	}

	public final int getTop() {
		return top;
	}

	public final int getWidth() {
		return right + left;
	}
}

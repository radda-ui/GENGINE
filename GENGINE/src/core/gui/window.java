package core.gui;

import core.input.MouseHandler;
import core.utils.Position;
import core.utils.Size;

public class window extends container {

	private MouseHandler mouseHandler;

	public window() {
		super();
	}

	@Override
	protected void calculateContentPosition() {
		int x = padding.getLeft();
		int y = padding.getTop();
		switch (layout) {
		case List: {
			for (component c : children) {
				y += (c.getMargin().getTop());
				c.setPosition(new Position(x + c.getMargin().getLeft(), y));
				y += (c.getSize().getH() + c.getMargin().getBottom());
			}
			break;
		}
		case Flow: {
			for (component c : children) {
				x += c.getMargin().getLeft();
				c.setPosition(new Position(x, padding.getTop()));
				x += c.getSize().getW();
				x += c.getMargin().getRight();
			}
			break;
		}
		case Grid:
		default:
			break;

		}
		changed = true;
	}

	@Override
	protected Size calculateContentSize() {
		int w = 0, h = 0;

		for (component c : children)
			switch (layout) {
			case List: {
				w = Math.max(c.getSize().getW() + c.getMargin().getWidth(), w);
				h += c.getSize().getH() + c.getMargin().getHeight();
				break;
			}
			case Flow: {
				w += c.getSize().getW() + c.getMargin().getWidth();
				h = Math.max(c.getSize().getH(), h);
			}
			case Grid:
			default:
				break;
			}
		changed = true;
		return new Size(w, h);
	}

	public MouseHandler getMouseHandler() {
		return mouseHandler;
	}

	@Override
	public container Set(Size windowSize) {
		return this;
	}

	public void setMouseHandler(MouseHandler mh) {
		mouseHandler = mh;
	}

}

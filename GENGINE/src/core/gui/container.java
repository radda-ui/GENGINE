package core.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import core.Main.ImageUtils;
import core.gui.utils.Layout;
import core.utils.Position;
import core.utils.Size;
import core.utils.Space;

public abstract class container extends component {

	protected Color backgroundColor;
	protected boolean center;
	protected boolean changed;
	protected List<component> children;

	protected boolean visible = true;

	protected Size FixedSize;

	protected Layout layout;

	protected container parent;

	public container() {
		super();
		backgroundColor = Color.RED;
		layout = Layout.List;
		margin = new Space(5);
		children = new ArrayList<>();
		calculateSize();
		calculatePosition();
	}

	public void addComponent(component uiComponent) {
		children.add(uiComponent);
	}

	protected abstract void calculateContentPosition();

	protected abstract Size calculateContentSize();

	private void calculatePosition() {
		position = new Position(margin.getLeft(), margin.getTop());
		calculateContentPosition();
	}

	private void calculateSize() {
		Size s = calculateContentSize();

		size = new Size(padding.getWidth() + s.getW(), padding.getHeight() + s.getH());
		changed = true;
	}

	public void clear() {
		children.clear();
	}

	@Override
	public BufferedImage getSprite() {
		if (!visible)
			return null;
		BufferedImage image = ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_BIT_MASKED);
		Graphics2D graphics = image.createGraphics();

		graphics.setColor(backgroundColor);
		graphics.fillRect(0, 0, size.getW(), size.getH());

		for (component uiComponent : children)
			graphics.drawImage(uiComponent.getSprite(), uiComponent.getPosition().x, uiComponent.getPosition().y, null);

		graphics.dispose();
		return image;
	}

	public abstract container Set(Size windowSize);

	public void setBackgroundColor(Color color) {
		backgroundColor = color;
	}

	public void setCenterChildren(boolean b) {
		center = b;
	}

	public void setColor(Color c) {
		backgroundColor = c;

	}

	public void setFixedSize(Size fixedSize) {
		FixedSize = fixedSize;
	}

	public void setLayout(Layout layout) {
		this.layout = layout;
	}

	public void setParent(container parent) {
		this.parent = parent;
	}

	@Override
	public void update() {
		if (!visible)
			return;
		children.forEach(component -> component.update());
		calculateSize();
		calculatePosition();
	}

	public boolean isVisible() {
		return visible;
	}

	public void hide() {
		visible = false;
	}

	public void show() {
		visible = true;
	}
}

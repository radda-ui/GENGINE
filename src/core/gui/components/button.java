package core.gui.components;

import java.awt.Color;
import java.awt.image.BufferedImage;

import core.gui.container;
import core.gui.window;
import core.interfaces.Callback;
import core.utils.Size;
import core.utils.Space;

public class button extends clickable {

	protected Color backgroundColor;
	protected container box;

	protected Callback clickAction;

	protected text label;

	public button() {

		backgroundColor = Color.GRAY;

		setMargin(new Space(5, 0, 0, 0));

		box = new window();
		box.setCenterChildren(true);
		box.setFixedSize(new Size(150, 30));
	}

	@Override
	public BufferedImage getSprite() {
		return box.getSprite();
	}

	@Override
	public void onClick() {
		if (hasFocus)
			clickAction.action();
	}

	@Override
	public void onDrag() {

	}

	@Override
	protected void onFocus() {
		// state.getAudioPlayer().playSound("button.wav");
	}

	@Override
	public void onRelease() {

	}

	public button Set(String label, Callback c) {
		clickAction = c;
		this.label = new text().Set(label);
		box.addComponent(this.label);
		return this;
	}

	@Override
	public void update() {
		super.update();
		box.update();
		size = box.getSize();

		Color color = backgroundColor;

		if (hasFocus)
			color = Color.LIGHT_GRAY;

		if (isPressed)
			color = Color.DARK_GRAY;

		box.setColor(color);
	}

}

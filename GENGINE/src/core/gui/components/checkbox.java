package core.gui.components;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import core.Main.ImageUtils;
import core.gui.component;
import core.gui.container;
import core.gui.window;
import core.utils.Setting;
import core.utils.Size;
import core.utils.Space;

public class checkbox extends component {

	private static class checkboxHelper extends clickable {

		private Color color;
		private Setting<Boolean> setting;

		private checkboxHelper() {
			size = new Size(20, 20);
			color = Color.GRAY;
			margin = new Space(0, 5, 0, 0);
		}

		@Override
		public BufferedImage getSprite() {
			BufferedImage sprite = ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_BIT_MASKED);
			Graphics2D graphics = sprite.createGraphics();

			graphics.setColor(color);
			if (setting.getValue())
				graphics.fillRect(0, 0, size.getW(), size.getH());
			else
				graphics.drawRect(0, 0, size.getW() - 1, size.getH() - 1);

			graphics.dispose();
			return sprite;
		}

		@Override
		public void onClick() {
			if (hasFocus)
				setting.setValue(!setting.getValue());
		}

		@Override
		public void onDrag() {
		}

		@Override
		protected void onFocus() {
		}

		@Override
		public void onRelease() {
		}

		public checkboxHelper set(Setting<Boolean> s) {
			setting = s;
			return this;
		}

		@Override
		public void update() {
			super.update();

			color = setting.getValue() ? Color.WHITE : Color.GRAY;

			if (hasFocus) {
				color = Color.LIGHT_GRAY;

				if (isPressed)
					color = Color.DARK_GRAY;
			}
		}
	}

	private container box;

	public checkbox() {
		box = new window();
		box.setPadding(new Space(0));
		setMargin(new Space(5, 0, 0, 0));
	}

	@Override
	public BufferedImage getSprite() {
		return box.getSprite();
	}

	public checkbox Set(String label, Setting<Boolean> setting) {

		box.addComponent(new checkboxHelper().set(setting));
		box.addComponent(new text().Set(label));
		return this;
	}

	@Override
	public void update() {
		box.update();
		size = box.getSize();
		// box.setParent();
		box.setPosition(position);
	}
}

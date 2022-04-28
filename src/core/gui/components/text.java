package core.gui.components;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import core.Main.ImageUtils;
import core.gui.component;
import core.utils.Size;

public class text extends component {

	private Color color;
	private boolean dropShadow;
	private int dropShadowOffset;
	private Font font;
	private String fontFamily;

	private int fontSize;
	private int fontStyle;
	private Color shadowColor;

	private String text;

	public text() {
		text = "";
		fontSize = 16;
		fontStyle = Font.PLAIN;
		fontFamily = Font.SERIF;// "Camberia";
		color = Color.WHITE;

		dropShadow = true;
		dropShadowOffset = 2;
		shadowColor = new Color(140, 140, 140);
	}

	private void calculateSize() {
		FontMetrics fontMetrics = new Canvas().getFontMetrics(font);
		size = new Size(fontMetrics.stringWidth(text) + padding.getWidth(),
				fontMetrics.getHeight() + padding.getHeight());
	}

	private void createFont() {
		font = new Font(fontFamily, fontStyle, fontSize);
	}

	@Override
	public BufferedImage getSprite() {
		BufferedImage image = ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_BIT_MASKED);
		Graphics2D graphics = image.createGraphics();
		graphics.setFont(font);

		if (dropShadow) {
			graphics.setColor(shadowColor);
			graphics.drawString(text, padding.getLeft() + dropShadowOffset,
					fontSize + padding.getTop() + dropShadowOffset);
		}

		graphics.setColor(color);
		graphics.drawString(text, padding.getLeft(), fontSize + padding.getTop());

		graphics.dispose();
		return image;
	}

	public text Set(String text) {
		this.text = text;
		return this;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setDropShadow(boolean dropShadow) {
		this.dropShadow = dropShadow;
	}

	public void setDropShadowOffset(int dropShadowOffset) {
		this.dropShadowOffset = dropShadowOffset;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public void setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public void setShadowColor(Color shadowColor) {
		this.shadowColor = shadowColor;
	}

	@Override
	public void update() {
		createFont();
		calculateSize();
	}
}

package core.gfx;

import java.awt.image.BufferedImage;

public class AnimatedSprite {

	private BufferedImage[] sprites;
	private int currentSprite = 0;
	private int speed;
	private int counter = 0;

	private int startSprite = 0;
	private int endSprite;

	public AnimatedSprite(SpriteSheet sheet, int speed) {
		sprites = sheet.getSprites();
		this.speed = speed;
		this.endSprite = sprites.length - 1;
	}

	public void update() {
		counter++;
		if (counter >= speed) {
			counter = 0;
			play();
		}
	}

	public void reset() {
		counter = 0;
		currentSprite = startSprite;
	}

	public void setAnimationRange(int startSprite, int endSprite) {
		this.startSprite = startSprite;
		this.endSprite = endSprite;
		reset();
	}

	public int getWidth() {
		return sprites[currentSprite].getWidth();
	}

	public int getHeight() {
		return sprites[currentSprite].getHeight();
	}


	public void play() {
		currentSprite++;
		if (currentSprite >= endSprite)
			currentSprite = startSprite;
	}

}
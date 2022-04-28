package core.gfx;

import java.awt.image.BufferedImage;

import core.Main.ImageUtils;

public class SpriteSheet {
	
	BufferedImage image,sprites[];
	
	public SpriteSheet() {
		
	}
	public SpriteSheet Set(String path, int spritewidth,int spriteheight) {
		image = ImageUtils.loadImage(path);
		sprites = ImageUtils.sliceImage2(image , spritewidth, spriteheight);
		return this;
		
	}
	public BufferedImage[] getSprites() {
		return sprites;
	}
	
	
	
	

}

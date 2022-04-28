package core.Main;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import core.utils.Size;

public class ImageUtils extends Canvas {

	public static final int ALPHA_BIT_MASKED = 2;

	public static final int ALPHA_BLEND = 3;
	public static final int ALPHA_OPAQUE = 1;
	private static final GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment()
			.getDefaultScreenDevice().getDefaultConfiguration();
	private static final long serialVersionUID = 3423882566722340773L;

	public static BufferedImage createCompatibleImage(Size size, int transparency) {
		return ImageUtils.gc.createCompatibleImage(size.getW(), size.getH(), transparency);
	}

	public static VolatileImage createVolatileImage(Size size, int transparency) {
		return ImageUtils.gc.createCompatibleVolatileImage(size.getW(), size.getH(), transparency);
	}

	public static BufferedImage loadImage(String filePath) {
		try {
			BufferedImage im = ImageIO.read(ImageUtils.class.getResource(filePath));
			BufferedImage result = ImageUtils.createCompatibleImage(new Size(im.getWidth(), im.getHeight()),
					ImageUtils.ALPHA_BIT_MASKED);

			Graphics2D graphics = result.createGraphics();

			graphics.drawImage(im, 0, 0, null);

			graphics.dispose();
			return result;
		} catch (IOException e) {
			Engine.logger.log("Could not load image from path: " + filePath);
		}

		return null;
	}

	public static void SaveImage(String name, BufferedImage img) {
		try {
			if (ImageIO.write(img, "png", new File("./" + name + ".png")))
				Engine.logger.log(" image was saved: " + name + ".png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static BufferedImage[][] sliceImage(String Path, int w, int h) {
		BufferedImage image = ImageUtils.loadImage(Path);
		return ImageUtils.sliceImage(image, w, h);
	}
	public static BufferedImage[] sliceImage2(String Path, int w, int h) {
		BufferedImage image = ImageUtils.loadImage(Path);
		return ImageUtils.sliceImage2(image, w, h);
	}

	public static BufferedImage[][] sliceImage(BufferedImage image, int tw, int th) {
		int h = image.getHeight() / th;
		int w = image.getWidth() / tw;
		BufferedImage[][] images = new BufferedImage[h][w];
		for (int j = 0; j < h; j++)
			for (int i = 0; i < w; i++)
				images[j][i] = image.getSubimage(i * tw, j * th, tw, th);
		return images;
	}

	public static BufferedImage[] sliceImage2(BufferedImage image, int tw, int th) {
		int h = image.getHeight() / th;
		int w = image.getWidth() / tw;
		BufferedImage[] images = new BufferedImage[h * w];
		for (int j = 0; j < h; j++)
			for (int i = 0; i < w; i++)
				images[j * w + i] = image.getSubimage(i * tw, j * th, tw, th);
		return images;
	}

}

package core.Main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import core.gui.window;
import core.utils.Size;

public class Renderer {

	private static final int max = 1632;
	private static final int maxB = Renderer.max - 32;
	private Camera camera;
	private BufferedImage image;
	private Graphics2D pen;
	private BufferedImage buffer;
	private Game game;
	private Graphics2D bg;

	public Renderer(Camera cam) {
		camera = cam;
		image = ImageUtils.createCompatibleImage(new Size(800, 800), ImageUtils.ALPHA_OPAQUE);
		buffer = ImageUtils.createCompatibleImage(new Size(Renderer.max, Renderer.max), ImageUtils.ALPHA_OPAQUE);
		buffer.setAccelerationPriority(1f);
		image.setAccelerationPriority(1f);
		pen = image.createGraphics();
		bg = buffer.createGraphics();
	}

	private void clear(Color c) {
		pen.setColor(c);
		pen.fillRect(0, 0, image.getWidth(), image.getHeight());
	}

	public void Clear() {
		clear(Color.BLACK);
	}

	public void renderGUI(window ui) {

		if (ui.isVisible())
			pen.drawImage(ui.getSprite(), 0, 0, null);
	}

	public void renderGame(Game g) {
		game = g;
		if (!game.isInitiated())
			return;
		rendr();
	}

	public void render(Graphics2D g) {
		g.drawImage(image, 0, 0, null);
	}

	private void rendr() {
		bg.clearRect(0, 0, buffer.getWidth(), buffer.getHeight());
		bg.drawImage(game.getMap().getMap(), 0, 0, null);
		if (camera.getPosition().x + camera.getSize().getW() > Renderer.maxB)
			camera.getPosition().set(Renderer.maxB - camera.getSize().getW(), camera.getPosition().y);
		if ((camera.getPosition().y + camera.getSize().getH()) > Renderer.maxB)
			camera.getPosition().set(camera.getPosition().x, Renderer.maxB - camera.getSize().getH());
		pen.drawImage(buffer.getSubimage(camera.getPosition().x, camera.getPosition().y, camera.getSize().getW(),
				camera.getSize().getH()), 0, 0, null);
	}

}

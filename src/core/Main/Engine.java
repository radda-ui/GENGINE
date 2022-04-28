package core.Main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import core.audio.AudioPlayer;
import core.gui.window;
import core.gui.components.button;
import core.input.Keyboard;
import core.input.Mouse;
import core.input.MouseHandler;
import core.interfaces.Callback;
import core.utils.AssetPool;
import core.utils.Keys;
import core.utils.Logger;
import core.utils.Space;
import core.utils.Time;

public class Engine {

	public static final AssetPool assets = AssetPool.assets;
	public static final AudioPlayer sound = new AudioPlayer(.7f);
	public static final Logger logger = new Logger("Engine"); // Allow for the Engine to log things.
	public static final Keyboard keyboard = new Keyboard();
	public static final Mouse mouse = new Mouse();

	public static final Time time = Time.getInctance();
	public static final String TITLE = "Engine v0.43";

	public static final int width = 640;
	public static final int height = 480;
	public static Camera camera;

	public static final double UPDATES_PER_SECOND = 60f;
	private final double updateRate = 1.0d / Engine.UPDATES_PER_SECOND;
	private long nextStatTime;
	private int fps, ups;

	private int maxH;
	private int maxW;
	private boolean resized;
	private boolean running;

	private static Game game;
	private Renderer renderer;

	private JFrame frame;
	private Canvas canvas;
	private BufferStrategy bs;
	private Graphics2D g;
	private Thread thread;

	private MouseHandler mh = new MouseHandler();

	public static final window ui = new window();

	public Engine() {
		canvas = new Canvas();
		canvas.setBounds(0, 0, Engine.width, Engine.height);
		Engine.camera = new Camera(Engine.width, Engine.height);
		renderer = new Renderer(Engine.camera);
		Engine.assets.addImage("bg");
		GraphicsDevice[] gd = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
		for (GraphicsDevice element : gd) {
			maxW = Math.max(maxW, element.getDisplayMode().getWidth());
			maxH = Math.max(maxH, element.getDisplayMode().getHeight());
		}

		frame = new JFrame(Engine.TITLE);

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(canvas);
		// frame.setUndecorated(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		frame.setFocusable(false);
		canvas.addKeyListener(Engine.keyboard);
		canvas.addMouseListener(Engine.mouse);
		canvas.addMouseMotionListener(Engine.mouse);
		canvas.addMouseWheelListener(Engine.mouse);
		canvas.createBufferStrategy(2);
		canvas.requestFocus();
		bs = canvas.getBufferStrategy();

		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				canvas.setSize(Math.min(canvas.getWidth(), maxW), Math.min(canvas.getHeight(), maxH));
				frame.pack();
				resized = true;
			}

		});
		Keys.BINDING.put(Keys.VK_ESCAPE, Engine::end);

		Engine.ui.setColor(new Color(0,0,0,0));
		Engine.ui.setMargin(new Space(20));
		Engine.ui.setLayout(core.gui.utils.Layout.Flow);
		Engine.ui.setMouseHandler(mh);
		Engine.ui.addComponent(new button().Set("menu", () -> {
			if (Engine.ui.isVisible())
				Engine.ui.hide();
			else
				Engine.ui.show();
		}));
		Engine.ui.show();

		Keys.BINDING.put(Keys.VK_A, () -> {
			if (Engine.ui.isVisible())
				Engine.ui.hide();
			else
				Engine.ui.show();
		});
		Engine.logger.log("Engine initialized");
	}

	private void update() {
		
		if (resized) {
			Engine.camera.setSize(canvas.getWidth(), canvas.getHeight());
			resized = !resized;
		}
		Keys.BINDING.forEach((key, c) -> this.binding(key, c));

		if (!Engine.game.isInitiated())
			Engine.game.init();

		Engine.camera.update();
		Engine.game.update();


		Engine.ui.update();
		mh.update();
		mh.setActiveConsumer(null);
		Engine.mouse.update();
		Engine.keyboard.update();
		Engine.time.update();
		ups++;
	}

	private void binding(Integer key, Callback c) {
		if (Engine.keyboard.isKeyUp(key)) c.action();
	}

	private void render() {
		g = (Graphics2D) bs.getDrawGraphics();
		canvas.paint(g);
		renderer.renderGame(Engine.game);
		renderer.renderGUI(Engine.ui);
		renderer.render(g);
		bs.show();
		g.dispose();
		renderer.Clear();
		fps++;
	}

	public void run() {
		running = true;
		double accumulator = 0;
		long currentTime, lastUpdate = System.currentTimeMillis();
		nextStatTime = System.currentTimeMillis() + 1000;
		while (running) {
			currentTime = System.currentTimeMillis();
			double lastRenderTimeInSeconds = (currentTime - lastUpdate) / 1000d;
			accumulator += lastRenderTimeInSeconds;
			lastUpdate = currentTime;
			if (accumulator >= updateRate)
				if (accumulator >= updateRate) {
					update();
					accumulator -= updateRate;
				}
			render();
			if (System.currentTimeMillis() > nextStatTime) {
				Engine.logger.log("FPS: " + fps + " UPS: " + ups);
				fps = 0;
				ups = 0;
				nextStatTime = System.currentTimeMillis() + 1000;
			}
		}
		stop();
	}

	public void start() {
		thread = new Thread(this::run);
		thread.setDaemon(false);
		thread.start();
	}

	public void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void end() {
		Engine.logger.log("exit ");
		System.exit(0);
	}
	public static void setGame(Game game) {
		Engine.game = game;
	}
}

package core.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class Keyboard implements KeyListener {

	private final int NUM_KEYS = 512;
	private boolean[] keys = new boolean[NUM_KEYS];
	private boolean[] keysLast = new boolean[NUM_KEYS];

	public Keyboard() {
	}

	public void update() {
		System.arraycopy(keys, 0, keysLast, 0, NUM_KEYS);
	}

	public boolean isKey(int keycode) {
		return keys[keycode];
	}

	public boolean isKeyDown(int keycode) {
		return keys[keycode] && !keysLast[keycode];
	}

	public boolean isKeyUp(int keycode) {
		return !keys[keycode] && keysLast[keycode];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}

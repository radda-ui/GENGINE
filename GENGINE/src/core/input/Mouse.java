package core.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public final class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener {

	private final int NUM_BUTTONS = 5;
	private boolean[] buttons = new boolean[NUM_BUTTONS];
	private boolean[] buttonsLast = new boolean[NUM_BUTTONS];

	private int mouseX, mouseY;
	private int scroll;

	public Mouse() {
		mouseX = 0;
		mouseY = 0;
		scroll = 0;
	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

	public int getScroll() {
		return scroll;
	}

	public boolean isButton(int buttonCode) {
		return buttons[buttonCode];
	}

	public boolean isButtonDown(int buttonCode) {
		return buttons[buttonCode] && !buttonsLast[buttonCode];
	}

	public boolean isButtonUp(int buttonCode) {
		return !buttons[buttonCode] && buttonsLast[buttonCode];
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = (e.getX());
		mouseY = (e.getY());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = (e.getX());
		mouseY = (e.getY());
	}

	public boolean mouseOver(int x, int y, int width, int height) {
		if (mouseX >= x && mouseX < x + width)
			if (mouseY >= y && mouseY < y + height)
				return true;

		return false;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		buttons[e.getButton()] = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		buttons[e.getButton()] = false;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.getWheelRotation() != 0)
			scroll = e.getWheelRotation() * 10;
		else
			scroll = 0;
	}

	public void update() {
		scroll = 0;
		for (int i = 0; i < buttons.length; i++)
			buttonsLast[i] = buttons[i];
		for (int i = 0; i < buttons.length; i++)
			buttons[i] = false;
	}

}

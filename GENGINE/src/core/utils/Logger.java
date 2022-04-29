package core.utils;

import core.Main.Engine;

public class Logger {

	private String name;

	public Logger(String name) {
		this.name = name;
	}

	private String formatPrefix() {

		return "[" + name + "] [" + Engine.time.getFormattedTime() + "] ";
	}

	public void log(boolean b) {
		System.out.println(formatPrefix() + b);
	}

	public void log(byte b) {
		System.out.println(formatPrefix() + b);
	}

	public void log(byte[] b) {
		System.out.println(formatPrefix() + new String(b));
	}

	public void log(char c) {
		System.out.println(formatPrefix() + c);
	}

	public void log(char[] c) {
		System.out.println(formatPrefix() + new String(c));
	}

	public void log(double d) {
		System.out.println(formatPrefix() + d);
	}

	public void log(float f) {
		System.out.println(formatPrefix() + f);
	}

	public void log(int i) {
		System.out.println(formatPrefix() + i);
	}

	public void log(long l) {
		System.out.println(formatPrefix() + l);
	}

	public void log(short s) {
		System.out.println(formatPrefix() + s);
	}

	public void log(String s) {
		System.out.println(formatPrefix() + s);
	}

}

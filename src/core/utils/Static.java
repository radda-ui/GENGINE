package core.utils;

import core.interfaces.Callback;

public class Static {
	public static long getTotalMemory() {
		return Runtime.getRuntime().totalMemory();
	}

	public static long getUsedMemory() {
		Runtime t = Runtime.getRuntime();
		return t.totalMemory() - t.freeMemory();
	}

	public static void thread(Callback c) {
		Thread thread = new Thread(c::action);
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
		}
	}
}
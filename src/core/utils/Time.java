package core.utils;

import core.Main.Engine;

public class Time {

	private static Time time = new Time();

	public static Time getInctance() {
		return Time.time;
	}

	protected int currentUpdates;
	private long TimerStart;
	private long TimerStop;

	private Time() {
		currentUpdates = 0;
	}

	public int asSeconds() {
		return (int) (currentUpdates / Engine.UPDATES_PER_SECOND);
	}

	public String getFormattedTime() {
		StringBuilder stringBuilder = new StringBuilder();
		int minutes = (int) (currentUpdates / Engine.UPDATES_PER_SECOND / 60);
		int seconds = (int) (currentUpdates / Engine.UPDATES_PER_SECOND % 60);

		if (minutes < 10)
			stringBuilder.append(0);
		stringBuilder.append(minutes);
		stringBuilder.append(":");

		if (seconds < 10)
			stringBuilder.append(0);
		stringBuilder.append(seconds);
		return stringBuilder.toString();
	}

	public int getUpdatesFromSeconds(double seconds) {
		return (int) Math.round(seconds * Engine.UPDATES_PER_SECOND);
	}

	public boolean secondsDividableBy(double seconds) {
		return currentUpdates % getUpdatesFromSeconds(seconds) == 0;
	}

	public void update() {
		currentUpdates++;
	}

	public void timerStart() {
		TimerStart = System.currentTimeMillis();
		
	}
	public void timerStop() {
		TimerStop = System.currentTimeMillis();

		System.out.println((float)((TimerStop - TimerStart)));
		
	}
}

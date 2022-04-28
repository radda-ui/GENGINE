package core.audio;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public abstract class AudioClip {

	private final Clip clip;

	public AudioClip(Clip clip) {
		this.clip = clip;
		clip.start();
	}

	public void cleanUp() {
		clip.close();
	}

	protected abstract float getVolume(float audioSettings);

	public boolean hasFinishedPlaying() {
		return !clip.isRunning();
	}

	void setVolume(float audioSettings) {
		final FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		float range = control.getMaximum() - control.getMinimum();
		float gain = (range * getVolume(audioSettings)) + control.getMinimum();

		control.setValue(gain);
	}

	public void update(float audioSettings) {
		setVolume(audioSettings);
	}
}

package core.audio;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {

	private List<AudioClip> audioClips;
	private float audioSettings;

	public AudioPlayer(float audioSettings) {
		this.audioSettings = audioSettings;
		audioClips = new ArrayList<>();
	}

	public void clear() {
		audioClips.forEach(AudioClip::cleanUp);
		audioClips.clear();
	}

	private Clip getClip(String fileName) {
		final URL soundFile = AudioPlayer.class.getResource("/sounds/" + fileName + ".wav");
		try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile)) {
			final Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.setMicrosecondPosition(0);
			return clip;

		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			System.out.println(e);
		}

		return null;
	}

	public void playMusic(String fileName) {
		final Clip clip = getClip(fileName);
		final MusicClip musicClip = new MusicClip(clip);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		musicClip.setVolume(audioSettings);
		audioClips.add(musicClip);
	}

	public void playSound(String fileName) {
		final Clip clip = getClip(fileName);
		final SoundClip soundClip = new SoundClip(clip);
		soundClip.setVolume(audioSettings);
		audioClips.add(soundClip);
	}

	public void update() {
		audioClips.forEach(audioClip -> audioClip.update(audioSettings));

		List<AudioClip> l = (audioClips);
		l.forEach(audioClip -> {
			if (audioClip.hasFinishedPlaying()) {
				audioClip.cleanUp();
				audioClips.remove(audioClip);
			}
		});
		audioClips = l;
	}
}

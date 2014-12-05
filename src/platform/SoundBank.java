package platform;

import java.io.*;

import javax.sound.sampled.*;

import ui.Options;

public class SoundBank {
	
	public SoundBank(){
		
	}	
	
	public static void sound_play_eat(){
		if (Options.effectsSound)
			try {
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(new File(
						"src/resources/sound_food.wav")));
				clip.start();
			} catch (Exception exc) {
				exc.printStackTrace(System.out);
			}
	}
	public static void sound_play_death(){
		if (Options.effectsSound)
			try {
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(new File(
						"src/resources/sound_death.wav")));
				clip.start();
			} catch (Exception exc) {
				exc.printStackTrace(System.out);
			}
	}
	public static void sound_play_menu_swipe(){
		if (Options.effectsSound)
			try {
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(new File(
						"src/resources/sound_menu_swipe.wav")));
				clip.start();
			} catch (Exception exc) {
				exc.printStackTrace(System.out);
			}
	}
	
	// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
	
	static boolean play_theme = false;
	static AudioFormat audioFormat;
	static AudioInputStream audioInputStream;
	static SourceDataLine theme_sourceDataLine;

	static public void sound_play_theme_simple() {
		if (Options.musicSound){
			try {
				File soundFile = new File("src/resources/sound_theme_simple.wav");
				audioInputStream = AudioSystem.getAudioInputStream(soundFile);
				audioFormat = audioInputStream.getFormat();
				DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
				theme_sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
				play_theme = true;
				new Thread() {
					byte tempBuffer[] = new byte[10000];
					public void run() {
						try {
							theme_sourceDataLine.open(audioFormat);
							theme_sourceDataLine.start();
							int cnt;
							while ((cnt = audioInputStream.read(tempBuffer, 0, tempBuffer.length)) != -1
									&& play_theme == true) {
								if (cnt > 0) {
									theme_sourceDataLine.write(tempBuffer, 0, cnt);
								}
							}
							theme_sourceDataLine.drain();
							theme_sourceDataLine.close();
							play_theme = false;
						} catch (Exception e) { e.printStackTrace(); }
					}
				}.start();
			} catch (Exception e) { e.printStackTrace(); }
		}
	}
	static public void sound_stop_theme_simple() {
		play_theme = false;
	}
	
	static public void sound_play_theme() {
		if (Options.musicSound){
			try {
				File soundFile = new File("src/resources/sound_ambience.wav");
				audioInputStream = AudioSystem.getAudioInputStream(soundFile);
				audioFormat = audioInputStream.getFormat();
				DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
				theme_sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
				play_theme = true;
				new Thread() {
					byte tempBuffer[] = new byte[10000];
					public void run() {
						try {
							theme_sourceDataLine.open(audioFormat);
							theme_sourceDataLine.start();

							int cnt;
							while ((cnt = audioInputStream.read(tempBuffer, 0, tempBuffer.length)) != -1
									&& play_theme == true) {
								if (cnt > 0) {
									theme_sourceDataLine.write(tempBuffer, 0, cnt);
								}
							}
							theme_sourceDataLine.drain();
							theme_sourceDataLine.close();
							play_theme = false;
						} catch (Exception e) { e.printStackTrace(); }
					}
				}.start();
			} catch (Exception e) { e.printStackTrace(); }
		}
	}
	static public void sound_stop_theme() {
		play_theme = false;
	}
}



package platform;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundBank {
	
	public SoundBank(){
		
	}
	
	public static void sound_play_eat(){
		 try {
		        Clip clip = AudioSystem.getClip();
		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(Launch.class.getResourceAsStream("src/resources/Food_Sound.wav"));
		        clip.open(inputStream);
		        clip.start(); 
		      } catch (Exception e) {
		        System.err.println(e.getMessage());
		      }
	}

}



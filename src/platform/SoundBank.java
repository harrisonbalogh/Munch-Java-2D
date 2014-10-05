package platform;

import java.io.*;
import javax.sound.sampled.*;


public class SoundBank {
	
	public SoundBank(){
		
	}

	
	public static void sound_play_eat(){
		new Thread(){
			   public void run(){
		try
	    {
	        Clip clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(new File("src/resources/sound_food.wav")));
	        clip.start();
	    }
	    catch (Exception exc)
	    {
	        exc.printStackTrace(System.out);
	    }
			   }
		}.start();
	}
	public static void sound_play_death(){
		try
	    {
	        Clip clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(new File("src/resources/sound_death.wav")));
	        clip.start();
	    }
	    catch (Exception exc)
	    {
	        exc.printStackTrace(System.out);
	    }
	}
	public static void sound_play_theme(){
		new Thread(){
			   public void run(){
				   try
				    {
				        Clip clip = AudioSystem.getClip();
				        clip.open(AudioSystem.getAudioInputStream(new File("src/resources/sound_theme.wav")));
				        clip.start();
				    }
				    catch (Exception exc)
				    {
				        exc.printStackTrace(System.out);
				    }
			   }
		}.start(); 
		
	}
	public static void sound_play_simpleTheme(){
		try
	    {
	        Clip clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(new File("src/resources/sound_theme_simple.wav")));
	        clip.start();
	    }
	    catch (Exception exc)
	    {
	        exc.printStackTrace(System.out);
	    }
	}

}



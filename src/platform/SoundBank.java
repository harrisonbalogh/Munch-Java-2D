package platform;

import java.io.*;
import javax.sound.sampled.*;

public class SoundBank {
	
	public SoundBank(){
		
	}	
	public static void sound_play_eat(){
		try {
	        Clip clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(new File("src/resources/sound_food.wav")));
	        clip.start();
	    }
	    catch (Exception exc){
	        exc.printStackTrace(System.out);
	    }
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
				int total, totalToRead, numBytesRead, numBytesToRead;
		        byte[] buffer;
		        boolean         stopped;
		        AudioFormat     wav;
		        TargetDataLine  line;
		        SourceDataLine  lineIn;
		        DataLine.Info   info;
		        File            file;
		        FileInputStream fis;

		        //AudioFormat(float sampleRate, int sampleSizeInBits, 
		        //int channels, boolean signed, boolean bigEndian) 
		        wav = new AudioFormat(44100, 8, 2, false, false);
		        info = new DataLine.Info(SourceDataLine.class, wav);


		        buffer = new byte[1024*333];
		        numBytesToRead = 1024*333;
		        total=0;
		        stopped = false;

		        if (!AudioSystem.isLineSupported(info)) {
		            System.out.print("no support for " + wav.toString() );
		        }
		        try {
		            // Obtain and open the line.
		            lineIn = (SourceDataLine) AudioSystem.getLine(info);
		            lineIn.open(wav);
		            lineIn.start();
		            fis = new FileInputStream(file = new File("src/resources/sound_theme.wav"));
		            totalToRead = fis.available();



		            while (total < totalToRead && !stopped){
		                numBytesRead = fis.read(buffer, 0, numBytesToRead);
		                if (numBytesRead == -1) break;
		                total += numBytesRead;
		                lineIn.write(buffer, 0, numBytesRead);
		            }

		        } catch (LineUnavailableException ex) {
		            ex.printStackTrace();
		        } catch (FileNotFoundException nofile) {
		            nofile.printStackTrace();
		        } catch (IOException io) {
		            io.printStackTrace();
		        }
			}
		}.start();
	}
	public static void sound_play_simpleTheme(){
		new Thread(){
			public void run(){
				int total, totalToRead, numBytesRead, numBytesToRead;
		        byte[] buffer;
		        boolean         stopped;
		        AudioFormat     wav;
		        TargetDataLine  line;
		        SourceDataLine  lineIn;
		        DataLine.Info   info;
		        File            file;
		        FileInputStream fis;

		        //AudioFormat(float sampleRate, int sampleSizeInBits, 
		        //int channels, boolean signed, boolean bigEndian) 
		        wav = new AudioFormat(44100, 8, 2, false, false);
		        info = new DataLine.Info(SourceDataLine.class, wav);


		        buffer = new byte[1024*333];
		        numBytesToRead = 1024*333;
		        total=0;
		        stopped = false;

		        if (!AudioSystem.isLineSupported(info)) {
		            System.out.print("no support for " + wav.toString() );
		        }
		        try {
		            // Obtain and open the line.
		            lineIn = (SourceDataLine) AudioSystem.getLine(info);
		            lineIn.open(wav);
		            lineIn.start();
		            fis = new FileInputStream(file = new File("src/resources/sound_theme_simple.wav"));
		            totalToRead = fis.available();



		            while (total < totalToRead && !stopped){
		                numBytesRead = fis.read(buffer, 0, numBytesToRead);
		                if (numBytesRead == -1) break;
		                total += numBytesRead;
		                lineIn.write(buffer, 0, numBytesRead);
		            }

		        } catch (LineUnavailableException ex) {
		            ex.printStackTrace();
		        } catch (FileNotFoundException nofile) {
		            nofile.printStackTrace();
		        } catch (IOException io) {
		            io.printStackTrace();
		        }
			}
		}.start();
	}

}



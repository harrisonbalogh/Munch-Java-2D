package ui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class Options {
	
	public static int		difficulty = 1;
	public static boolean 	stats_top = false;
	public static boolean	arrowMovement = true;
	public static boolean 	musicSound = true;
	public static boolean 	effectsSound = true;
	public static boolean 	reset = false;
	
	public Options(){
		initializeOptions();
	}
	
	public static void updateOptionsFile(){
		try {
			FileWriter fw = new FileWriter("options.txt", false);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("difficulty=" + difficulty + "\n");
			bw.write("arrowMovement=" + arrowMovement + "\n");
			bw.write("musicSound=" + musicSound + "\n");
			bw.write("effectsSound=" + effectsSound + "\n");
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void initializeOptions(){
		StringBuilder stringBuilder = new StringBuilder();
		try {
            File f = new File("options.txt");
            Scanner sc = new Scanner(f);
            while(sc.hasNextLine())
            	stringBuilder.append(sc.nextLine() + "\n");
        } catch (FileNotFoundException e) {         
            e.printStackTrace();
            return;
        }
		Scanner sc = new Scanner(stringBuilder.toString());
		String temp;
		//difficulty parse
		temp = sc.nextLine();
		temp = temp.substring(temp.indexOf("=") + 1, temp.length());
		try {
			difficulty = Integer.parseInt(temp);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}	
		//arrowMovement parse
		temp = sc.nextLine();
		temp = temp.substring(temp.indexOf("=") + 1, temp.length());
		try {
			arrowMovement = Boolean.parseBoolean(temp);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
		// musicSound parse
		temp = sc.nextLine();
		temp = temp.substring(temp.indexOf("=") + 1, temp.length());
		try {
			musicSound = Boolean.parseBoolean(temp);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
		// effectsSound parse
		temp = sc.nextLine();
		temp = temp.substring(temp.indexOf("=") + 1, temp.length());
		try {
			effectsSound = Boolean.parseBoolean(temp);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
	}
	
}

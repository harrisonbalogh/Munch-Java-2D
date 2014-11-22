package ui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import platform.InputController;

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
	
	public static void toggleDifficulty(JCheckBox[] checkBox, int diff){
		checkBox[0].setSelected(false);
		checkBox[1].setSelected(false);
		checkBox[2].setSelected(false);
		checkBox[3].setSelected(false);
		checkBox[diff].setSelected(true);
		difficulty = diff;
	}
	public static void toggleSoundMusic(){
		Options.musicSound = !Options.musicSound;
	}
	public static void toggleSoundEffects(){
		Options.effectsSound = !Options.effectsSound;
	}
	public static void toggleControlScheme(JPanel panel, JCheckBox checkBox){
		Options.updateOptionsFile();
		Options.arrowMovement = !Options.arrowMovement;
		if (checkBox.isSelected()) {
			InputMap in = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
			in.put(KeyStroke.getKeyStroke("LEFT"), null);
			in.put(KeyStroke.getKeyStroke("RIGHT"), null);
			
			ActionMap am = panel.getActionMap();
			in.put(KeyStroke.getKeyStroke('a'), "doA_Pressed");
			am.put("doA_Pressed", new InputController.A_Pressed());
			in.put(KeyStroke.getKeyStroke('d'), "doD_Pressed");
			am.put("doD_Pressed", new InputController.D_Pressed());
			in.put(KeyStroke.getKeyStroke('s'), "doS_Pressed");
			am.put("doS_Pressed", new InputController.S_Pressed());
			in.put(KeyStroke.getKeyStroke('w'), "doW_Pressed");
			am.put("doW_Pressed", new InputController.W_Pressed());
		} else {
			InputMap in = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
			in.put(KeyStroke.getKeyStroke('a'), null);
			in.put(KeyStroke.getKeyStroke('d'), null);
			in.put(KeyStroke.getKeyStroke('s'), null);
			in.put(KeyStroke.getKeyStroke('w'), null);
			
			ActionMap am = panel.getActionMap();
			in.put(KeyStroke.getKeyStroke("LEFT"), "doA_Pressed");
			am.put("doA_Pressed", new InputController.A_Pressed());
			in.put(KeyStroke.getKeyStroke("RIGHT"), "doD_Pressed");
			am.put("doD_Pressed", new InputController.D_Pressed());
		}
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

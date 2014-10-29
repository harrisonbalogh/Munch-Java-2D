package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import platform.InputController;
import platform.Statistics;

import entities.Food;
import entities.GameClock;
import entities.Player;

public class UserDisplay {
	
	public static final int        WINDOW_X = 400;
	public static final int        WINDOW_Y = 600;
	public static final int WINDOW_X_OFFSET = 0;
	public static final int WINDOW_Y_OFFSET = 22;
	
	private String openScene = "";
	
	private JFrame 		frame_main = new MainFrame();
	private JPanel 		panel_menu = new JPanel(new BorderLayout());
	private JPanel 		panel_game = new GamePanel();
	private JLabel		scene_background_1 = new JLabel(new ImageIcon("src/resources/scene_background_1.png"));
	private JLabel		scene_background_2 = new JLabel(new ImageIcon("src/resources/scene_background_2.png"));
	private JLabel 		scene_logo = new JLabel(new ImageIcon("src/resources/scene_logo.png"));
	private JLabel 		scene_play = new JLabel(new ImageIcon("src/resources/scene_play.png"));
	private JLabel 		scene_menu = new JLabel(new ImageIcon("src/resources/scene_menu.png"));
	private JLabel 		scene_stats = new JLabel(new ImageIcon("src/resources/scene_stats.png"));
	private JLabel 		scene_about = new JLabel(new ImageIcon("src/resources/scene_about.png"));
	private JLabel 		scene_options = new JLabel(new ImageIcon("src/resources/scene_options.png"));
	private JButton		button_play = new JButton(new ImageIcon("src/resources/button_play.png"));
	private JButton 	button_stats = new JButton(new ImageIcon("src/resources/button_stats.png"));
	private JButton 	button_stats_top = new JButton(new ImageIcon("src/resources/button_stats_top.png"));
	private JButton 	button_stats_recent = new JButton(new ImageIcon("src/resources/button_stats_recent.png"));
	private JButton 	button_about = new JButton(new ImageIcon("src/resources/button_about.png"));
	private JButton 	button_options = new JButton(new ImageIcon("src/resources/button_options.png"));
	private JButton 	button_back_game = new JButton(new ImageIcon("src/resources/button_play_back.png"));
	private JButton 	button_back = new JButton(new ImageIcon("src/resources/button_back.png"));
	private JButton 	button_reload = new JButton(new ImageIcon("src/resources/button_play_reload.png"));
	private JCheckBox 	toggle_sound_music = new JCheckBox(new ImageIcon("src/resources/toggle_options_off.png"));
	private JCheckBox 	toggle_sound_effects = new JCheckBox(new ImageIcon("src/resources/toggle_options_off.png"));
	private JCheckBox 	toggle_game_controls = new JCheckBox(new ImageIcon("src/resources/toggle_options_control_off.png"));
	private JCheckBox 	toggle_stats_reset = new JCheckBox(new ImageIcon("src/resources/toggle_options_off.png"));
	private JCheckBox[]	toggle_game_difficulty = new JCheckBox[4];
	//private JLabel 		progress_play_fill = new JLabel(new ImageIcon("src/resources/progress_play_fill.png"));
	private JTextField 	text_score = new JTextField("0");
	private JTextArea	text_warning = new JTextArea("Pressing back while reset is toggled\n        will remove all stat history.");
	private JTextArea[] text_stats = new JTextArea[3];
	
	
	@SuppressWarnings("unchecked")
	private ArrayList<JComponent> menu_components = new ArrayList<JComponent>(Arrays.asList(
			button_play,
			button_stats,
			button_stats_top,
			button_stats_recent,
			button_about,
			button_options,
			button_back,
			scene_logo,
			scene_menu,
			scene_stats,
			scene_about,
			scene_options,
			toggle_sound_music,
			toggle_sound_effects,
			toggle_game_controls,
			toggle_stats_reset,
			toggle_game_difficulty[0] = new JCheckBox(new ImageIcon("src/resources/toggle_options_off.png")),
			toggle_game_difficulty[1] = new JCheckBox(new ImageIcon("src/resources/toggle_options_off.png")),
			toggle_game_difficulty[2] = new JCheckBox(new ImageIcon("src/resources/toggle_options_off.png")),
			toggle_game_difficulty[3] = new JCheckBox(new ImageIcon("src/resources/toggle_options_off.png")),
			text_stats[0] = new JTextArea(""),
			text_stats[1] = new JTextArea(""),
			text_stats[2] = new JTextArea(""),
			text_warning,
			scene_background_2,
			scene_background_1
			));
	@SuppressWarnings("unchecked")
	private ArrayList<JComponent> game_components = new ArrayList<JComponent>(Arrays.asList(
			panel_game,
			button_reload,
			button_back_game,
			text_score,
			scene_play
			));
	
	public UserDisplay(){
		// - - - - - P A N E L S - - - - -
		panel_menu.setSize(WINDOW_X, WINDOW_Y);
		panel_game.setBounds(WINDOW_X, 0, WINDOW_X, WINDOW_Y-39);
		panel_game.setBackground(Color.WHITE);
		
		// - - - - - B U T T O N S - - - - -
		button_play.setBounds(14, 168, 171, 24);
		button_play.setPressedIcon(new ImageIcon("src/resources/button_play_pressed.png"));
		button_play.setRolloverIcon(new ImageIcon("src/resources/button_play_rollover.png"));
		button_play.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){runPlayScene();}});
		button_play.setBorderPainted(false);
		button_stats.setBounds(14, 216, 171, 24);
		button_stats.setPressedIcon(new ImageIcon("src/resources/button_stats_pressed.png"));
		button_stats.setRolloverIcon(new ImageIcon("src/resources/button_stats_rollover.png"));
		button_stats.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){runStatsScene();}});
		button_stats.setBorderPainted(false);
		button_stats_top.setBounds(76 + WINDOW_X, 147, 116, 24);
		button_stats_top.setPressedIcon(new ImageIcon("src/resources/button_stats_top_pressed.png"));
		button_stats_top.setRolloverIcon(new ImageIcon("src/resources/button_stats_top_rollover.png"));
		button_stats_top.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){switchStatsPage();}});
		button_stats_top.setEnabled(false);
		button_stats_top.setBorderPainted(false);
		button_stats_recent.setBounds(208 + WINDOW_X, 147, 116, 24);
		button_stats_recent.setPressedIcon(new ImageIcon("src/resources/button_stats_recent_pressed.png"));
		button_stats_recent.setRolloverIcon(new ImageIcon("src/resources/button_stats_recent_rollover.png"));
		button_stats_recent.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){switchStatsPage();}});
		button_stats_recent.setEnabled(false);
		button_stats_recent.setBorderPainted(false);
		button_about.setBounds(14, 264, 171, 24);
		button_about.setPressedIcon(new ImageIcon("src/resources/button_about_pressed.png"));
		button_about.setRolloverIcon(new ImageIcon("src/resources/button_about_rollover.png"));
		button_about.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){runAboutScene();}});
		button_about.setBorderPainted(false);
		button_options.setBounds(14, 312, 171, 24);
		button_options.setPressedIcon(new ImageIcon("src/resources/button_options_pressed.png"));
		button_options.setRolloverIcon(new ImageIcon("src/resources/button_options_rollover.png"));
		button_options.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){runOptionsScene();}});
		button_options.setBorderPainted(false);
		button_back.setBounds(142 + WINDOW_X, 524, 116, 24);
		button_back.setPressedIcon(new ImageIcon("src/resources/button_back_pressed.png"));
		button_back.setRolloverIcon(new ImageIcon("src/resources/button_back_rollover.png"));
		button_back.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){runMenuScene();}});
		button_back.setBorderPainted(false);
		button_back_game.setBounds(2 + WINDOW_X, 564, 34, 34);
		button_back_game.setPressedIcon(new ImageIcon("src/resources/button_play_back_pressed.png"));
		button_back_game.setRolloverIcon(new ImageIcon("src/resources/button_play_back_rollover.png"));
		button_back_game.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){runMenuScene();}});
		button_back_game.setBorderPainted(false);
		button_reload.setBounds(38 + WINDOW_X, 564, 34, 34);
		button_reload.setPressedIcon(new ImageIcon("src/resources/button_play_reload_pressed.png"));
		button_reload.setRolloverIcon(new ImageIcon("src/resources/button_play_reload_rollover.png"));
		button_reload.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){reload();}});
		button_reload.setBorderPainted(false);
		
		// - - - - - T O G G L E S - - - - -
		toggle_sound_music.setBounds(223 + WINDOW_X, 224, 18, 18);
		toggle_sound_music.setSelectedIcon(new ImageIcon("src/resources/toggle_options_on.png"));
		toggle_sound_music.setPressedIcon(new ImageIcon("src/resources/toggle_options_pressed.png"));
		toggle_sound_music.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){toggleSoundMusic();}});
		toggle_sound_music.setSelected(Options.musicSound);
		toggle_sound_effects.setBounds(223 + WINDOW_X, 245,18,18);
		toggle_sound_effects.setSelectedIcon(new ImageIcon("src/resources/toggle_options_on.png"));
		toggle_sound_effects.setPressedIcon(new ImageIcon("src/resources/toggle_options_pressed.png"));
		toggle_sound_effects.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){toggleSoundEffects();}});
		toggle_sound_effects.setSelected(Options.effectsSound);
		toggle_game_controls.setBounds(223 + WINDOW_X, 296, 120, 18);
		toggle_game_controls.setSelectedIcon(new ImageIcon("src/resources/toggle_options_control_on.png"));
		toggle_game_controls.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){switchControlScheme();}});
		toggle_game_controls.setSelected(Options.arrowMovement);
		for(int x = 0; x < 4; x++){
			final int y = x;
			toggle_game_difficulty[x].setBounds(223 + WINDOW_X + x*20, 317, 18, 18);
			toggle_game_difficulty[x].setSelectedIcon(new ImageIcon("src/resources/toggle_options_on.png"));
			toggle_game_difficulty[x].setPressedIcon(new ImageIcon("src/resources/toggle_options_pressed.png"));
			toggle_game_difficulty[x].addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){switchDifficulty(y);}});
		}
		toggle_game_difficulty[Options.difficulty].setSelected(true);
		toggle_stats_reset.setBounds(223 + WINDOW_X, 365, 18, 18);
		toggle_stats_reset.setSelectedIcon(new ImageIcon("src/resources/toggle_options_on.png"));
		toggle_stats_reset.setPressedIcon(new ImageIcon("src/resources/toggle_options_pressed.png"));
		toggle_stats_reset.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){resetStats();}});
		
		// - - - - -  T E X T  F I E L D S - - - - -
		text_score.setBounds(88 + WINDOW_X, 568, 150, 25);
		text_score.setForeground(Color.BLACK);
		text_score.setBackground(null);
		text_score.setEditable(false);
		text_score.setHighlighter(null);
		text_score.setFont(new Font("Roboto", Font.PLAIN, 25));
		text_score.setVisible(false);
		for(int x = 0; x < 3; x++){
			text_stats[x].setForeground(Color.BLACK);
			text_stats[x].setEditable(false);
			text_stats[x].setBounds(18 + WINDOW_X + 124*x, 173, 117, 300);
			text_stats[x].setHighlighter(null);
			text_stats[x].setFont(new Font("Roboto", Font.PLAIN, 12));
			text_stats[x].setBackground(null);
			text_stats[x].setOpaque(false);  
			text_stats[x].setBorder(null); 
			text_stats[x].setText(" 1 \n\n 2 \n\n 3 \n\n 4 \n\n 5 \n\n 6 \n\n 7 \n\n 8 \n\n 9 \n\n 10");
		}
		text_warning.setBounds(100 + WINDOW_X, 415, 250, 100);
		text_warning.setForeground(Color.RED);
		text_warning.setBackground(null);
		text_warning.setEditable(false);
		text_warning.setHighlighter(null);
		text_warning.setFont(new Font("Roboto", Font.PLAIN, 14));
		text_warning.setOpaque(false);  
		text_warning.setBorder(null); 
		text_warning.setVisible(false);
		
		// - - - - - P R O G R E S S  B A R S - - - - -
		//BufferedImage img = null;
		//try {img = ImageIO.read(new File("src/resources/progress_play_fill.png"));} catch (IOException e) {e.printStackTrace();}
		//Image dimg = img.getScaledInstance(100, 22, 4);
		//progress_play_fill = new JLabel(new ImageIcon(dimg));
		//progress_play_fill.setBounds(133 + WINDOW_X, 570, 100, 22);
		
		// - - - - - S C E N E S - - - - -
		scene_logo.setBounds(0, 0, WINDOW_X, WINDOW_Y);
		scene_menu.setBounds(0, 0, WINDOW_X, WINDOW_Y);
		scene_play.setBounds(WINDOW_X, 0, WINDOW_X, WINDOW_Y);
		scene_about.setBounds(WINDOW_X, 0, WINDOW_X, WINDOW_Y);
		scene_stats.setBounds(WINDOW_X, 0, WINDOW_X, WINDOW_Y);
		scene_options.setBounds(WINDOW_X, 0, WINDOW_X, WINDOW_Y);
		scene_background_1.setBounds(0, 0, WINDOW_X, WINDOW_Y);
		scene_background_2.setBounds(0, WINDOW_Y, WINDOW_X, WINDOW_Y);
		
		// - - - - - F R A M E - - - - -
		frame_main.setTitle("Munch");
		frame_main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame_main.setSize(WINDOW_X + WINDOW_X_OFFSET, WINDOW_Y + WINDOW_Y_OFFSET);
		frame_main.getContentPane().setBackground(Color.WHITE);
		frame_main.setLocationRelativeTo(null);
		frame_main.setLayout(null);
		frame_main.setResizable(false);
		frame_main.getContentPane().add(panel_game);
		frame_main.getContentPane().add(panel_menu);
		for(JComponent c : game_components) panel_menu.add(c);
		for(JComponent c : menu_components) panel_menu.add(c);
		frame_main.setVisible(true);

		// - - - - - A C T I O N  M A P S - - - - -
		switchControlScheme();
		
		panel_menu.setVisible(true);
		runLogoScene();
	}
	
	public void runMenuScene(){
			 if(openScene == "logo"){
			//placeholder - organizational purposes
		} 
		else if(openScene == "play"){
			new Thread() {
				public void run() {
					Player.playing = false;
					openScene = "menu";
					//backgroundSlideTimer();
					for(int x = 0; x < 200; x++) {
						button_play.setLocation(button_play.getX()+2, button_play.getY());
						button_stats.setLocation(button_stats.getX()+2, button_stats.getY());
						button_about.setLocation(button_about.getX()+2, button_about.getY());
						button_options.setLocation(button_options.getX()+2, button_options.getY());
						scene_menu.setLocation(scene_menu.getX()+2, scene_menu.getY());
						scene_background_1.setLocation(scene_background_1.getX()+2, scene_menu.getY());
						scene_background_2.setLocation(scene_background_2.getX()+2, scene_background_2.getY());
						for(JComponent c : game_components) c.setLocation(c.getX()+2, c.getY());
						try{Thread.sleep(1);} catch (InterruptedException ex) {}
					}
					Statistics.addStat(Player.p.getScore());
					// SoundBank.sound_play_theme();
					GameClock.stopGameClock();
				}
			}.start();
		}
		else if(openScene == "stats"){
			new Thread(){
				public void run(){
					for(int x = 0; x < 100; x++){
						scene_stats.setLocation(scene_stats.getX()+4, scene_stats.getY());
						button_back.setLocation(button_back.getX()+4, button_back.getY());
						button_stats_top.setLocation(button_stats_top.getX()+4, button_stats_top.getY());
						button_stats_recent.setLocation(button_stats_recent.getX()+4, button_stats_recent.getY());
						text_stats[0].setLocation(text_stats[0].getX()+4, text_stats[0].getY());
						text_stats[1].setLocation(text_stats[1].getX()+4, text_stats[1].getY());
						text_stats[2].setLocation(text_stats[2].getX()+4, text_stats[2].getY());
						try{Thread.sleep(1);} catch(InterruptedException ex){}
					}
					for(int x = 0; x < 59; x++){
						button_stats.setLocation(button_stats.getX(), button_stats.getY()+2);
						try{Thread.sleep(1);} catch(InterruptedException ex){}
					}
					for(int x = 0; x < 62; x++){
						button_play.setLocation(button_play.getX()+3, button_play.getY());
						button_about.setLocation(button_about.getX()+3, button_about.getY());
						button_options.setLocation(button_options.getX()+3, button_options.getY());
						try{Thread.sleep(1);} catch(InterruptedException ex){}
					}
					button_stats_top.setEnabled(false);
					button_stats_recent.setEnabled(false);
				}
			}.start();
		}
		else if(openScene == "about"){
			new Thread(){
				public void run(){
					for(int x = 0; x < 100; x++){
						scene_about.setLocation(scene_about.getX()+4, scene_about.getY());
						button_back.setLocation(button_back.getX()+4, button_back.getY());
						try{Thread.sleep(1);} catch(InterruptedException ex){}
					}
					for(int x = 0; x < 55; x++){
						button_about.setLocation(button_about.getX(), button_about.getY()+3);
						try{Thread.sleep(1);} catch(InterruptedException ex){}
					}
					for(int x = 0; x < 62; x++){
						button_play.setLocation(button_play.getX()+3, button_play.getY());
						button_stats.setLocation(button_stats.getX()+3, button_stats.getY());
						button_options.setLocation(button_options.getX()+3, button_options.getY());
						try{Thread.sleep(1);} catch(InterruptedException ex){}
					}
				}
			}.start();
		}
		else if(openScene == "options"){
			new Thread(){
				public void run(){
					for(int x = 0; x < 100; x++){
						scene_options.setLocation(scene_options.getX()+4, scene_options.getY());
						button_back.setLocation(button_back.getX()+4, button_back.getY());
						toggle_sound_music.setLocation(toggle_sound_music.getX()+4, toggle_sound_music.getY());
						toggle_sound_effects.setLocation(toggle_sound_effects.getX()+4, toggle_sound_effects.getY());
						toggle_game_controls.setLocation(toggle_game_controls.getX()+4, toggle_game_controls.getY());
						toggle_stats_reset.setLocation(toggle_stats_reset.getX()+4, toggle_stats_reset.getY());
						toggle_game_difficulty[0].setLocation(toggle_game_difficulty[0].getX()+4, toggle_game_difficulty[0].getY());
						toggle_game_difficulty[1].setLocation(toggle_game_difficulty[1].getX()+4, toggle_game_difficulty[1].getY());
						toggle_game_difficulty[2].setLocation(toggle_game_difficulty[2].getX()+4, toggle_game_difficulty[2].getY());
						toggle_game_difficulty[3].setLocation(toggle_game_difficulty[3].getX()+4, toggle_game_difficulty[3].getY());
						text_warning.setLocation(text_warning.getX()+4, text_warning.getY());
						try{Thread.sleep(1);} catch(InterruptedException ex){}
					}
					for(int x = 0; x < 71; x++){
						button_options.setLocation(button_options.getX(), button_options.getY()+3);
						try{Thread.sleep(1);} catch(InterruptedException ex){}
					}
					for(int x = 0; x < 62; x++){
						button_play.setLocation(button_play.getX()+3, button_play.getY());
						button_stats.setLocation(button_stats.getX()+3, button_stats.getY());
						button_about.setLocation(button_about.getX()+3, button_about.getY());
						try{Thread.sleep(1);} catch(InterruptedException ex){}
					}	
					text_warning.setVisible(false);
					toggle_stats_reset.setSelected(false);
				}
			}.start();
		}
		openScene = "menu";
		button_back.setEnabled(false);
		button_play.setEnabled(true);
		button_about.setEnabled(true);
		button_options.setEnabled(true);
		button_stats.setEnabled(true);
	}
	public void runPlayScene(){
		//openScene = "play";
		new Thread() {
			public void run() {
				Food.food.clear();
				Player.p = new Player();
				text_score.setText("Score: 0");	
				button_play.setEnabled(false);
				button_about.setEnabled(false);
				button_options.setEnabled(false);
				button_stats.setEnabled(false);
				for(int x = 0; x < 200; x++) {
					button_play.setLocation(button_play.getX() - 2, button_play.getY());
					button_stats.setLocation(button_stats.getX() - 2, button_stats.getY());
					button_about.setLocation(button_about.getX() - 2, button_about.getY());
					button_options.setLocation(button_options.getX() - 2, button_options.getY());
					scene_menu.setLocation(scene_menu.getX() - 2, scene_menu.getY());
					scene_background_1.setLocation(scene_background_1.getX() - 2, scene_menu.getY());
					scene_background_2.setLocation(scene_background_2.getX() - 2, scene_background_2.getY());
					for(JComponent c : game_components) c.setLocation(c.getX() - 2, c.getY());
					try{Thread.sleep(1);} catch (InterruptedException ex) {}
				}
				// SoundBank.sound_play_simpleTheme();
				GameClock.startGameClock();
				for (int x = 0; x < 300 / GameClock.movementSpeed; x++) {
					Player.p.setY(Player.p.getY() + GameClock.movementSpeed/2);
					try{Thread.sleep(10);} catch (InterruptedException ex) {}
				}
				openScene = "play";
				Player.playing = true;
				panel_game.requestFocusInWindow();
				GamePanel.scrolling = true;
			}
		}.start();
	}
	public void runStatsScene(){
		openScene = "stats";
		new Thread(){
			public void run(){
				//15 x 99 @ 216=y
				updateRecentStats();
				button_back.setEnabled(true);
				button_play.setEnabled(false);
				button_about.setEnabled(false);
				button_options.setEnabled(false);
				button_stats.setEnabled(false);
				button_stats_top.setEnabled(!Options.stats_top);
				button_stats_recent.setEnabled(Options.stats_top);
				for(int x = 0; x < 62; x++){
					button_play.setLocation(button_play.getX()-3, button_play.getY());
					button_about.setLocation(button_about.getX()-3, button_about.getY());
					button_options.setLocation(button_options.getX()-3, button_options.getY());
					try{Thread.sleep(1);} catch(InterruptedException ex){}
				}
				for(int x = 0; x < 59; x++){
					button_stats.setLocation(button_stats.getX(), button_stats.getY()-2);
					try{Thread.sleep(1);} catch(InterruptedException ex){}
				}
				for(int x = 0; x < 100; x++){
					scene_stats.setLocation(scene_stats.getX()-4, scene_stats.getY());
					button_back.setLocation(button_back.getX()-4, button_back.getY());
					button_stats_top.setLocation(button_stats_top.getX()-4, button_stats_top.getY());
					button_stats_recent.setLocation(button_stats_recent.getX()-4, button_stats_recent.getY());
					text_stats[0].setLocation(text_stats[0].getX()-4, text_stats[0].getY());
					text_stats[1].setLocation(text_stats[1].getX()-4, text_stats[1].getY());
					text_stats[2].setLocation(text_stats[2].getX()-4, text_stats[2].getY());
					try{Thread.sleep(1);} catch(InterruptedException ex){}
				}
				
			}
		}.start();
	}
	public void runAboutScene(){
		openScene = "about";
		new Thread(){
			public void run(){
				//15 x 99 @ 216=y
				button_back.setEnabled(true);
				button_play.setEnabled(false);
				button_about.setEnabled(false);
				button_options.setEnabled(false);
				button_stats.setEnabled(false);
				for(int x = 0; x < 62; x++){
					button_play.setLocation(button_play.getX()-3, button_play.getY());
					button_stats.setLocation(button_stats.getX()-3, button_stats.getY());
					button_options.setLocation(button_options.getX()-3, button_options.getY());
					try{Thread.sleep(1);} catch(InterruptedException ex){}
				}
				for(int x = 0; x < 55; x++){
					button_about.setLocation(button_about.getX(), button_about.getY()-3);
					try{Thread.sleep(1);} catch(InterruptedException ex){}
				}
				for(int x = 0; x < 100; x++){
					scene_about.setLocation(scene_about.getX()-4, scene_about.getY());
					button_back.setLocation(button_back.getX()-4, button_back.getY());
					try{Thread.sleep(1);} catch(InterruptedException ex){}
				}
			}
		}.start();
	}
	private void runOptionsScene(){
		openScene = "options";
		new Thread(){
			public void run(){
				//15 x 99 @ 312=y
				button_back.setEnabled(true);
				button_play.setEnabled(false);
				button_about.setEnabled(false);
				button_options.setEnabled(false);
				button_stats.setEnabled(false);
				for(int x = 0; x < 62; x++){
					button_play.setLocation(button_play.getX()-3, button_play.getY());
					button_stats.setLocation(button_stats.getX()-3, button_stats.getY());
					button_about.setLocation(button_about.getX()-3, button_about.getY());
					try{Thread.sleep(1);} catch(InterruptedException ex){}
				}
				for(int x = 0; x < 71; x++){
					button_options.setLocation(button_options.getX(), button_options.getY()-3);
					try{Thread.sleep(1);} catch(InterruptedException ex){}
				}
				for(int x = 0; x < 100; x++){
					scene_options.setLocation(scene_options.getX()-4, scene_options.getY());
					button_back.setLocation(button_back.getX()-4, button_back.getY());
					toggle_sound_music.setLocation(toggle_sound_music.getX()-4, toggle_sound_music.getY());
					toggle_sound_effects.setLocation(toggle_sound_effects.getX()-4, toggle_sound_effects.getY());
					toggle_game_controls.setLocation(toggle_game_controls.getX()-4, toggle_game_controls.getY());
					toggle_stats_reset.setLocation(toggle_stats_reset.getX()-4, toggle_stats_reset.getY());
					toggle_game_difficulty[0].setLocation(toggle_game_difficulty[0].getX()-4, toggle_game_difficulty[0].getY());
					toggle_game_difficulty[1].setLocation(toggle_game_difficulty[1].getX()-4, toggle_game_difficulty[1].getY());
					toggle_game_difficulty[2].setLocation(toggle_game_difficulty[2].getX()-4, toggle_game_difficulty[2].getY());
					toggle_game_difficulty[3].setLocation(toggle_game_difficulty[3].getX()-4, toggle_game_difficulty[3].getY());
					text_warning.setLocation(text_warning.getX()-4, text_warning.getY());
					try{Thread.sleep(1);} catch(InterruptedException ex){}
				}
			}
		}.start();
	}
	private void runLogoScene(){
		openScene = "logo";
		new Thread(){
			public void run(){
				//backgroundSlideTimer();
				button_play.setVisible(false);
				button_stats.setVisible(false);
				button_about.setVisible(false);
				button_options.setVisible(false);
				scene_menu.setVisible(false);
				scene_logo.setVisible(false);
				try{Thread.sleep(1000);}catch(InterruptedException ex){}
				scene_logo.setVisible(true);
				try{Thread.sleep(2500);}catch(InterruptedException ex){}
				scene_logo.setVisible(false);
				try{Thread.sleep(800);}catch(InterruptedException ex){}
				scene_menu.setVisible(true);
				button_play.setVisible(true);
				button_stats.setVisible(true);
				button_about.setVisible(true);
				button_options.setVisible(true);
				runMenuScene();
			}
		}.start();
	}
	
	public void setTextScore(int score){
		text_score.setText("Score: " + Player.p.getScore());
	}
	public void switchStatsPage(){
		Options.stats_top = !Options.stats_top;
		button_stats_top.setEnabled(!Options.stats_top);
		button_stats_recent.setEnabled(Options.stats_top);
		if(Options.stats_top) updateTopStats();
		else updateRecentStats();
	}
	public void switchDifficulty(int difficulty){
		toggle_game_difficulty[0].setSelected(false);
		toggle_game_difficulty[1].setSelected(false);
		toggle_game_difficulty[2].setSelected(false);
		toggle_game_difficulty[3].setSelected(false);
		toggle_game_difficulty[difficulty].setSelected(true);
		Options.difficulty = difficulty;
	}
	public void toggleSoundMusic(){
		Options.musicSound = !Options.musicSound;
	}
	public void toggleSoundEffects(){
		Options.effectsSound = !Options.effectsSound;
	}
	public void switchControlScheme(){
		Options.updateOptionsFile();
		Options.arrowMovement = !Options.arrowMovement;
		if (toggle_game_controls.isSelected()) {
			InputMap in = panel_game.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
			in.put(KeyStroke.getKeyStroke("LEFT"), null);
			in.put(KeyStroke.getKeyStroke("RIGHT"), null);
			
			ActionMap am = panel_game.getActionMap();
			in.put(KeyStroke.getKeyStroke('a'), "doA_Pressed");
			am.put("doA_Pressed", new InputController.A_Pressed());
			in.put(KeyStroke.getKeyStroke('d'), "doD_Pressed");
			am.put("doD_Pressed", new InputController.D_Pressed());
		} else {
			InputMap in = panel_game.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
			in.put(KeyStroke.getKeyStroke('a'), null);
			in.put(KeyStroke.getKeyStroke('d'), null);
			
			ActionMap am = panel_game.getActionMap();
			in.put(KeyStroke.getKeyStroke("LEFT"), "doA_Pressed");
			am.put("doA_Pressed", new InputController.A_Pressed());
			in.put(KeyStroke.getKeyStroke("RIGHT"), "doD_Pressed");
			am.put("doD_Pressed", new InputController.D_Pressed());
		}
	}
	public void backgroundSlideTimer(){
		new Thread(){
			public void run(){
				while(openScene != "play"){
					slideBackground();
					try{Thread.sleep(25);} catch(InterruptedException ex){}
				}
			}
		}.start();
	}
	public void reload(){
		new Thread() {
			public void run() {
				GamePanel.scrolling = false;
				Food.food.clear();
				Player.p = new Player();
				Player.playing = true;
				text_score.setText("Score: 0");	
				// SoundBank.sound_play_simpleTheme();ê
				for (int x = 0; x < 300 / GameClock.movementSpeed; x++) {
					Player.p.setY(Player.p.getY() + GameClock.movementSpeed/2);
					try{Thread.sleep(10);} catch (InterruptedException ex) {}
				}
				System.out.println("===================RELOAD BUTTON====================================");
				for(JComponent c : menu_components) System.out.println("- " + c + "\n    - Focus Owner: " + c.isFocusOwner());
				System.out.println("===================RELOAD BUTTON====================================");
				for(JComponent c : game_components) System.out.println("- " + c + "\n    - Focus Owner: " + c.isFocusOwner());
				System.out.println("=======================================================");
				GamePanel.scrolling = true;
			}
		}.start();
	}
	
	public void slideBackground(){
		int amount = 0;
		if(openScene != "play") 
			amount = 1;
		else amount = GameClock.movementSpeed;
		scene_background_1.setLocation(scene_background_1.getX(), scene_background_1.getY()-amount);
		scene_background_2.setLocation(scene_background_2.getX(), scene_background_2.getY()-amount);
		if(scene_background_1.getY()+scene_background_1.getHeight() < 1) 
			scene_background_1.setLocation(scene_background_1.getX(), scene_background_2.getY() + scene_background_2.getHeight());
		if(scene_background_2.getY()+scene_background_2.getHeight() < 1) 
			scene_background_2.setLocation(scene_background_2.getX(), scene_background_1.getY() + scene_background_1.getHeight());
	}
	public void updateRecentStats(){
		int capacity = 0; // out of 10
		Scanner sc = new Scanner(Statistics.getRecentRecords());
		StringBuilder stringBuilder;
		
		for(int x = 0; x < 3; x++){
			stringBuilder = new StringBuilder();
			while(capacity != 10 && sc.hasNextLine()){
				String line = sc.nextLine();
				String scoreText = line.substring(0, line.indexOf("@") - 1);
				String dateText = line.substring(line.indexOf("@") + 2, line.length());
				stringBuilder.append("Score: " + scoreText + "\n   " + dateText + "\n");
				capacity++;
			}
			text_stats[x].setText(stringBuilder.toString());
			capacity = 0;
		}
	}
	public void updateTopStats(){
		int capacity = 1; // out of 10
		Scanner sc = new Scanner(Statistics.getTopRecords());
		StringBuilder stringBuilder;
		
		stringBuilder = new StringBuilder();
		while (capacity != 11 && sc.hasNextLine()) {
			String line = sc.nextLine();
			String scoreText = line.substring(0, line.indexOf("@"));
			String dateText = line.substring(line.indexOf("@") + 2, line.length());
			stringBuilder.append(capacity + ": " + scoreText + "\n    " + dateText + "\n");
			capacity++;
		}
		text_stats[0].setText("");
		text_stats[1].setText(stringBuilder.toString());
		text_stats[2].setText("");
	}
	public void resetStats(){
		if(text_warning.isVisible()) text_warning.setVisible(false);
		else text_warning.setVisible(true);
	}
}
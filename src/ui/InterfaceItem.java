package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import platform.InputController;

import entities.Food;
import entities.GameClock;
import entities.Player;

public class InterfaceItem {
	
	public static final int        WINDOW_X = 400;
	public static final int        WINDOW_Y = 600;
	public static final int WINDOW_X_OFFSET = 0;
	public static final int WINDOW_Y_OFFSET = 22;
	
	private JFrame frame_main		= new MainFrame();
	private JPanel panel_menu 		= new JPanel(new BorderLayout());
	private JPanel panel_game 		= new GamePanel();
	private JLabel scene_logo 		= new JLabel(new ImageIcon("src/resources/scene_logo.png"));
	private JLabel scene_play 		= new JLabel(new ImageIcon("src/resources/scene_play.png"));
	private JLabel scene_menu 		= new JLabel(new ImageIcon("src/resources/scene_menu.png"));
	private JLabel scene_stats 		= new JLabel(new ImageIcon("src/resources/scene_stats.png"));
	private JLabel scene_about 		= new JLabel(new ImageIcon("src/resources/scene_about.png"));
	private JLabel scene_options	= new JLabel(new ImageIcon("src/resources/scene_options.png"));
	private JButton button_play 	= new JButton(new ImageIcon("src/resources/button_play.png"));
	private JButton button_stats 	= new JButton(new ImageIcon("src/resources/button_stats.png"));
	private JButton button_about 	= new JButton(new ImageIcon("src/resources/button_about.png"));
	private JButton button_options  = new JButton(new ImageIcon("src/resources/button_options.png"));
	private JButton button_back_game= new JButton(new ImageIcon("src/resources/button_back.png"));
	private JButton button_back_menu= new JButton(new ImageIcon("src/resources/button_back.png"));
	private JButton button_reload 	= new JButton(new ImageIcon("src/resources/button_reload.png"));
	private JTextArea text_score 	= new JTextArea("0");
	private JTextArea text_food 	= new JTextArea("0 / 10");
	
	@SuppressWarnings("unchecked")
	private ArrayList<JComponent> menu_components = new ArrayList<JComponent>(Arrays.asList(
			button_play,
			button_stats,
			button_about,
			button_options,
			button_back_menu,
			scene_menu,
			scene_stats,
			scene_about,
			scene_options,
			scene_logo
			));
	@SuppressWarnings("unchecked")
	private ArrayList<JComponent> game_components = new ArrayList<JComponent>(Arrays.asList(
			panel_game,
			button_reload,
			button_back_game,
			text_score,
			text_food,
			scene_play
			));
	
	public InterfaceItem(){
		// - - - - - P A N E L S - - - - -
		panel_menu.setSize(WINDOW_X, WINDOW_Y);
		panel_game.setSize(WINDOW_X, WINDOW_Y-39);
		panel_game.setBackground(Color.WHITE);
		
		// - - - - - B U T T O N S - - - - -
		button_play.setBounds(87, 237, 229, 39);
		button_play.setPressedIcon(new ImageIcon("src/resources/button_play_pressed.png"));
		button_play.setRolloverIcon(new ImageIcon("src/resources/button_play_rollover.png"));
		button_play.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){runPlayScene();}});
		button_stats.setBounds(87, 291, 229, 39);
		button_stats.setPressedIcon(new ImageIcon("src/resources/button_stats_pressed.png"));
		button_stats.setRolloverIcon(new ImageIcon("src/resources/button_stats_rollover.png"));
		button_stats.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){runStatsScene();}});
		button_about.setBounds(87, 345, 229, 39);
		button_about.setPressedIcon(new ImageIcon("src/resources/button_about_pressed.png"));
		button_about.setRolloverIcon(new ImageIcon("src/resources/button_about_rollover.png"));
		button_about.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){runAboutScene();}});
		button_options.setBounds(87, 399, 229, 39);
		button_options.setPressedIcon(new ImageIcon("src/resources/button_options_pressed.png"));
		button_options.setRolloverIcon(new ImageIcon("src/resources/button_options_rollover.png"));
		button_options.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){runOptionsScene();}});
		button_back_menu.setBounds(0, 561, 39, 39);
		button_back_menu.setPressedIcon(new ImageIcon("src/resources/button_back_pressed.png"));
		button_back_menu.setRolloverIcon(new ImageIcon("src/resources/button_back_rollover.png"));
		button_back_menu.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){runMenuScene();}});
		button_back_game.setBounds(0, 561, 39, 39);
		button_back_game.setPressedIcon(new ImageIcon("src/resources/button_back_pressed.png"));
		button_back_game.setRolloverIcon(new ImageIcon("src/resources/button_back_rollover.png"));
		button_back_game.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){runMenuScene();}});
		button_reload.setBounds(39, 561, 39, 39);
		button_reload.setPressedIcon(new ImageIcon("src/resources/button_reload_pressed.png"));
		button_reload.setRolloverIcon(new ImageIcon("src/resources/button_reload_rollover.png"));
		button_reload.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){runPlayScene();}});
		
		// - - - - -  T E X T  A R E A S - - - - -
		text_score.setBounds(88, 568, 150, 25);
		text_score.setForeground(Color.BLACK);
		text_score.setBackground(Color.WHITE);
		text_score.setEditable(false);
		text_score.setHighlighter(null);
		text_score.setFont(new Font("LeviWindows", Font.PLAIN, 35));
		text_food.setBounds(248, 568, 115, 25);
		text_food.setForeground(Color.BLACK);
		text_food.setBackground(Color.WHITE);
		text_food.setEditable(false);
		text_food.setHighlighter(null);
		text_food.setFont(new Font("LeviWindows", Font.PLAIN, 35));
		
		// - - - - - S C E N E S - - - - -
		scene_play.setBounds(0, 0, WINDOW_X, WINDOW_Y);
		scene_logo.setBounds(0, 0, WINDOW_X, WINDOW_Y);
		scene_menu.setBounds(0, 0, WINDOW_X, WINDOW_Y);
		scene_about.setBounds(0, 0, WINDOW_X, WINDOW_Y);
		scene_stats.setBounds(0, 0, WINDOW_X, WINDOW_Y);
		scene_options.setBounds(0, 0, WINDOW_X, WINDOW_Y);
		
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
		for(JComponent c : menu_components) panel_menu.add(c);
		for(JComponent c : game_components) panel_menu.add(c);
		frame_main.setVisible(true);

		// - - - - - A C T I O N  M A P S - - - - -
		InputMap in = panel_game.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap am = panel_game.getActionMap();
		in.put(KeyStroke.getKeyStroke("LEFT"), "doA_Pressed");
		am.put("doA_Pressed", new InputController.A_Pressed());
		in.put(KeyStroke.getKeyStroke("RIGHT"), "doD_Pressed");
		am.put("doD_Pressed", new InputController.D_Pressed());
		in.put( KeyStroke.getKeyStroke('r'), "doR_Pressed");
		am.put("doR_Pressed", new InputController.R_Pressed());
		in.put(KeyStroke.getKeyStroke('p'), "doP_Pressed");
		am.put( "doP_Pressed", new InputController.P_Pressed());

		for(JComponent c : game_components){
			c.setVisible(false);
			c.setLocation(c.getX() + WINDOW_X, c.getY());
		}
		for(JComponent c : menu_components) c.setVisible(false);
		panel_menu.setVisible(true);
		
		
		runLogoScene();
	}
	
	public void runMenuScene(){
		if(panel_game.isVisible()){
			new Thread(){
				public void run(){
					for(int x = 0; x < 200; x++){
						for(JComponent c : menu_components) c.setLocation(c.getX()+2, c.getY());
						for(JComponent c : game_components) c.setLocation(c.getX()+2, c.getY());
						try{Thread.sleep(1);} catch(InterruptedException ex){}
					}
					GameClock.stopGameClock();
					// SoundBank.sound_play_theme();
					Player.playing = false;
					Food.food.clear();
					Player.p.setScore(0);
					Player.p.setFood(0);
					panel_game.setVisible(false);
				}
			}.start();
		}
		else {
			GameClock.stopGameClock();
			// SoundBank.sound_play_theme();
			Player.playing = false;
			Food.food.clear();
			Player.p.setScore(0);
			Player.p.setFood(0);
			scene_menu.setVisible(true);
			button_play.setVisible(true);
			button_stats.setVisible(true);
			button_about.setVisible(true);
			button_options.setVisible(true);
		}
	}
	public void runPlayScene(){
		if(button_play.getX() > 0){
				new Thread(){
					public void run(){
						Food.food.clear();
						Player.p = new Player();
						Player.playing = true;
						for(JComponent c : game_components) c.setVisible(true);
						System.out.println("Location of reload button: " + button_reload.getX() + ", " + button_reload.getY());
						for(int x = 0; x < 200; x++){
							for(JComponent c : menu_components) c.setLocation(c.getX()-2, c.getY());
							for(JComponent c : game_components) c.setLocation(c.getX()-2, c.getY());
							try{Thread.sleep(1);} catch(InterruptedException ex){}
						}
						// SoundBank.sound_play_simpleTheme();
						GameClock.startGameClock();
						for(int x = 0; x < 150/GameClock.movementSpeed; x++){
							Player.p.setY(Player.p.getY() + GameClock.movementSpeed);
							try{Thread.sleep(11);}
							catch(InterruptedException ex){}
						}
					}
				}.start(); 
		} else {
			// SoundBank.sound_play_simpleTheme();
			new Thread(){
				public void run(){
					for(int x = 0; x < 150/GameClock.movementSpeed; x++){
						Player.p.setY(Player.p.getY() + GameClock.movementSpeed);
						try{Thread.sleep(11);}
						catch(InterruptedException ex){}
					}
					text_food.setText("Food: 0/10");
					text_score.setText("Score: 0");
					Food.food.clear();
					Player.p = new Player();
					Player.playing = true;
				}
			}.start();
		}
	}
	public void runStatsScene(){
		scene_stats.setVisible(true);
		button_back_menu.setVisible(true);
	}
	public void runAboutScene(){
		scene_about.setVisible(true);
		button_back_menu.setVisible(true);
	}
	private void runOptionsScene(){
		scene_options.setVisible(true);
		button_back_menu.setVisible(true);
	}
	private void runLogoScene(){
		scene_logo.setVisible(true);
		try{Thread.sleep(2000);}catch(InterruptedException ex){}
		scene_logo.setVisible(false);
		runMenuScene();
	}
	
	public void setTextScore(int score){
		text_score.setText("Score: " + Player.p.getScore());
	}
	public void setTextFood(int food, int foodNeeded){
		text_food.setText("Food: " + Player.p.getFood() + "/" + Player.p.getFoodNeeded());
	}
}
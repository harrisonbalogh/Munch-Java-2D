package platform;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import entities.*;

public class Launch extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	HELLO JAVA TEAM,
		This is the project 'Munch'. Browse through the project's
	classes as you like and check out any comments I left to make
	some areas of the project more clear...
	
	I'll note below things I think need to be implemented... (in order of precedence):
		- Gameplay algorithm to ensure the player won't be stumped with a solid wall of unpassable objects.
		- Sound affects for eating and dying, as well as menu/in-game music. Sound resources already provided in project's resources folder.
		- Screens for the About/Stats/Credits/Options menu buttons.
		- Gameplay difficulty adjuster. Difficulty explained below in comments.
		- Create images for the "food" that the player "eats". Doesn't have to be food. And doesn't have to eat it.
		- Constant movement animation for player character and possible animation when food is eaten.
		- (Temporarily Disabled) Logo screen shows before menu screen. scene_logo.png already provided. [Assuming we're team four.]
		иии *Please throw out more ideas to the group* иии
	
	Jump to main() towards bottom... GOTO line 138
	 */
	
	public static Player p;
	public static InterfaceItem ui;
	public static GameClock gc;
	public static boolean playing = false;
	
	// Difficulty should affect the rate the time refreshes, the max size of the foods, and the speed of the food.
	public static final boolean ARROW_MOVEMENT 	= true;
	public static final int        WINDOW_X = 400;
	public static final int        WINDOW_Y = 600;
	public static final int WINDOW_X_OFFSET = 0;
	public static final int WINDOW_Y_OFFSET = 22;
	public static final int     GRID_LENGTH = 20; // pixel width per grid space
	public static final int  MOVEMENT_SPEED = 2;  // pixels per repaint rate
	public static final int      CLOCK_RATE = 50; // in milliseconds
	public static final int    SPAWN_CHANCE = 2;  // out of 10
	public static final int   MAX_FOOD_SIZE = 5;  // in grid count number form
	
	
	public Launch(){
		// The next 7 lines are properties initialized for the JFrame.
		setTitle("Munch");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WINDOW_X + WINDOW_X_OFFSET, WINDOW_Y + WINDOW_Y_OFFSET);
		getContentPane().setBackground(Color.WHITE);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		
		// The next set of code adds two JPanel's to the JFrame and adds the advanced equivalent of KeyListeners (In this case called Key Bindings) to those JPanels.
		getContentPane().add(ui.panel_game);
		getContentPane().add(ui.panel_menu);
		// We use KeyBindings below instead of KeyListeners because KeyListeners rely on a JComponent being "focused" upon. Whenever a button is clicked
		// in a JFrame, you have to refocus the JPanel with the KeyListener to actually continue "listening" for key presses.
		// Basically this property called "focus" is moved around a lot, and KeyListeners are unreliable when focus is changing often.
		InputMap in = ui.panel_game.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap am = ui.panel_game.getActionMap();
		if(ARROW_MOVEMENT){
			in.put(KeyStroke.getKeyStroke("LEFT"), "doA_Pressed");
			am.put( "doA_Pressed", new InputController.A_Pressed());
			in.put( KeyStroke.getKeyStroke("RIGHT"), "doD_Pressed");
			am.put( "doD_Pressed", new InputController.D_Pressed());
		} else {
			in.put(KeyStroke.getKeyStroke('a'), "doA_Pressed");
			am.put( "doA_Pressed", new InputController.A_Pressed());
			in.put( KeyStroke.getKeyStroke('d'), "doD_Pressed");
			am.put( "doD_Pressed", new InputController.D_Pressed());
		}
		in.put(KeyStroke.getKeyStroke('p'), "doP_Pressed");
		am.put( "doP_Pressed", new InputController.P_Pressed());
		
		// You always set the JFrame to visible after all of its components have been added... Using: add(name_of_a_jcomponent);
		setVisible(true);
		
	}
	
	public void paint(Graphics g) { 
		// Paint currently does nothing by our JFrame, GOTO bottom of InterfaceItem class to see where painting of food and players is done.
		super.paint(g);
		paintComponent(g);

	}
	
	public void paintComponent(Graphics g){
		repaint();
	}
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
	        public void run(){
				// Anything that needs to happen when the program is started goes here.
				// New player has to be created here since InterfaceItem starts drawing all player objects at run-time.
				p = new Player();
				// See InterfaceItem class to see all interface buttons/labels/text that are initialized when a new InterfaceItem object is instantiated.
				ui = new InterfaceItem();
				// Must initialize game clock here before using startGameClock() method
				gc = new GameClock();
				// The Launch class extends a JFrame... so the below line creates a JFrame... GOTO line 62
				Launch la = new Launch();
				la.setVisible(true);
				// The below line runBlankScene() is this project's naming convention for displaying a different scene. Like About/Stats/Credits...
				ui.runMenuScene();
			}
		});
	
	}
	
	public static void wait(int time){
		try {
	    	Thread.sleep(time);
	    }
	    catch(InterruptedException ex) {}
	}
}

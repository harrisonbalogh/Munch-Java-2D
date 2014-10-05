package platform;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
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
	
	public static Random random = new Random(); // Library class needed for generated random numbers.
	
	public static Player p;
	public static InterfaceItem ui;
	
	// Constants for adjusting various application properties. Should be adjustable in-game in options menu. By adjusting gameplay difficulty.
	// Difficulty should affect the rate the time refreshes, the max size of the foods, and the speed of the food.
	public static final boolean ARROW_MOVEMENT 	= true;
	public static final int WINDOW_X 			= 400;
	public static final int WINDOW_Y			= 600;
	public static final int WINDOW_X_OFFSET		= 0;
	public static final int WINDOW_Y_OFFSET 	= 22;
	public static final int GRID_LENGTH 		= 20;
	public static final int MOVEMENT_SPEED		= 5;
	
	
	public Launch(){
		// The next 7 lines are properties initialized for the JFrame.
		setTitle("Munch");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WINDOW_X + WINDOW_X_OFFSET, WINDOW_Y + WINDOW_Y_OFFSET);
		getContentPane().setBackground(Color.WHITE);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		
		// The next set of code adds two JPanel's to the JFrame and adds basically KeyListeners (In this case called Key Bindings) to those JPanels.
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
		
		// You always set the JFrame to visible after all of its components have been added... Using: add(name_of_a_jcomponent);
		setVisible(true);
		
		// The below timer will be called our game clock. Which will be used to update the positions of the "food" and used to generate new "food".
		// The number 50 is how often the timer runs (in milliseconds).
		Timer timer = new Timer(50, new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				p.animate();
				if(p.getPlaying()){
					// The below two lines is our probability for generating new food... currently there's a 2/10 chance a food will be created every 50 milliseconds.
					int produceFood = random.nextInt(10);
					if(produceFood < 2){
						int size = (random.nextInt(5)+1)*GRID_LENGTH;
						int x = random.nextInt(WINDOW_X/GRID_LENGTH - size/GRID_LENGTH + 1) * GRID_LENGTH;
						int y = WINDOW_Y;
						boolean intersects = false;
						Rectangle rect = new Rectangle(x, y, size, size);
						for(Food f : Food.food)
							if(rect.intersects(f.rect))
								intersects = true;
						if (!intersects)
							new Food(x, y, size, null);
					}
					for(Food f : Food.food){
						f.setY(f.getY()-MOVEMENT_SPEED);
					}
				}
				//System.out.println("5/100th of a second");
            }
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();
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

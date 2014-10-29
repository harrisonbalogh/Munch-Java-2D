package platform;
import javax.swing.SwingUtilities;

import ui.Options;
import ui.UserDisplay;

public class Launch{
	/*
	
	NYI: (Not Yet Implemented)
		- Gameplay algorithm to ensure the player won't be stumped with a solid wall of unpassable objects.
		- Draw components over painted panels.
		- Show score at Game Over.
		- Options scene's buttons/toggles/sliders.
		- Background scene images (.png) for Stats/About/Credits/Options scenes.
		- Gameplay difficulty adjuster for options menu, which affects the food spawn rate time, max size of food, and speed of food.
		- "Food" and Player images (.png) and spritesheet containing their animaêtion frames.
		- Player and food animations in their respective classes [animate() method].
		- Re-implment logo screen that fades out before going to main menu.
		- ...
		
	Bugs:
		- (FIXED) Removing food from the CopyOnWriteArrayList doesn't work.
		- The menu music play method in the soundbank class doesn't work. Buffer at max capacity (song file too long).
	 */

	public static UserDisplay ui;
	
	public static final boolean ARROW_MOVEMENT 	= true; // Temporary. Replaced with options menu element.
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
	        public void run(){
				// See InterfaceItem class to see all interface buttons/labels/text that are initialized when a new InterfaceItem object is instantiated.
				ui = new UserDisplay();
				new Statistics();
				new Options();
			}
		});
	
	}
}

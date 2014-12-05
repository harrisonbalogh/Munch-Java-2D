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
		- Background scene images (.png) for Stats/About/Credits/Options scenes.
		- Gameplay difficulty adjuster.
		- "Food" and Player images (.png) and spritesheet containing their animaêtion frames.
		- Player and food animations in their respective classes [animate() method].
		- ...
	 */

	public static final int USING_RETINA_CONSTANT = 0; // set to 10 if on retina, set to 0 if not on retina
	
	public static UserDisplay ui;
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
	        public void run(){
				new Statistics();
				new Options();
				ui = new UserDisplay();

			}
		});
	
	}
}

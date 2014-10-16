package platform;
import javax.swing.SwingUtilities;
import entities.*;

public class Launch{
	/*
	
	NYI: (Not Yet Implemented)
		- Gameplay algorithm to ensure the player won't be stumped with a solid wall of unpassable objects.
		- Music in menu and during gameplay.
		- An options scene and menu button - along with the Options scene's buttons/toggles/sliders.
		- Background scene images (.png) for Stats/About/Credits/Options scenes.
		- Gameplay difficulty adjuster for options menu, which affects the food spawn rate time, max size of food, and speed of food.
		- "Food" and Player images (.png) and spritesheet containing their animaêtion frames.
		- Player and food animations in their respective classes [animate() method].
		- Re-implment logo screen that fades out before going to main menu.
		- Merge "Credits" scene into "About" scene to make room for "Options" scene.
		- ...
		
	Bugs:
		- Removing food from the CopyOnWriteArrayList doesn't work. Food builds up in the array list.
		- The menu music play method in the soundbank class doesn't work. Buffer at max capacity (song file too long).
	 */
	
	public static Player p;
	public static InterfaceItem ui;
	public static GameClock gc;
	public static boolean playing = false;
	
	public static final boolean ARROW_MOVEMENT 	= true; // Temporary. Replaced with options menu element.
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
	        public void run(){
				// Anything that needs to happen when the program is started goes here.
				// Must initialize game clock here before using startGameClock() method
				gc = new GameClock();
				// New player has to be created here since InterfaceItem starts drawing all player objects at run-time.
				p = new Player();
				// See InterfaceItem class to see all interface buttons/labels/text that are initialized when a new InterfaceItem object is instantiated.
				ui = new InterfaceItem();
				// The below line runBlankScene() is this project's naming convention for displaying a different scene. Like About/Stats/Credits...
				ui.runMenuScene();
			}
		});
	
	}
}

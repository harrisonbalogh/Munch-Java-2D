package entities;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;
import ui.UserDisplay;


public class GameClocks {

	// difficulty listings:
	/*
	 1: gridLength: 25
	 	movementSpeed: 4
	 	maxFoodSize: 5
	 
	 2: gridLength: 20
	 	movementSpeed: 5
	 	maxFoodSize: 6
	 
	 3: gridLength: 15
	 	movementSpped: 6
	 	maxFoodSize: 7
	 	
	 4: gridLength: 10
	 	movementSpeed: 7
		maxFoodSize: 8
	
	*/
	
	public static int  	    gridLength = 20; // pixel width per grid space
	public static int    movementSpeed = 6;  // pixels per repaint rate
	public static int      spawnChance = 2;  // out of 10
	public static int      maxFoodSize = 5;  // in grid count number form
	public final int 		SPAWN_RATE = 50; // in milliseconds
	public final int 		BUBBLE_RATE = 150; // in milliseconds
	
	//private static ArrayList<Rectangle>[] checkers;
	//private static boolean[] checkerState;
	private static Random random = new Random();
	private static Timer spawnClock;
	private static Timer bubbleClock;
	
	public GameClocks(){
		spawnClock = new Timer(SPAWN_RATE, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				spawnFood();
			}
		});
		spawnClock.setRepeats(true);
		spawnClock.setCoalesce(true);
		
		bubbleClock = new Timer(BUBBLE_RATE, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				Bubble.spawnBubble();
			}
		});
		bubbleClock.setRepeats(true);
		bubbleClock.setCoalesce(true);
	}
	
	public static void startSpawnClock(){
		spawnClock.start();
	}
	public static void stopSpawnClock(){
		spawnClock.stop();
	}
	public static void startBubbleClock(){
		bubbleClock.start();
	}
	public static void stopBubbleClock(){
		bubbleClock.stop();
	}
	
	public void spawnFood(){
		if (random.nextInt(10) < 2) {	//0,1 É 2/10 chance. 20%
			int size = (random.nextInt(maxFoodSize) + 1) * gridLength;
			int x = (random.nextInt(UserDisplay.WINDOW_X / gridLength - 1 + size/gridLength - 1) - size/gridLength + 1) * gridLength;
			int y = UserDisplay.WINDOW_Y;
			for (Entity e : Entity.entities)
				if (e.rect.intersects(new Rectangle(x, y, size, size)) && (e instanceof Food))
					return;
			new Food(x, y, size, null);
			
			//final int checkerCount = UserDisplay.WINDOW_X / (Player.p.getSize() / gridLength);
			//checkerState = new boolean[checkerCount];
			//for(int x = 0; x < checkerCount; x++)
			//	checkerState[x] = true;
		}	
	}
	
}

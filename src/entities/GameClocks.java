package entities;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;

import ui.Options;
import ui.UserDisplay;


public class GameClocks {

	// difficulty listings:
	/*
	 1: gridLength: 20
	 	movementSpeed: 3
	 	spawnChance: 1
	 	maxFoodSize: 4
	 
	 2: gridLength: 20
	 	movementSpeed: 4
	 	spawnChance: 1
	 	maxFoodSize: 5
	 
	 3: gridLength: 10
	 	movementSpped: 5
	 	spawnChance: 8
	 	maxFoodSize: 6
	 	
	 4: gridLength: 10
	 	movementSpeed: 6
	 	spawnChance: 8
		maxFoodSize: 7
	
	*/
	
	// EASIEST SETTINGS BELOW
	public static int  	    gridLength = 20; // pixel width per grid space
	public static int    movementSpeed = 3;  // pixels per repaint rate
	public static int      maxFoodSize = 4;  // in grid count number form
	public final int 		SPAWN_RATE = 50; // in milliseconds
	public final int 	   BUBBLE_RATE = 150; // in milliseconds
	public static int		  offspace = 0; // -0: false -1: true
	
	private static Random random = new Random();
	private static Timer spawnClock;
	private static Timer bubbleClock;
	
	public GameClocks(){
		spawnClock = new Timer(SPAWN_RATE, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if (Options.difficulty < 2){
					if (random.nextInt(100) < 50)
						spawnFood();
				} else {
					spawnFood();
				}
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
		for (int x = 0; x < UserDisplay.WINDOW_X/GameClocks.gridLength; x++) new Checkers(Player.player1.getSize());
		spawnClock.start();
	}
	public static void stopSpawnClock(){
		Checkers.clearCheckers();
		spawnClock.stop();
	}
	public static void startBubbleClock(){
		bubbleClock.start();
	}
	public static void stopBubbleClock(){
		bubbleClock.stop();
	}
	
	public void spawnFood(){
		int size = (random.nextInt(maxFoodSize) + 1) * gridLength;
		int x = (random.nextInt(UserDisplay.WINDOW_X / gridLength - 1 + size/gridLength - 1) - size/gridLength + 1) * gridLength + offspace*gridLength/2;
		int y = UserDisplay.WINDOW_Y-1;
		
		for ( Entity e : Entity.entities)
			if (e.rect.intersects(new Rectangle(x, y, size, size)) && (e instanceof Food))
				return;
		
		int collateralIntersections = 0;

		for (Checkers c : Checkers.checkers)
				if (c.rect.intersects(new Rectangle(x , y, size, size)) && c.onState)
						collateralIntersections++;

		if(Checkers.checkersOpen == collateralIntersections)
			return;
		
		new Food(x , y, size, null);
	}
}

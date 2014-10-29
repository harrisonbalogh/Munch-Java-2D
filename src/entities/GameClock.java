package entities;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

import ui.UserDisplay;


public class GameClock {

	public static int  	    gridLength = 20; // pixel width per grid space
	public static int    movementSpeed = 6;  // pixels per repaint rate
	public static int      spawnChance = 2;  // out of 10
	public static int      maxFoodSize = 5;  // in grid count number form
	public final int 		CLOCK_RATE = 50; // in milliseconds
	
	private static ArrayList<Rectangle>[] checkers;
	private static boolean[] checkerState;
	private static Random random = new Random();
	private static Timer gameClock;
	
	public GameClock(){
		gameClock = new Timer(CLOCK_RATE, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (random.nextInt(10) < 2) {	//0,1 É 2/10 chance. 20%
					int size = (random.nextInt(maxFoodSize) + 1) * gridLength;
					int x = (random.nextInt(UserDisplay.WINDOW_X / gridLength - 1 + size/gridLength - 1) - size/gridLength + 1) * gridLength;
					int y = UserDisplay.WINDOW_Y;
					for (Food f : Food.food)
						if (f.rect.intersects(new Rectangle(x, y, size, size)))
							return;
					new Food(x, y, size, null);
				}	
				
				final int checkerCount = UserDisplay.WINDOW_X / (Player.p.getSize() / gridLength);
				checkerState = new boolean[checkerCount];
				for(int x = 0; x < checkerCount; x++)
					checkerState[x] = true;
			}
		});
		gameClock.setRepeats(true);
		gameClock.setCoalesce(true);
	}
	
	public static void startGameClock(){
		gameClock.start();
	}
	
	public static void stopGameClock(){
		gameClock.stop();
	}
	
}

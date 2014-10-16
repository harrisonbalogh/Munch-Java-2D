package platform;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import entities.Food;

public class GameClock {

	public int  	 gridLength = 20; // pixel width per grid space
	public int    movementSpeed = 2;  // pixels per repaint rate
	public int      spawnChance = 2;  // out of 10
	public int      maxFoodSize = 5;  // in grid count number form
	public final int CLOCK_RATE = 50; // in milliseconds
	
	private static Random random = new Random();
	private static Timer gameClock;
	
	public GameClock(){
		// Initialize Game Clock in Launch
		gameClock = new Timer(CLOCK_RATE, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (random.nextInt(10) < spawnChance) {
					int size = (random.nextInt(maxFoodSize) + 1) * gridLength;
					int x = (random.nextInt(InterfaceItem.WINDOW_X / gridLength - 1 + size/gridLength - 1) - size/gridLength + 1) * gridLength;
					int y = InterfaceItem.WINDOW_Y;
					Rectangle rect = new Rectangle(x, y, size, size);
					boolean success = true;
					for (Food f : Food.food)
						if (rect.intersects(f.rect)){
							success = false;
							break;
						}
					if (success) new Food(x, y, size, null);
						
				}
			}
		});
		gameClock.setRepeats(true);
		gameClock.setCoalesce(true);
	}
	
	static void startGameClock(){
		gameClock.start();
	}
	
	static void stopGameClock(){
		gameClock.stop();
	}
	
}

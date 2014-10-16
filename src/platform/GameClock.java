package platform;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import entities.Food;

public class GameClock {

	public static final int GRID_LENGTH = Launch.GRID_LENGTH;
	public static final int MOVEMENT_SPEED = Launch.MOVEMENT_SPEED;
	public static final int CLOCK_RATE = Launch.CLOCK_RATE;
	public static final int SPAWN_CHANCE = Launch.SPAWN_CHANCE;
	public static final int MAX_FOOD_SIZE = Launch.MAX_FOOD_SIZE;
	
	private static Random random = new Random();
	private static Timer gameClock;
	
	public GameClock(){
		// Initialize Game Clock in Launch
		gameClock = new Timer(CLOCK_RATE, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (random.nextInt(10) < SPAWN_CHANCE) {
					int size = (random.nextInt(MAX_FOOD_SIZE) + 1) * GRID_LENGTH;
					int x = (random.nextInt(InterfaceItem.WINDOW_X / GRID_LENGTH - 1 + size/GRID_LENGTH - 1) - size/GRID_LENGTH + 1) * GRID_LENGTH;
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

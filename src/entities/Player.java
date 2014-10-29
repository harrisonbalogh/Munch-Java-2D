package entities;

import java.awt.Graphics;

import java.awt.Rectangle;
import java.awt.Toolkit;

import platform.Launch;
import platform.SoundBank;
import ui.UserDisplay;

public class Player extends Entity{
	
	public static Player p = new Player();
	
	private int score;
	private int food;
	private int foodNeeded;
	private boolean growing;
	private boolean alive;
	
	public static boolean playing = false;
	private static int SPRITESHEET_X = 25;
	private static int SPRITESHEET_Y = 25;
	//private static final int SPRITESHEET_FRAMES = 0;
	
	public Player(int x, int y, int size){
		this.x = x;
		this.y = y;
		this.size = size;
		this.rect = new Rectangle(x, y, size, size);
		this.growing = false;
		foodNeeded = this.size/GameClock.gridLength * 10;
		img = Toolkit.getDefaultToolkit().getImage("src/resources/entity_player.png");
		alive = true;
		animate();
	}
	
	public Player(){
		this(UserDisplay.WINDOW_X/2, 1-GameClock.gridLength, GameClock.gridLength);
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x+1, y+1, x + size, y + size, SPRITESHEET_X*(size/GameClock.gridLength - 1), 0, SPRITESHEET_X*(size/GameClock.gridLength - 1) + SPRITESHEET_X*(size/GameClock.gridLength), SPRITESHEET_Y*(size/GameClock.gridLength), null, null);
		if(playing && alive) update();
	}

	@Override
	public void update() {
		if(getY()+getSize() <= 0 && playing){
			death();
			return;
		}else{
			setScore(getScore()+1);
			Launch.ui.setTextScore(score);
		}
	}

	@Override
	public void animate() {
		new Thread(){
			public void run(){
				boolean directionChange = false;
				while(alive){
					if(!directionChange) {
						SPRITESHEET_X++;
						if (SPRITESHEET_X > size) directionChange = !directionChange;
					}
					else {
						SPRITESHEET_X--;
						if (SPRITESHEET_X < 1) directionChange = !directionChange;
					}
					try{Thread.sleep(25);}catch(InterruptedException ex){};
				}
			}
		}.start();
	}
	
	public void death(){
		SoundBank.sound_play_death();
		alive = false;
	}
	public void grow(){
		growing = true;
		Food.food.clear();
		setSize(getSize() + GameClock.gridLength);
		foodNeeded = getSize()/GameClock.gridLength * 10;
		food = 0;
		if(x < UserDisplay.WINDOW_X - size)
			moveLeft();
		growing = false;
	}
	
	public void moveRight(){
		// moveRight() and moveLeft() methods could be moved to the update() method and check if left or right arrow keys are held down.
		if(x < UserDisplay.WINDOW_X - size){
			boolean success = true;
			int x1 = getX() + GameClock.gridLength;
			Rectangle temp = new Rectangle(x1, getY(), getSize(), getSize());
			for(Food f : Food.food)
				if(temp.intersects(f.rect) && size < f.size)
					success = false;			
			if (success) setX(x1);
		}
	}
	
	public void moveLeft(){
		if(x > 0){
			boolean success = true;
			int x1 = getX() - GameClock.gridLength;
			Rectangle temp = new Rectangle(x1, getY(), getSize(), getSize());
			for(Food f : Food.food)
				if(temp.intersects(f.rect) && size < f.size)
					success = false;			
			if (success) setX(x1);
		}
	}
	
	public void setScore(int score){
		this.score = score;
	}
	public int getScore(){
		return score;
	}
	public void setFood(int food){
		this.food = food;
	}
	public int getFood(){
		return food;
	}
	public int getFoodNeeded(){
		return foodNeeded;
	}
	public void setFoodNeeded(int foodNeeded){
		this.foodNeeded = foodNeeded;
	}
	public void setGrowing(){
		grow();
	}
	public boolean getGrowing(){
		return growing;
	}
	public void setAlive(boolean living){
		this.alive = living;
	}
	public boolean getAlive(){
		return alive;
	}
}

package entities;

import java.awt.Graphics;

import java.awt.Rectangle;
import java.awt.Toolkit;
import platform.Launch;
import platform.SoundBank;

public class Player extends Entity{
	
	private int score;
	private int food;
	private int foodNeeded;
	private boolean growing;
	
	private static final int SPRITESHEET_X = 25;
	private static final int SPRITESHEET_Y = 25;
	//private static final int SPRITESHEET_FRAMES = 0;
	
	public Player(int x, int y, int size){
		this.x = x;
		this.y = y;
		this.size = size;
		this.rect = new Rectangle(x, y, size, size);
		this.growing = false;
		foodNeeded = this.size/Launch.GRID_LENGTH * 10;
		img = Toolkit.getDefaultToolkit().getImage("src/resources/entity_player.png");
		alive = true;
	}
	
	public Player(){
		this(300, 150, Launch.GRID_LENGTH);
	}
	
	public void death(){
		SoundBank.sound_play_death();
		alive = false;
	}
	public void grow(){
		growing = true;
		Food.food.clear();
		setSize(getSize() + Launch.GRID_LENGTH);
		foodNeeded = getSize()/Launch.GRID_LENGTH * 10;
		food = 0;
		if(x < Launch.WINDOW_X - size)
			moveLeft();
		growing = false;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x+1, y+1, x + size, y + size, SPRITESHEET_X*(size/Launch.GRID_LENGTH - 1), 0, SPRITESHEET_X*(size/Launch.GRID_LENGTH - 1) + SPRITESHEET_X*(size/Launch.GRID_LENGTH), SPRITESHEET_Y*(size/Launch.GRID_LENGTH), null, null);
		update();
	}

	@Override
	public void update() {
		if(getY()+getSize() <= 0){
			death();
			return;
		}
		animate();
	}

	@Override
	public void animate() {
		//You need to open a new thread and use Thread.sleep(milliseconds) to make little simple animations.
	}
	
	public void moveRight(){
		// moveRight() and moveLeft() methods could be moved to the update() method and check if left or right arrow keys are held down.
		if(x < Launch.WINDOW_X - size){
			boolean success = true;
			int x1 = getX() + Launch.GRID_LENGTH;
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
			int x1 = getX() - Launch.GRID_LENGTH;
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

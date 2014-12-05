package entities;

import java.awt.Graphics;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;

import platform.Launch;
import platform.SoundBank;
import ui.MainPanel;
import ui.UserDisplay;

public class Player extends Entity{
	
	private static String[] playerImages = { 	
		"src/resources/entity_food_1.png", 
		"src/resources/entity_food_2.png",
		"src/resources/entity_food_3.png",
		"src/resources/entity_food_4.png",
		"src/resources/entity_food_5.png",
		"src/resources/entity_food_6.png"
	 };
	
	
	public static Player player1;
	private static boolean alive;
	private static int speedHolder;
	
	private int score;
	private int food;
	private int foodNeeded; 
	private boolean growing;
	
	public Player(int x, int y){
		this.img = Toolkit.getDefaultToolkit().getImage(playerImages[(new Random()).nextInt(6)]);
		this.rect = new Rectangle(x, y, 0, 0);
		this.growing = false;
		this.foodNeeded = 10;
		this.food = 0;
		entities.add(this);
		alive = true;
		grow();
	}
	
	public Player(){
		this(UserDisplay.WINDOW_X/2, 100);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(getX(), getY(), getSize(), getSize());
		g.setColor(Color.BLACK);
		g.drawRect(getX(), getY(), getSize(), getSize());
		if(isAlive()) update();
	}

	@Override
	public void update() {
		if(getY()+getSize() <= 0){
			// DEATH...
			if (MainPanel.isPlaying()) SoundBank.sound_play_death();
			Entity.entities.remove(this);
			alive = false;
			GameClocks.stopBubbleClock();
			Launch.ui.toggleGameOverPopup();
		}else{
			setScore(getScore()+1);
			Launch.ui.setTextScore(score);
		}
	}

	@Override
	public void animate() {
	}
	
	public void grow(){
		growing = true;	
		speedHolder = GameClocks.movementSpeed;
		GameClocks.movementSpeed = 0;
		setFoodNeeded((getSize()/GameClocks.gridLength+1) * 10);
		setFood(0);
		new Thread(){
			public void run(){
				for(int t = 0; t < GameClocks.gridLength; t++){
					player1.setSize(player1.getSize()+1);
					if (t % 2 == 1) player1.setX(player1.getX()-1); // this makes it so every other iteration he shifts left to look smooth.
					// Below lines reduce the size of all food to make the player appear to get larger
					for(Entity e : entities){
						if (e instanceof Food){
							e.setSize(e.getSize()-1);
							if (t % 2 == 1) e.setX(e.getX()+1);
							if(e.getSize() <= 1) 
								entities.remove(e);
						}
					}
					try{Thread.sleep(50);}catch(InterruptedException e){}
				}
				if(getX() % GameClocks.gridLength == 0)
					GameClocks.offspace = 0; //swap back to grid...
				else
					GameClocks.offspace = 1; //swap to offspace...
				GameClocks.movementSpeed = speedHolder;
				Checkers.clearCheckers();
				for (int x = 0; x < UserDisplay.WINDOW_X/Player.player1.getSize(); x++) new Checkers(Player.player1.getSize());
				Player.player1.growing = false;

			}
		}.start();
	}
	
	public void moveRight(){
		if(getX() + GameClocks.gridLength <= UserDisplay.WINDOW_X - getSize()){
			boolean success = true;
			int x1 = getX() + GameClocks.gridLength;
			Rectangle temp = new Rectangle(x1, getY(), getSize(), getSize());
			for(Entity e : entities)
				if(e instanceof Food)
					if(temp.intersects(e.rect) && getSize() < e.getSize())
						success = false;			
			if (success) setX(x1);
		}
	}
	public void moveLeft(){
		if(getX() - GameClocks.gridLength >= 0){
			boolean success = true;
			int x1 = getX() - GameClocks.gridLength;
			Rectangle temp = new Rectangle(x1, getY(), getSize(), getSize());
			for (Entity e : entities)
				if(e instanceof Food)
					if(temp.intersects(e.rect) && getSize() < e.getSize())
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
	public boolean getGrowing(){
		return growing;
	}
	public static void setAlive(boolean living){
		alive = living;
	}
	public static  boolean isAlive(){
		return alive;
	}
}

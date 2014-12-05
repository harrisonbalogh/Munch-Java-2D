package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;
import platform.Launch;
import platform.SoundBank;
import ui.UserDisplay;

public class Food extends Entity{
	
	private boolean eaten = false;
	
	private static String[] foodImages = { 	"src/resources/entity_food_1.png", 
											"src/resources/entity_food_2.png",
											"src/resources/entity_food_3.png",
											"src/resources/entity_food_4.png",
											"src/resources/entity_food_5.png",
											"src/resources/entity_food_6.png"
										 };
	
	public Food(int x, int y, int size, Image img){
		this.rect = new Rectangle(x, y, size, size);
		Random random = new Random();
		this.img = Toolkit.getDefaultToolkit().getImage(foodImages[random.nextInt(6)]);
		entities.add(this);
	}
	
	public Food(int size, Image img){
		this(0,0,size, img);
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(getX(), getY(), getSize(), getSize());
		g.setColor(Color.BLACK);
		g.drawRect(getX(),  getY(), getSize(), getSize());
		update();
	}
	
	@Override
	public void update() {
		setY(getY() - GameClocks.movementSpeed); // Moves food up on the screen by movementSpeed
		
		if (getY()+getSize() < 1 || getX() > UserDisplay.WINDOW_X || getX()+getSize() < 0) {
			entities.remove(this); // Removes food if it goes beyond screen border
		}

		if(rect.intersects(Player.player1.rect) && Player.isAlive()){
			if(Player.player1.getSize() >= getSize()){
				int direction = 270;
				
				// calculate angle between center of player and food that its intersecting
				int[] position_x_ = {getX() + getSize()/2, Player.player1.getX() + Player.player1.getSize()/2};
				int[] position_y_ = {getY() + getSize()/2, Player.player1.getY() + Player.player1.getSize()/2};
				
				if (position_x_[1] - position_x_[0] != 0 && position_y_[1] - position_y_[0] != 0) {
					if (position_x_[1] - position_x_[0] == 0) {
						if (position_y_[1] - position_y_[0] > 0)
							direction = 0;
						else if (position_y_[1] - position_y_[0] < 0)
							direction = 180;
					} else{
						double deltaX = position_x_[1] - position_x_[0];
						double deltaY = -1 * (position_y_[1] - position_y_[0]);
						direction = (int) (180 / Math.PI * Math.atan(deltaY/deltaX));
						if(deltaX < 0 && deltaY > 0) direction += 180;
						else if(deltaX < 0 && deltaY < 0) direction += 180;
					}
				}

				if (!isEaten()) eat(direction); // If food intersects player of equal or less than size
			} else {
				Player.player1.setY(Player.player1.getY()-GameClocks.movementSpeed); // Moves player along with food if player is smaller than it
			}
		}
		animate();
	}
	
	@Override
	public void animate() {
		// Some food could have animation... You need to open a new thread and use Thread.sleep(milliseconds) to make little simple animations.
	}
	
	public void eat(int directionOfEat){
		setEaten(true);
		Particle.spawnParticleCluster(this, directionOfEat);
		new ScoreSprite(getX() + getSize()/2, getY());
		Player.player1.setScore(Player.player1.getScore() + (int)(getSize()/Player.player1.getSize() * 100));
		Player.player1.setFood(Player.player1.getFood() + getSize()/GameClocks.gridLength);
		entities.remove(this);
		Launch.ui.setTextScore(Player.player1.getScore());
		SoundBank.sound_play_eat();
		if(Player.player1.getFood() >= Player.player1.getFoodNeeded()){
			Player.player1.grow();
		}
	}
	
	public static void clearFood(){
		ArrayList<Entity> removeFood = new ArrayList<Entity>();
		for(Entity e : entities)
			if (e instanceof Food)
				removeFood.add(e);
		entities.remove(removeFood);
	}
	
	public boolean isEaten(){
		return eaten;
	}
	public void setEaten(boolean state){
		eaten = state;
	}
}

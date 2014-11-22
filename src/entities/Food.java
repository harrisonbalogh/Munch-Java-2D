package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
import platform.Launch;
import platform.SoundBank;

public class Food extends Entity{
	
	private static int SPRITESHEET_X = 0;
	private static int SPRITESHEET_Y = 0;
	
	public Food(int x, int y, int size, Image img){
		this.rect = new Rectangle(x, y, size, size);
		this.img = Toolkit.getDefaultToolkit().getImage("src/resources/charSpriteSheet.png");
		entities.add(this);
	}
	
	public Food(int size, Image img){
		this(0,0,size, img);
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(img, getX(), getY(), getX() + getSize(), getY() + getSize(), SPRITESHEET_X, SPRITESHEET_Y, SPRITESHEET_X + getSize(), SPRITESHEET_Y + getSize(), null, null);
		g.setColor(Color.black);
		g.drawRect(getX(),  getY(), getSize(), getSize());
		if(Player.playing && !Player.p.getGrowing()) update();
	}
	
	@Override
	public void update() {
		setY(getY() - GameClocks.movementSpeed); // Moves food up on the screen by movementSpeed
		
		if (getY()+getSize() < 1) {
			entities.remove(this); // Removes food if it goes above screen border
		}
		
		if(rect.intersects(Player.p.rect) && Player.p.getAlive()){
			if(Player.p.getSize() >= getSize()){
				eat(); // If food intersects player of equal or less than size
			} else {
				Player.p.setY(Player.p.getY()-GameClocks.movementSpeed); // Moves player along with food if player is smaller than it
			}
		}
		
		animate();
	}
	
	@Override
	public void animate() {
		// Some food could have animation... You need to open a new thread and use Thread.sleep(milliseconds) to make little simple animations.
	}
	
	public void eat(){
		new ScoreSprite(Player.p.getX() + Player.p.getSize()/2, Player.p.getY(), 100);
		Player.p.setScore(Player.p.getScore() + (int)(getSize()/Player.p.getSize() * 100));
		Player.p.setFood(Player.p.getFood() + getSize()/GameClocks.gridLength);
		entities.remove(this);
		Launch.ui.setTextScore(Player.p.getScore());
		SoundBank.sound_play_eat();
		if(Player.p.getFood() >= Player.p.getFoodNeeded()){
			Player.p.grow();
		}
	}
	
	public static void clearFood(){
		ArrayList<Entity> removeFood = new ArrayList<Entity>();
		for(Entity e : entities)
			if (e instanceof Food)
				removeFood.add(e);
		entities.remove(removeFood);
	}
}

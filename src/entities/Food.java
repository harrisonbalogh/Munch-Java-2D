package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.concurrent.CopyOnWriteArrayList;

import platform.Launch;
import platform.SoundBank;

public class Food extends Entity{
	
	public static CopyOnWriteArrayList<Food> food = new CopyOnWriteArrayList<Food>();
	
	private static int SPRITESHEET_X = 0;
	private static int SPRITESHEET_Y = 0;
	
	public Food(int x, int y, int size, Image img){
		this.x = x;
		this.y = y;
		this.size = size;
		this.rect = new Rectangle(x, y, size, size);
		img = Toolkit.getDefaultToolkit().getImage("src/resources/charSpriteSheet.png");
		food.add(this);
	}
	
	public Food(int size, Image img){
		this(0,0,size, img);
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, x + size, y + size, SPRITESHEET_X, SPRITESHEET_Y, SPRITESHEET_X + size, SPRITESHEET_Y + size, null, null);
		g.setColor(Color.black);
		g.drawRect(x,  y, size, size);
		if(Player.playing) update();
	}
	
	@Override
	public void update() {
		setY(getY() - GameClock.movementSpeed);
		if (getY()+getSize() < 1) food.remove(this);
		if(rect.intersects(Player.p.rect) && Player.p.getAlive()){
			if(Player.p.getSize() >= size){
				Player.p.setScore(Player.p.getScore() + (int)(size/Player.p.getSize() * 100));
				Player.p.setFood(Player.p.getFood() + getSize()/GameClock.gridLength);
				if(Player.p.getFood() >= Player.p.getFoodNeeded()){
					Player.p.setGrowing();
				}
				eat();
			}
			else if (Player.p.getAlive()) {
				Player.p.setY(Player.p.getY()-GameClock.movementSpeed);
				Player.p.setScore(Player.p.getScore()-1);
				Launch.ui.setTextScore(Player.p.getScore());
			}
		}
		animate();
	}
	
	@Override
	public void animate() {
		// Some food could have animation... You need to open a new thread and use Thread.sleep(milliseconds) to make little simple animations.
	}
	
	public void eat(){
		food.remove(this);
		Launch.ui.setTextScore(Player.p.getScore());
		SoundBank.sound_play_eat();
	}
	
}

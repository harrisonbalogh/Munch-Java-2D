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
	
	private static final int SPRITESHEET_X = 0;
	private static final int SPRITESHEET_Y = 0;
	
	public Food(int x, int y, int size, Image img){
		this.x = x;
		this.y = y;
		this.size = size;
		this.rect = new Rectangle(x, y, size, size);
		img = Toolkit.getDefaultToolkit().getImage("images/charSpriteSheet.png");
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
		if(Launch.playing) update();
	}
	
	@Override
	public void update() {
		setY(getY() - Launch.MOVEMENT_SPEED);
		// if (getX()+getSize() <= 0) food.remove(this); // BROKEN
		if(rect.intersects(Launch.p.rect)){
			if(Launch.p.getSize() >= size){
				Launch.p.setScore(Launch.p.getScore() + size/getSize() * 100);
				Launch.p.setFood(Launch.p.getFood() + getSize()/Launch.GRID_LENGTH);
				if(Launch.p.getFood() >= Launch.p.getFoodNeeded()){
					Launch.p.setGrowing();
				}
				Launch.ui.text_score.setText("Score: " + Launch.p.getScore());
				Launch.ui.text_food.setText("Food: " + Launch.p.getFood() + "/" + Launch.p.getFoodNeeded());
				eat();
			}
			else Launch.p.setY(Launch.p.getY()-Launch.MOVEMENT_SPEED);
		}
		animate();
	}
	
	public void eat(){
		food.remove(this);
		SoundBank.sound_play_eat();
	}

	@Override
	public void animate() {
		// Some food could have animation... You need to open a new thread and use Thread.sleep(milliseconds) to make little simple animations.
	}
	
}

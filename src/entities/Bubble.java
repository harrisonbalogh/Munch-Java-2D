package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Bubble extends Entity{

	private static boolean cooldown = false;
	private int lifespan = 20;

	private Bubble(int x, int y){
		this.rect = new Rectangle(x, y, 4, 4);
		entities.add(this);
	}

	@Override
	public void draw(Graphics g){
		g.setColor(Color.BLUE);
		g.drawOval(getX(), getY(), getSize(), getSize());
		update();
	}

	@Override
	public void update(){
		setY(getY() - GameClocks.movementSpeed);
		decrementLifespan();
		if (lifespan == 10)
			setSize(3);
		if (lifespan == 5){
			setX(getX() - 1);
			setSize(2);
		}
		if (lifespan == 2){
			setX(getX() + 1);
			setSize(1);
		}
		if(getLifespan() == 0)
			entities.remove(this);
	}
	
	@Override
	public void animate(){
	}

	public static void spawnBubble(){
		if(!isCooldown()){
			bubbleCooldown();
			new Bubble(Player.p.getX() + Player.p.getSize()/2, 
				       Player.p.getY() - 2);
		}
	}
	public static void bubbleCooldown(){
		new Thread(){
			public void run(){
				setCooldown(true);
				long initialTime = System.currentTimeMillis();
				long currentTime = System.currentTimeMillis();
				while (currentTime - initialTime < 10) {
					currentTime = System.currentTimeMillis();
				}
				setCooldown(false);
			}
		}.start();
	}

	public static boolean isCooldown() {
		return cooldown;
	}
	public static void setCooldown(boolean cooldown) {
		Bubble.cooldown = cooldown;
	}
	public int getLifespan() {
		return lifespan;
	}
	public void decrementLifespan(){
		lifespan--;
	}
}

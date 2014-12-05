package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Bubble extends Entity{

	private static boolean cooldown = false;
	private int lifespan = 20;

	private Bubble(int x, int y){
		this.rect = new Rectangle(x, y, Player.player1.getSize()/2, Player.player1.getSize()/2);
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
		setSize(getSize() - 1);
		setX(getX() + getSize()%2);
		
		if(getLifespan() == 0)
			entities.remove(this);
	}
	
	@Override
	public void animate(){
	}

	public static void spawnBubble(){
		if(!isCooldown() && Player.isAlive() && GameClocks.movementSpeed != 0){
			bubbleCooldown();
			new Bubble(Player.player1.getX() + Player.player1.getSize()/4, 
				       Player.player1.getY() - Player.player1.getSize()/2);
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

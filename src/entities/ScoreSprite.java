package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class ScoreSprite extends Entity{
	
	int amount;
	
	public ScoreSprite(int x, int y, int amount){
		this.amount = amount;
		this.rect = new Rectangle(x, y, 14, 14);
		entities.add(this);
		animate();
	}

	@Override
	public void draw(Graphics g) {
		//g.drawImage(img, getX()+1, getY()+1, getX() + getSize(), getY() + getSize(), SPRITESHEET_X*(getSize()/GameClock.gridLength - 1), 0, SPRITESHEET_X*(getSize()/GameClock.gridLength - 1) + SPRITESHEET_X*(getSize()/GameClock.gridLength), SPRITESHEET_Y*(getSize()/GameClock.gridLength), null, null);
		g.drawImage(img, 100, 100, 100, 100, 100, 100, 100, 100, null);
		g.setColor(Color.BLACK);
		g.fillRect(getX(), getY(), getSize()+20, getSize());
		g.setColor(Color.GREEN);
		g.drawString("" + amount, getX(), getY() + 11);
	}

	@Override
	public void update() {	
		// empty
	}

	@Override
	public void animate() {
		new Thread(){
			public void run(){
				for(int t = 0; t < 60; t++){
					rect.y--;
					//rect.x++;
					try{Thread.sleep(3);}catch(InterruptedException e){}
				}	
				try{Thread.sleep(100);}catch(InterruptedException e){}
				for(Entity e : Entity.entities)
					if (e instanceof ScoreSprite){
						//find first occurance of a scoresprite (meaning oldest scoresprite) and remove it
						entities.remove(e);
						return;
					}
			}
		}.start();
	}
}

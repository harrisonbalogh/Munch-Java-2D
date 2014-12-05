package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import ui.UserDisplay;

public class ScoreSprite extends Entity{
	
	private int lifespan = 10;
	private int sideOfScreen = 0;
	
	public ScoreSprite(int centerX, int centerY){
		if (Player.player1.getX() + Player.player1.getSize()/2 > UserDisplay.WINDOW_X/2) sideOfScreen = -1; // if player is on right side, make constant go left.
		else sideOfScreen = 1; // else player is on left side, so make constant go right.
		
		this.rect = new Rectangle(Player.player1.getX() + Player.player1.getSize()/2 - (-1)*(15)*(-1 + 1*sideOfScreen), Player.player1.getY() + Player.player1.getSize(), 10, 10);

		entities.add(this);
	}

	@Override
	public void draw(Graphics g) {
		
		g.setColor(Color.WHITE);
		g.fillRect(getX(), getY(), getSize()+20, getSize());
		g.setColor(Color.BLACK);
		g.drawRect(getX(), getY(), getSize()+20, getSize());
		g.setColor(Color.BLACK);
		g.drawString("nom", getX() + 1, getY() + 9);
		update();
	}

	@Override
	public void update() {	
		setX(getX() + 3*sideOfScreen);
		setY(Player.player1.getY() + Player.player1.getSize());
		
		decrementLifespan();
		
		if (getLifespan() == 0)
			Entity.entities.remove(this);
	}

	@Override
	public void animate() {
	}
	
	public int getLifespan(){
		return lifespan;
	}
	public void decrementLifespan(){
		lifespan--;
	}
}

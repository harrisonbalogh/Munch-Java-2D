package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import ui.UserDisplay;

public class Checkers extends Entity {
	
	public static CopyOnWriteArrayList<Checkers> checkers = new CopyOnWriteArrayList<Checkers>();
	static boolean openerCooldown = false;
	boolean onState = true;
	static int checkersOpen = 0;
	
	public Checkers(int size){
		int x = checkers.size()*size;
		this.rect = new Rectangle(x, UserDisplay.WINDOW_Y-1, size, size);
		checkers.add(this);
		checkersOpen++;
	}
	
	@Override
	public void draw(Graphics g) {
		if (!onState) g.setColor(Color.RED);
		else g.setColor(Color.WHITE);
		g.drawRect(getX(), getY(), getSize(), getSize());
		update();
	}

	@Override
	public void update() {
		//System.out.println("    Checkers Open: " + checkersOpen);
		
		for (Entity e : Entity.entities)
			if (e instanceof Food && e.rect.intersects(this.rect) && e.getSize() > this.rect.width && checkersOpen > 1 && onState){
				setCheckerOff();
				}
		
		if(!openerCooldown){
			openerIntervalCooldown();
			openNeighbors();
		}
	}

	@Override
	public void animate() {
	}
	
	private static void openNeighbors(){
		
		ArrayList<Checkers> openCheckers = new ArrayList<Checkers>();
		for (Checkers c : checkers){
			if (c.onState){
				openCheckers.add(c);
			}
		}
		
		for (Checkers c : openCheckers) {
				if (checkers.indexOf(c) == 0)
				{
					for ( Entity e : Entity.entities)
						if (e.rect.intersects(checkers.get(1).rect) && (e instanceof Food)) 
							return;
					if (!checkers.get(1).onState) checkers.get(1).setCheckerOn();
				} 
				
				else if (checkers.indexOf(c) == checkers.size()-1)
				{
					for ( Entity e : Entity.entities)
						if (e.rect.intersects(checkers.get((checkers.size()-1)-1).rect) && (e instanceof Food)) 
							return;
					if (!checkers.get((checkers.size()-1)-1).onState) checkers.get((checkers.size()-1)-1).setCheckerOn();
				} 
				
				else 
				{
					boolean success = true;
					for ( Entity e : Entity.entities)
						if (e.rect.intersects(checkers.get(checkers.indexOf(c)-1).rect) && (e instanceof Food)){
							success = false;
							break;
						}
					if (success) {
						if (!checkers.get(checkers.indexOf(c)-1).onState) checkers.get(checkers.indexOf(c)-1).setCheckerOn();
					}
					
					for ( Entity e : Entity.entities)
						if (e.rect.intersects(checkers.get(checkers.indexOf(c)+1).rect) && (e instanceof Food)) 
							return;
					if (!checkers.get(checkers.indexOf(c)+1).onState) checkers.get(checkers.indexOf(c)+1).setCheckerOn();
				}
		}
		
	}
	
	public static void openerIntervalCooldown(){
		new Thread(){
			public void run(){
				setOpenerCooldown(true);
				long initialTime = System.currentTimeMillis();
				long currentTime = System.currentTimeMillis();
				while (currentTime - initialTime < 500) {
					currentTime = System.currentTimeMillis();
				}
				setOpenerCooldown(false);
			}
		}.start();
	}
	
	private void setCheckerOn(){
		checkersOpen++;
		onState = true; 
	}
	
	private void setCheckerOff(){
		checkersOpen--;
		onState = false;
	}
	
	public static void setOpenerCooldown(boolean b){
		openerCooldown = b;
	}
	
	public static void clearCheckers(){
		checkersOpen = 0;
		checkers.clear();
	}
}

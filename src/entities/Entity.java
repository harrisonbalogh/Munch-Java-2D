package entities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Entity {
	
	public static CopyOnWriteArrayList<Entity> entities = new CopyOnWriteArrayList<Entity>();
	
	protected Image img;
	public Rectangle rect;

	abstract public void draw(Graphics g);
	abstract public void update();
	abstract public void animate();

	public void setLocation(int x, int y){
		rect.x = x;
		rect.y = y;
	}
	public int getX() {
		return rect.x;
	}
	public void setX(int x) {
		rect.x = x;
	}
	public int getY() {
		return rect.y;
	}
	public void setY(int y) {
		rect.y = y;
	}
	public int getSize() {
		return rect.width;
	}
	public void setSize(int size) {
		rect.width = size;
		rect.height = size;
	}
}

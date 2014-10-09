package entities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public abstract class Entity {
	protected int x;
	protected int y;
	protected int size;
	protected Image img;
	public Rectangle rect;
	protected boolean alive;

	abstract public void draw(Graphics g);
	abstract public void update();
	abstract public void animate();

	public int getX() {
		return x;
	}
	public void setX(int x) {
		rect.x = x;
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		rect.y = y;
		this.y = y;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
		rect.width = size;
		rect.height = size;
	}
}

package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Particle extends Entity{
	
	

	private static int PARTICLE_TERMINAL_VELOCITY = 10;
	
	private int lifespan = PARTICLE_TERMINAL_VELOCITY;
	private int velocity = PARTICLE_TERMINAL_VELOCITY;
	private int angle;
	
	private Particle(int x, int y, int angle){
		this.angle = angle;
		this.rect = new Rectangle(x, y, 1, 1);
		entities.add(this);
	}

	@Override
	public void draw(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(getX(), getY(), getSize(), getSize());
		update();
	}

	@Override
	public void update(){
		double rad = Math.toRadians(getAngle());
		setLocation(getX() + (int)(getVelocity()/2*Math.cos(rad)), getY() - (int)(getVelocity()/2*Math.sin(rad)));
		
		decrementVelocity();
		decrementLifespan();
		
		if(getLifespan() == 0){
			entities.remove(this);
		}
	}
	
	@Override
	public void animate(){
	}
	
	public static void spawnParticleCluster(Entity onEntity, int direction){
		int x = onEntity.getX() + onEntity.getSize()/2;
		int y = onEntity.getY() + onEntity.getSize()/4;
		int calcAngle = direction - 45 - 180;
		calcAngle = calcAngle % 360;
		
		double calcRad;
		
		for (int i = 0; i < 90; i++){
			calcRad = Math.toRadians(calcAngle);
			
			if(calcAngle != 180 || calcAngle != 90 || calcAngle != 270 || calcAngle != 0)
				new Particle(x + (int)(onEntity.getSize()/2*Math.cos(calcRad)), y - (int)(onEntity.getSize()/2*Math.sin(calcRad)), calcAngle);
			
			calcAngle += 2;
			calcAngle = calcAngle%360;
		}
	}
	public int getLifespan() {
		return lifespan;
	}
	public void decrementLifespan(){
		lifespan--;
	}
	public int getAngle() {
		return angle;
	}
	public void setAngle(int angle) {
		this.angle = angle;
	}
	public int getVelocity() {
		return velocity;
	}
	public void decrementVelocity(){
		velocity--;
	}
	
}

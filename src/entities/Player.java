package entities;

import java.awt.Graphics;

import java.awt.Rectangle;
import java.awt.Toolkit;
import platform.Launch;
import platform.SoundBank;
import ui.UserDisplay;

public class Player extends Entity{
	
	public static Player p = new Player();
	
	private int score;
	private int food;
	private int foodNeeded;
	private boolean growing;
	private boolean alive;
	
	public static boolean playing = false;
	private static int SPRITESHEET_X = 25;
	private static int SPRITESHEET_Y = 25;
	//private static final int SPRITESHEET_FRAMES = 0;
	
	public Player(int x, int y, int size){
		this.img = Toolkit.getDefaultToolkit().getImage("src/resources/entity_player.png");
		this.rect = new Rectangle(x, y, size, size);
		this.growing = false;
		this.foodNeeded = rect.width/GameClocks.gridLength * 10;
		this.alive = true;
		entities.add(this);
		animate();
	}
	
	public Player(){
		this(UserDisplay.WINDOW_X/2, 1-GameClocks.gridLength, GameClocks.gridLength);
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, getX()+1, getY()+1, getX() + getSize(), getY() + getSize(), 
				SPRITESHEET_X*(getSize()/GameClocks.gridLength - 1), 0, 
				SPRITESHEET_X*(getSize()/GameClocks.gridLength - 1) + SPRITESHEET_X*(getSize()/GameClocks.gridLength), 
				SPRITESHEET_Y*(getSize()/GameClocks.gridLength), 
				null, null);
		if(playing && alive) update();
	}

	@Override
	public void update() {
		if(getY()+getSize() <= 0 && playing){
			death();
			return;
		}else{
			setScore(getScore()+1);
			Launch.ui.setTextScore(score);
		}
	}

	@Override
	public void animate() {
		new Thread(){
			public void run(){
				//boolean directionChange = false;
				while(alive){
					//if(!directionChange) {
						//SPRITESHEET_X++;
						//if (SPRITESHEET_X > getSize()) directionChange = !directionChange;
					//}
					//else {
					//	SPRITESHEET_X--;
					//	if (SPRITESHEET_X < 1) directionChange = !directionChange;
					//}
					try{Thread.sleep(25);}catch(InterruptedException ex){};
				}
			}
		}.start();
	}
	
	public void death(){
		SoundBank.sound_play_death();
		alive = false;
	}
	
	public void grow(){
		growing = true;			
		setFoodNeeded((getSize()+1)/GameClocks.gridLength * 10);
		setFood(0);
		System.out.println("Growing");
		new Thread(){
			public void run(){
				boolean shifting = true;
				for(int t = 0; t < GameClocks.gridLength; t++){
					p.setSize(p.getSize()+1);
					if (shifting) p.setX(p.getX()-1);
					// Below lines reduce the size of all food to make the player appear to get larger
					for(Entity e : entities){
						if (e instanceof Food){
							e.setSize(e.getSize()-1);
							if (shifting) e.setX(e.getX()-1);
							if(e.getSize() <= 1) 
								entities.remove(e);
						}
					}
					shifting = !shifting;
					try{Thread.sleep(50);}catch(InterruptedException e){}
				}
				Player.p.growing = false;
			}
		}.start();
	}
	
	public void moveRight(){
		if(getX() + GameClocks.gridLength <= UserDisplay.WINDOW_X - getSize()){
			boolean success = true;
			int x1 = getX() + GameClocks.gridLength;
			Rectangle temp = new Rectangle(x1, getY(), getSize(), getSize());
			for(Entity e : entities)
				if(e instanceof Food)
					if(temp.intersects(e.rect) && getSize() < e.getSize())
						success = false;			
			if (success) setX(x1);
		}
	}
	
	public void moveLeft(){
		if(getX() - GameClocks.gridLength >= 0){
			boolean success = true;
			int x1 = getX() - GameClocks.gridLength;
			Rectangle temp = new Rectangle(x1, getY(), getSize(), getSize());
			for (Entity e : entities)
				if(e instanceof Food)
					if(temp.intersects(e.rect) && getSize() < e.getSize())
						success = false;			
			if (success) setX(x1);
		}
	}
	
	public void setScore(int score){
		this.score = score;
	}
	public int getScore(){
		return score;
	}
	public void setFood(int food){
		this.food = food;
	}
	public int getFood(){
		return food;
	}
	public int getFoodNeeded(){
		return foodNeeded;
	}
	public void setFoodNeeded(int foodNeeded){
		this.foodNeeded = foodNeeded;
	}
	public boolean getGrowing(){
		return growing;
	}
	public void setAlive(boolean living){
		this.alive = living;
	}
	public boolean getAlive(){
		return alive;
	}
}

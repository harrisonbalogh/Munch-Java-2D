package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import entities.Food;
import entities.GameClock;
import entities.Player;

public class GamePanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Image bg1 = Toolkit.getDefaultToolkit().getImage("src/resources/scene_background_1.png");
	int bg1_x = 0;
	int bg1_y = 0;
	Image bg2 = Toolkit.getDefaultToolkit().getImage("src/resources/scene_background_2.png");
	int bg2_x = 0;
	int bg2_y = 600;
	public static boolean scrolling = false;
	
	public GamePanel(){
		setLayout(new BorderLayout());
		new GameClock();
	}
	
	@Override 
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		
		g2d.drawImage(bg1, bg1_x, bg1_y, bg1_x + 400, bg1_y + 600, 400, 0, 800, 600, null, null);
		g2d.drawImage(bg2, bg2_x, bg2_y, bg2_x + 400, bg2_y + 600, 400, 0, 800, 600, null, null);
		g2d.drawString(Player.p.getScore() + "", 4, 20);
		if(Player.playing){
			bg1_y -= GameClock.movementSpeed;
			bg2_y -= GameClock.movementSpeed;
			if(bg1_y+600 < 1) bg1_y = 600;
			if(bg2_y+600 < 1) bg2_y = 600;
		}
		
		
		Player.p.draw(g2d);
		g2d.setColor(Color.BLACK);
		if(!Player.p.getAlive()) g2d.drawString("GAME OVER", 155, 300);
		else if(!Player.playing) g2d.drawString("PAUSED", 155, 300);
		for(Food f : Food.food){
			f.draw(g2d);
		}
		g2d.dispose();
		try {
	    	Thread.sleep(10);
	    	}
	    catch(InterruptedException ex) {}
    }		
}
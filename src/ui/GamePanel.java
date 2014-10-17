package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entities.Food;
import entities.GameClock;
import entities.Player;

public class GamePanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public GamePanel(){
		setLayout(new BorderLayout());
		new GameClock();
	}
	
	@Override 
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		
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
package ui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;
import entities.Checkers;
import entities.Entity;
import entities.GameClocks;

public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static boolean playing = false;
	private static boolean paused = false;
	
	Image bg1 = Toolkit.getDefaultToolkit().getImage("src/resources/scene_background_1.png");
	int bg1_x = 0;
	int bg1_y = 0;
	Image bg2 = Toolkit.getDefaultToolkit().getImage("src/resources/scene_background_2.png");
	int bg2_x = 0;
	int bg2_y = 600;
	public static boolean scrolling = false;
	
	public MainPanel(){
		setLayout(new BorderLayout());
		new GameClocks();
	}
	
	@Override 
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		
		if (Options.difficulty == 0){
			for (int x = 0; x < UserDisplay.WINDOW_X/GameClocks.gridLength; x++){
				g.drawLine(x*GameClocks.gridLength, 0, x*GameClocks.gridLength, UserDisplay.WINDOW_Y);
			}
		}
		
		for ( Entity e : Entity.entities ) 
			e.draw(g2d);
	
		if (MainPanel.isPlaying()){
			bg1_y -= GameClocks.movementSpeed;
			bg2_y -= GameClocks.movementSpeed;
			if(bg1_y+600 < 1) bg1_y = 600;
			if(bg2_y+600 < 1) bg2_y = 600;
			
			if (Options.difficulty == 0) for ( Checkers c : Checkers.checkers ) c.draw(g2d);
			if(MainPanel.isPaused()) g2d.drawString("PAUSED", 155, 300);
		}

//		g2d.drawImage(bg1, bg1_x, bg1_y, bg1_x + 400, bg1_y + 600, 400, 0, 800, 600, null, null);
//		g2d.drawImage(bg2, bg2_x, bg2_y, bg2_x + 400, bg2_y + 600, 400, 0, 800, 600, null, null);
//		g2d.drawString(Player.p.getScore() + "", 4, 20);
		
		g2d.dispose();
		try {
	    	Thread.sleep(10);
	    	}
	    catch(InterruptedException ex) {}
    }

	public static boolean isPlaying() {
		return playing;
	}
	public static void setPlaying(boolean state){
		playing = state;
	}
	public static boolean isPaused() {
		return paused;
	}
	public static void setPaused(boolean state) {
		paused = state;
	}
}
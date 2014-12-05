package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

import entities.Player;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MainFrame(){
		setLayout(new BorderLayout());
	}
	public void paint(Graphics g) { 
		// Paint currently does nothing by our JFrame, GOTO bottom of InterfaceItem class to see where painting of food and players is done.
		super.paint(g);
		if(MainPanel.isPlaying() && Player.isAlive()){
			if(Player.player1.getFood() != 0 ){
				g.setColor(Color.BLACK);
				g.drawLine(133 + 255*Player.player1.getFood()/Player.player1.getFoodNeeded(), 592, 133 + 255*Player.player1.getFood()/Player.player1.getFoodNeeded(), 613);
				g.setColor(Color.GREEN);
				g.fillRect(133, 592, 255*Player.player1.getFood()/Player.player1.getFoodNeeded(), 21);
			}
		}
		repaint();
	}
}
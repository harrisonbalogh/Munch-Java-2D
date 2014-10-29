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
		if(Player.playing && Player.p.getAlive()){
			g.setColor(Color.BLACK);
			g.drawLine(133 + 255*Player.p.getFood()/Player.p.getFoodNeeded(), 592, 133 + 255*Player.p.getFood()/Player.p.getFoodNeeded(), 613);
			g.setColor(Color.GREEN);
			g.fillRect(133, 592, 255*Player.p.getFood()/Player.p.getFoodNeeded(), 21);
		}
		repaint();
	}
}
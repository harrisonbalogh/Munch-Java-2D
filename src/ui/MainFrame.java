package ui;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JFrame;

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
		paintComponent(g);

	}
	
	public void paintComponent(Graphics g){
		repaint();
	}
}
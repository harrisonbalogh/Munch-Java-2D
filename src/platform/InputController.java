package platform;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import ui.MainPanel;

import entities.Bubble;
import entities.GameClocks;
import entities.Player;
import entities.ScoreSprite;

public class InputController {
		
	public static class A_Pressed extends AbstractAction
    {
		private static final long serialVersionUID = 1L;
		public void actionPerformed( ActionEvent tf )
        {
			if (MainPanel.isPlaying() && !Player.player1.getGrowing())
				Player.player1.moveLeft();
        }
    }
	
	public static class D_Pressed extends AbstractAction
    {
		private static final long serialVersionUID = 1L;
		public void actionPerformed( ActionEvent tf )
        {
			if (MainPanel.isPlaying() && !Player.player1.getGrowing())
				Player.player1.moveRight();
        }
    }
	
	public static class R_Pressed extends AbstractAction
    {
		private static final long serialVersionUID = 1L;
		public void actionPerformed( ActionEvent tf )
        {
			if(MainPanel.isPlaying()) {
				Launch.ui.reload();
			}
        }
    }
	
	public static class S_Pressed extends AbstractAction
    {
		private static final long serialVersionUID = 1L;
		public void actionPerformed( ActionEvent tf )
        {
			new ScoreSprite(Player.player1.getX() + Player.player1.getSize()/2, Player.player1.getY() + Player.player1.getSize());
			Player.player1.setScore(Player.player1.getScore() + (int)(Player.player1.getSize()/Player.player1.getSize() * 100));
			Player.player1.setFood(Player.player1.getFood() + Player.player1.getSize()/GameClocks.gridLength);
			Launch.ui.setTextScore(Player.player1.getScore());
			SoundBank.sound_play_eat();
			if(Player.player1.getFood() >= Player.player1.getFoodNeeded()){
				Player.player1.grow();
			}
        }
    }
	
	public static class W_Pressed extends AbstractAction
    {
		private static final long serialVersionUID = 1L;
		public void actionPerformed( ActionEvent tf )
        {
			if(MainPanel.isPlaying()) {
				if(MainPanel.isPlaying()){
					Bubble.spawnBubble();
				}
			}
        }
    }
	
	public static class P_Pressed extends AbstractAction
    {
		private static final long serialVersionUID = 1L;
		public void actionPerformed( ActionEvent tf )
        {
			if (MainPanel.isPlaying() && Player.isAlive()) 
				MainPanel.setPlaying(false);
			else if (!MainPanel.isPlaying() && Player.isAlive()) 
				MainPanel.setPlaying(true);
        }
    }
	
}

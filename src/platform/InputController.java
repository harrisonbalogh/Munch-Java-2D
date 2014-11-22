package platform;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

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
			if (Player.playing && !Player.p.getGrowing())
				Player.p.moveLeft();
        }
    }
	
	public static class D_Pressed extends AbstractAction
    {
		private static final long serialVersionUID = 1L;
		public void actionPerformed( ActionEvent tf )
        {
			if (Player.playing && !Player.p.getGrowing())
				Player.p.moveRight();
        }
    }
	
	public static class R_Pressed extends AbstractAction
    {
		private static final long serialVersionUID = 1L;
		public void actionPerformed( ActionEvent tf )
        {
			if(Player.playing) {
				Launch.ui.reload();
			}
        }
    }
	
	public static class S_Pressed extends AbstractAction
    {
		private static final long serialVersionUID = 1L;
		public void actionPerformed( ActionEvent tf )
        {
			new ScoreSprite(Player.p.getX() + Player.p.getSize()/2, Player.p.getY(), 100);
			Player.p.setScore(Player.p.getScore() + (int)(Player.p.getSize()/Player.p.getSize() * 100));
			Player.p.setFood(Player.p.getFood() + Player.p.getSize()/GameClocks.gridLength);
			Launch.ui.setTextScore(Player.p.getScore());
			SoundBank.sound_play_eat();
			if(Player.p.getFood() >= Player.p.getFoodNeeded()){
				Player.p.grow();
			}
        }
    }
	
	public static class W_Pressed extends AbstractAction
    {
		private static final long serialVersionUID = 1L;
		public void actionPerformed( ActionEvent tf )
        {
			if(Player.playing) {
				if(Player.playing){
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
			if (Player.playing && Player.p.getAlive()) 
				Player.playing = false;
			else if (!Player.playing && Player.p.getAlive()) 
				Player.playing = true;
        }
    }
	
}

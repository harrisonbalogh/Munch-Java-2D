package platform;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import entities.Player;

public class InputController {
		
	public static class A_Pressed extends AbstractAction
    {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void actionPerformed( ActionEvent tf )
        {
            if(Player.playing && !Player.p.getGrowing()) Player.p.moveLeft();
        }
    }
	
	public static class D_Pressed extends AbstractAction
    {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void actionPerformed( ActionEvent tf )
        {
			if(Player.playing && !Player.p.getGrowing()) Player.p.moveRight();
        }
    }
	
	public static class R_Pressed extends AbstractAction
    {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void actionPerformed( ActionEvent tf )
        {
			if(Player.playing) {
				Launch.ui.runPlayScene();
			}
        }
    }
	
	public static class P_Pressed extends AbstractAction
    {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void actionPerformed( ActionEvent tf )
        {
			if( Player.playing && Player.p.getAlive()) Player.playing = false;
			else if(!Player.playing && Player.p.getAlive()) Player.playing = true;
        }
    }
	
}

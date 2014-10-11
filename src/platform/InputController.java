package platform;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class InputController {
		
	public static class A_Pressed extends AbstractAction
    {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void actionPerformed( ActionEvent tf )
        {
            if(Launch.playing && !Launch.p.getGrowing()) Launch.p.moveLeft();
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
			if(Launch.playing && !Launch.p.getGrowing()) Launch.p.moveRight();
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
			if( Launch.playing && Launch.p.getAlive()) Launch.playing = false;
			else if(!Launch.playing && Launch.p.getAlive()) Launch.playing = true;
        }
    }
	
}

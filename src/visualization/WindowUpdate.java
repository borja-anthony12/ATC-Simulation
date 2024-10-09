package src.visualization;

import src.main.*;
import src.visualization.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class WindowUpdate extends JFrame{
	private static final long serialVersionUID = 1435203151832523191L;
	
	/**
	 * Updates the screen constantly
	 * 
	 * @param Panel Takes in the Panel for updating what's on the screen
	 */
	public WindowUpdate(JPanel Panel, Tower tower) {
		Timer timer = new Timer(Main.UPDATE_INTERAVAL,  new ActionListener() {
		PlaneAttributes one = new PlaneAttributes();
		int vel;
		double z;
		boolean takeoff, landing;
		
			@Override
			public void actionPerformed(ActionEvent e) {
				Panel.repaint();
				
				
				for(PlaneAttributes plane: tower.getPlanes()) {
					//plane movement
				}
				
				
				
				
				
				
				
				
				/*
				one.takeoff(takeoff);
				one.landing(landing);
				
				this.takeoff = takeoff;
				this.landing = landing;
				
				
				if(takeoff == true) {
					one.upVelocity(vel, takeoff);
					one.upAlt(z);
				}else if(landing == true)
					one.downVelocity(vel, landing);
					one.downAlt(z);
				*/
				
			}
		});
		timer.start();
	}
}

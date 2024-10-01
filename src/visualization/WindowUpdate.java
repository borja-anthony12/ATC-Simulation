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
	public WindowUpdate(JPanel Panel) {
		Timer timer = new Timer(Main.UPDATE_INTERAVAL,  new ActionListener() {
//		PlaneAttributes one = new PlaneAttributes();
		int vel;
			@Override
			public void actionPerformed(ActionEvent e) {
				Panel.repaint();
				/*if(???? = true) {
					one.upVelocity(vel);
				}
				one.upVelocity(vel);
				this.vel = vel;
				
				vel *= 1.2;*/
			}
		});
		timer.start();
	}
}

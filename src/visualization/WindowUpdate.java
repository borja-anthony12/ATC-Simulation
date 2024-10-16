package src.visualization;

import src.main.*;
import src.visualization.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class WindowUpdate extends JFrame {
	private static final long serialVersionUID = 1435203151832523191L;
	
	/**
	 * turns an angle into a reference angle
	 * @param angle is the given angle
	 * @return returns the reference, returns -1 if an error occurs
	 */
	public static double toReferenceAngle(double angle) {
		int quadrant = (int) ((angle % 360) / 90) + 1;
		
		switch(quadrant) {
		case 1:
			return angle;
		case 2:
			return 180 - angle;
		case 3:
			return angle - 180;
		case 4:
			return 360 - angle;
		default: 
			System.out.println("Error: reference angle calculation bad");
		}
		return -1;
	}

	/**
	 * Updates the screen constantly
	 * 
	 * @param mainPanel Takes in the Panel for updating what's on the screen
	 * @param tower     Takes in tower class for creating planes
	 */
	public WindowUpdate(JPanel mainPanel, Tower tower) {
		Timer timer = new Timer(Main.UPDATE_INTERAVAL, new ActionListener() {
			// PlaneAttributes one = new PlaneAttributes();
			PlaneBehavior two = new PlaneBehavior();
			int vel;
			double z;
			boolean takeoff, landing;

			@Override
			public void actionPerformed(ActionEvent e) {

				for (PlaneAttributes plane : tower.getPlanes()) {
					double dx = 0;
					double dy = 0;
					double vel = plane.getVel();
					double dir = plane.getDirection() % 360;

					//movement
					switch (plane.getDirectionQuadrant()) {
					case 1:
						dx = vel * Math.sin(toReferenceAngle(dir));
						dy = -vel * Math.cos(toReferenceAngle(dir));
						break;
					case 2:
						dx = -vel * Math.cos(toReferenceAngle(dir));
						dy = -vel * Math.sin(toReferenceAngle(dir));
						break;
					case 3:
						dx = -vel * Math.sin(toReferenceAngle(dir));
						dy = vel * Math.cos(toReferenceAngle(dir));
						break;
					case 4:
						dx = vel * Math.cos(toReferenceAngle(dir));
						dy = vel * Math.sin(toReferenceAngle(dir));
						break;
					default:
						System.out.println("Stupid error");
					}
				}

				mainPanel.repaint();

				/*
				 * one.takeoff(takeoff); one.landing(landing);
				 * 
				 * this.takeoff = takeoff; this.landing = landing;
				 * 
				 * 
				 * if(takeoff == true) { one.upVelocity(vel, takeoff); one.upAlt(z); }else
				 * if(landing == true) one.downVelocity(vel, landing); one.downAlt(z);
				 */

			}
		});
		timer.start();
	}
}

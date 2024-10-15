package src.visualization;

import src.main.*;
import src.visualization.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class WindowUpdate extends JFrame {
	private static final long serialVersionUID = 1435203151832523191L;

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
					plane.vel = 1;
					double vel = plane.getVel();
					double dir = plane.getDirection() % 360;
					plane.turn(0.1);

					switch (plane.getDirectionQuadrant()) {
					case 1:
						dx = vel * Math.sin(dir);
						dy = -vel * Math.cos(dir);
						break;
					case 2:
						dx = -vel * Math.cos(180 - dir);
						dy = -vel * Math.sin(180 - dir);
						break;
					case 3:
						dx = -vel * Math.sin(dir - 180);
						dy = vel * Math.cos(dir - 180);
						break;
					case 4:
						dx = vel * Math.cos(360 - dir);
						dy = vel * Math.sin(360 - dir);
						break;
					default:
						System.out.println("Stupid error");
					}

					System.out.println("Direction: " + dir);
					plane.move(dx, dy);
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

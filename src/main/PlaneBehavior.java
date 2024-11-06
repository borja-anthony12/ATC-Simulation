package src.main;

import java.awt.image.BufferedImage;

public class PlaneBehavior {
	int vel = 0;
	protected boolean isCrashed = false;
	boolean takeoff = false;
	boolean landing = false;
	boolean cruise = true;
	
	/**
	 * init
	 */
	public PlaneBehavior(/*BufferedImage image*/) {
			//PlaneAttributes plane = new PlaneAttributes(image);
	}
	
	/**
	 * makes plane take off
	 */
	public void takeOff() {
		System.out.println("Plane is taking off");
		boolean cruise = false;
	}

	/**
	 * handles landing process
	 */
	public void landing () {

	}

	/**
	 * handles flying logic
	 */
	public void flying () {
		System.out.println("Plane is flying");

	}
	
	/**
	 * crashes plane
	 */
	public void crash() {
		isCrashed = true;
	}

}

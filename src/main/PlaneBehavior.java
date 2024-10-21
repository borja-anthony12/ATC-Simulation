package src.main;

import java.awt.image.BufferedImage;

public class PlaneBehavior {
	int vel = 0;
	protected boolean isCrashed = false;
	boolean takeoff = false;
	boolean landing = false;
	boolean cruise = true;
	//PlaneAttributes plane = new PlaneAttributes();
	public void takeOff() {

		boolean cruise = false;
	}
	
	public PlaneBehavior(/*BufferedImage image*/) {
			//PlaneAttributes plane = new PlaneAttributes(image);
	}

	public void takeoff () {

	}

	public void landing () {

	}

	public void flying () {


	}
	
	public void crash() {
		isCrashed = true;
	}

}

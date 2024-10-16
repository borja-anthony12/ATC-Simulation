package src.main;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

/*
 * Class for handling all logic and detection of crashes
 */
public class Tower {

	protected ArrayList<PlaneAttributes> planes;
	private PlaneAttributes[] gatePlanes;
	private boolean[] gates;
	private final int MINIMUM_SPACE_BETWEEN_PLANES = 10; // ADJUST THESE VALUES
	private final int CRASH_DIVERT_DISTANCE = 40; // ADJUST THESE VALUES

	/**
	 * Initializes the tower
	 */
	public Tower() {
		planes = new ArrayList<PlaneAttributes>();
		gates = new boolean[4];
		gatePlanes = new PlaneAttributes[4];
	}
	
	/**
	 * Checks if planes collide
	 */
	public void checkPlaneCollision() {
		PlaneAttributes[] planeArray = new PlaneAttributes[planes.size()];
		for (int i = 0; i < planes.size(); i++) {
			planeArray[i] = planes.get(i);
		}

		for (int i = 0; i < planeArray.length; i++) {
			for (int j = 0; j < planeArray.length; j++) {
				double dif = comparePlanePos(planeArray[i], planeArray[j]);

				if (dif < MINIMUM_SPACE_BETWEEN_PLANES) {
					planeArray[i].isCrashed();
					planeArray[j].isCrashed();
				}
			}
		}
	}

	/**
	 * Tells planes to turn away if they get too close together
	 */
	public void divertPlanesFromCollision() {
		PlaneAttributes[] planeArray = new PlaneAttributes[planes.size()];
		for (int i = 0; i < planes.size(); i++) {
			planeArray[i] = planes.get(i);
		}

		for (int i = 0; i < planeArray.length; i++) {
			for (int j = 0; j < planeArray.length; j++) {
				double dif = comparePlanePos(planeArray[i], planeArray[j]);

				if (dif < CRASH_DIVERT_DISTANCE) {
					planeArray[i].turn(45);
					planeArray[j].turn(-45);
				}
			}
		}

	}

	/**
	 * moves a plane to a gate from the runway
	 */
	public void taxiPlaneToGate(PlaneAttributes plane) {
		for (int i = 0; i < gates.length; i++) {
			if (!gates[i]) {
				gates[i] = true;
				gatePlanes[i] = plane;
			}
		}
	}

	/**
	 * moves a plane to the runway from a gate
	 */
	public void taxiPlaneToRunway(PlaneAttributes plane) {
		for (int i = 0; i < gates.length; i++) {
			if (plane.getCallSign() == gatePlanes[i].getCallSign()) {
				gates[i] = false;
			}
		}
	}

	/**
	 * adds a new plane to simulation
	 */
	public void spawnPlane(int runwayAmount, int gateAmount, BufferedImage image) {
		
		PlaneAttributes plane = new PlaneAttributes(image);
		Random r = new Random();
		int chooseRunway;
		
		switch(runwayAmount){
		case 1:
			
			plane.setPlane(125, 300, 0);
			planes.add(plane);
			plane.turn(30);
			break;
		case 2: 
			chooseRunway = r.nextInt(2)+1;
			if(chooseRunway == 1) {
				plane.setPlane(125, 300, 0);
				planes.add(plane);
				plane.turn(30);
			}else if(chooseRunway == 2) {
				plane.setPlane(230, 45, 0);
				planes.add(plane);
				plane.turn(330);
			}
			
			
			break;
		default:
			System.out.println("*Sighs*");
			break;
		}
		System.out.println("Total planes: " + planes.size());
	}
	
	/**
	 * deletes a plane
	 */
	public void despawnPlane() {
		planes.remove(planes.size() - 1);
		System.out.println("Total planes: " + planes.size());
	}

	/*
	 * adjusts gate availability
	 */
	public void setGateCount(int count) {
		for (int i = 4 - count; i > 0; i--) {
			gates[4 - i] = true;
		}
	}

	/**
	 * gets the difference the X Y values of two planes Receives positions in X Y
	 * array format
	 * 
	 * @param p1 is plane 1
	 * @param p2 is plane 2
	 */
	private double comparePlanePos(PlaneAttributes p1, PlaneAttributes p2) {
		double deltaX = p1.getPosition()[0] - p2.getPosition()[0];
		double deltaY = p1.getPosition()[1] - p2.getPosition()[1];
		double dif = Math.sqrt(Math.pow(deltaX, deltaY));
		return dif;
	}

	/**
	 * Gets list planes for info stuff
	 */
	public ArrayList<PlaneAttributes> getPlanes() {
		return planes;
	}
}
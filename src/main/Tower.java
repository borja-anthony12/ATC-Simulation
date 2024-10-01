package src.main;

import java.util.ArrayList;

/*
 * Class for handling all logic and detection of crashes
 */
public class Tower {

	protected ArrayList<PlaneAttributes> planes;
	private PlaneAttributes[] gatePlanes;
	private boolean[] gates;
	private final int MINIMUM_SPACE_BETWEEN_PLANES = 10;	//ADJUST THESE VALUES
	private final int CRASH_DIVERT_DISTANCE = 40;			//ADJUST THESE VALUES

	/**
	 * 
	 * @param gateCount is the number of gates in the airport The max amount of
	 *                  gates is 5, the minimum is 2
	 */
	public Tower(int gateCount) {
		planes = new ArrayList<PlaneAttributes>();

		if (gateCount < 2)
			gateCount = 2;
		if (gateCount > 5)
			gateCount = 5;
		gates = new boolean[gateCount];
		gatePlanes = new PlaneAttributes[gateCount];
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
				int dif = comparePlanePos(planeArray[i], planeArray[j]);

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
				int dif = comparePlanePos(planeArray[i], planeArray[j]);

				if (dif < CRASH_DIVERT_DISTANCE) {
				//	planeArray[i].turn();		//turn the planes away
				//	planeArray[j].turn();
				}
			}
		}

	}

	/**
	 * moves a plane to a gate from the run way
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
	 * moves a plane to the run way from a gate
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
	public void spawnPlane() {
		PlaneAttributes plane = new PlaneAttributes();
		planes.add(plane);
	}

	/**
	 * gets the difference the X Y values of two planes Receives positions in X Y
	 * array format
	 * 
	 * @param p1 is plane 1
	 * @param p2 is plane 2
	 */
	private int comparePlanePos(PlaneAttributes p1, PlaneAttributes p2) {
		int deltaX = p1.getPosition()[0] - p2.getPosition()[0];
		int deltaY = p1.getPosition()[1] - p2.getPosition()[1];
		int dif = (int) Math.sqrt(deltaX ^ 2 + deltaY ^ 2);
		return dif;
	}

	/**
	 * Gets list planes for info stuff
	 */
	public ArrayList<PlaneAttributes> getPlanes() {
		return planes;
	}
}
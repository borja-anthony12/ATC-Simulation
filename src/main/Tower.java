package src.main;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * Class for handling all logic and detection of crashes
 */
public class Tower {
	
	protected ArrayList<PlaneAttributes> planes;
	private PlaneAttributes[] gatePlanes;
	private boolean[] gates;
	private int minimumSpaceBetweenPlanes;
	
	/**
	 * 
	 * @param gateCount is the number of gates in the airport
	 */
	public Tower(int gateCount) {
		planes = new ArrayList<PlaneAttributes>();
		gates = new boolean[gateCount];
		gatePlanes = new PlaneAttributes[gateCount];
	}
	
	/**
	 * Checks if planes collide
	 */
	public void checkPlaneCollision() {
		PlaneAttributes[] planeArray = (PlaneAttributes[]) planes.toArray();
		
		for(int i = 0; i < planeArray.length; i++) {
			for(int j = 0; j < planeArray.length; j++) {
				int dif = comparePlanePos(planeArray[i], planeArray[j]);
				
				if(dif < minimumSpaceBetweenPlanes) {
					planeArray[i].isCrashed();
					planeArray[j].isCrashed();
				}
			}
		}
	}
	
	/**
	 * moves a plane to a gate from the runway
	 */
	public void taxiPlaneToGate(PlaneAttributes plane) {
		for(int i = 0; i < gates.length; i++) {
			if(!gates[i]) {
				gates[i] = true;
				gatePlanes[i] = plane;
			}
		}
	}
	
	/**
	 * moves a plane to the runway from a gate
	 */
	public void taxiPlaneToRunway(PlaneAttributes plane) {
		for(int i = 0; i < gates.length; i++) {
			if(plane.getCallSign() == gatePlanes[i].getCallSign()) {
				gates[i] = false;
			}
		}
	}
	
	public void spawnPlane() {
		PlaneAttributes plane = new PlaneAttributes();
		planes.add(plane);
	}
	
	/**
	 * gets the difference the X Y values of two planes
	 * Receives positions in X Y array format
	 */
	private int comparePlanePos(PlaneAttributes p1, PlaneAttributes p2) {
		int deltaX = p1.getPosition()[0] - p2.getPosition()[0];
		int deltaY = p1.getPosition()[1] - p2.getPosition()[1];
		int dif = (int) Math.sqrt(deltaX^2 + deltaY^2);
		return dif;
	}
	
	/**
	 * Gets list planes for info stuff
	 */
	public ArrayList<PlaneAttributes> getPlanes() {
		return planes;
	}
} 
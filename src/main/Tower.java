package src.main;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * Class for handling all logic and detection of crashes
 */
public class Tower {
	
	protected ArrayList<PlaneAttributes> planeAttributes;
	private PlaneAttributes[] gatePlanes;
	private boolean[] gates;
	private int minimumSpaceBetweenPlanes;
	
	public Tower(int gateCount) {
		planeAttributes = new ArrayList<PlaneAttributes>();
		gates = new boolean[gateCount];
		gatePlanes = new PlaneAttributes[gateCount];
	}
	
	/*
	 * Gets list planes for info stuff
	 */
	public ArrayList<PlaneAttributes> getPlanes() {
		return planeAttributes;
	}
	
	/*
	 * Checks if planes collide
	 */
	public void checkPlaneCollision() {
		PlaneAttributes[] planeArray = (PlaneAttributes[]) planeAttributes.toArray();
		
		for(int i = 0; i < planeArray.length; i++) {
			for(int j = 0; j < planeArray.length; j++) {
				int dif = comparePlanePos(planeArray[i], planeArray[j]);
				if(dif < minimumSpaceBetweenPlanes) {
					planeArray[i].crash();
					planeArray[j].crash();
				}
			}
		}
	}
	
	/*
	 * moves a plane to a gate from the runway
	 */
	public void taxiPlaneToGate(PlaneAttributes plane) {
		for(int i = 0; i < gates.length; i++) {
			if(!gates[i]) {
				gates[i] = true;
				gatePlanes[i] = plane.getCallSign();
			}
		}
	}
	
	/*
	 * moves a plane to the runway from a gate
	 */
	public void taxiPlaneToRunway(PlaneAttributes plane) {
		for(int i = 0; i < gates.length; i++) {
			if(plane.getCallSign() == gate) {
				
			}
		}
	}
	
	/*
	 * gets the difference the X Y values of two planes
	 * Receives positions in X Y array format
	 */
	private int comparePlanePos(PlaneAttributes p1, PlaneAttributes p2) {
		int deltaX = p1.getPosition()[0] - p2.getPosition()[0];
		int deltaY = p1.getPosition()[1] - p2.getPosition()[1];
		int dif = (int) Math.sqrt(deltaX^2 + deltaY^2);
		return dif;
	}
} 
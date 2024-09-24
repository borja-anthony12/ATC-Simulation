package src.main;

import java.util.ArrayList;
import java.util.Arrays;

public class Tower {
	
	protected ArrayList<PlaneAttributes> planeAttributes;
	private int x;
	private int y;
	private int minimumSpaceBetweenPlanes;
	
	public Tower() {
		planeAttributes = new ArrayList<PlaneAttributes>();
	}
	
	public Tower(int x, int y) {
		planeAttributes = new ArrayList<PlaneAttributes>();
		this.x = x;
		this.y= y;
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
	 * gets the difference the X Y Z values of two planes
	 * Receives positions in X Y Z array format
	 */
	private int comparePlanePos(PlaneAttributes p1, PlaneAttributes p2) {
		int[] deltaPosition = new int[3];
		deltaPosition = p1.getPosition() - p2.getPosition();
		
		int dif = (int) Math.sqrt(deltaPosition[0]^2 + deltaPosition[1]^2);
		return dif;
	}
} 
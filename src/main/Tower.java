package src.main;


import java.awt.*;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import src.visualization.*;

/*
 * Class for handling all logic and detection of crashes
 */
public class Tower {

	protected ArrayList<PlaneAttributes> planes;
	protected GUIElements.AirportPanel apVisuals = new GUIElements.AirportPanel();
	private PlaneAttributes[] gatePlanes;
	private boolean[] gates;
	private final int MINIMUM_SPACE_BETWEEN_PLANES = 30; // ADJUST THESE VALUES
	private final int CRASH_DIVERT_DISTANCE = 100; // ADJUST THESE VALUES
	private int runwayOneXPos, runwayOneYPos;
	private int runwayTwoXPos, runwayTwoYPos;
	private int runwayWidth, runwayHeight;
	private int runwayRotation;
	private int runwayAmount;

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

				if (dif < MINIMUM_SPACE_BETWEEN_PLANES && i != j && i < j) {
					System.out.println("Planes crashed");
					planeArray[i].crash();
					planeArray[j].crash();
					despawnPlane(planeArray[i]);
					despawnPlane(planeArray[j]);
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

				if (dif < CRASH_DIVERT_DISTANCE && i != j && i < j) {
					planeArray[i].turn(2.5);
					planeArray[j].turn(-2.5);
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

	public boolean planeOnRunway(PlaneAttributes plane) {
		//TODO Fix bug where the runway One is offset
		// and runway Two is only being detected at the intersect point of the two runways

		// Get the polygons for both the runway and the plane
		Polygon planePolygon = apVisuals.getPlanePolygon(plane);

		// Check if the polygons intersect
		Area planeArea = new Area(planePolygon);
		System.out.println(runwayAmount);
		if(runwayAmount == 1) {
			Polygon runwayPolygon = apVisuals.getRunwayPolygon(runwayOneXPos, runwayOneYPos, runwayWidth, runwayHeight, runwayRotation);
			Area runwayArea = new Area(runwayPolygon);
			System.out.println("Entered");
			planeArea.intersect(runwayArea);
		}
		if (runwayAmount == 2) {
			System.out.println("Runway Rotation: " + runwayRotation);
			Polygon runwayOnePoly = apVisuals.getRunwayPolygon(runwayOneXPos, runwayOneYPos, runwayWidth, runwayHeight, runwayRotation);
			Polygon runwayTwoPoly = apVisuals.getRunwayPolygon(runwayTwoXPos, runwayTwoYPos, runwayWidth, runwayHeight, -runwayRotation);
			Area runwayOneArea = new Area(runwayOnePoly);
			Area runwayTwoArea = new Area(runwayTwoPoly);
			planeArea.intersect(runwayOneArea);
			planeArea.intersect(runwayTwoArea);
			System.out.println("Runway One X: " + runwayOneXPos + " Runway One Y: " + runwayOneYPos);
			System.out.println("Runway Two X: " + runwayTwoXPos + " Runway Two Y: " + runwayTwoYPos);
		}

		return !planeArea.isEmpty();  // If not empty, there's an overlap
	}

	public void getRunwayParam(int runwayOneXPos, int runwayOneYPos, int runwayTwoXPos, int runwayTwoYPos, int runwayWidth, int runwayHeight, int runwayRotation, int runwayAmount) {
		this.runwayOneXPos = runwayOneXPos;
		this.runwayOneYPos = runwayOneYPos;
		this.runwayTwoXPos = runwayTwoXPos;
		this.runwayTwoYPos = runwayTwoYPos;
		this.runwayWidth = runwayWidth;
		this.runwayHeight = runwayHeight;
		this.runwayRotation = runwayRotation;
		this.runwayAmount = runwayAmount;
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
	
	public void despawnPlane(PlaneAttributes plane) {
		planes.remove(plane);
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
		double dif = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
		return dif;
	}

	/**
	 * Gets list planes for info stuff
	 */
	public ArrayList<PlaneAttributes> getPlanes() {
		return planes;
	}
}
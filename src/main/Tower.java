package src.main;


import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import src.visualization.*;

/*
 * Class for handling all logic and detection of crashes
 */
public class Tower {

	protected ArrayList<PlaneAttributes> planes;
	protected ArrayList<Rectangle2D> runways;
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
        // Get the polygon for the plane
        Polygon planePolygon = apVisuals.getPlanePolygon(plane);
        Area planeArea = new Area(planePolygon);

        if (runwayAmount == 1) {
            // Single runway check
            Polygon runwayPolygon = apVisuals.getRunwayPolygon(runwayOneXPos, runwayOneYPos, runwayWidth, runwayHeight, runwayRotation);
            Area runwayArea = new Area(runwayPolygon);

            // Create a copy of planeArea for intersection
            Area intersectionArea = new Area(planeArea);
            intersectionArea.intersect(runwayArea);
            return !intersectionArea.isEmpty();
        } else if (runwayAmount == 2) {
            // Check first runway
            Polygon runwayOnePoly = apVisuals.getRunwayPolygon(runwayOneXPos, runwayOneYPos, runwayWidth, runwayHeight, runwayRotation);
            Area runwayOneArea = new Area(runwayOnePoly);
            Area intersectionOne = new Area(planeArea);
            intersectionOne.intersect(runwayOneArea);

            // Check second runway
            Polygon runwayTwoPoly = apVisuals.getRunwayPolygon(runwayTwoXPos, runwayTwoYPos, runwayWidth, runwayHeight, -runwayRotation);
            Area runwayTwoArea = new Area(runwayTwoPoly);
            Area intersectionTwo = new Area(planeArea);
            intersectionTwo.intersect(runwayTwoArea);

            // Return true if the plane intersects with either runway
            return !intersectionOne.isEmpty() || !intersectionTwo.isEmpty();
        }

        return false;
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

		PlaneAttributes plane;
		Random r = new Random();
		int randNum;

		if(r.nextInt(2) == 0) {
			plane = new PlaneAttributes(image, false);
			switch(runwayAmount){
			case 1:

				plane.setPlane(125, 300, 0);
				planes.add(plane);
				plane.turn(30);
				break;
			case 2:
				randNum = r.nextInt(2)+1;
				if(randNum == 1) {
					plane.setPlane(125, 300, 0);
					planes.add(plane);
					plane.turn(30);
				}
				else if(randNum == 2) {
					plane.setPlane(230, 45, 0);
					planes.add(plane);
					plane.turn(330);
				}
				break;
			default:
				System.out.println("*Sighs*");
				break;
			}
		}
		else {
			plane = new PlaneAttributes(image, true);
			for(int i = 0; i < gateAmount; i++){
				if(!gates[i]) {
					plane.setPlane(160, 300, 0);
					planes.add(plane);
					plane.turn(110);
				}
			}
		}
		
		System.out.println("Total planes: " + planes.size());
	}

	/**
	 * deletes a plane
	 */
	public void despawnPlane() {
		planes.remove(planes.size() - 1);
	}

	public void despawnPlane(PlaneAttributes plane) {
		planes.remove(plane);

	}
	
	public int getPlaneAmount() {
		return planes.size();
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
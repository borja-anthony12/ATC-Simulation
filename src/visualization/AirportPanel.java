package src.visualization;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import src.main.*;
//import src.visualization.*;

/**
 * Class that extends JPanel and creates runways
 */
class AirportPanel extends JPanel {
	private static final long serialVersionUID = 2853523647566452733L;

	private final Color taxiWayColour = Color.DARK_GRAY;
	private final Color runwayColour = Color.GRAY;
	private final Color buildingColour = Color.LIGHT_GRAY;
	private final Color background = Color.BLACK;

	/* Initialises Airports X & Y positions */
	private int runwayXPos, runwayYPos; // Initialises the runways X and Y
	private int buildingXPos, buildingYPos; // Initialises the buildings X and Y
	private int gateXPos, gateYPos; // Initialises the gates X and Y
	private int runwayAmount;
	private int gateAmount = 4;

	private final int RUNWAY_WIDTH = 40; // Initialises the runway width
	private final int RUNWAY_HEIGHT = 450; // Initialises the runway height
	private final int RUNWAY_ROTATION = 60; // Initialises the runway rotation

	private final int GATE_WIDTH = 10; // Initialises the gate width
	private final int GATE_HEIGHT = 65; // Initialises the gate height

	private final int BUILDING_WIDTH = 340; // Initialises the building width
	private final int BUILDING_HEIGHT = 110; // Initialises the building height
	private final int BUILDING_ROTATION = -30;

	private final int TAXIWAY_WIDTH = RUNWAY_WIDTH / 2;

	private final String[] imageLocation = { "/plane-images/plane-blue.png", "/plane-images/plane-jared.png",
			"/plane-images/plane-cyan.png", "/plane-images/plane-green.png", "/plane-images/plane-purple.png",
			"/plane-images/plane-nolan.png", "/plane-images/plane-white.png", "/plane-images/plane-will.png" };

	private Graphics2D g2d;
	private Tower tower;

	/* Initialises Airport and Window Components X and Y */
	private int windowWidth, windowHeight; // Initialises the windows width (720) and height (600)

	/**
	 * Constructor of Airport Panel and initialises the background as black
	 * 
	 * @param windowWidth  Takes in window Width
	 * @param windowHeight Takes in window Height
	 */
	public AirportPanel() {
		setBackground(background); // Sets the panel background to black
	}

	void getWindowSize(int windowWidth, int windowHeight) {
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
	}

	void getRunwayAmount(int runwayAmount) {
		this.runwayAmount = runwayAmount;
	}

	public void getGateAmount(int gateAmount) {
		this.gateAmount = gateAmount;
	}

	/**
	 * Overrides paintComponent to create taxi ways, runway, gates and airport
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2d = (Graphics2D) g;
		tower = new Tower();

		int taxiWayXPos = taxiWayXPos(windowWidth, RUNWAY_WIDTH);
		int taxiWayYPos = taxiWayYPos(windowHeight, RUNWAY_HEIGHT);

		int[][] taxiWayData = { { taxiWayXPos - 85, taxiWayYPos - 160, TAXIWAY_WIDTH, RUNWAY_HEIGHT, RUNWAY_ROTATION },
				{ taxiWayXPos - 15, taxiWayYPos - 40, TAXIWAY_WIDTH, RUNWAY_HEIGHT, RUNWAY_ROTATION },
				{ taxiWayXPos - 237, taxiWayYPos + 156, TAXIWAY_WIDTH, 155, -30 },
				{ taxiWayXPos - 190, taxiWayYPos + 126, TAXIWAY_WIDTH, 155, -30 },
				{ taxiWayXPos - 144, taxiWayYPos + 100, TAXIWAY_WIDTH, 155, -30 },
				{ taxiWayXPos(windowWidth, BUILDING_WIDTH) - 30, taxiWayYPos(windowHeight, BUILDING_HEIGHT) + 30,
						BUILDING_WIDTH, 155, -30 } };

		drawALLTaxiWays(taxiWayData);

		checkIfRunway(runwayAmount, taxiWayXPos, taxiWayYPos);

		drawAirportBuilding(); // Draws the main building of the airport

		drawGates();

		try {
			drawPlanes();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Updates the Airport Components whenever the window is size is changed
	 * 
	 * @param newWidth  Takes in the windows new width
	 * @param newHeight Takes in the windows new height
	 */
	public void updateRunwayPosition(int newWidth, int newHeight) {
		/* Updates the window size */
		windowWidth = newWidth; // Updates the original width to new width
		windowHeight = newHeight; // Updates the original height to new height

		/* Updates Airport Components positions dynamically */
		// Runways
		runwayXPos = (windowWidth - RUNWAY_WIDTH) / 2; // Adjust X position to centre runway
		runwayYPos = (windowHeight - 250) / 2; // Adjust Y position to centre runway
		// Building
		buildingXPos = (windowWidth - BUILDING_WIDTH) / 2; // Adjusts the X position to centre the building
		buildingYPos = (windowHeight - BUILDING_HEIGHT) / 2; // Adjusts the Y Position to centre the building
		// Gate
		gateXPos = (windowWidth - GATE_WIDTH) / 2; // Adjusts the X position to centre the building
		gateYPos = (windowHeight - GATE_HEIGHT) / 2; // Adjusts the Y position to centre the building

	}

	/**
	 * Checks what the selected amount of runways are available
	 * 
	 * @param runwayAmount takes in the amount of runways
	 * @param taxiWayXPos  takes in taxiWayXPos
	 * @param taxiWayYPos  takes in taxiWayYPos
	 */
	private void checkIfRunway(int runwayAmount, int taxiWayXPos, int taxiWayYPos) {
		int[][] taxiWayData = { { taxiWayXPos + 170, taxiWayYPos - 50, TAXIWAY_WIDTH, 260, 150 },
				{ taxiWayXPos - 144, taxiWayYPos - 25, TAXIWAY_WIDTH, 155, 30 }, };

		int[][] runwayData = { { RUNWAY_ROTATION, -60, -100 }, { -RUNWAY_ROTATION, 40, -155 } };

		// Checks the runway amount and makes sure that it's not greater than two
		if (runwayAmount > 1 && runwayAmount <= 2) {
			drawALLTaxiWays(taxiWayData);

			drawALLRunways(runwayData);
		} else { // If RUNWAY_AMOUNT is less than 1 it draws the taxi way and than runway
			drawTaxiWays(taxiWayXPos + 138, taxiWayYPos - 62, TAXIWAY_WIDTH, 159, 150);

			drawRunways(RUNWAY_ROTATION, -60, -100); // Creates the first runway
		}
	}

	/**
	 * Creates all of the taxi ways
	 * 
	 * @param taxiWayData takes in the data of taxi ways
	 */
	private void drawALLTaxiWays(int[][] taxiWayData) {
		for (int[] data : taxiWayData) {
			drawTaxiWays(data[0], data[1], data[2], data[3], data[4]);
		}
	}

	/**
	 * Creates all of the runways
	 * 
	 * @param runwayData takes in runway data
	 */
	private void drawALLRunways(int[][] runwayData) {
		for (int[] data : runwayData) {
			drawRunways(data[0], data[1], data[2]);
		}
	}

	/**
	 * Calculates the centred X position relative to an object
	 * 
	 * @param windowWidth takes in the window width
	 * @param objectWidth takes in the object width (i.e., building width and runway
	 *                    width)
	 * @return returns calculated X value
	 */
	private int taxiWayXPos(int windowWidth, int objectWidth) {
		return (windowWidth - objectWidth) / 2;
	}

	/**
	 * Calculates the centred Y position relative to an object
	 * 
	 * @param windowHeight takes in the window height
	 * @param objectHeight takes in the object height (i.e., building height and
	 *                     runway height)
	 * @return returns calculated Y value
	 */
	private int taxiWayYPos(int windowHeight, int objectHeight) {
		return (windowHeight - objectHeight) / 2;
	}

	/**
	 * Rotates the object i.e., runways and taxi ways
	 * 
	 * @param g2d      Takes in the graphics component from method
	 * @param xPos     Takes in the desired x Position
	 * @param yPos     Takes in the desired y Position
	 * @param rotation Takes in the desired angle
	 */
	public void rotateObject(Graphics2D g2d, int xPos, int yPos, int rotation) {
		g2d.translate(xPos, yPos); // Translate the object to yPos and xPos
		g2d.rotate(Math.toRadians(rotation)); // Rotates the image to desired rotation and converts to radians
		g2d.translate(-xPos, -yPos); // Translates the object back to the original position
	}

	/**
	 * Draws the object and displays it
	 * 
	 * @param g2d    Gets the Graphics2D component from method
	 * @param visual Gets the visual
	 */
	public void drawObject(Graphics2D g2d, Rectangle visual) {
		g2d.draw(visual); // Draws the desired visual
		g2d.fill(visual); // Fills the desired visual
	}

	/**
	 * Resets panel so any changes done (i.e., rotating/translating) will be reset
	 * 
	 * @param g2d Takes in the Graphics2D component from method
	 */
	public void resetTransformation(Graphics2D g2d) {
		g2d.setTransform(g2d.getDeviceConfiguration().getDefaultTransform()); // Resets window
	}

	/**
	 * Does the calculation for centring an object when translating the object
	 * 
	 * @param xPos   Takes in the desired X position
	 * @param yPos   Takes in the desired Y position
	 * @param width  Takes in the width
	 * @param height Takes in the height
	 * @return Returns a list which contains the X and Y position centred
	 */
	public int[] centreObject(int xPos, int yPos, int width, int height) {
		int centreXPos = xPos + (width / 2); // Calculates X centred
		int centreYPos = yPos + (height / 2); // Calculates Y centred

		int[] centrePos = { centreXPos, centreYPos }; // Initialises a list which contains X and Y centred

		return centrePos; // Returns the list
	}

	/**
	 * draws the planes
	 * 
	 * @throws IOException
	 */
	public void drawPlanes() throws IOException {

		BufferedImage Image;

		Random rand = new Random();

		String url = imageLocation[rand.nextInt(imageLocation.length)];

		Image = ImageIO.read(getClass().getResource(url));
		
		
		for (PlaneAttributes plane : tower.getPlanes()) {
			double xPlane = plane.getPosition()[0];
			double yPlane = plane.getPosition()[1];
			double anglePlane = -plane.getDirection();

			System.out.println("Created plane");

	        g2d.drawRect((int) xPlane, (int) yPlane, Image.getWidth(), Image.getHeight()); // Check plane position 

			rotateObject(g2d, (int) xPlane, (int) yPlane, (int) anglePlane);

			// Draw the image after applying the transform
			g2d.drawImage(Image, (int) xPlane - (Image.getWidth() / 2), (int) yPlane - (Image.getHeight() / 2), null);

			resetTransformation(g2d);
		}
	}

	/**
	 * Draws runways and calculates the position/centres the runways
	 * 
	 * @param rotation Takes in variable for rotation
	 * @param xPos     Takes in the offset X position
	 * @param yPos     Takes in the offset Y position
	 */
	public void drawRunways(int rotation, int xPos, int yPos) {

		/* Calculates runway X & Y position */
		runwayXPos = (windowWidth - (RUNWAY_WIDTH)) / 2 + xPos; // Calculates the runway's X position on the X axis
																// and offsets by xPos
		runwayYPos = (windowHeight - RUNWAY_HEIGHT) / 2 + yPos; // Calculates the runway's Y position on the Y axis
																// and offsets by yPos
		int[] getCentredPos = centreObject(runwayXPos, runwayYPos, RUNWAY_WIDTH, RUNWAY_HEIGHT);

		/* Calculates the runway's position when centred */
		int centreXRunway = getCentredPos[0]; // Centres the runway on the X axis
		int centreYRunway = getCentredPos[1]; // Centres the runway on the Y axis

		g2d.setColor(runwayColour); // Sets the runway colour to dark gray

		rotateObject(g2d, centreXRunway, centreYRunway, rotation);

		Rectangle runwayVisual = new Rectangle(runwayXPos, runwayYPos, RUNWAY_WIDTH, RUNWAY_HEIGHT); // Creates the
																										// runway
																										// and sets
																										// the X, Y,
																										// runway
																										// width and
																										// height
		drawObject(g2d, runwayVisual);

		// Resets any canvas transformation
		resetTransformation(g2d);
	}

	/**
	 * Creates the airport building and calculates the position
	 */
	public void drawAirportBuilding() {

		final int offsetY = 100; // Sets the offset of the building

		/* Calculates the buildings X & Y position */
		buildingXPos = (windowWidth - BUILDING_WIDTH) / 2; // Calculates the buildings X position on the X axis
		buildingYPos = (windowHeight - (BUILDING_HEIGHT)) / 2 + offsetY; // Calculates the buildings Y position on
																			// the Y axis and offsets by 100
		int[] getCentredPos = centreObject(buildingXPos, buildingYPos, 350, 115);

		/* Calculates the buildings position when centred */
		int centreXBuilding = getCentredPos[0]; // Calculates the building centre. (350 is what got to the
												// position I wanted)
		int centreYBuilding = getCentredPos[1]; // Calculates the building centre. (115 is what got the the
												// position on I wanted)

		g2d.setColor(buildingColour); // Sets the building colour to light gray

		rotateObject(g2d, centreXBuilding, centreYBuilding, BUILDING_ROTATION);

		Rectangle building = new Rectangle(buildingXPos, buildingYPos, BUILDING_WIDTH, BUILDING_HEIGHT); // Creates
																											// the
																											// building
																											// and
																											// sets
																											// the
																											// X, Y,
																											// building
																											// width
																											// and
																											// height

		drawObject(g2d, building);

		resetTransformation(g2d);
	}

	/**
	 * Creates the gates and calculates the positions
	 */
	public void drawGates() {

		final int gateOffsetY = -44; // Offsets the gate on the Y axis
		final int gateOffsetX = 2; // Offsets the gate on the X axis
		final int gateSpacing = 85; // Determines the spacing between each gate

		int[] getCentredPos = centreObject(buildingXPos, buildingYPos, BUILDING_WIDTH, BUILDING_HEIGHT);

		/* Calculates the Gates centre */
		int centreXBuilding = getCentredPos[0]; // Calculates the buildings X position on the X
												// axis
		int centreYBuilding = getCentredPos[1]; // Calculates the buildings Y position on the Y
												// axis

		rotateObject(g2d, centreXBuilding, centreYBuilding, BUILDING_ROTATION);

		// Loop to create multiple gates evenly spaced along the building
		for (int i = 0; i < gateAmount; i++) {
			// Calculates gate position based on the building position and size
			gateXPos = buildingXPos + (i * gateSpacing) - gateOffsetX; // Evenly spaces gates along the building
			gateYPos = buildingYPos + gateOffsetY; // Position gates relative to the building

			g2d.setColor(buildingColour); // Sets the gates colour to light gray
			Rectangle gate = new Rectangle(gateXPos, gateYPos, GATE_WIDTH, GATE_HEIGHT); // Creates the gate and
																							// sets the X, Y, gate
																							// width and height
			drawObject(g2d, gate);
		}

		resetTransformation(g2d);
	}

	/**
	 * Creates the taxi ways with the given information
	 * 
	 * @param xPos          Takes in the desired X position
	 * @param yPos          Takes in the desired Y position
	 * @param width         Takes in the desired width
	 * @param height        Takes in the desired height
	 * @param rotationAngle Takes in in the angle for rotating
	 */
	public void drawTaxiWays(int xPos, int yPos, int width, int height, int rotationAngle) {

		int[] getCentredPos = centreObject(xPos, yPos, width, height);

		int centreX = getCentredPos[0];
		int centreY = getCentredPos[1];

		rotateObject(g2d, centreX, centreY, rotationAngle);

		Rectangle taxiWayVisual = new Rectangle(xPos, yPos, width, height);

		g2d.setColor(taxiWayColour);

		drawObject(g2d, taxiWayVisual);

		resetTransformation(g2d);
	}

}
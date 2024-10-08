package src.visualization;

import java.util.Random;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

import src.main.*;

/**
 * Class which creates all of the ATC Simulation Visuals
 */
public class GUIElements extends JFrame {
	private static final long serialVersionUID = 2178084625298728326L;

	// Creates instances of classes
	Tower tower; // Creates an instance of tower class
	AirportPanel airportVisuals; // Creates an instance of the AirportPanel class

	// Creates JComboBox (Creates a drop down)
	JComboBox<Integer> changeGateAmount; // Creates a JCombox for changing the amount of gates
	JComboBox<Integer> changeRunwayAmount; // Creates an JCombox for changing the amount of planes

	// Creates JLabels
	JLabel gateAmountDisplay; // Creates a JLabel for displaying the amount of gates (Gate Amount: )
	JLabel runwayAmountDisplay; // Creates a JLabel for displaying the amount of runways (Runway Amount: )
	JLabel planeAmountDisplay; // Creates a JLabel for displaying the amount of planes (0)

	// Creates JButtons
	JButton addPlane; // Creates a JButton for adding a plane
	JButton removePlane; // Creates a JButton for removing a plane

	// Creates font style
	Font font = new Font("Arial", Font.BOLD, 14); // Sets the font for the entire window

	// Initialises variables
	public int runwayAmount; // Initialises the runway amount variable
	public int gateAmount; // Initialises the gate amount variable
	public int planeAmount; // Initialises the plane amount variable

	// Initialises lists
	private Integer[] setRunwayAmount = { 1, 2 }; // Creates a 1D list for the available runways
	private Integer[] setGateAmount = { 1, 2, 3, 4 }; // Creates a 1D list for the available gates

	/**
	 * Constructor of class AirportVisuals. Calls the method initializeWindow to
	 * create the window within the constructor
	 * 
	 * @param windowWidth  Takes in the width of the window
	 * @param windowHeight Takes in the height of the window
	 * @param runwayAmount Takes in the amount of runways
	 * @param gateAmount
	 */
	public GUIElements(int windowWidth, int windowHeight, int runwayAmount, int gateAmount) {
		// Creates variables
		this.runwayAmount = runwayAmount; // Sets the runwayAmount to runwayAmount
		this.gateAmount = gateAmount; // Sets the gateAmount to gateAmount

		tower = new Tower(); // Creates an instance of tower class
		Random rand = new Random();

		window(windowWidth, windowHeight); // Calls the initializeWindow method and creates a window setting the width
											// and height to windowWidth and windowHeight
	}

	/**
	 * Method that initialises the creation of the window
	 * 
	 * @param windowWidth  Takes in the width given by the constructor
	 * @param windowHeight Takes in the height given by the constructor
	 */
	private void window(int windowWidth, int windowHeight) {
		planeAmount = 0; // Sets planeAmount variable to 0

		// Styles the Drop down menu (JComboBox)
		UIManager.put("ComboBox.background", Color.DARK_GRAY); // Sets the background to dark gray
		UIManager.put("ComboBox.foreground", Color.WHITE); // Sets the text to white
		UIManager.put("ComboBox.buttonBackground", Color.WHITE); // Sets the button background to white
		UIManager.put("ComboBox.selectionBackground", Color.WHITE); // Sets the selection background to white

		// Setup the current JFrame
		setTitle("ATC Simulation"); // Sets the title of the Window to ATC Simulation
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fully closes the Window
		setSize(windowWidth, windowHeight); // Initialise the Height and Width of the window
		setLocationRelativeTo(null); // Sets the Location of the window to the centre of the screen

		airportVisuals = new AirportPanel(windowWidth, windowHeight); // Creates an instance of JPanel by calling
																		// AirportPanel

		// Creates addPlane Button
		addPlane = new JButton("Add Plane"); // Creates a JButton and sets the button text to "Add PLane"
		// Adds Action Listener to addPLane button. When button is pressed adds one to
		// planeAmount and spawns plane
		addPlane.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				planeAmount += 1; // Adds one to planeAmount and sets planeAmount to that value
				planeAmountDisplay.setText(String.valueOf(planeAmount)); // Sets the planeAmountDisplay to the value of
																			// planeAmount
				tower.spawnPlane(); // Spawns plane and places it on JPanel
			}

		});

		airportVisuals.add(addPlane); // Adds addPlane to airportVisuals

		// Creates planeAmountDisplay Label
		planeAmountDisplay = new JLabel(String.valueOf(planeAmount)); // Creates planeAmountDisplay and sets text to
																		// planeAmount
		planeAmountDisplay.setFont(font); // Sets font to font
		planeAmountDisplay.setForeground(Color.WHITE); // Sets text colour to white

		airportVisuals.add(planeAmountDisplay); // Adds planeAmountDisplay to airportVisuals

		// Creates removePlane Button
		removePlane = new JButton("Remove Plane"); // Creates a JButton and sets the button text to "Remove Plane"
		// Adds Action Listener to removePLane . When button is pressed it removes one
		// from planeAmount and removes plane
		removePlane.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Checks if plane amount does not equal zero
				if (planeAmount > 0) {
					planeAmount -= 1; // Removes one to planeAmount and sets palneAmount to that value
					tower.despawnPlane();
					planeAmountDisplay.setText(String.valueOf(planeAmount)); // Sets the planeAmountDisplay to the value
																				// of planeAmount
				}

			}

		});

		airportVisuals.add(removePlane); // Adds removePlane to airportVisuals

		// Creates gateAmountDisplay label
		gateAmountDisplay = new JLabel("Gate Amount:"); // Creates Label with text "Gate Amount:"

		gateAmountDisplay.setFont(font); // Sets the font to font
		gateAmountDisplay.setForeground(Color.WHITE); // Sets text colour to white

		airportVisuals.add(gateAmountDisplay); // Adds gateAmountDisplay to airportVisuals

		// Creates JComboBox for setting the amount of gates
		changeGateAmount = new JComboBox<Integer>(setGateAmount); // Creates JCombobox (Drop down) and sets the value to
																	// setGateAmount list
		changeGateAmount.setSelectedIndex(3); // Sets the start selected index to 3

		// adds ActionListener to changeGateAmount to check how many gates there will be
		changeGateAmount.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gateAmount = (int) changeGateAmount.getSelectedItem(); // Sets gateAmount to the selected item from the
																		// JComboBox(Drop
																		// down)

			}

		});

		changeGateAmount.setFont(font); // Sets font to font

		airportVisuals.add(changeGateAmount); // Adds changeGateAmount to airportVisuals

		// Creates JLabel for displaying the amount of runways
		runwayAmountDisplay = new JLabel("Runway Amount:"); // Sets label text to "Runway Amount:"

		runwayAmountDisplay.setFont(font); // Sets the font to font
		runwayAmountDisplay.setForeground(Color.WHITE); // Sets the text colour to white

		airportVisuals.add(runwayAmountDisplay); // Adds runwayAmountDisplay to airportVisuals

		// Creates a JComboBox for setting the amount of runways
		changeRunwayAmount = new JComboBox<Integer>(setRunwayAmount);

		changeRunwayAmount.setSelectedIndex(0); // Sets the start selected index to 0

		// Adds ActionListener to changeRunwayAmount to check how many runways there
		// will be
		changeRunwayAmount.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				runwayAmount = (int) changeRunwayAmount.getSelectedItem(); // Sets runwayAmount to the selected item
																			// from the JComboBox (Drop down)
			}
		});

		changeRunwayAmount.setFont(font); // Sets font of JComboBox to font
		changeRunwayAmount.setForeground(Color.WHITE); // Sets the text colour to white
		changeRunwayAmount.setBackground(Color.DARK_GRAY); // Sets the background colour to dark gray

		airportVisuals.add(changeRunwayAmount); // Adds changeRunwayAmount to airportVisuals

		add(airportVisuals); // Add the runway panel to the current frame
		setVisible(true); // Sets window to visible

		// Added Component Listener for updating runways
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Dimension newSize = getSize(); // Gets the new size of the window when it changes
				airportVisuals.updateRunwayPosition(newSize.width, newSize.height); // Sets the new position of all
																					// JComponents
				airportVisuals.repaint(); // Updates the window
			}
		});

		// Create a WindowUpdate object and start the update process
		@SuppressWarnings("unused")
		WindowUpdate update = new WindowUpdate(airportVisuals, tower);

	}

	/**
	 * Class that extends JPanel and creates runways
	 */
	private class AirportPanel extends JPanel {
		private static final long serialVersionUID = 2853523647566452733L;

		private String[] imageLocation = { "/plane-images/plane-blue.png", "/plane-images/plane-jared.png",
				"/plane-images/plane-cyan.png", "/plane-images/plane-green.png", "/plane-images/plane-purple.png",
				"/plane-images/plane-nolan.png", "/plane-images/plane-white.png", "/plane-images/plane-will.png" };
		
		private final Color taxiWayColour = Color.DARK_GRAY;
		private final Color runwayColour = Color.GRAY;
		private final Color buildingColour = Color.LIGHT_GRAY;

		/* Initialises Airports X & Y positions */
		private int runwayXPos, runwayYPos; // Initialises the runways X and Y
		private int buildingXPos, buildingYPos; // Initialises the buildings X and Y
		private int gateXPos, gateYPos; // Initialises the gates X and Y

		/* Initialises Airport and Window Components X and Y */
		private int windowWidth, windowHeight; // Initialises the windows width (720) and height (600)

		private final int RUNWAY_WIDTH = 40; // Initialises the runway width
		private final int RUNWAY_HEIGHT = 450; // Initialises the runway height
		private final int RUNWAY_ROTATION = 60; // Initialises the runway rotation

		private final int GATE_WIDTH = 10; // Initialises the gate width
		private final int GATE_HEIGHT = 65; // Initialises the gate height

		private final int BUILDING_WIDTH = 340; // Initialises the building width
		private final int BUILDING_HEIGHT = 110; // Initialises the building height
		private final int BUILDING_ROTATION = -30;

		private final int TAXIWAY_WIDTH = RUNWAY_WIDTH / 2;

		/**
		 * Constructor of Airport Panel and initialises the background as black
		 * 
		 * @param windowWidth  Takes in window Width
		 * @param windowHeight Takes in window Height
		 */
		public AirportPanel(int windowWidth, int windowHeight) {
			setBackground(Color.BLACK); // Sets the panel background to black

			this.windowWidth = windowWidth; // Sets the windowWidth to windowWidth
			this.windowHeight = windowHeight; // Sets the windowHeight to windowHeight
		}

		/**
		 * Overrides paintComponent to create taxi ways, runway, gates and airport
		 */
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			drawTaxiWays(g, ((windowWidth - RUNWAY_WIDTH) / 2) - 85, ((windowHeight - RUNWAY_HEIGHT) / 2) - 160,
					TAXIWAY_WIDTH, RUNWAY_HEIGHT, RUNWAY_ROTATION); // Draws parallel taxi way above
																						// the
																						// main runway

			drawTaxiWays(g, ((windowWidth - RUNWAY_WIDTH) / 2) - 15, ((windowHeight - RUNWAY_HEIGHT) / 2) - 40,
					TAXIWAY_WIDTH, RUNWAY_HEIGHT, RUNWAY_ROTATION); // Draws parallel taxi way below
																						// the
																						// main runway

			drawTaxiWays(g, ((windowWidth - RUNWAY_WIDTH) / 2) - 237, ((windowHeight - RUNWAY_HEIGHT) / 2) + 156,
					TAXIWAY_WIDTH, 155, -30);

			drawTaxiWays(g, ((windowWidth - RUNWAY_WIDTH) / 2) - 190, ((windowHeight - RUNWAY_HEIGHT) / 2) + 126,
					TAXIWAY_WIDTH, 155, -30);

			drawTaxiWays(g, ((windowWidth - RUNWAY_WIDTH) / 2) - 144, ((windowHeight - RUNWAY_HEIGHT) / 2) + 100,
					TAXIWAY_WIDTH, 155, -30);

			drawTaxiWays(g, (windowWidth - BUILDING_WIDTH) / 2 - 30, (windowHeight - BUILDING_HEIGHT) / 2 + 30,
					BUILDING_WIDTH, 155, -30);

			// Checks the runway amount and makes sure that it's not greater than two
			if (runwayAmount > 1 && runwayAmount <= 2) {
				drawTaxiWays(g, ((windowWidth - RUNWAY_WIDTH) / 2) + 170, ((windowHeight - RUNWAY_HEIGHT) / 2) - 50,
						TAXIWAY_WIDTH, 260, 150);
				drawTaxiWays(g, ((windowWidth - RUNWAY_WIDTH) / 2) - 144, ((windowHeight - RUNWAY_HEIGHT) / 2) - 25,
						TAXIWAY_WIDTH, 155, 30);
				drawRunways(g, RUNWAY_ROTATION, -60, -100); // Creates the first runway
				drawRunways(g, -RUNWAY_ROTATION, 40, -155); // Creates the second runway
			} else { // If RUNWAY_AMOUNT is less than 1 it draws the taxiway and than runway
				drawTaxiWays(g, ((windowWidth - RUNWAY_WIDTH) / 2) + 138, ((windowHeight - RUNWAY_HEIGHT) / 2) - 62,
						TAXIWAY_WIDTH, 159, 150);
				drawRunways(g, RUNWAY_ROTATION, -60, -100); // Creates the first runway
			}

			drawAirportBuilding(g); // Draws the main building of the airport

			drawGates(g);

			try {
				drawPlanes(g);
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

		public void rotateObject(Graphics2D g2d, int xPos, int yPos, int rotation) {
			g2d.translate(xPos, yPos);
			g2d.rotate(Math.toRadians(rotation));
			g2d.translate(-xPos, -yPos);
		}

		public void drawObject(Graphics2D g2d, Rectangle visual) {
			g2d.draw(visual);
			g2d.fill(visual);
		}

		public void resetTransformation(Graphics2D g2d) {
			g2d.setTransform(g2d.getDeviceConfiguration().getDefaultTransform());
		}

		public int[] centreObject(int xPos, int yPos, int width, int height) {
			int centreXPos = xPos + (width / 2);
			int centreYPos = yPos + (height / 2);

			int[] centrePos = { centreXPos, centreYPos };

			return centrePos;
		}

		/**
		 * draws the planes
		 * 
		 * @param g graphics input
		 * @throws IOException
		 */
		public void drawPlanes(Graphics g) throws IOException {
			Graphics2D g2d = (Graphics2D) g;

			BufferedImage Image;

			Random rand = new Random();

			String url = imageLocation[rand.nextInt(imageLocation.length)];

			Image = ImageIO.read(getClass().getResource(url));

			for (PlaneAttributes plane : tower.getPlanes()) {
				double xPlane = plane.getPosition()[0];
				double yPlane = plane.getPosition()[1];
				double anglePlane = -plane.getDirection();

				System.out.println("Created plane");

//		        g2d.drawRect((int) xPlane, (int) yPlane, Image.getWidth(), Image.getHeight()); // Check plane position 

				rotateObject(g2d, (int) xPlane, (int) yPlane, (int) anglePlane);

				// Draw the image after applying the transform
				g2d.drawImage(Image, (int) xPlane - (Image.getWidth() / 2), (int) yPlane - (Image.getHeight() / 2),
						null);

				resetTransformation(g2d);
			}
		}

		/**
		 * Draws runways and calculates the position/centres the runways
		 * 
		 * @param g        Takes in the graphic object
		 * @param rotation Takes in variable for rotation
		 * @param xPos     Takes in the offset X position
		 * @param yPos     Takes in the offset Y position
		 */
		public void drawRunways(Graphics g, int rotation, int xPos, int yPos) {
			Graphics2D g2d = (Graphics2D) g; // Creates an instance of 2d graphics

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
		 * 
		 * @param g Takes in graphics object
		 */
		public void drawAirportBuilding(Graphics g) {
			Graphics2D g2d = (Graphics2D) g; // Creates an instance of 2d graphics

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
		 * 
		 * @param g
		 */
		public void drawGates(Graphics g) {
			Graphics2D g2d = (Graphics2D) g; // Creates an instance of 2d graphics

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
		 * Draws and calculates the taxiway's position
		 * 
		 * @param g
		 */
		public void drawTaxiWays(Graphics g, int xPos, int yPos, int width, int height, int rotationAngle) {
			Graphics2D g2d = (Graphics2D) g;
			
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
}

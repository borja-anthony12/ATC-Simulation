package src.visualization;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

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
				airportVisuals.updateRunwayPosition(newSize.width, newSize.height); // Sets the new position of all JComponents
				airportVisuals.repaint(); // Updates the window
			}
		});

		// Create a WindowUpdate object and start the update process
		@SuppressWarnings("unused")
		WindowUpdate update = new WindowUpdate(airportVisuals);

	}

	/**
	 * Class that extends JPanel and creates runways
	 */
	private class AirportPanel extends JPanel {
		private static final long serialVersionUID = 2853523647566452733L;

		// Define relative positions as percentages (from 0 to 1)
		private final double TAXIWAY_1_X_PERCENT = 0.3400;
		private final double TAXIWAY_1_Y_PERCENT = -0.1417;

		private final double TAXIWAY_2_X_PERCENT = 0.4514;
		private final double TAXIWAY_2_Y_PERCENT = 0.0583;

		private final double TAXIWAY_3_X_PERCENT = 0.1444;
		private final double TAXIWAY_3_Y_PERCENT = 0.3850;

		private final double TAXIWAY_4_X_PERCENT = 0.2083;
		private final double TAXIWAY_4_Y_PERCENT = 0.3350;

		private final double TAXIWAY_5_X_PERCENT = 0.2722;
		private final double TAXIWAY_5_Y_PERCENT = 0.2917;

		private final double TAXIWAY_6_X_PERCENT = 0.2222;
		private final double TAXIWAY_6_Y_PERCENT = 0.4583;

		private final double TAXIWAY_7_X_PERCENT = 0.7083;
		private final double TAXIWAY_7_Y_PERCENT = 0.0333;

		private final double TAXIWAY_8_X_PERCENT = 0.2722;
		private final double TAXIWAY_8_Y_PERCENT = 0.0833;

		private final double TAXIWAY_9_X_PERCENT = 0.6639;
		private final double TAXIWAY_9_Y_PERCENT = 0.0217;

		/* Initialises Airports X & Y positions */
		private int runwayXPos, runwayYPos; // Initialises the runways X and Y
		private int buildingXPos, buildingYPos; // Initialises the buildings X and Y
		private int gateXPos, gateYPos; // Initialises the gates X and Y

		/* Initialises Airport and Window Components X and Y */
		private int windowWidth, windowHeight; // Initialises the windows width and height

		private final int RUNWAY_WIDTH = 40; // Initialises the runway width
		private final int RUNWAY_HEIGHT = 450; // Initialises the runway height
		private final int RUNWAY_ROTATION = 60; // Initialises the runway rotation

		private final int GATE_WIDTH = 10; // Initialises the gate width
		private final int GATE_HEIGHT = 65; // Initialises the gate height

		private final int BUILDING_WIDTH = 340; // Initialises the building width
		private final int BUILDING_HEIGHT = 110; // Initialises the building height

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
					TAXIWAY_WIDTH, RUNWAY_HEIGHT, RUNWAY_ROTATION, Color.DARK_GRAY); // Draws parallel taxi way above the
																						// main runway

			drawTaxiWays(g, ((windowWidth - RUNWAY_WIDTH) / 2) - 15, ((windowHeight - RUNWAY_HEIGHT) / 2) - 40,
					TAXIWAY_WIDTH, RUNWAY_HEIGHT, RUNWAY_ROTATION, Color.DARK_GRAY); // Draws parallel taxi way below the
																						// main runway

			drawTaxiWays(g, 103, 231, TAXIWAY_WIDTH, 155, -30, Color.DARK_GRAY);

			drawTaxiWays(g, 150, 201, TAXIWAY_WIDTH, 155, -30, Color.DARK_GRAY);

			drawTaxiWays(g, 196, 175, TAXIWAY_WIDTH, 155, -30, Color.DARK_GRAY);

			drawTaxiWays(g, (windowWidth - BUILDING_WIDTH) / 2 - 30, (windowHeight - BUILDING_HEIGHT) / 2 + 30,
					BUILDING_WIDTH, 155, -30, Color.DARK_GRAY);

			// Checks the runway amount and makes sure that it's not greater than two
			if (runwayAmount > 1 && runwayAmount <= 2) {
				drawTaxiWays(g, 510, 20, TAXIWAY_WIDTH, 260, 150, Color.DARK_GRAY);
				drawTaxiWays(g, 196, 50, TAXIWAY_WIDTH, 155, 30, Color.DARK_GRAY);
				drawRunways(g, RUNWAY_ROTATION, -60, -100); // Creates the first runway
				drawRunways(g, -RUNWAY_ROTATION, 40, -155); // Creates the second runway
			} else { // If RUNWAY_AMOUNT is less than 1 it draws the taxiway and than runway
				drawTaxiWays(g, 478, 13, TAXIWAY_WIDTH, 159, 150, Color.DARK_GRAY);
				drawRunways(g, RUNWAY_ROTATION, -60, -100); // Creates the first runway
			}

			drawAirportBuilding(g); // Draws the main building of the airport

			drawGates(g); // Draws the gates
			
			drawPlanes(g);
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
		
		public void drawPlanes(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			for (PlaneAttributes plane: tower.getPlanes()) {
				Rectangle planeVisual = new Rectangle((int) plane.getPosition()[0], (int) plane.getPosition()[1], 50, 50);
				g2d.draw(planeVisual);
				g2d.setColor(Color.GREEN);
				g2d.fill(planeVisual);
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

			/* Calculates the runway's position when centred */
			int centreXRunway = runwayXPos + (RUNWAY_WIDTH / 2); // Centres the runway on the X axis
			int centreYRunway = runwayYPos + (RUNWAY_HEIGHT / 2); // Centres the runway on the Y axis

			g2d.setColor(Color.GRAY); // Sets the runway colour to dark gray
			g2d.translate(centreXRunway, centreYRunway); // Translates the runway to the centre
			g2d.rotate(Math.toRadians(rotation)); // Converts rotation to radians and rotates the runway
			g2d.translate(-centreXRunway, -centreYRunway); // Translates the runway back to the centre (This was added
															// due to the runway being in a random position)

			Rectangle runwayVisual = new Rectangle(runwayXPos, runwayYPos, RUNWAY_WIDTH, RUNWAY_HEIGHT); // Creates the
																											// runway
																											// and sets
																											// the X, Y,
																											// runway
																											// width and
																											// height

			g2d.draw(runwayVisual); // Draws the runway on the panel
			g2d.fill(runwayVisual); // Fills the runway on the panel

			// Resets any canvas transformation
			g2d.setTransform(g2d.getDeviceConfiguration().getDefaultTransform()); // Added due to when building, gates
																					// and taxi way weren't in predicted
																					// position
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

			/* Calculates the buildings position when centred */
			int centreXBuilding = buildingXPos + (350 / 2); // Calculates the building centre. (350 is what got to the
															// position I wanted)
			int centreYBuilding = buildingYPos + (115 / 2); // Calculates the building centre. (115 is what got the the
															// position on I wanted)

			g2d.setColor(Color.LIGHT_GRAY); // Sets the building colour to light gray
			g2d.translate(centreXBuilding, centreYBuilding); // Translates the building to the centre
			g2d.rotate(Math.toRadians(-30)); // Takes in -30 degrees and converts to radians and rotates building
			g2d.translate(-centreXBuilding, -centreYBuilding); // Translates the building back to the centre

			Rectangle Building = new Rectangle(buildingXPos, buildingYPos, BUILDING_WIDTH, BUILDING_HEIGHT); // Creates
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

			g2d.draw(Building); // Draws the building on the panel
			g2d.fill(Building); // Fills the building on the panel

			g2d.setTransform(g2d.getDeviceConfiguration().getDefaultTransform()); // Resets any canvas transformation
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

			/* Calculates the Gates centre */
			int centerXBuilding = buildingXPos + (BUILDING_WIDTH / 2); // Calculates the buildings X position on the X
																		// axis
			int centerYBuilding = buildingYPos + (BUILDING_HEIGHT / 2); // Calculates the buildings Y position on the Y
																		// axis

			g2d.translate(centerXBuilding, centerYBuilding); // Translates the gate to the centre
			g2d.rotate(Math.toRadians(-30)); // Converts -30 to degrees and converts to radians and rotates building
			g2d.translate(-centerXBuilding, -centerYBuilding); // Translates the gate back to the centre

			// Loop to create multiple gates evenly spaced along the building
			for (int i = 0; i < gateAmount; i++) {
				// Calculates gate position based on the building position and size
				gateXPos = buildingXPos + (i * gateSpacing) - gateOffsetX; // Evenly spaces gates along the building
				gateYPos = buildingYPos + gateOffsetY; // Position gates relative to the building

				g2d.setColor(Color.LIGHT_GRAY); // Sets the gates colour to light gray
				Rectangle Gate = new Rectangle(gateXPos, gateYPos, GATE_WIDTH, GATE_HEIGHT); // Creates the gate and
																								// sets the X, Y, gate
																								// width and height
				g2d.draw(Gate); // Draws gate
				g2d.fill(Gate); // Fills gate
			}

			g2d.setTransform(g2d.getDeviceConfiguration().getDefaultTransform()); // Resets any canvas transformation
		}

		/**
		 * Draws and calculates the taxiway's position
		 * 
		 * @param g
		 */
		public void drawTaxiWays(Graphics g, int tAXIWAY_3_X_PERCENT2, int yPos, int width, int height, int rotationAngle, Color c) {
			Graphics2D g2d = (Graphics2D) g;

			double centerX = tAXIWAY_3_X_PERCENT2 + (width / 2);
			int centerY = yPos + (height / 2);

			g2d.translate(centerX, centerY);
			g2d.rotate(Math.toRadians(rotationAngle));
			g2d.translate(-centerX, -centerY);

			Rectangle taxiWayVisual = new Rectangle(tAXIWAY_3_X_PERCENT2, yPos, width, height);

			g2d.setColor(c);
			g2d.draw(taxiWayVisual);
			g2d.fill(taxiWayVisual);

			g2d.setTransform(g2d.getDeviceConfiguration().getDefaultTransform()); // Resets any canvas transformation
		}
	}
}

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
	JLabel planeAmountDisplay = new JLabel(); // Creates a JLabel for displaying the amount of planes (0)
	JLabel gateAmountDisplay = new JLabel(); // Creates a JLabel for displaying the amount of gates (Gate Amount: )
	JLabel runwayAmountDisplay = new JLabel(); // Creates a JLabel for displaying the amount of runways (Runway Amount:
												// )

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

		airportVisuals = new AirportPanel(); // Creates an instance of JPanel by calling
												// AirportPanel
		airportVisuals.getWindowSize(windowWidth, windowHeight);

		airportVisuals.add(createAddPlane()); // Adds addPlane to airportVisuals

		airportVisuals.add(createLabel(planeAmountDisplay, String.valueOf(planeAmount))); // Adds planeAmountDisplay to
																							// airportVisuals
		airportVisuals.add(createRemovePlane()); // Adds removePlane to airportVisuals

		airportVisuals.add(createLabel(gateAmountDisplay, "Gate Amount:")); // Adds gateAmountDisplay to airportVisuals

		airportVisuals.add(createChangeGateAmount()); // Adds changeGateAmount to airportVisuals

		airportVisuals.add(createLabel(runwayAmountDisplay, "Runway Amount:")); // Adds runwayAmountDisplay to
																				// airportVisuals

		airportVisuals.add(createChangeRunwayAmount()); // Adds changeRunwayAmount to airportVisuals

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
	 * Creates a drop for changing the amount of gates
	 * 
	 * @return changeGateAmount for adding to panel
	 */
	private JComboBox<Integer> createChangeGateAmount() {
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
				airportVisuals.getGateAmount(gateAmount);
			}

		});

		changeGateAmount.setFont(font); // Sets font to font

		return changeGateAmount;
	}

	/**
	 * Creates a drop down for changing the amount of gates
	 * 
	 * @return drop down for being added to the panel
	 */
	private JComboBox<Integer> createChangeRunwayAmount() {
		// Creates JComboBox for setting the amount of gates
		changeRunwayAmount = new JComboBox<Integer>(setRunwayAmount); // Creates JCombobox (Drop down) and sets the
																		// value to
																		// setGateAmount list
		changeRunwayAmount.setSelectedIndex(0); // Sets the start selected index to 3

		// adds ActionListener to changeGateAmount to check how many gates there will be
		changeRunwayAmount.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				runwayAmount = (int) changeRunwayAmount.getSelectedItem(); // Sets gateAmount to the selected item from
																			// the
																			// JComboBox(Drop
																			// down)
				airportVisuals.getRunwayAmount(runwayAmount);
			}

		});

		changeRunwayAmount.setFont(font); // Sets font to font

		return changeRunwayAmount;
	}

	/**
	 * Made a function which creates a JLabel
	 * 
	 * @param label Takes in the initialised label
	 * @param text  Takes in the text which is going to be displayed on the label
	 * @return label for displaying on the panel
	 */
	private JLabel createLabel(JLabel label, String text) {
		// Creates planeAmountDisplay Label
		label.setText(text); // Creates planeAmountDisplay and sets text to
								// planeAmount
		label.setFont(font); // Sets font to font
		label.setForeground(Color.WHITE); // Sets text colour to white

		return label;
	}

	/**
	 * Creates a JButton for adding 1 plane
	 * 
	 * @return JButton for being displayed on the panel
	 */
	private JButton createAddPlane() {
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

		return addPlane;
	}

	/**
	 * Creates a JButton for removing 1 plane
	 * 
	 * @return JButton to be added to panel
	 */
	private JButton createRemovePlane() {
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
		return removePlane;
	}

}

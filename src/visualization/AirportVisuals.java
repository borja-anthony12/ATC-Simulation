package src.visualization;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

/**
 * Class which creates all of the ATC Simulation Visuals
 */
public class AirportVisuals extends JFrame{
	/**
	 * UID Serial for class
	 */
	private static final long serialVersionUID = 2178084625298728326L;
	
	/**
	 * Constructor of class AirportVisuals. Calls the method intializeWindow 
	 * to create the window within the constructor
	 * 
	 * @param windowWidth 	Takes in the width of the window
	 * @param windowHeight	Takes in the height of the window
	 */
	public AirportVisuals(int windowWidth, int windowHeight) {
		intializeWindow(windowWidth, windowHeight); // Calls the initialize method and creates the Window
	}
	
	/**
	 * Method that initializes the creation of the window
	 * 
	 * @param windowWidth		Takes in the width given by the constructor
	 * @param windowHeight	Takes in the height given by the constructor
	 */
	public void intializeWindow(int windowWidth, int windowHeight) {
		final int towerWidth = 300;
		final int towerHeight = 300;
		int centerObjectWidth = (windowWidth - towerWidth) / 2;
		int cneterObjectHeight= (windowHeight - towerHeight) / 2;
		
		setTitle("ATC Simulation"); // Sets the title of the Window to ATC Simulation
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fully closes the Window
		setSize(windowWidth, windowHeight); // Initialize the Height and Width of the window
		setLocationRelativeTo(null); // Sets the Location of the window to the center of the screen
		setBackground(Color.BLACK);
		
		JPanel Airport = new JPanel() {
			
			@Override
			public void paint(Graphics g) {
				g.setColor(Color.red);
				g.fillRect(centerObjectWidth, cneterObjectHeight, towerWidth, towerHeight);
			}
		};
		
		add(Airport);
		
		setVisible(true); // Sets window to visible
	}
}

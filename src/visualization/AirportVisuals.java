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
	 * @param width 	Takes in the width of the window
	 * @param height	Takes in the height of the window
	 */
	public AirportVisuals(int width, int height) {
		intializeWindow(width, height); // Calls the initialize method and creates the Window
	}
	
	/**
	 * Method that initializes the creation of the window
	 * 
	 * @param width		Takes in the width given by the constructor
	 * @param height	Takes in the height given by the constructor
	 */
	public void intializeWindow(int width, int height) {
		setTitle("ATC Simulation"); // Sets the title of the Window to ATC Simulation
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fully closes the Window
		setSize(width, height); // Initialize the Height and Width of the window
		setLocationRelativeTo(null); // Sets the Location of the window to the center of the screen
		setBackground(Color.BLACK);
		
		JPanel Airport = new JPanel() {
			
			@Override
			public void paint(Graphics g) {
				g.setColor(Color.red);
				g.fillRect(100, 100, 300, 300);
			}
		};
		
		add(Airport);
		
		setVisible(true); // Sets window to visible
	}
}

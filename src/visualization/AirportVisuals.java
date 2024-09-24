package src.visualization;

import java.awt.*;
import javax.swing.*;

/**
 * Class which creates all of the ATC Simulation Visuals
 */
public class AirportVisuals extends JFrame{
	private static final long serialVersionUID = 2178084625298728326L;
	
	/**
	 * Constructor of class AirportVisuals. Calls the method intializeWindow 
	 * to create the window within the constructor
	 * 
	 * @param windowWidth 	Takes in the width of the window
	 * @param windowHeight	Takes in the height of the window
	 */
	public AirportVisuals(int windowWidth, int windowHeight) {
		intializeWindow(windowWidth, windowHeight); // Calls the initialiseWindow method and creates the Window
	}
	
	/**
	 * Method that initialises the creation of the window
	 * 
	 * @param windowWidth	Takes in the width given by the constructor
	 * @param windowHeight	Takes in the height given by the constructor
	 */
	private void intializeWindow(int windowWidth, int windowHeight) {
		RunwayPanel runway = new RunwayPanel(windowWidth, windowHeight);
		
		setTitle("ATC Simulation"); // Sets the title of the Window to ATC Simulation
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fully closes the Window
		setSize(windowWidth, windowHeight); // Initialise the Height and Width of the window
		setLocationRelativeTo(null); // Sets the Location of the window to the centre of the screen
		
		
		add(runway);
		
		setVisible(true); // Sets window to visible
	}
	
	/**
	 * Class that extends JPanel and creates runways
	 */
	private class RunwayPanel extends JPanel {
		private static final long serialVersionUID = 2853523647566452733L;
		
		private int windowWidth;
		private int windowHeight;
		
		/**
		 * Runway Constructor that takes in 
		 * the windowWidth and Height which
		 * is used to calculate 
		 * the position of the runway
		 * 
		 * @param windowWidth	takes in Width of the Window
		 * @param windowHeight	takes the Height of the Window
		 */
		public RunwayPanel(int windowWidth, int windowHeight) {
			setBackground(Color.BLACK);
			this.windowWidth = windowWidth;
			this.windowHeight = windowHeight;
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			drawRunways(g);
		}
		
		/**
		 * Creates the runway and does the calculations for
		 * the position of the runway
		 * 
		 * @param g takes in the graphics and calls it g
		 */
		 public void drawRunways(Graphics g) {
			 	/* runway width and height*/
	            final int runwayWidth = 150;
	            final int runwayHeight = 300;
	            int runwayX = (windowWidth - runwayWidth) / 2;
	            int runwayY = (windowHeight - runwayHeight) / 2;

	            g.setColor(Color.BLACK);  // Set runway colour
	            g.fillRect(runwayX, runwayY, runwayWidth, runwayHeight);  // Draw the runway
	        }
	}
}

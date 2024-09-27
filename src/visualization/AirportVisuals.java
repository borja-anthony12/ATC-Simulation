package src.visualization;

import java.awt.*;
import javax.swing.*;
import java.math.*;
import src.visualization.WindowUpdate;

/**
 * Class which creates all of the ATC Simulation Visuals
 */
public class AirportVisuals extends JFrame{
	private static final long serialVersionUID = 2178084625298728326L;
	public final int RUNWAY_AMOUNT;
	
	/**
	 * Constructor of class AirportVisuals. Calls the method intializeWindow 
	 * to create the window within the constructor
	 * 
	 * @param windowWidth 	Takes in the width of the window
	 * @param windowHeight	Takes in the height of the window
	 * @param runwayAmount  Takes in the amount of runways
	 */
	public AirportVisuals(int windowWidth, int windowHeight, int runwayAmount) {
		this.RUNWAY_AMOUNT = runwayAmount;
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
		WindowUpdate update = new WindowUpdate(runway);
	}
	
	/**
	 * Class that extends JPanel and creates runways
	 */
	private class RunwayPanel extends JPanel {
		private static final long serialVersionUID = 2853523647566452733L;
		
		private int windowWidth;
		private int windowHeight;
		private final int RUNWAY_WIDTH = 40;
		
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
		
		/**
		 * Overrides the paintComponent
		 */
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			
			drawRunways(g, 75, -50, 0, 400, RUNWAY_WIDTH); // Creates the first runway
			
			// Checks whether runway amount is greater than 2 and runway amount isn't greater than 2;
			if (RUNWAY_AMOUNT > 1 && RUNWAY_AMOUNT <= 2) {
				drawRunways(g, -60, -25, -50, 450, RUNWAY_WIDTH); // Creates the second runway
			}
			// TO-DO 
//			drawTaxiWays(g);
			
		}
		
		/**
		 * Creates the runway and does the calculations for
		 * the position of the runway
		 * 
		 * @param g 			takes in the graphics and calls it g
		 * @param rotation 		takes in the degrees of how much the runway is rotated
		 * @param xPos 			takes in the x position of the runway
		 * @param yPos 			takes in the y position of the runway
		 * @param runwayHeight 	takes in specified height of  runway
		 * @param runwayWidth 	takes in specified width of runway
		 */
		 public void drawRunways(Graphics g, int rotation, int xPos, int yPos, int runwayHeight, int runwayWidth) {
	            Graphics2D g2d = (Graphics2D) g; // Creates and instance of Graphics2D
	            
            	int runwayX = (windowWidth - (runwayWidth)) / 2 + xPos; 	// Does the calculations for the runway X position and offsets it by 25 to centre the runway
	            int runwayY = (windowHeight - runwayHeight) / 2 + yPos; 			// Does the calculations for the runway Y position and offsets it by 100 to centre the runway
	            int centerX = runwayX + (runwayWidth / 2);		// Calculates the rotated X values for the canvas
	            int centerY = runwayY + (runwayHeight / 2);	// Calculates the rotated Y values for the canvas
			 	
	            g2d.setColor(Color.GRAY); // Set runway colour
	            g2d.translate(centerX, centerY); // Translates the object
	            g2d.rotate(Math.toRadians(rotation)); // Rotates runwayVisual to the given rotation in degrees and converts to radiens
	            g2d.translate(-centerX, -centerY);	// Moves the Object back
	            
	            Rectangle runwayVisual = new Rectangle(runwayX, runwayY, runwayWidth, runwayHeight); // Creates a rectangle Object called 'runwayVisual'
	            
	            g2d.draw(runwayVisual);  	// Draws the runway
	            g2d.fill(runwayVisual); 	// Fills the runway
	            
	            // Resets canvas
	            g2d.setTransform(g2d.getDeviceConfiguration().getDefaultTransform());
	 	}
		
		 public void drawTaxiWays(Graphics g) {
			 Graphics2D g2d = (Graphics2D) g;
			 
			 Rectangle taxiWayVisual = new Rectangle(50, 50, 100, 100);
			 
			 g2d.setColor(Color.GRAY);
			 g2d.draw(taxiWayVisual);
			 g2d.fill(taxiWayVisual);
			 }
	}
}

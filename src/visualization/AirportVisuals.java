package src.visualization;

import java.awt.*;
import javax.swing.*;
import java.math.*;

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
	            final int runwayWidth = 50; // Initialises the runway Width
	            final int runwayHeight = 250; // Initialises the runway Height
	            int spacing = 50;
	            
	            for (int i = 0; i < RUNWAY_AMOUNT; i++) {
	            	System.out.println(i);
	            	int offset = i * (runwayWidth + spacing);
	            	int runwayX = (windowWidth - (runwayWidth + offset)) / 2; // Does the calculations for the runway X position
		            int runwayY = ((windowHeight - runwayHeight) / 2); // Does the calculations for the runway Y position
		            int centerX = runwayX + (runwayWidth / 2);
		            int centerY = (runwayY + (runwayHeight / 2));
		            System.out.println("Offset: " + offset);
		            System.out.println("RunwayX: " + runwayX);
		            System.out.println("RunwayY: " + runwayY);
		            System.out.println("centerx: " + centerX);
		            System.out.println("centerY: " + centerY);
				 	Graphics2D g2d = (Graphics2D) g; // Creates and instance of Graphics2D
				 	
		            g2d.setColor(Color.GRAY); // Set runway colour
		            g2d.translate(centerX, centerY);
		            g2d.rotate(Math.toRadians(45)); // Rotates runwayVisual to 45 degrees
		            g2d.translate(-centerX, -centerY);
		            
		            Rectangle runwayVisual = new Rectangle(runwayX + offset, runwayY, runwayWidth, runwayHeight); // Creates a rectangle Object called 'runwayVisual'
		            
		            g2d.draw(runwayVisual);  	// Draw the runway
		            g2d.fill(runwayVisual); 	// Fills the runway
		            
		            g2d.setTransform(g2d.getDeviceConfiguration().getDefaultTransform());
	            }
	        }
	}
}

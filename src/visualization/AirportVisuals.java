package src.visualization;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import javax.swing.*;

/**
 * Class which creates all of the ATC Simulation Visuals
 */
public class AirportVisuals extends JFrame {
    private static final long serialVersionUID = 2178084625298728326L;
    public final int RUNWAY_AMOUNT;

    /**
     * Constructor of class AirportVisuals. Calls the method initializeWindow 
     * to create the window within the constructor
     * 
     * @param windowWidth 	Takes in the width of the window
     * @param windowHeight	Takes in the height of the window
     * @param runwayAmount  Takes in the amount of runways
     */
    public AirportVisuals(int windowWidth, int windowHeight, int runwayAmount) {
        this.RUNWAY_AMOUNT = runwayAmount;
        initializeWindow(windowWidth, windowHeight); // Calls the initializeWindow method and creates the Window
    }

    /**
     * Method that initialises the creation of the window
     * 
     * @param windowWidth	Takes in the width given by the constructor
     * @param windowHeight	Takes in the height given by the constructor
     */
    private void initializeWindow(int windowWidth, int windowHeight) {
        AirportPanel airport = new AirportPanel(windowWidth, windowHeight);
        
        // Setup the current JFrame (AirportVisuals extends JFrame)
        setTitle("ATC Simulation"); // Sets the title of the Window to ATC Simulation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fully closes the Window
        setSize(windowWidth, windowHeight); // Initialise the Height and Width of the window
        setLocationRelativeTo(null); // Sets the Location of the window to the centre of the screen
        
        add(airport); // Add the runway panel to the current frame
        setVisible(true); // Sets window to visible
        
        // Added Component Listener for updating runways
        addComponentListener(new ComponentAdapter() {
        	@Override
            public void componentResized(ComponentEvent e) {
            	 Dimension newSize = getSize();
                 airport.updateRunwayPosition(newSize.width, newSize.height);
                 airport.repaint();
            }
        });
        

        

        // Create a WindowUpdate object and start the update process
        WindowUpdate update = new WindowUpdate(airport);
    }

    /**
     * Class that extends JPanel and creates runways
     */
    private class AirportPanel extends JPanel {
        private static final long serialVersionUID = 2853523647566452733L;
        
        private int windowWidth, windowHeight;
        private int runwayX, runwayY;
        private final int RUNWAY_WIDTH = 40;

        public AirportPanel(int windowWidth, int windowHeight) {
            setBackground(Color.BLACK);
            this.windowWidth = windowWidth;
            this.windowHeight = windowHeight;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawRunways(g, 60, 0, 0, 400, RUNWAY_WIDTH); // Creates the first runway

            if (RUNWAY_AMOUNT > 1 && RUNWAY_AMOUNT <= 2) {
                drawRunways(g, -60, 23, -50, 450, RUNWAY_WIDTH); // Creates the second runway
            }
            drawAirportBuilding(g);
        }
        
        public void updateRunwayPosition(int newWidth, int newHeight) {
            windowWidth = newWidth;
            windowHeight = newHeight;
            
            // Example to update runway positions dynamically
            runwayX = (windowWidth - RUNWAY_WIDTH) / 2;  // Adjust X position to centre runway
            runwayY = (windowHeight - 250) / 2;  // Adjust Y position to centre runway
        }

        public void drawRunways(Graphics g, int rotation, int xPos, int yPos, int runwayHeight, int runwayWidth) {
            Graphics2D g2d = (Graphics2D) g;

            runwayX = (windowWidth - (runwayWidth)) / 2 + xPos;
            runwayY = (windowHeight - runwayHeight) / 2 + yPos;
            int centerX = runwayX + (runwayWidth / 2);
            int centerY = runwayY + (runwayHeight / 2);

            g2d.setColor(Color.GRAY);
            g2d.translate(centerX, centerY);
            g2d.rotate(Math.toRadians(rotation));
            g2d.translate(-centerX, -centerY);

            Rectangle runwayVisual = new Rectangle(runwayX, runwayY, runwayWidth, runwayHeight);

            g2d.draw(runwayVisual);
            g2d.fill(runwayVisual);

            // Reset canvas transformation
            g2d.setTransform(g2d.getDeviceConfiguration().getDefaultTransform());
        }
        
        public void drawAirportBuilding(Graphics g) {
        	Graphics2D g2d = (Graphics2D) g;
        	Rectangle Building = new Rectangle (100, 50, 350, 115);
        	
        	g2d.setColor(Color.BLUE);
        	g2d.draw(Building);
        	g2d.fill(Building);
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

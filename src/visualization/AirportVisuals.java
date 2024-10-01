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
    public final int GATE_AMOUNT;

    /**
     * Constructor of class AirportVisuals. Calls the method initializeWindow 
     * to create the window within the constructor
     * 
     * @param windowWidth 	Takes in the width of the window
     * @param windowHeight	Takes in the height of the window
     * @param runwayAmount  Takes in the amount of runways
     * @param gateAmount 
     */
    public AirportVisuals(int windowWidth, int windowHeight, int runwayAmount, int gateAmount) {
        this.RUNWAY_AMOUNT = runwayAmount;
        this.GATE_AMOUNT = gateAmount;
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
        
        /* Initialises Airports X & Y positions */
        private int runwayXPos, runwayYPos;		// Initialises the runways X and Y
        private int buildingXPos, buildingYPos;	// Initialises the buildings X and Y
        private int gateXPos, gateYPos;			// Initialises the gates X and Y
        
        /* Initialises Airport and Window Components X and Y */
        private int WINDOW_WIDTH, WINDOW_HEIGHT; 	// Initialises the windows width and height
        
        private final int RUNWAY_WIDTH = 40;	// Initialises the runway width
        private final int RUNWAY_HEIGHT = 450;	// Initialises the runway height
        private final int RUNWAY_ROTATION = 60;	// Initialises the runway rotation

        private final int GATE_WIDTH = 10;	// Initialises the gate width
        private final int GATE_HEIGHT = 65; // Initialises the gate height
        
        private final int BUILDING_WIDTH = 340;		// Initialises the building width
        private final int BUILDING_HEIGHT = 110;	// Initialises the building height
        
        private final int TAXIWAY_WIDTH = RUNWAY_WIDTH / 2;

        /**
         * Constructor of Airport Panel and initialises the background as black
         * 
         * @param windowWidth	Takes in window Width
         * @param windowHeight	Takes in window Height
         */
        public AirportPanel(int windowWidth, int windowHeight) {
            setBackground(Color.BLACK);
            this.WINDOW_WIDTH = windowWidth;
            this.WINDOW_HEIGHT = windowHeight;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            
        	
            // Checks the runway amount and makes sure that it's not greater than two
            if (RUNWAY_AMOUNT > 1 && RUNWAY_AMOUNT <= 2) {
            	drawTaxiWays(g, 510, 20, 260, 150, Color.DARK_GRAY);
                drawRunways(g, -RUNWAY_ROTATION, 40, -155); // Creates the second runway
                drawTaxiWays(g, 196, 50, 155, 30, Color.DARK_GRAY);
            } else {
            	drawTaxiWays(g, 478, 13, 159, 150, Color.DARK_GRAY);
            }
            
            // Draws runway
            drawRunways(g, RUNWAY_ROTATION, -60, -100); // Creates the first runway
            
            
            
            drawTaxiWays(g, ((WINDOW_WIDTH - RUNWAY_WIDTH) / 2) - 85, ((WINDOW_HEIGHT - RUNWAY_HEIGHT) / 2) - 160, RUNWAY_HEIGHT, RUNWAY_ROTATION, Color.DARK_GRAY); // Draws taxi way 
            
            drawTaxiWays(g, ((WINDOW_WIDTH - RUNWAY_WIDTH) / 2) - 15, ((WINDOW_HEIGHT - RUNWAY_HEIGHT) / 2) - 40, RUNWAY_HEIGHT, RUNWAY_ROTATION, Color.DARK_GRAY); // Draws taxi way 
            
            drawTaxiWays(g, 104, 231, 155, -30, Color.DARK_GRAY);
            
            drawTaxiWays(g, 150, 201, 155, -30, Color.DARK_GRAY);
            
            drawTaxiWays(g, 196, 175, 155, -30, Color.DARK_GRAY);
            
            
            
            drawTaxiWays(g, 100, 100, -30, 100, Color.GREEN);
            
            drawAirportBuilding(g);	// Draws the main building of the airport
            
            drawGates(g);	// Draws the gates
        }
        
        /**
         * Updates the Airport Components whenever the window is size is changed
         * 
         * @param newWidth		Takes in the windows new width
         * @param newHeight		Takes in the windows new height
         */
        public void updateRunwayPosition(int newWidth, int newHeight) {
        	/* Updates the window size */
            WINDOW_WIDTH = newWidth;		// Updates the original width to new width
            WINDOW_HEIGHT = newHeight;	// Updates the original height to new height
            
            /* Updates Airport Components positions dynamically */
            // Runways
            runwayXPos = (WINDOW_WIDTH - RUNWAY_WIDTH) / 2;  // Adjust X position to centre runway
            runwayYPos = (WINDOW_HEIGHT - 250) / 2;  		// Adjust Y position to centre runway
            // Building
            buildingXPos = (WINDOW_WIDTH - BUILDING_WIDTH) / 2; 		// Adjusts the X position to centre the building
            buildingYPos = (WINDOW_HEIGHT - BUILDING_HEIGHT) / 2;	 // Adjusts the Y Position to centre the building
            // Gate
            gateXPos = (WINDOW_WIDTH - GATE_WIDTH) / 2;		// Adjusts the X position to centre the building
            gateYPos = (WINDOW_HEIGHT - GATE_HEIGHT) / 2;	// Adjusts the Y position to centre the building
            
            
        }
        
        /**
         * Draws runways and calculates the position/centres the runways
         * 
         * @param g				Takes in the graphic object
         * @param rotation		Takes in variable for rotation
         * @param xPos			Takes in the offset X position
         * @param yPos			Takes in the offset Y position
         */
        public void drawRunways(Graphics g, int rotation, int xPos, int yPos) {
            Graphics2D g2d = (Graphics2D) g; // Creates an instance of 2d graphics
            
            /* Calculates runway X & Y position */
            runwayXPos = (WINDOW_WIDTH - (RUNWAY_WIDTH)) / 2 + xPos; // Calculates the runway's X position on the X axis and offsets by xPos
            runwayYPos = (WINDOW_HEIGHT - RUNWAY_HEIGHT) / 2 + yPos;	// Calculates the runway's Y position on the Y axis and offsets by yPos
            
            /* Calculates the runway's position when centred */
            int centreXRunway = runwayXPos + (RUNWAY_WIDTH / 2);	// Centres the runway on the X axis
            int centreYRunway = runwayYPos + (RUNWAY_HEIGHT / 2);	// Centres the runway on the Y axis

            g2d.setColor(Color.DARK_GRAY); // Sets the runway colour to dark gray
            g2d.translate(centreXRunway, centreYRunway);	// Translates the runway to the centre
            g2d.rotate(Math.toRadians(rotation));			// Converts rotation to radians and rotates the runway
            g2d.translate(-centreXRunway, -centreYRunway);	// Translates the runway back to the centre (This was added due to the runway being in a random position)

            Rectangle runwayVisual = new Rectangle(runwayXPos, runwayYPos, RUNWAY_WIDTH, RUNWAY_HEIGHT); // Creates the runway and sets the X, Y, runway width and height

            g2d.draw(runwayVisual); // Draws the runway on the panel
            g2d.fill(runwayVisual);	// Fills the runway on the panel

            // Resets any canvas transformation
            g2d.setTransform(g2d.getDeviceConfiguration().getDefaultTransform()); // Added due to when building, gates and taxi way weren't in predicted position
        }
        
        /**
         * Creates the airport building and calculates the position
         * 
         * @param g	Takes in graphics object
         */
        public void drawAirportBuilding(Graphics g) {
        	Graphics2D g2d = (Graphics2D) g; // Creates an instance of 2d graphics
        	
        	final int offsetY = 100; // Sets the offset of the building
        	
        	/* Calculates the buildings X & Y position */
        	buildingXPos = (WINDOW_WIDTH - BUILDING_WIDTH) / 2;					// Calculates the buildings X position on the X axis
        	buildingYPos = (WINDOW_HEIGHT - (BUILDING_HEIGHT)) / 2 + offsetY;	// Calculates the buildings Y position on the Y axis and offsets by 100
        	
        	/* Calculates the buildings position when centred */
        	int centreXBuilding = buildingXPos + (350 / 2);	// Calculates the building centre. (350 is what got to the position I wanted)
        	int centreYBuilding = buildingYPos + (115 / 2); // Calculates the building centre. (115 is what got the the position on I wanted)
        	
        	g2d.setColor(Color.LIGHT_GRAY); // Sets the building colour to light gray
        	g2d.translate(centreXBuilding, centreYBuilding); 	// Translates the building to the centre
        	g2d.rotate(Math.toRadians(-30));					// Takes in -30 degrees and converts to radians and rotates building
        	g2d.translate(-centreXBuilding, -centreYBuilding);	// Translates the building back to the centre
        	
        	Rectangle Building = new Rectangle (buildingXPos, buildingYPos, BUILDING_WIDTH, BUILDING_HEIGHT); // Creates the building and sets the X, Y, building width and height
        	
        	g2d.draw(Building);	// Draws the building on the panel
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


            final int gateOffsetY = -44;	// Offsets the gate on the Y axis
            final int gateOffsetX = 2;		// Offsets the gate on the X axis
            final int gateSpacing = 85;  	// Determines the spacing between each gate

            /* Calculates the Gates centre */
            int centerXBuilding = buildingXPos + (BUILDING_WIDTH / 2);		// Calculates the buildings X position on the X axis
            int centerYBuilding = buildingYPos + (BUILDING_HEIGHT / 2);		// Calculates the buildings Y position on the Y axis
            
            g2d.translate(centerXBuilding, centerYBuilding);	// Translates the gate to the centre
            g2d.rotate(Math.toRadians(-30));					// Converts -30 to degrees and converts to radians and rotates building
            g2d.translate(-centerXBuilding, -centerYBuilding);	// Translates the gate back to the centre

            // Loop to create multiple gates evenly spaced along the building
            for (int i = 0; i < GATE_AMOUNT; i++) {
                // Calculates gate position based on the building position and size
                gateXPos = buildingXPos + (i * gateSpacing) - gateOffsetX;  // Evenly spaces gates along the building
                gateYPos = buildingYPos + gateOffsetY;  // Position gates relative to the building
                
                g2d.setColor(Color.LIGHT_GRAY); // Sets the gates colour to light gray
                Rectangle Gate = new Rectangle(gateXPos, gateYPos, GATE_WIDTH, GATE_HEIGHT); // Creates the gate and sets the X, Y, gate width and height
                g2d.draw(Gate);	// Draws gate
                g2d.fill(Gate); // Fills gate
            }

            g2d.setTransform(g2d.getDeviceConfiguration().getDefaultTransform()); // Resets any canvas transformation
        }
        
        /**
         * Draws and calculates the taxiway's position
         * 
         * @param g
         */
        public void drawTaxiWays(Graphics g, int xPos, int yPos, int height, int rotationAngle, Color c) {
            Graphics2D g2d = (Graphics2D) g;
            
            int centerX = xPos + (TAXIWAY_WIDTH / 2);
            int centerY = yPos + (height / 2);

            g2d.translate(centerX, centerY);
            g2d.rotate(Math.toRadians(rotationAngle));
            g2d.translate(-centerX, -centerY);
            
            Rectangle taxiWayVisual = new Rectangle(xPos, yPos, TAXIWAY_WIDTH, height);
            
            g2d.setColor(c);
            g2d.draw(taxiWayVisual);
            g2d.fill(taxiWayVisual);
            
            g2d.setTransform(g2d.getDeviceConfiguration().getDefaultTransform()); // Resets any canvas transformation
        }
    }
}

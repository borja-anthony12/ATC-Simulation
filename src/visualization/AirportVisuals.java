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
        private int runwayXPos, runwayYPos;
        private int buildingXPos, buildingYPos;
        private int gateXPos, gateYPos;
        
        private final int RUNWAY_WIDTH = 40;
        private final int RUNWAY_HEIGHT = 450;
        private final int ROTATION = 60;

        private final int GATE_WIDTH = 25;
        private final int GATE_HEIGHT = 50;
        
        private final int BUILDING_WIDTH = 325;
        private final int BUILDING_HEIGHT = 100;

        public AirportPanel(int windowWidth, int windowHeight) {
            setBackground(Color.BLACK);
            this.windowWidth = windowWidth;
            this.windowHeight = windowHeight;
        }

        @Override
        protected void paintComponent(Graphics g) {
        	/* TO-DO */
            super.paintComponent(g);
//            drawRunways(g, ROTATION, 0, 0); // Creates the first runway
//
//            if (RUNWAY_AMOUNT > 1 && RUNWAY_AMOUNT <= 2) {
//                drawRunways(g, -ROTATION, 23, -50); // Creates the second runway
//            }
            drawAirportBuilding(g);
            drawGates(g);
        }
        
        public void updateRunwayPosition(int newWidth, int newHeight) {
            windowWidth = newWidth;
            windowHeight = newHeight;
            
            // Example to update runway positions dynamically
            runwayXPos = (windowWidth - RUNWAY_WIDTH) / 2;  // Adjust X position to centre runway
            runwayYPos = (windowHeight - 250) / 2;  // Adjust Y position to centre runway
            
            buildingXPos = (windowWidth - BUILDING_WIDTH) / 2;
            buildingYPos = (windowHeight - BUILDING_HEIGHT) / 2;
            
            gateXPos = (windowWidth - GATE_WIDTH) / 2;
            gateYPos = (windowHeight - GATE_HEIGHT) / 2;
        }

        public void drawRunways(Graphics g, int ROTATION, int xPos, int yPos) {
        	/* TO-DO */
            Graphics2D g2d = (Graphics2D) g;

            runwayXPos = (windowWidth - (RUNWAY_WIDTH)) / 2 + xPos;
            runwayYPos = (windowHeight - RUNWAY_HEIGHT) / 2 + yPos;
            int centreXRunway = runwayXPos + (RUNWAY_WIDTH / 2);
            int centreYRunway = runwayYPos + (RUNWAY_HEIGHT / 2);

            g2d.setColor(Color.GRAY);
            g2d.translate(centreXRunway, centreYRunway);
            g2d.rotate(Math.toRadians(ROTATION));
            g2d.translate(-centreXRunway, -centreYRunway);

            Rectangle runwayVisual = new Rectangle(runwayXPos, runwayYPos, RUNWAY_WIDTH, RUNWAY_HEIGHT);

            g2d.draw(runwayVisual);
            g2d.fill(runwayVisual);

            // Reset canvas transformation
            g2d.setTransform(g2d.getDeviceConfiguration().getDefaultTransform());
        }
        
        public void drawAirportBuilding(Graphics g) {
        	Graphics2D g2d = (Graphics2D) g;
        	
        	final int offsetY = 100;
        	buildingXPos = (windowWidth - BUILDING_WIDTH) / 2;
        	buildingYPos = (windowHeight - (BUILDING_HEIGHT)) / 2 + offsetY;
        	int centreXBuilding = buildingXPos + (350 / 2);
        	int centreYBuilding = buildingYPos + (115 / 2);
        	
        	g2d.setColor(Color.GRAY);
        	g2d.translate(centreXBuilding, centreYBuilding);
        	g2d.rotate(Math.toRadians(-30));
        	g2d.translate(-centreXBuilding, -centreYBuilding);
        	
        	Rectangle Building = new Rectangle (buildingXPos, buildingYPos, BUILDING_WIDTH, BUILDING_HEIGHT);
        	
        	g2d.draw(Building);
        	g2d.fill(Building);
        	
        	g2d.setTransform(g2d.getDeviceConfiguration().getDefaultTransform());
        }
        
        public void drawGates(Graphics g) {
        	/* TO-DO */
        	Graphics2D g2d = (Graphics2D) g;
        	
        	gateXPos = (windowWidth - GATE_WIDTH) / 2;
        	gateYPos = (windowHeight - GATE_HEIGHT) / 2;
        	int centerXGates = gateXPos + (350 / 2);
        	int centerYGates = gateYPos + (115 / 2);
        	
        	g2d.setColor(Color.BLUE);
        	g2d.translate(centerXGates, centerYGates);
        	g2d.rotate(Math.toRadians(-30));
        	g2d.translate(-centerXGates, -centerYGates);
        	
        	Rectangle Gate = new Rectangle(gateXPos, gateYPos, GATE_WIDTH, GATE_HEIGHT);
        	
        	g2d.draw(Gate);
        	g2d.fill(Gate);
        	
        	g2d.setTransform(g2d.getDeviceConfiguration().getDefaultTransform());
        }
        
        public void drawTaxiWays(Graphics g) {
        	/* TO-DO */
            Graphics2D g2d = (Graphics2D) g;
            Rectangle taxiWayVisual = new Rectangle(50, 50, 100, 100);

            g2d.setColor(Color.GRAY);
            g2d.draw(taxiWayVisual);
            g2d.fill(taxiWayVisual);
        }
    }
}

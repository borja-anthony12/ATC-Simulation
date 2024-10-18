package src.visualization;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.io.Serial;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import src.main.*;

/**
 * Class which creates all the ATC Simulation Visuals
 */
public class GUIElements extends JFrame {
    @Serial
    private static final long serialVersionUID = 2178084625298728326L;

    private static final Logger logger = Logger.getLogger(GUIElements.class.getName());
    static AirportPanel airportVisuals; // Creates an instance of the AirportPanel class
    // Initialises lists
    private final Integer[] setRunwayAmount = {1, 2}; // Creates a 1D list for the available runways
    private final Integer[] setGateAmount = {1, 2, 3, 4}; // Creates a 1D list for the available gates
    // Initialises variables
    public int runwayAmount = 1; // Initialises the runway amount variable
    public int gateAmount; // Initialises the gate amount variable
    public int planeAmount; // Initialises the plane amount variable
    public int windowWidth;
    public int windowHeight;
    // Creates instances of classes
    static Tower tower; // Creates an instance of tower class
    // Creates JComboBox (Creates a drop-down)
    JComboBox<Integer> changeGateAmount; // Creates a Combo box for changing the number of gates
    JComboBox<Integer> changeRunwayAmount; // Creates a Combo box for changing the number of planes
    // Creates JLabels
    JLabel planeAmountDisplay = new JLabel(); // Creates a JLabel for displaying the number of planes (0)
    JLabel gateAmountDisplay = new JLabel(); // Creates a JLabel for displaying the number of gates (Gate Amount:)
    JLabel runwayAmountDisplay = new JLabel(); // Creates a JLabel for displaying the number of runways (Runway Amount:)
    // Creates JButtons
    JButton addPlane; // Creates a JButton for adding a plane
    JButton removePlane; // Creates a JButton for removing a plane
    // Creates font style
    Font font = new Font("Arial", Font.BOLD, 14); // Sets the font for the entire window


    /**
     * Constructor of class AirportVisuals.
     * Calls the method initializeWindow to
     * create the window within the constructor
     */
    public GUIElements() {
    }

    public void getWindowProportions(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    public void getAirportDetails(int runwayAmount, int gateAmount) {
        this.runwayAmount = runwayAmount;
        this.gateAmount = gateAmount;
    }

    public void startWindow() {
        window(this.windowWidth, this.windowHeight); // Calls the initializeWindow method and creates a window setting the width
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
        tower = new Tower(); // Creates an instance of tower class

        // Styles the Drop-down menu (JComboBox)
        UIManager.put("ComboBox.background", Color.DARK_GRAY); // Sets the background to dark grey
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
        setVisible(true); // Sets a window to visible

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
     * Creates a drop for changing the number of gates
     *
     * @return changeGateAmount for adding to panel
     */
    private JComboBox<Integer> createChangeGateAmount() {
        // Creates JComboBox for setting the number of gates
        changeGateAmount = new JComboBox<>(setGateAmount); // Creates Combo box (Drop down) and sets the value to
        // setGateAmount list
        changeGateAmount.setSelectedIndex(3); // Sets the start selected index to 3

        // adds ActionListener to changeGateAmount to check how many gates there will be
        changeGateAmount.addActionListener(e -> {
            if (changeGateAmount.getSelectedItem() != null && airportVisuals != null) {
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
     * Creates a drop-down for changing the number of gates
     *
     * @return drop down for being added to the panel
     */
    private JComboBox<Integer> createChangeRunwayAmount() {
        // Creates JComboBox for setting the number of gates
        changeRunwayAmount = new JComboBox<>(setRunwayAmount); // Creates Combo box (Drop-down) and sets the
        // value to
        // setGateAmount list
        changeRunwayAmount.setSelectedIndex(0); // Sets the start selected index to 3

        // adds ActionListener to changeGateAmount to check how many gates there will be
        changeRunwayAmount.addActionListener(e -> {
            if (changeRunwayAmount.getSelectedItem() != null && airportVisuals != null) {
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
     * Creates a JButton for adding one plane
     *
     * @return JButton for being displayed on the panel
     */
    private JButton createAddPlane() {
        // Creates addPlane Button
        addPlane = new JButton("Add Plane"); // Creates a JButton and sets the button text to "Add PLane"
        // Adds Action Listener to addPLane button.
        // When the button is pressed adds one to
        // planeAmount and spawns plane
        addPlane.addActionListener(e -> {
            planeAmount += 1; // Adds one to planeAmount and sets planeAmount to that value
            planeAmountDisplay.setText(String.valueOf(planeAmount)); // Sets the planeAmountDisplay to the value of
            // planeAmount
            try {
                BufferedImage planeImage = airportVisuals.getImage();
                tower.spawnPlane(runwayAmount, gateAmount, planeImage); // Spawns a plane and places it on JPanel
            } catch (IOException ex) {
                System.out.println("Error is plane spawning");
                throw new RuntimeException(ex);
            }
        });

        return addPlane;
    }

    /**
     * Creates a JButton for removing one plane
     *
     * @return JButton to be added to panel
     */
    private JButton createRemovePlane() {
        // Creates removePlane Button
        removePlane = new JButton("Remove Plane"); // Creates a JButton and sets the button text to "Remove Plane"
        // Adds Action Listener to removePLane.
        // When the button is pressed, it removes one
        // from planeAmount and removes the plane
        removePlane.addActionListener(e -> {
            // Checks if plane amount does not equal zero
            if (planeAmount > 0) {
                planeAmount -= 1; // Removes one to planeAmount and sets planeAmount to that value
                tower.despawnPlane();
                planeAmountDisplay.setText(String.valueOf(planeAmount)); // Sets the planeAmountDisplay to the value
                // of planeAmount
            }
        });
        return removePlane;
    }

    /**
     * Class that extends JPanel and creates runways
     */
    public static class AirportPanel extends JPanel {
        @Serial
        private static final long serialVersionUID = 2853523647566452733L;

        private final String[] imageLocation = {
                "/plane-images/plane-blue.png",
                "/plane-images/plane-jared.png",
                "/plane-images/plane-cyan.png",
                "/plane-images/plane-green.png",
                "/plane-images/plane-purple.png",
                "/plane-images/plane-nolan.png",
                "/plane-images/plane-white.png",
                "/plane-images/plane-will.png"
        };

        private final Color taxiWayColour = Color.DARK_GRAY;
        private final Color runwayColour = Color.GRAY;
        private final Color buildingColour = Color.LIGHT_GRAY;
        public final int RUNWAY_WIDTH = 40; // Initialises the runway width
        public final int RUNWAY_HEIGHT = 450; // Initialises the runway height
        public final int RUNWAY_ROTATION = 60; // Initialises the runway rotation
        private final int GATE_WIDTH = 10; // Initialises the gate width
        private final int GATE_HEIGHT = 65; // Initialises the gate height
        private final int BUILDING_WIDTH = 340; // Initialises the building width
        private final int BUILDING_HEIGHT = 110; // Initialises the building height
        private final int BUILDING_ROTATION = -30;

        private final int TAXIWAY_WIDTH = RUNWAY_WIDTH / 2;
        /* Initialises Airports X & Y positions */
        public int runwayOneXPos, runwayOneYPos; // Initialises the runways X and Y
        public int runwayTwoXPos, runwayTwoYPos;
        private int buildingXPos, buildingYPos; // Initialises the buildings X and Y
        private int gateXPos, gateYPos; // Initialises the gates X and Y
        private final int imageWidth = 28;
        private final int imageHeight = 28;
        public int runwayAmount = 1;
        public int gateAmount = 4;
        private BufferedImage Image;
        private Graphics2D g2d;

        /* Initialises Airport and Window Components X and Y */
        private int windowWidth, windowHeight; // Initialises the windows width (720) and height (600)

        /**
         * Constructor of Airport Panel and initialises the background as black
         */
        public AirportPanel() {
            Color background = Color.BLACK;
            setBackground(background); // Sets the panel background to black
        }

        void getWindowSize(int windowWidth, int windowHeight) {
            this.windowWidth = windowWidth;
            this.windowHeight = windowHeight;
        }

        void getRunwayAmount(int runwayAmount) {
            this.runwayAmount = runwayAmount;
        }

        public void getGateAmount(int gateAmount) {
            this.gateAmount = gateAmount;
        }

        /**
         * Overrides paintComponent to create taxiways, runway, gates and airport
         */
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g2d = (Graphics2D) g;

            int taxiWayXPos = taxiWayXPos(windowWidth, RUNWAY_WIDTH);
            int taxiWayYPos = taxiWayYPos(windowHeight, RUNWAY_HEIGHT);

            int[][] taxiWayData = {
                    {taxiWayXPos - 85, taxiWayYPos - 160, TAXIWAY_WIDTH, RUNWAY_HEIGHT, RUNWAY_ROTATION},
                    {taxiWayXPos - 15, taxiWayYPos - 40, TAXIWAY_WIDTH, RUNWAY_HEIGHT, RUNWAY_ROTATION},
                    {taxiWayXPos - 237, taxiWayYPos + 156, TAXIWAY_WIDTH, 155, -30},
                    {taxiWayXPos - 190, taxiWayYPos + 126, TAXIWAY_WIDTH, 155, -30},
                    {taxiWayXPos - 144, taxiWayYPos + 100, TAXIWAY_WIDTH, 155, -30},
                    {taxiWayXPos(windowWidth, BUILDING_WIDTH) - 30, taxiWayYPos(windowHeight, BUILDING_HEIGHT) + 30,
                            BUILDING_WIDTH, 155, -30}};

            drawALLTaxiWays(taxiWayData);

            checkIfRunway(runwayAmount, taxiWayXPos, taxiWayYPos);

            drawAirportBuilding(); // Draws the main building of the airport

            drawTower();

            drawGates();

            try {
                drawPlanes();
            } catch (IOException e) {
                logger.log(Level.SEVERE, "An error has occurred", e);
            }
        }

        /**
         * Updates the Airport Components whenever the window is size is changed
         *
         * @param newWidth  Takes in the windows new width
         * @param newHeight Takes in the windows new height
         */
        public void updateRunwayPosition( int newWidth, int newHeight) {
            /* Updates the window size */
            windowWidth = newWidth; // Updates the original width to new width
            windowHeight = newHeight; // Updates the original height to new height

            /* Updates Airport Components positions dynamically */
            // Runways
            runwayOneXPos = (windowWidth - RUNWAY_WIDTH) / 2; // Adjust X position to centre runway
            runwayOneYPos = (windowHeight - RUNWAY_HEIGHT) / 2; // Adjust Y position to centre runway

            System.out.println("Runway One X Pos (UpdateRunwayPosition): " + runwayOneXPos + "\nRunway One Y Pos (UpdateRunwayPosition): " + runwayOneYPos);

            runwayTwoXPos = (windowWidth - RUNWAY_WIDTH) / 2;
            runwayTwoYPos = (windowHeight - RUNWAY_HEIGHT) / 2;

            System.out.println("Runway One X Pos (UpdateRunwayPosition): " + runwayTwoXPos + "\nRunway One Y Pos (UpdateRunwayPosition): " + runwayTwoYPos);

            // Building
            buildingXPos = (windowWidth - BUILDING_WIDTH) / 2; // Adjusts the X position to centre the building
            buildingYPos = (windowHeight - BUILDING_HEIGHT) / 2; // Adjusts the Y Position to centre the building
            // Gate
            gateXPos = (windowWidth - GATE_WIDTH) / 2; // Adjusts the X position to centre the building
            gateYPos = (windowHeight - GATE_HEIGHT) / 2; // Adjusts the Y position to centre the building
            tower.getRunwayParam(runwayOneXPos, runwayOneYPos, RUNWAY_WIDTH, RUNWAY_HEIGHT, RUNWAY_ROTATION, runwayAmount);
            tower.getRunwayParam(runwayTwoXPos, runwayTwoYPos, RUNWAY_WIDTH, RUNWAY_HEIGHT, RUNWAY_ROTATION, runwayAmount);
        }

        /**
         * Checks what the selected number of runways are available
         *
         * @param runwayAmount takes in the number of runways
         * @param taxiWayXPos  takes in taxiWayXPos
         * @param taxiWayYPos  takes in taxiWayYPos
         */
        private void checkIfRunway(int runwayAmount, int taxiWayXPos, int taxiWayYPos) {
            int[][] taxiWayData = {{taxiWayXPos + 170, taxiWayYPos - 50, TAXIWAY_WIDTH, 260, 150},
                    {taxiWayXPos - 144, taxiWayYPos - 25, TAXIWAY_WIDTH, 155, 30},};
            System.out.println("Runway One X Pos: " + runwayOneXPos + "\nRunway One Y Pos: " + runwayOneYPos);
            System.out.println("Runway Two X Pos: " + runwayTwoXPos + "\nRunway Two Y Pos: " + runwayTwoYPos);

            int[][] runwayData = {{runwayOneXPos, runwayOneYPos, RUNWAY_ROTATION, -60, -100}, {runwayTwoXPos, runwayTwoYPos, -RUNWAY_ROTATION, 40, -155}};

            // Checks the runway amount and makes sure that it's not greater than two
            if (runwayAmount == 2) {
                drawALLTaxiWays(taxiWayData);

                drawALLRunways(runwayData);
            } else { // If RUNWAY_AMOUNT is less than 1, it draws the taxiway and then runway
                drawTaxiWays(taxiWayXPos + 138, taxiWayYPos - 62, TAXIWAY_WIDTH, 159, 150);

                drawRunways(runwayOneXPos, runwayOneYPos, RUNWAY_ROTATION, -60, -100); // Creates the first runway
            }
        }

        /**
         * Creates all the taxiways
         *
         * @param taxiWayData takes in the data of taxiways
         */
        private void drawALLTaxiWays(int[][] taxiWayData) {
            for (int[] data : taxiWayData) {
                drawTaxiWays(data[0], data[1], data[2], data[3], data[4]);
            }
        }

        /**
         * Creates all the runways
         *
         * @param runwayData takes in runway data
         */
        private void drawALLRunways(int[][] runwayData) {

            for (int[] data : runwayData) {
                System.out.println(Arrays.toString(data));
                drawRunways(data[0], data[1], data[2], data[3], data[4]);
            }
        }

        /**
         * Calculates the centred X position relative to an object
         *
         * @param windowWidth takes in the window width
         * @param objectWidth takes in the object width (i.e. building width and runway
         *                    width)
         * @return returns calculated X value
         */
        private int taxiWayXPos(int windowWidth, int objectWidth) {
            return (windowWidth - objectWidth) / 2;
        }

        /**
         * Calculates the centred Y position relative to an object
         *
         * @param windowHeight takes in the window height
         * @param objectHeight takes in the object height (i.e. building height and
         *                     runway height)
         * @return returns calculated Y value
         */
        private int taxiWayYPos(int windowHeight, int objectHeight) {
            return (windowHeight - objectHeight) / 2;
        }

        /**
         * Rotates the object i.e. runways and taxiways
         *
         * @param g2d      Takes in the graphics component from method
         * @param xPos     Takes in the desired x Position
         * @param yPos     Takes in the desired y Position
         * @param rotation Takes in the desired angle
         */
        public void rotateObject(Graphics2D g2d, int xPos, int yPos, int rotation) {
            g2d.translate(xPos, yPos); // Translate the object to yPos and xPos
            g2d.rotate(Math.toRadians(rotation)); // Rotates the image to desired rotation and converts to radians
            g2d.translate(-xPos, -yPos); // Translates the object back to the original position
        }

        /**
         * Draws the object and displays it
         *
         * @param g2d    Gets the Graphics2D component from method
         * @param visual Gets the visual
         */
        public void drawObject(Graphics2D g2d, Rectangle visual, Ellipse2D circle) {
            if (visual != null) {
                g2d.draw(visual); // Draws the desired visual
                g2d.fill(visual); // Fills the desired visual
            }else{
                g2d.draw(circle);
                g2d.fill(circle);
            }

        }

        /**
         * Resets a panel, so any changes done (i.e. rotating/translating) will be reset
         *
         * @param g2d Takes in the Graphics2D component from method
         */
        public void resetTransformation(Graphics2D g2d) {
            g2d.setTransform(g2d.getDeviceConfiguration().getDefaultTransform()); // Resets window
        }

        /**
         * Does the calculation for centring an object when translating the object
         *
         * @param xPos   Takes in the desired X position
         * @param yPos   Takes in the desired Y position
         * @param width  Takes in the width
         * @param height Takes in the height
         * @return Returns a list which contains the X and Y position centred
         */
        public int[] centreObject(int xPos, int yPos, int width, int height) {
            int centreXPos = xPos + (width / 2); // Calculates X centred
            int centreYPos = yPos + (height / 2); // Calculates Y centred

            // Initialises a list which contains X and Y centred

            return new int[]{centreXPos, centreYPos}; // Returns the list
        }

        public BufferedImage getImage() throws IOException {
            Random rand = new Random();
            String url = imageLocation[rand.nextInt(imageLocation.length)];
            this.Image = ImageIO.read(Objects.requireNonNull(getClass().getResource(url)));
            return Image;
        }

        /**
         * draws the planes
         */
        public void drawPlanes() throws IOException {
            for (PlaneAttributes plane : tower.getPlanes()) {
                double xPlane = plane.getPosition()[0];
                double yPlane = plane.getPosition()[1];
                double anglePlane = -plane.getDirection();


                Rectangle boundBox = new Rectangle((int) xPlane - imageWidth / 2, (int) yPlane - imageHeight / 2, imageWidth, imageHeight); // Check plane position
                g2d.draw(boundBox);
                g2d.fill(boundBox);
                rotateObject(g2d, (int) xPlane, (int) yPlane, (int) anglePlane);

                // Draw the image after applying the transform
                g2d.drawImage(plane.getPlaneImage(), (int) xPlane - (Image.getWidth() / 2), (int) yPlane - (Image.getHeight() / 2),
                        null);

                resetTransformation(g2d);
            }
        }

        public Rectangle getPlaneBoundBox(PlaneAttributes plane) {
            return new Rectangle((int) plane.getPosition()[0], (int) plane.getPosition()[1], imageWidth, imageHeight);
        }

        /**
         * Draws runways and calculates the position/centres the runways
         *
         * @param rotation Takes in variable for rotation
         * @param xPos     Takes in the offset X position
         * @param yPos     Takes in the offset Y position
         */
        public void drawRunways(int runwayXPos, int runwayYPos, int rotation, int xPos, int yPos) {
    //            int[][] runwayData = {{runwayOneXPos, runwayOneYPos, RUNWAY_ROTATION,
            //            -60, -100}, {runwayTwoXPos, runwayTwoYPos, -RUNWAY_ROTATION, 40, -155}};
            /* Calculates runway X & Y position */
            runwayXPos = (windowWidth - (RUNWAY_WIDTH)) / 2 + xPos; // Calculates the runway's X position on the X axis
            // and offsets by xPos
            runwayYPos = (windowHeight - RUNWAY_HEIGHT) / 2 + yPos; // Calculates the runway's Y position on the Y axis
            // and offsets by yPos

            System.out.println(runwayXPos + " " +  runwayYPos);
            System.out.println();
            int[] getCentredPos = centreObject(runwayXPos, runwayYPos, RUNWAY_WIDTH, RUNWAY_HEIGHT);
            tower.getRunwayParam(runwayXPos, runwayYPos, RUNWAY_WIDTH, RUNWAY_HEIGHT, RUNWAY_ROTATION, runwayAmount);
            /* Calculates the runway's position when centred */
            int centreXRunway = getCentredPos[0]; // Centres the runway on the X axis
            int centreYRunway = getCentredPos[1]; // Centres the runway on the Y axis

            g2d.setColor(runwayColour); // Sets the runway colour to dark grey

            rotateObject(g2d, centreXRunway, centreYRunway, rotation);

            Rectangle runwayVisual = new Rectangle(this.runwayOneXPos, this.runwayOneYPos, RUNWAY_WIDTH, RUNWAY_HEIGHT); // Creates the
            // runway
            // and sets
            // the X, Y,
            // runway
            // width and
            // height
            drawObject(g2d, runwayVisual, null);

            //Resets any canvas transformation
            resetTransformation(g2d);
        }

        public void drawTower() {
            int TOWER_SIZE = 75;
            int towerXPos = (windowWidth - TOWER_SIZE) / 2 - 250;
            int towerYPos = (windowHeight - TOWER_SIZE) / 2 - 200;

             int[] getCentredPos = centreObject(towerXPos, towerYPos, TOWER_SIZE, TOWER_SIZE);

             int centreXTower = getCentredPos[0];
             int centreYTower = getCentredPos[1];

             g2d.setColor(buildingColour);

             rotateObject(g2d, centreXTower, centreYTower, 0);

             Ellipse2D tower = new Ellipse2D.Double(towerXPos, towerYPos, TOWER_SIZE, TOWER_SIZE);

             drawObject(g2d, null, tower);

             resetTransformation(g2d);
        }

        /**
         * Creates the airport building and calculates the position
         */
        public void drawAirportBuilding() {

            final int offsetY = 100; // Sets the offset of the building

            /* Calculates the buildings X & Y position */
            buildingXPos = (windowWidth - BUILDING_WIDTH) / 2; // Calculates the buildings X position on the X axis
            buildingYPos = (windowHeight - (BUILDING_HEIGHT)) / 2 + offsetY; // Calculates the buildings Y position on
            // the Y axis and offsets by 100
            int[] getCentredPos = centreObject(buildingXPos, buildingYPos, BUILDING_WIDTH, BUILDING_HEIGHT);

            /* Calculates the building position when centred */
            int centreXBuilding = getCentredPos[0]; // Calculates the building centre. (350 is what got to the
            // position I wanted)
            int centreYBuilding = getCentredPos[1]; // Calculates the building centre. (115 is what got the
            // position on I wanted)

            g2d.setColor(buildingColour); // Sets the building colour to light grey

            rotateObject(g2d, centreXBuilding, centreYBuilding, BUILDING_ROTATION);

            // Creates the building and sets the X, Y, building width and height
            Rectangle building = new Rectangle(buildingXPos, buildingYPos, BUILDING_WIDTH, BUILDING_HEIGHT);

            drawObject(g2d, building, null);

            resetTransformation(g2d);
        }

        /**
         * Creates the gates and calculates the positions
         */
        public void drawGates() {

            final int gateOffsetY = -44; // Offsets the gate on the Y axis
            final int gateOffsetX = 2; // Offsets the gate on the X axis
            final int gateSpacing = 85; // Determines the spacing between each gate

            int[] getCentredPos = centreObject(buildingXPos, buildingYPos, BUILDING_WIDTH, BUILDING_HEIGHT);

            /* Calculates the Gates centre */
            int centreXBuilding = getCentredPos[0]; // Calculates the buildings X position on the X
            // axis
            int centreYBuilding = getCentredPos[1]; // Calculates the buildings Y position on the Y
            // axis

            rotateObject(g2d, centreXBuilding, centreYBuilding, BUILDING_ROTATION);

            // Loop to create multiple gates evenly spaced along the building
            for (int i = 0; i < gateAmount; i++) {
                // Calculates gate position based on the building position and size
                gateXPos = buildingXPos + (i * gateSpacing) - gateOffsetX; // Evenly spaces gates along the building
                gateYPos = buildingYPos + gateOffsetY; // Position gates relative to the building

                g2d.setColor(buildingColour); // Sets the gate colour to light grey
                Rectangle gate = new Rectangle(gateXPos, gateYPos, GATE_WIDTH, GATE_HEIGHT); // Creates the gate and
                // sets the X, Y, gate
                // width and height
                drawObject(g2d, gate, null);
            }

            resetTransformation(g2d);
        }

        /**
         * Creates the taxiways with the given information
         *
         * @param xPos          Takes in the desired X position
         * @param yPos          Takes in the desired Y position
         * @param width         Takes in the desired width
         * @param height        Takes in the desired height
         * @param rotationAngle Takes in the angle for rotating
         */
        public void drawTaxiWays(int xPos, int yPos, int width, int height, int rotationAngle) {

            int[] getCentredPos = centreObject(xPos, yPos, width, height);

            int centreX = getCentredPos[0];
            int centreY = getCentredPos[1];

            rotateObject(g2d, centreX, centreY, rotationAngle);

            Rectangle taxiWayVisual = new Rectangle(xPos, yPos, width, height);

            g2d.setColor(taxiWayColour);

            drawObject(g2d, taxiWayVisual, null);

            resetTransformation(g2d);
        }

        public Polygon getPlanePolygon(PlaneAttributes plane) {
            double x = plane.getPosition()[0]; // Plane's x position
            double y = plane.getPosition()[1]; // Plane's y position
            // Plane's width
            // Plane's height
            double angleDegrees = plane.getDirection(); // Plane's rotation angle (in degrees)

            // Get the rotated rectangle corners
            Point2D.Double[] corners = calculateRotatedRectangle(x, y, imageWidth, imageHeight, angleDegrees);

            // Create a Polygon from the rotated corners
            Polygon polygon = new Polygon();
            for (Point2D.Double corner : corners) {
                polygon.addPoint((int) corner.x, (int) corner.y);
            }

            return polygon;
        }

        public Polygon getRunwayPolygon(double x, double y, double width, double height, double angleDegrees) {
            Point2D.Double[] corners = calculateRotatedRectangle(x, y, width, height, angleDegrees);

            Polygon polygon = new Polygon();
            for (Point2D.Double corner : corners) {
                polygon.addPoint((int) corner.x, (int) corner.y);
            }
            return polygon;
        }

        // Helper method to calculate rotated rectangle corners
        private Point2D.Double[] calculateRotatedRectangle(double x, double y, double width, double height, double angleDegrees) {
            double angleRadians = Math.toRadians(angleDegrees);
            double cosTheta = Math.cos(angleRadians);
            double sinTheta = Math.sin(angleRadians);

            // Center of the rectangle
            double centerX = x + width / 2;
            double centerY = y + height / 2;

            // Calculate corner offsets
            Point2D.Double[] corners = new Point2D.Double[4];
            corners[0] = rotatePoint(centerX, centerY, x, y, cosTheta, sinTheta);  // Top-left
            corners[1] = rotatePoint(centerX, centerY, x + width, y, cosTheta, sinTheta);  // Top-right
            corners[2] = rotatePoint(centerX, centerY, x + width, y + height, cosTheta, sinTheta);  // Bottom-right
            corners[3] = rotatePoint(centerX, centerY, x, y + height, cosTheta, sinTheta);  // Bottom-left

            return corners;
        }

        // Rotate a point around the center by angle
        private Point2D.Double rotatePoint(double centerX, double centerY, double x, double y, double cosTheta, double sinTheta) {
            double dx = x - centerX;
            double dy = y - centerY;
            double rotatedX = centerX + dx * cosTheta - dy * sinTheta;
            double rotatedY = centerY + dx * sinTheta + dy * cosTheta;
            return new Point2D.Double(rotatedX, rotatedY);
        }
    }
}
package src.visualization;

public class Main {
	/* Width and Height */
	private static final int WIDTH = 720; 	// Width for a window
	private static final int HEIGHT = 600;	// Height for a window

    public static final int UPDATE_INTERVAL = 100;
	
	public static void main(String[] args) {
		// Creates instances of all the imported modules
//		Tower tower = new Tower();
//		PlaneAttributes planeAttributes = new PlaneAttributes();
        // The Maximum number of runways is 2
        int runwayAmount = 1;
        // The Maximum number of gates is 4
        int gateAmount = 4;
        GUIElements apVisuals = new GUIElements();
		apVisuals.getWindowProportions(WIDTH, HEIGHT);
		apVisuals.getAirportDetails(runwayAmount, gateAmount);
		apVisuals.startWindow();
	}
}

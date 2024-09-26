package src.visualization;

// Module imports from src.main
import src.main.Tower;
import src.main.PlaneAttributes;

// Local Module Imports
import src.visualization.AirportVisuals;

public class Main {
	/* Width and Height */
	private static final int WIDTH = 720; 	// Width for window
	private static final int HEIGHT = 600;	// Height for window
	
	private static final int RUNWAY_AMOUNT = 3;
	
	public static void main(String[] args) {
		// Creates instances of all of the imported modules
		Tower tower = new Tower(3);
//		PlaneAttributes planeAttributes = new PlaneAttributes();
		AirportVisuals apVisuals = new AirportVisuals(WIDTH, HEIGHT, RUNWAY_AMOUNT);
	}
}

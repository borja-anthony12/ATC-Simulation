package src.visualization;

// Module imports from src.main
import src.main.Tower;
import src.main.PlaneAttributes;

// Local Module Imports
import src.visualization.AirportVisuals;

public class Main {
	/* Width and Height */
	private static final int width = 720; 	// Width for window
	private static final int height = 600;	// Height for window
	
	public static void main(String[] args) {
		// Creates instances of all of the imported modules
		Tower tower = new Tower();
		PlaneAttributes planeAttributes = new PlaneAttributes();
		AirportVisuals apVisuals = new AirportVisuals(width, height);
	}
}

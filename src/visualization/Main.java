package src.visualization;

// Module imports from src.main
import src.main.ATC;
import src.main.Plane;

// Local Module Imports
import src.visualization.AirportVisuals;

public class Main {
	private static int width = 720;
	private static int height = 600;
	public static void main(String[] args) {
		// Creates instances of all of the imported modules
		ATC atc = new ATC();
		Plane plane = new Plane();
		AirportVisuals apVisuals = new AirportVisuals(width, height);
		
		
		
		
		plane.test();
		atc.test();
	}
}

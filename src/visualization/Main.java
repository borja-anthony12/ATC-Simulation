package src.visualization;

// Module imports from src.main
import src.main.ATC;
import src.main.Plane;

// Local Module Imports
import src.visualization.AirportVisuals;

public class Main {
	public static void main(String[] args) {
		ATC atc = new ATC();
		Plane plane = new Plane();
		AirportVisuals apVisuals = new AirportVisuals();
		
		System.out.println("Main Class Works!");
		
		plane.test();
		atc.test();
	}
}

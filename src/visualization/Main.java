package src.visualization;

import src.main.ATC;
import src.main.Plane;

public class Main {
	public static void main(String[] args) {
		ATC atc = new ATC();
		Plane plane = new Plane();
		System.out.println("Main Class Works!");
		
		plane.test();
		atc.test();
	}
}

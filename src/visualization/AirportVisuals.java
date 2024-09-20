package src.visualization;

import javax.swing.*;

public class AirportVisuals {
	JFrame window;
	
	public void WindowCreate() {
		window = new JFrame("Photo Album");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(600, 600);
		window.setLocationRelativeTo(null);
	}
}

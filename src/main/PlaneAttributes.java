package src.main;
import java.util.Random;

public class PlaneAttributes extends PlaneBehavior {
	double x, y, z;
	double dir = 0;
	double vel = 0;
	double v = 1.1;
	double accel = 0;
	double altaccel = 0;
	double takeoffvel;
	String a, b, c, d, e;
	String call;
	boolean power = false;
	
	//String[] digits = (a, b, c, d, e);
	 int ran;
	
	public PlaneAttributes() {

		x = 200;
		y = 200;
		z = 300;
	}

	// make a list of letters and choose a random one
	public String getCallSign() {
		// n c f g
		String[] digits = {a, b, c, d, e};
		Random r = new Random();
		ran = r.nextInt(4) + 1;
		
		if(ran == 1) {
			a = "c";
		}else if(ran == 2) {
			a = "f";
		}else if(ran == 3) {
			a = "g";
		}else if(ran == 4) {
			a = "n";
		}else {
			a = "z";
		}
		
		for(int i = 1; i < 5; i++) {
			ran = r.nextInt(10) + 1;
			digits[i] = Integer.toString(ran);
		}
		call = a + "-" + b + c + d + e;
		
		return call;
	}
	
	
	// 160 - 180 mph takeoff speed
	//increases velocity rate
	public void upVelocity(double vel, boolean takeoff) {
		
		if(vel < 180) {
			//speed is square root of height
			//height is speed squared
			//TO-DO ------------------------------------------------------
			vel = Math.sqrt(z);
			v = vel;
		}else if(vel >= 180) {
			takeoff = false;
			cruise = true;
		}
	}
	
	//decreases velocity rate
	public void downVelocity(double vel, boolean landing) {
		if(vel > 0) {
			vel /= 2;
		}else if(vel <= 0) {
			landing = false;
		}
	}
	
	//increases z coordinate
	public void upAlt(double z) {
		
		if(z < 500 && vel >= 150) {
			//z *= 1.3;
			takeoffvel = vel - 150;
			z = Math.pow(takeoffvel,  2);
		}
	}
	
	// reduces z coordinate
	public void downAlt(double z) {
		
		if(z > 0) {
			z /= 1.4;
		}
	}
	
	/*public int getAltitude(int z) {
		if(vel >= 150) {
			z *= altaccel;
		}
		
		
		
		return z;
	}*/
	
	/*
	 * turns the plane
	 */
	public void turn(double degrees) {
		dir += degrees;
	}
	
	public double[] getPosition() {
		double[] coords = {x, y, z};
		return coords;
	}
}


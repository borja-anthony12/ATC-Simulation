package src.main;
import java.util.Random;

public class PlaneAttributes extends PlaneBehavior {
	int x = 0, y = 0, z = 0;
	int vel = 0;
	int accel = 0;
	int altaccel = 0;
	String a, b, c, d, e;
	String call;
	boolean power = false;
	
	//String[] digits = (a, b, c, d, e);
	 int ran;
	
	public PlaneAttributes() {
		
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
	public void upVelocity(int vel, boolean takeoff) {
		
		if(vel < 180) {
			vel *= 2;
		}else if(vel >= 180) {
			takeoff = false;
			cruise = true;
		}
	}
	
	//decreases velocity rate
	public void downVelocity(int vel, boolean landing) {
		if(vel > 0) {
			vel /= 2;
		}else if(vel <= 0) {
			landing = false;
		}
	}
	
	//increases z coordinate
	public void upAlt(double z) {
		
		if(z < 500 && vel >= 150) {
			z *= 1.3;
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
	
	public int[] getPosition() {
		int[] coords = {x, y, z};
		
		/*x = 0;
		y = 0;
		z = 0;*/
		
		return coords;
	}
}


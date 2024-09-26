package src.main;
import java.util.Random;

public class PlaneAttributes extends PlaneBehavior {
	int x = 0, y = 0, z = 0;
	int vel = 0;
	int accel = 0;
	int altaccel = 0;
	String call;
	String[] {a, b, c, d, e};
	int ran;
	
	public PlaneAttributes() {
		
	}
	
	public String getCallSign() {
		// n c f g
		Random r = new Random();
		ran = r.nextInt(4) + 1;
		/*a = r.nextInt(4) + 1;
		b = r.nextInt(10) + 1;
		c = r.nextInt(10) + 1;
		d = r.nextInt(10) + 1;
		e = r.nextInt(10) + 1;*/
		
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
		
		for(int i = 0; i <= 4; i++) {
			ran = r.nextInt(10) + 1;
			b = Integer.toString(ran);
		}
		
		
		return call;
	}
	
	
	// trying to make a timer to get increasing velocity for a plane
	// 160 - 180 mph takeoff speed
	public void upVelocity() {
		
		
		
		
	}
	
	public int getAltitude(int z) {
		if(vel >= 150) {
			z *= altaccel;
		}
		return z;
	}
	
	public int[] getPosition() {
		int[] coords = {x, y ,z};
		x = 0;
		y = 0;
		z = 0;
		
		// TO-DO 
		//return coords(x, y, z);
		return coords;
	}
}


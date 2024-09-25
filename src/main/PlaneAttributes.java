package src.main;

public class PlaneAttributes extends PlaneBehavior {
	int x = 0, y = 0, z = 0;
	int vel = 0;
	int accel = 0;
	int altaccel = 0;
	
	public PlaneAttributes() {
		
	}
	
	/* TO-DO: 
	Method to get call sign of plane
	*/
	
	
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


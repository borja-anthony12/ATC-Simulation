package src.main;

public class PlaneAttributes extends PlaneBehavior {
	int x = 0, y = 0, z = 0;
	
	
	boolean veltimer = false;
	int count = 0;
	public PlaneAttributes() {
		
	}
	
	
	// trying to make a timer to get increasing velocity for a plane
	public void upVelocity() {
		if(veltimer = false) {
			veltimer = true;
			count = 0;
			
		}
		
			
			
		
		//timer.start();
		
	}
	
	public int getAltitude(int z) {
		
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


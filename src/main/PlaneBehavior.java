package src.main;

public class PlaneBehavior {
	int vel = 0;
	boolean takeoff = false;
	boolean landing = false;
	boolean cruise = false;
	//PlaneAttributes plane = new PlaneAttributes();
	public void takeoff(boolean takeoff) {
		takeoff = true;
		//plane.move(5,-2.9);
		
	}
	
	public void landing(boolean landing) {
		landing = true;
		//one.downVelocity(vel);
	}
	
	public void flying() {
		if(cruise == true) {
			vel = 180;
			
		}
	}
	
	protected boolean isCrashed() {
		return true;
	}

}

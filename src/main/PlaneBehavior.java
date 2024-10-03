package src.main;

public class PlaneBehavior {
	int vel = 0;
	boolean takeoff = false;
	boolean landing = false;
	boolean cruise = false;
	public void takeoff(boolean takeoff) {
		takeoff = true;
		//one.upVelocity(vel);
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

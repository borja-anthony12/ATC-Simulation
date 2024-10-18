package src.main;

public class PlaneBehavior {
	int vel = 0;
	boolean takeoff = false;
	boolean landing = false;
	boolean cruise = true;
	//PlaneAttributes plane = new PlaneAttributes();
	public void takeOff() {

		
		
	}
	
	public void landing() {
		
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

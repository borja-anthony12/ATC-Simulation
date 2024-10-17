package src.main;

public class PlaneBehavior {
	int vel = 0;
	boolean takeoff = false;
	boolean landing = false;
	boolean cruise = false;
	//PlaneAttributes plane = new PlaneAttributes();
	public void takeoff() {
		
		
		
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

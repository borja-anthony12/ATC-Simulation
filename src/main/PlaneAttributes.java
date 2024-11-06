package src.main;

import java.awt.image.BufferedImage;
import java.util.Random;

public class PlaneAttributes extends PlaneBehavior {
    private static final String[] AIRLINE_CODES = {"AYO", "BYK", "CQY", "DAS", "ETE", "FAS", "GIV", "HMS", "IHR", "JUI", "KVY", "LXE", "MTH", "NRS", "OHP", "PQW", "QTO", "RQU", "SVN", "TDX", "UIM", "VOX", "WZE", "XKF", "YSD", "ZUS"};
    private double x, y, z;
    private double dir = 0;
    public double vel = 3;
    private double v = 1.1;
    private double accel = 0;
    private double altaccel = 0;
    private double takeoffvel;
    private int gateNum;
    private boolean power = false;
    private boolean atGate;
    private String callSign;

    private BufferedImage planeSprite;

    /**
     * initializes new plane
     * @param image is the image of the plane being displayed
     * @param atGate is true if its at gate, false if its not
     * @param gate is the gate number its at, -1 if its not at a gate
     */
    public PlaneAttributes(BufferedImage image, boolean atGate, int gate) {
        /*
         * x = 155; y = 375; z = 300;
         */

        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.dir = 0;
        this.callSign = getCallSign();

        gateNum = gate;
        this.atGate = atGate;
        planeSprite = image;
    }

    /**
     * picks a random call sign
     * @return is the call sign returned
     */
    public String getCallSign() {
        Random rand = new Random();
        String airlineCode = AIRLINE_CODES[rand.nextInt(AIRLINE_CODES.length)];
        int flightNumber = rand.nextInt(9999) + 100;

        return airlineCode + flightNumber;
    }
    
    /**
     * @return gets gate number
     */
    public int getGateNum() {
    	return gateNum;
    }

    /**
     * @return gets the plane image for rendering
     */
    public BufferedImage getPlaneImage() {
            return planeSprite;
    }

    /**
     * increases the velocity of the plane
     * @param vel
     * @param takeoff
     */
    public void upVelocity(double vel, boolean takeoff) {
    	// 160 - 180 mph takeoff speed
        // increases velocity rate
    	
        if (vel < 500) {

            vel += 2.5;

        } else if (vel >= 180) {
            takeoff = false;
            cruise = true;
        }
    }

    /**
     * decreases plane velocity
     * @param vel
     * @param landing
     */
    public void downVelocity(double vel, boolean landing) {
        if (vel > 0) {
            vel -= 2.5;
        } else if (vel <= 0) {
            landing = false;
        }
    }

    /**
     * moves the plane
     *
     * @param x: change of x
     * @param y: change of y
     */
    public void move(double x, double y) {
    	if(!isCrashed && !atGate) {
    		this.x += x;
        	this.y += y;
    	}
    }

    /**
     * increases plane altitude
     * @param z
     */
    public void upAlt(double z) {

        if (z < 500 && vel >= 150) {
            // z *= 1.3;

            takeoffvel = vel - 150;
            z = Math.pow(takeoffvel, 2);
        }
    }

    /**
     * reduces plane altitude
     * @param z
     */
    public void downAlt(double z) {
        if (z > 0) {
            z /= 1.4;
        }
    }

    /**
     * returns velocity
     * @return
     */
    public double getVel() {
        return vel;
    }

    /**
     * turns the plane
     * @param degrees is the degrees turned counter-clockwise
     */
    public void turn(double degrees) {
    	if(!isCrashed) dir += degrees;
    }

    /**
     * @return gets the position in an integer array {x, y, z}
     */
    public double[] getPosition() {
        return new double[]{x, y, z};
    }

    /**
     * returns direction
     * @return the direction of the plane
     */
    public double getDirection() {
        return dir;
    }
    
    /**
     * checks if plane is at a gate
     * @return 
     */
    public boolean isAtGate() {
    	return atGate;
    }

    /**
     * sets the position of the plane
     * @param x
     * @param y
     * @param z
     */
    public void setPlane(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

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
    private boolean power = false;
    private boolean atGate;
    private String callSign;

    private BufferedImage planeSprite;

    public PlaneAttributes(BufferedImage image, boolean atGate) {
        /*
         * x = 155; y = 375; z = 300;
         */

        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.dir = 0;
        this.callSign = getCallSign();

        this.atGate = atGate;
        planeSprite = image;
    }

    // make a list of letters and choose a random one
    public String getCallSign() {
        Random rand = new Random();
        String airlineCode = AIRLINE_CODES[rand.nextInt(AIRLINE_CODES.length)];
        int flightNumber = rand.nextInt(9999) + 100;

        return airlineCode + flightNumber;
    }

    public BufferedImage getPlaneImage() {
            return planeSprite;
    }

    // 160 - 180 mph takeoff speed
    // increases velocity rate
    public void upVelocity(double vel, boolean takeoff) {

        if (vel < 500) {

            vel += 2.5;

        } else if (vel >= 180) {
            takeoff = false;
            cruise = true;
        }
    }

    // decreases velocity rate
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

    // increases z coordinate
    public void upAlt(double z) {

        if (z < 500 && vel >= 150) {
            // z *= 1.3;

            takeoffvel = vel - 150;
            z = Math.pow(takeoffvel, 2);
        }
    }

    // reduces z coordinate
    public void downAlt(double z) {
        if (z > 0) {
            z /= 1.4;
        }
    }

    public int getAltitude(int z) {
        if (vel >= 150) {
            z *= altaccel;
        }
        return z;
    }

    public double getVel() {
        return vel;
    }

    /*
     * turns the plane
     */
    public void turn(double degrees) {
    	if(!isCrashed) dir += degrees;
    }

    public double[] getPosition() {

        return new double[]{x, y, z};
    }

    /**
     * returns direction
     *
     * @return the direction of the plane
     */
    public double getDirection() {
        return dir;
    }
    
    public boolean isAtGate() {
    	return atGate;
    }


    public void setPlane(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;


    }

    /**
     * gets the quadrant that the plane is in
     * @return
     */
    @Deprecated
    public int getDirectionQuadrant() {
        return (int) (Math.floor(((dir % 360) / 90)) + 1);
    }
}

package src.main;

import java.util.Random;

public class PlaneAttributes extends PlaneBehavior {
<<<<<<< HEAD
	double x, y, z;
	double dir = 0;
	public double vel = 0;
	double v = 1.1;
	double accel = 0;
	double altaccel = 0;
	double takeoffvel;
	String a, b, c, d, e;
	String call;
	boolean power = false;
=======
    private static final String[] AIRLINE_CODES = {"AYO",
            "BYK",
            "CQY",
            "DAS",
            "ETE",
            "FAS",
            "GIV",
            "HMS",
            "IHR",
            "JUI",
            "KVY",
            "LXE",
            "MTH",
            "NRS",
            "OHP",
            "PQW",
            "QTO",
            "RQU",
            "SVN",
            "TDX",
            "UIM",
            "VOX",
            "WZE",
            "XKF",
            "YSD",
            "ZUS"};
>>>>>>> branch 'main' of https://github.com/borja-anthony12/ATC-Simulation.git

    public double vel = 0;
    double x, y, z;
    double dir = 0;
    double v = 1.1;
    double accel = 0;
    double altaccel = 0;
    double takeoffvel;
    String callSign;
    boolean power = false;

    public PlaneAttributes() {
        /*
         * x = 155; y = 375; z = 300;
         */
        this.x = 185;
        this.y = 305;
        this.z = 300;
        this.dir = 0;
        this.callSign = getCallSign();
    }

    // make a list of letters and choose a random one
    public String getCallSign() {
        Random rand = new Random();
        String airlineCode = AIRLINE_CODES[rand.nextInt(AIRLINE_CODES.length)];
        int flightNumber = rand.nextInt(9999) + 100;

        return airlineCode + flightNumber;
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
        this.x += x;
        this.y += y;
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
        dir += degrees;
    }

    public double[] getPosition() {
        double[] coords = {x, y, z};

<<<<<<< HEAD
	public double getVel() {
		return vel;
	}

	/*
	 * turns the plane
	 */
	public void turn(double degrees) {
		dir += degrees;
	}
=======
        return coords;
    }
>>>>>>> branch 'main' of https://github.com/borja-anthony12/ATC-Simulation.git

    /**
     * returns direction
     *
     * @return
     */
    public double getDirection() {
        return dir;
    }

<<<<<<< HEAD
		return coords;
	}

	/**
	 * returns direction
	 * @return
	 */
	public double getDirection() {
		return dir;
	}

	
	public void setPlane(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		
		
	}
	
	/*public void setPlaneAngle(int dir, int runwayAmount) {
		PlaneAttributes plane = new PlaneAttributes();
		
		
	}*/


	public int getDirectionQuadrant() {
		int quadrant = (int) Math.floor((dir % 360) / 90) + 1;
		return quadrant;
	}

=======
    public int getDirectionQuadrant() {
        int quadrant = (int) Math.floor((dir % 360) / 90) + 1;
        return quadrant;
    }
>>>>>>> branch 'main' of https://github.com/borja-anthony12/ATC-Simulation.git
}

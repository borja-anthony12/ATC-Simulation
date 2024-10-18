package src.visualization;

import src.main.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;

import javax.swing.*;

public class WindowUpdate extends JFrame {
    @Serial
    private static final long serialVersionUID = 1435203151832523191L;

    /**
     * Updates the screen constantly
     *
     * @param mainPanel Takes in the Panel for updating what's on the screen
     * @param tower     Takes in tower class for creating planes
     */
    public WindowUpdate(JPanel mainPanel, Tower tower) {
        Timer timer = new Timer(Main.UPDATE_INTERVAL, new ActionListener() {
            // PlaneAttributes one = new PlaneAttributes();
            PlaneBehavior two = new PlaneBehavior();
            int vel;
            double z;
            boolean takeoff, landing;

            @Override
            public void actionPerformed(ActionEvent e) {
            	
                for (PlaneAttributes plane : tower.getPlanes()) {
                    double dx = 0;
                    double dy = 0;
                    plane.vel = 3;
                    double velocity = plane.getVel();
                    double dir = plane.getDirection() % 360;

                    dx = velocity * Math.cos(Math.toRadians(dir));
                    dy = -velocity * Math.sin(Math.toRadians(dir));

                    System.out.println("Dir: " + dir + " Velocity: "+ velocity);
                    System.out.println("Move: " + dx + ", " + dy+"\n");

                    if(tower.planeOnRunway(plane)) {
                        plane.takeOff();
                    }else {
                        plane.flying();
                    }
                    plane.move(dx, dy);
                }
                mainPanel.repaint();
            }
        });
        timer.start();
    }

    /**
     * turns an angle into a reference angle
     *
     * @param angle is the given angle
     * @return returns the reference, returns -1 if an error occurs
     */
    @Deprecated
    public static double toReferenceAngle(double angle) {
        int quadrant = (int) ((angle % 360) / 90) + 1;

        switch (quadrant) {
            case 1:
                return angle;
            case 2:
                return 180 - angle;
            case 3:
                return angle - 180;
            case 4:
                return 360 - angle;
            default:
                System.out.println("Error: reference angle calculation bad");
        }
        return -1;
    }
}

package frc.lib.util;

import java.util.ArrayList;

/**
 * Handles creating list of setpoints and distances.
 * <p>
 * This class allows you to create list of setpoints and distances for use with
 * shooters. It can find the closest value for either the setpoint or distance.
 */
public class DistanceDatabase {

    private ArrayList<Double> distanceList = new ArrayList<Double>();
    private ArrayList<Integer> setpointList = new ArrayList<Integer>();

    public DistanceDatabase() {

    }

    /**
     * @param distance the distance value.
     * @param setpoint  the desired value for at the distance.
     */
    public void addSetpoint(double distance, int setpoint) {
        distanceList.add(distance);
        setpointList.add(setpoint);
    }

    /**
     * @param distance the distance value.
     * @return the desired value for at the distance.
     */
    public int getSetpoint(double distance) {
        int index = 0;
        double smallestDifference = 0;

        for (int i = 0; i < distanceList.size(); i++) {
            double difference = Math.abs(distanceList.get(i) - distance);

            if (difference < smallestDifference) {
                index = i;
                smallestDifference = difference;
            }
        }

        return setpointList.get(index);
    }

    /**
     * @param setpoint the desired value for at the distance.
     * @return the distance value.
     */
    public double getDistance(int setpoint) {
        int index = 0;
        int smallestDifference = 0;

        for (int i = 0; i < distanceList.size(); i++) {
            int difference = Math.abs(setpointList.get(i) - setpoint);

            if (difference < smallestDifference) {
                index = i;
                smallestDifference = difference;
            }
        }

        return distanceList.get(index);
    }
}
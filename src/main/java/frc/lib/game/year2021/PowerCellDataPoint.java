package frc.lib.game.year2021;

public class PowerCellDataPoint implements Comparable<PowerCellDataPoint> {

    // Variables
    private double distance;
    private int goodShooterSpeed;
    private int badShooterSpeed;
    private double goodHoodPosition;
    private double badHoodPosition;

    public PowerCellDataPoint(double distance, int goodShooterSpeed, int badShooterSpeed, double goodHoodPosition,
            double badHoodPosition) {
        // Save the Variables
        this.distance = distance;
        this.goodShooterSpeed = goodShooterSpeed;
        this.badShooterSpeed = badShooterSpeed;
        this.goodHoodPosition = goodHoodPosition;
        this.badHoodPosition = badHoodPosition;
    }

    public PowerCellDataPoint() {
        this(0.0, 0, 0, 0.0, 0.0);
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    public void setGoodShooterSpeed(int speed) {
        this.goodShooterSpeed = speed;
    }

    public int getGoodShooterSpeed() {
        return goodShooterSpeed;
    }

    public void setBadShooterSpeed(int speed) {
        this.badShooterSpeed = speed;
    }

    public int getBadShooterSpeed() {
        return badShooterSpeed;
    }

    public void setGoodHoodPosition(double position) {
        this.goodHoodPosition = position;
    }

    public double getGoodHoodPosition() {
        return goodHoodPosition;
    }

    public void setBadHoodPosition(double position) {
        this.badHoodPosition = position;
    }

    public double getBadHoodPosition() {
        return badHoodPosition;
    }

    @Override
    public int compareTo(PowerCellDataPoint dataPoint) {
        return Double.valueOf(distance).compareTo(dataPoint.getDistance());
    }
}

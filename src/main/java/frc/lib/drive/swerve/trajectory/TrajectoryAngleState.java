package frc.lib.drive.swerve.trajectory;

import edu.wpi.first.wpilibj.geometry.Translation2d;

public class TrajectoryAngleState implements Comparable<TrajectoryAngleState> {

    // Variables
    private double time;
    private double angle;
    private Translation2d pivot;

    public TrajectoryAngleState(double seconds, double degrees) {
        // Save the Variables
        time = seconds;
        angle = degrees;
    }

    public TrajectoryAngleState(double seconds, Translation2d pivotPosition, double robotAngle) {
        // Save the Variables
        time = seconds;
        angle = robotAngle;
        pivot = pivotPosition;
    }

    public TrajectoryAngleState() {
        this(0.0, 0.0);
    }

    public void setTime(double seconds) {
        time = seconds;
    }

    public double getTime() {
        return time;
    }

    public void setAngle(double degrees) {
        angle = degrees;
    }

    public double getAngle() {
        return angle;
    }

    public void setPivot(Translation2d pivotPosition) {
        this.pivot = pivotPosition;
    }

    public Translation2d getPivot() {
        return pivot;
    }

    @Override
    public int compareTo(TrajectoryAngleState state) {
        return Double.valueOf(time).compareTo(state.getTime());
    }
}

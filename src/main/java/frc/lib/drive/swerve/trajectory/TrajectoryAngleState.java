package frc.lib.drive.swerve.trajectory;

public class TrajectoryAngleState implements Comparable<TrajectoryAngleState> {

    // Variables
    private double time;
    private double angle;

    public TrajectoryAngleState(double seconds, double degrees) {
        // Save the Variables
        time = seconds;
        angle = degrees;
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

    @Override
    public int compareTo(TrajectoryAngleState state) {
        return Double.valueOf(time).compareTo(state.time);
    }
}

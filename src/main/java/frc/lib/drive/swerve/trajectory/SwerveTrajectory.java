package frc.lib.drive.swerve.trajectory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;

public class SwerveTrajectory {

    // Variables
    private Trajectory trajectory;
    private List<TrajectoryAngleState> headingWaypoints;

    public SwerveTrajectory(Trajectory trajectory, List<TrajectoryAngleState> headingWaypoints) {
        // Save the Variables
        this.trajectory = trajectory;
        this.headingWaypoints = headingWaypoints;

        // Sort the Heading Waypoint List
        Collections.sort(headingWaypoints);
    }

    public SwerveTrajectory() {
        this(new Trajectory(), new ArrayList<TrajectoryAngleState>());
    }

    public void setTrajectory(Trajectory trajectory) {
        this.trajectory = trajectory;
    }

    public Trajectory getTrajectory() {
        return trajectory;
    }

    public void setHeadingWaypoints(List<TrajectoryAngleState> headingWaypoints) {
        // Sort the Heading Waypoint List
        Collections.sort(headingWaypoints);

        this.headingWaypoints = headingWaypoints;
    }

    public List<TrajectoryAngleState> getHeadingWavpoints() {
        return headingWaypoints;
    }

    public double getDesiredHeading(double time, Translation2d robotPosition) {
        TrajectoryAngleState desiredHeadingState = new TrajectoryAngleState(0.0, 0.0);

        for (TrajectoryAngleState tempState : headingWaypoints) {
            if (time >= tempState.getTime()) {
                desiredHeadingState = tempState;
            }
        }

        if (desiredHeadingState.getPivot() != null) {
            double pivotX = desiredHeadingState.getPivot().getX();
            double pivotY = desiredHeadingState.getPivot().getY();

            double robotX = robotPosition.getX();
            double robotY = robotPosition.getY();

            double relativeAngle = Math.atan2((robotX - pivotX), (robotY - pivotY)) * (180 / Math.PI);

            return 180 - relativeAngle - desiredHeadingState.getAngle();
        }

        return desiredHeadingState.getAngle();
    }
}

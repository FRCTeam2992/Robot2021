package frc.lib.drive.swerve.trajectory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;

public class SwerveTrajectoryGenerator {

    // Trajectory Settings
    private double maxVelocity;
    private double maxAcceleration;

    // Waypoints
    private Pose2d startPose;
    private Pose2d endPose;
    private List<Translation2d> interiorWaypoints;
    private List<TrajectoryAngleState> headingWaypoints;

    // Set Trajectory
    private Trajectory setTrajectory;

    public SwerveTrajectoryGenerator(Pose2d startPose, Pose2d endPose, double maxVelocityMetersPerSecond,
            double maxAccelerationMetersPerSecondSq) {
        // Save the Start and End Translations
        this.startPose = startPose;
        this.endPose = endPose;

        // Save the Trajectory Settings
        this.maxVelocity = maxVelocityMetersPerSecond;
        this.maxAcceleration = maxAccelerationMetersPerSecondSq;

        // Initialize the Interior Waypoints List
        interiorWaypoints = new ArrayList<>();

        // Initialize the Angle Waypoints Map
        headingWaypoints = new ArrayList<>();
    }

    public SwerveTrajectoryGenerator(Trajectory trajectory) {
        // Save the Set Trajectory
        setTrajectory = trajectory;

        // Initialize the Angle Waypoints Map
        headingWaypoints = new ArrayList<>();
    }

    public SwerveTrajectoryGenerator() {
        this(new Pose2d(), new Pose2d(), 0.0, 0.0);
    }

    public void setStartPose(Pose2d pose) {
        startPose = pose;
    }

    public Pose2d getStartPose() {
        return startPose;
    }

    public void setEndPose(Pose2d pose) {
        endPose = pose;
    }

    public Pose2d getEndPose() {
        return endPose;
    }

    public void setMaxVelocity(double metersPerSecond) {
        maxVelocity = metersPerSecond;
    }

    public double getMaxVelocity() {
        return maxVelocity;
    }

    public void setMaxAcceleration(double metersPerSecondSquared) {
        maxAcceleration = metersPerSecondSquared;
    }

    public double getMaxAcceleration() {
        return maxAcceleration;
    }

    public void addWaypoint(double x, double y) {
        interiorWaypoints.add(new Translation2d(x, y));
    }

    public void addHeadingWaypoint(double seconds, double degrees) {
        headingWaypoints.add(new TrajectoryAngleState(seconds, -degrees));
    }

    public void addTimedHeadingWaypoint(double startTime, double endTime, double degrees) {
        headingWaypoints.add(new TrajectoryAngleState(startTime, endTime, -degrees));
    }

    public void setTrajectory(Trajectory trajectory) {
        setTrajectory = trajectory;
    }

    public SwerveTrajectory generateSwerveTrajectory() {
        Trajectory trajectory;

        // Check for Set Trajectory
        if (setTrajectory != null) {
            trajectory = setTrajectory;
        } else {
            // Create the Trajectory Config
            TrajectoryConfig config = new TrajectoryConfig(maxVelocity, maxAcceleration);

            // Generate the Trajectory
            trajectory = TrajectoryGenerator.generateTrajectory(startPose, interiorWaypoints, endPose, config);
        }

        Collections.sort(headingWaypoints);

        List<TrajectoryAngleState> tempHeadingList = new ArrayList<>();

        for (TrajectoryAngleState tempState : headingWaypoints) {
            if (tempState.getEndTime() > 0.0) {
                double stepTime = 0.0;

                double lastAngle = tempHeadingList.get(tempHeadingList.size() - 1).getAngle();
                double stateAngle = tempState.getAngle();

                double angleChange = stateAngle - lastAngle;

                if (angleChange > 180.0) {
                    angleChange -= 360.0;
                } else if (angleChange < -180.0) {
                    angleChange += 360.0;
                }

                double angleSteps = angleChange / (tempState.getEndTime() - tempState.getTime());

                while (stepTime <= (tempState.getEndTime() - tempState.getTime())) {
                    double tempAngle = lastAngle + (angleSteps * stepTime);

                    if (tempAngle > 180.0) {
                        tempAngle -= 360.0;
                    } else if (tempAngle < -180.0) {
                        tempAngle += 360.0;
                    }

                    tempHeadingList.add(new TrajectoryAngleState(tempState.getTime() + stepTime, tempAngle));

                    stepTime += 0.02;
                }
            } else {
                tempHeadingList.add(tempState);
            }
        }

        return new SwerveTrajectory(trajectory, tempHeadingList);
    }
}

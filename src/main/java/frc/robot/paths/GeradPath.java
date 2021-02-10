package frc.robot.paths;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import frc.lib.drive.swerve.trajectory.SwerveTrajectoryGenerator;
import frc.robot.Constants;

public class GeradPath extends SwerveTrajectoryGenerator {

    public GeradPath() {
        // Setup
        super(new Pose2d(0.0, 0.0, Rotation2d.fromDegrees(0.0)), new Pose2d(0, 1.524, Rotation2d.fromDegrees(180)),
                Constants.maxPathFollowingVelocity, Constants.maxPathFollowingAcceleration);

        addWaypoint(1.524, 0.254);
        addWaypoint(1.905, 0.508);
        addWaypoint(2.286, 1.524);
        addWaypoint(3.048, 1.778);
        addWaypoint(4.572, 1.778);
        addWaypoint(5.334, 0.762);
        addWaypoint(5.715, 0);
        addWaypoint(6.858, 0);
        addWaypoint(7.239, 0.762);
        addWaypoint(6.858, 1.524);
        addWaypoint(6.096, 1.524);
        addWaypoint(6.096, 0);
        addWaypoint(1.524, 0);
        addWaypoint(0.762, 1.524);


        // Heading Waypoints
        addHeadingWaypoint(0.0, 0.0);
    }
}

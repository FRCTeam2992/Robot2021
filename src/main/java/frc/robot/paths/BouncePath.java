package frc.robot.paths;

import frc.lib.drive.swerve.trajectory.SwerveTrajectoryGenerator;
import frc.robot.subsystems.DriveTrain;

public class BouncePath extends SwerveTrajectoryGenerator {

    public BouncePath(DriveTrain subsystem) {
        // Setup
        super(subsystem.BounceTrajectory);

        // Set the Start Rotation
        setStartRotation(-90.0);

        // Heading Waypoints
        addHeadingWaypoint(0.0, 0.0);
    }
}

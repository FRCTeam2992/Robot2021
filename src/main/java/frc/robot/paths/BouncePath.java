package frc.robot.paths;

import frc.lib.drive.swerve.trajectory.SwerveTrajectoryGenerator;
import frc.robot.subsystems.DriveTrain;

public class BouncePath extends SwerveTrajectoryGenerator {

    public BouncePath(DriveTrain subsystem) {
        // Setup
        super(subsystem.BounceTrajectory);

        // Heading Waypoints
        addHeadingWaypoint(0.0, 0.0);
    }
}

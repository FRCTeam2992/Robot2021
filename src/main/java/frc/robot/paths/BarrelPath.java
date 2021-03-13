package frc.robot.paths;

import frc.lib.drive.swerve.trajectory.SwerveTrajectoryGenerator;
import frc.robot.subsystems.DriveTrain;

public class BarrelPath extends SwerveTrajectoryGenerator {

    public BarrelPath(DriveTrain subsystem) {
        // Setup
        super(subsystem.BarrelTrajectory);

        // Heading Waypoints
        addHeadingWaypoint(0.0, 0.0);
    }
}

package frc.robot.paths;

import frc.lib.drive.swerve.trajectory.SwerveTrajectoryGenerator;
import frc.robot.subsystems.DriveTrain;

public class BarrelPath extends SwerveTrajectoryGenerator {

    public BarrelPath(DriveTrain subsystem) {
        // Setup
        super(subsystem.BarrelTrajectory);

        // Set the Start Rotation
        setStartRotation(0.0);

        // Heading Waypoints
        addHeadingWaypoint(0.0, 0.0);
        addTimedHeadingWaypoint(8.0, 9.0, -45.0);
    }
}

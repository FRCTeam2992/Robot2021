package frc.robot.paths;

import frc.lib.drive.swerve.trajectory.SwerveTrajectoryGenerator;
import frc.robot.subsystems.DriveTrain;

public class TeamNumberPath extends SwerveTrajectoryGenerator {
    public TeamNumberPath(DriveTrain subsystem) {
        // Setup
        super(subsystem.TeamNumberPath);

        // Set the Start Rotation
        setStartRotation(0.0);

        // Heading Waypoints
        addHeadingWaypoint(0.0, 0.0);
    }
}

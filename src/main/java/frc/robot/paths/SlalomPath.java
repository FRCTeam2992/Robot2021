package frc.robot.paths;

import frc.lib.drive.swerve.trajectory.SwerveTrajectoryGenerator;
import frc.robot.subsystems.DriveTrain;

public class SlalomPath extends SwerveTrajectoryGenerator {

    public SlalomPath(DriveTrain subsystem) {
        // Setup
        super(subsystem.SlalomTrajectory);

        // Set Starting Heading Waypoint
        addHeadingWaypoint(-1.0, 0.0);

        // Heading Waypoints
        addHeadingWaypoint(0.0, 0.0);
    }
}

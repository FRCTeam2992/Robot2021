package frc.robot.paths;

import frc.lib.drive.swerve.trajectory.SwerveTrajectoryGenerator;
import frc.robot.subsystems.DriveTrain;

public class GalacticSearchBRed extends SwerveTrajectoryGenerator {

    public GalacticSearchBRed(DriveTrain subsystem) {
        // Setup
        super(subsystem.GalacticSearchBRedTrajectory);

        // Heading Waypoints
        addHeadingWaypoint(0.0, 0.0);
        addHeadingWaypoint(0.1, -45.0);
        addHeadingWaypoint(0.75, 55.0);
        addHeadingWaypoint(1.5, -45.0);
    }
}

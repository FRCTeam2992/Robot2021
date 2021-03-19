package frc.robot.paths;

import frc.lib.drive.swerve.trajectory.SwerveTrajectoryGenerator;
import frc.robot.subsystems.DriveTrain;

public class GalacticSearchBRed extends SwerveTrajectoryGenerator {

    public GalacticSearchBRed(DriveTrain subsystem) {
        // Setup
        super(subsystem.GalacticSearchBRedTrajectory);

        // Heading Waypoints
        addHeadingWaypoint(0.0, 0.0);
    }
}

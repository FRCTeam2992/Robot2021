package frc.robot.paths;

import frc.lib.drive.swerve.trajectory.SwerveTrajectoryGenerator;
import frc.robot.subsystems.DriveTrain;

public class GalacticSearchARed extends SwerveTrajectoryGenerator {

    public GalacticSearchARed(DriveTrain subsystem) {
        // Setup
        super(subsystem.GalacticSearchARedTrajectory);

        // Heading Waypoints
        addHeadingWaypoint(0.0, 0.0);
    }
}

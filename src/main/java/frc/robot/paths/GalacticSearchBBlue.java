package frc.robot.paths;

import frc.lib.drive.swerve.trajectory.SwerveTrajectoryGenerator;
import frc.robot.subsystems.DriveTrain;

public class GalacticSearchBBlue extends SwerveTrajectoryGenerator {

    public GalacticSearchBBlue(DriveTrain subsystem) {
        // Setup
        super(subsystem.GalacticSearchBBlueTrajectory);

        // Heading Waypoints
        addHeadingWaypoint(0.0, 0.0);
        addHeadingWaypoint(0.5, 45.0);
        addHeadingWaypoint(1.5, -80.0);
        addHeadingWaypoint(2.0, 45.0);
    }
}

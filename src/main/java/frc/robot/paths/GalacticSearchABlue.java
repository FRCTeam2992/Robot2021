package frc.robot.paths;

import frc.lib.drive.swerve.trajectory.SwerveTrajectoryGenerator;
import frc.robot.subsystems.DriveTrain;

public class GalacticSearchABlue extends SwerveTrajectoryGenerator {

    public GalacticSearchABlue(DriveTrain subsystem) {
        // Setup
        super(subsystem.GalacticSearchABlueTrajectory);

        // Heading Waypoints
        addHeadingWaypoint(0.0, 0.0);
        addHeadingWaypoint(0.5, 30.0);
        addHeadingWaypoint(1.75, -55.0);
        addHeadingWaypoint(2.5, 45.0);
    }
}

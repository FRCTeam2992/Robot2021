package frc.robot.paths;

import frc.lib.drive.swerve.trajectory.SwerveTrajectoryGenerator;
import frc.robot.subsystems.DriveTrain;

public class GalacticSearchBBlue extends SwerveTrajectoryGenerator {

    public GalacticSearchBBlue(DriveTrain subsystem) {
        // Setup
        super(subsystem.GalacticSearchBBlueTrajectory);

        // Heading Waypoints
        addHeadingWaypoint(0.0, 0.0);
    }
}

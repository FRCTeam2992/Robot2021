package frc.robot.paths;

import frc.lib.drive.swerve.trajectory.SwerveTrajectoryGenerator;
import frc.robot.subsystems.DriveTrain;

public class GalacticSearchBBlue extends SwerveTrajectoryGenerator {

    public GalacticSearchBBlue(DriveTrain subsystem) {
        // Setup
        super(subsystem.GalacticSearchBBlueTrajectory);

        // Set the Start Rotation
        setStartRotation(0.0);

        // Heading Waypoints
        addHeadingWaypoint(0.0, 0.0);
        addHeadingWaypoint(1.0, -30.0);
        addHeadingWaypoint(1.2, 30.0);
    }
}

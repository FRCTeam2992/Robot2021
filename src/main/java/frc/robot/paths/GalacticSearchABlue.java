package frc.robot.paths;

import frc.lib.drive.swerve.trajectory.SwerveTrajectoryGenerator;
import frc.robot.subsystems.DriveTrain;

public class GalacticSearchABlue extends SwerveTrajectoryGenerator {

    public GalacticSearchABlue(DriveTrain subsystem) {
        // Setup
        super(subsystem.GalacticSearchABlueTrajectory);

        // Set the Start Rotation
        setStartRotation(0.0);

        // Heading Waypoints
        addHeadingWaypoint(0.0, 0.0);
        addHeadingWaypoint(0.9, -40.0);
        addHeadingWaypoint(1.2, 0.0);
    }
}

package frc.robot.paths;

import frc.lib.drive.swerve.trajectory.SwerveTrajectoryGenerator;
import frc.robot.subsystems.DriveTrain;

public class LeftTrenchTwoPath extends SwerveTrajectoryGenerator {

    public LeftTrenchTwoPath(DriveTrain subsystem) {
        // Setup
        super(subsystem.leftTrenchTwoTrajectory);

        // Set the Start Rotation
        setStartRotation(0.0);
        addHeadingWaypoint(0.1, 0.0);
        addTimedHeadingWaypoint(1, 2, 25);
        addTimedHeadingWaypoint(4.0, 4.9, 45.0);
    }
}

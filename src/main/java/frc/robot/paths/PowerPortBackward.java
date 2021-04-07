package frc.robot.paths;

import frc.lib.drive.swerve.trajectory.SwerveTrajectoryGenerator;
import frc.robot.subsystems.DriveTrain;

public class PowerPortBackward extends SwerveTrajectoryGenerator {

    public PowerPortBackward(DriveTrain subsystem) {
        // Setup
        super(subsystem.PowerPortBackward);

        // Set the Start Rotation
        setStartRotation(-133.0);

        // Heading Waypoints
        addHeadingWaypoint(0.0, 0.0);
    }
}

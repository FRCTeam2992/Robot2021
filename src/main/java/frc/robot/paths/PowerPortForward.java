package frc.robot.paths;

import frc.lib.drive.swerve.trajectory.SwerveTrajectoryGenerator;
import frc.robot.subsystems.DriveTrain;

public class PowerPortForward extends SwerveTrajectoryGenerator {

    public PowerPortForward(DriveTrain subsystem) {
        // Setup
        super(subsystem.PowerPortForward);

        // Set the Start Rotation
        setStartRotation(-133.0);

        // Heading Waypoints
        addHeadingWaypoint(0.0, 0.0);
    }
}

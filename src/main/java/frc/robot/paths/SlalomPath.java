package frc.robot.paths;

import frc.lib.drive.swerve.trajectory.SwerveTrajectoryGenerator;
import frc.robot.subsystems.DriveTrain;

public class SlalomPath extends SwerveTrajectoryGenerator {

    public SlalomPath(DriveTrain subsystem) {
        // Setup
        super(subsystem.SlalomTrajectory);

        // Set the Start Rotation
        setStartRotation(-90.0);

        // Heading Waypoints
        addHeadingWaypoint(0.0, 0.0);
        addHeadingWaypoint(0.1, 30.0);
        addTimedHeadingWaypoint(0.5, 2.0, -30.0);
        // addTimedHeadingWaypoint(2.75, 3.15, -130.0);
        // addTimedHeadingWaypoint(3.15, 3.65, 130.0);
        addTimedHeadingWaypoint(3.0, 4.0, 15.0);
        addTimedHeadingWaypoint(4.5, 6.0, -30.0);
    }
}

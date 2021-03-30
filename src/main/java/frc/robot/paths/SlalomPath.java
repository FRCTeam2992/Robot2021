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
        addHeadingWaypoint(1.5, -30.0);
        addTimedHeadingWaypoint(2.75, 3.5, -130.0);
        addTimedHeadingWaypoint(3.5, 4.25, 130.0);
        addTimedHeadingWaypoint(4.25, 5.0, 30.0);
        addTimedHeadingWaypoint(5.5, 7.0, -30.0);
    }
}

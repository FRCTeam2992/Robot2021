package frc.robot.paths;

import frc.lib.drive.swerve.trajectory.SwerveTrajectoryGenerator;
import frc.robot.subsystems.DriveTrain;

public class TeamNumberPath extends SwerveTrajectoryGenerator {
    public TeamNumberPath(DriveTrain subsystem) {
        // Setup
        super(subsystem.TeamNumberPath);

        // Set the Start Rotation
        setStartRotation(0.0);

        addHeadingWaypoint(0.0, 0);

        // 2 Spins
        // addTimedHeadingWaypoint(0.1, 2.9, 90);
        // addTimedHeadingWaypoint(2.9, 5.8, 180);
        // addTimedHeadingWaypoint(5.8, 8.7, 270);
        // addTimedHeadingWaypoint(8.7, 11.6, 0);
        // addTimedHeadingWaypoint(11.6, 14.5, 90);
        // addTimedHeadingWaypoint(14.5, 17.4, 180);
        // addTimedHeadingWaypoint(17.4, 20.3, 270);
        // addTimedHeadingWaypoint(20.3, 23.2, 0);

        // 5 spins
        addTimedHeadingWaypoint(.1, 1.17, 90);
        addTimedHeadingWaypoint(1.17, 2.34, 180);
        addTimedHeadingWaypoint(2.34, 3.51, 270);
        addTimedHeadingWaypoint(3.51, 4.68, 0);
        addTimedHeadingWaypoint(4.68, 5.85, 90);
        addTimedHeadingWaypoint(5.85, 7.02, 180);
        addTimedHeadingWaypoint(7.02, 8.19, 270);
        addTimedHeadingWaypoint(8.19, 9.36, 0);
        addTimedHeadingWaypoint(9.36, 10.53, 90);
        addTimedHeadingWaypoint(10.53, 11.7, 180);
        addTimedHeadingWaypoint(11.7, 12.87, 270);
        addTimedHeadingWaypoint(12.87, 14.04, 0);
        addTimedHeadingWaypoint(14.04, 15.21, 90);
        addTimedHeadingWaypoint(15.21, 16.38, 180);
        addTimedHeadingWaypoint(16.38, 17.55, 270);
        addTimedHeadingWaypoint(17.55, 18.72, 0);
        addTimedHeadingWaypoint(18.72, 19.89, 90);
        addTimedHeadingWaypoint(19.89, 21.06, 180);
        addTimedHeadingWaypoint(21.23, 22.23, 270);
        addTimedHeadingWaypoint(22.23, 23.4, 0);
    }
}

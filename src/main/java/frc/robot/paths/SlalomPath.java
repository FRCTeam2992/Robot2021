package frc.robot.paths;

import frc.lib.drive.swerve.trajectory.SwerveTrajectoryGenerator;
import frc.robot.Robot;

public class SlalomPath extends SwerveTrajectoryGenerator {

    public SlalomPath() {
        // Setup
        super(Robot.mRobotContainer.mDriveTrain.SlalomTrajectory);

        // Heading Waypoints
        addHeadingWaypoint(0.0, 0.0);
    }
}

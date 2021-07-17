package frc.robot.paths;

import frc.lib.drive.swerve.trajectory.SwerveTrajectoryGenerator;
import frc.robot.subsystems.DriveTrain;

public class RightTrenchFivePath extends SwerveTrajectoryGenerator {

    public RightTrenchFivePath(DriveTrain subsystem){
        // Setup
        super(subsystem.RightTrenchFiveTrajectory);

        // Set the Start Rotation
        setStartRotation(0.0);
    
        addHeadingWaypoint(0.1, 8.0);
        addTimedHeadingWaypoint(4.0, 6.3, 37.0);
    }
}

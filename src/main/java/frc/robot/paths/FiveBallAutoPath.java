package frc.robot.paths;

import frc.lib.drive.swerve.trajectory.SwerveTrajectoryGenerator;
import frc.robot.subsystems.DriveTrain;

public class FiveBallAutoPath extends SwerveTrajectoryGenerator {

    public FiveBallAutoPath(DriveTrain subsystem){
        // Setup
        super(subsystem.FiveBallTrenchRunPath);

        // Set the Start Rotation
        setStartRotation(63.5);
    
        addHeadingWaypoint(0.1, 0.0);
        addTimedHeadingWaypoint(7.25, 10.0, 57.0);
    }
}

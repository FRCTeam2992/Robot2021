package frc.robot.paths;

import frc.lib.drive.swerve.trajectory.SwerveTrajectoryGenerator;
import frc.robot.subsystems.DriveTrain;

public class RightTrenchThreePath extends SwerveTrajectoryGenerator {

    public RightTrenchThreePath(DriveTrain subsystem){
        // Setup
        super(subsystem.RightTrenchThreeTrajectory);

        // Set the Start Rotation
        setStartRotation(0.0);
    
        addHeadingWaypoint(0.1, 0.0);
        addTimedHeadingWaypoint(4.0, 4.9, 37.0);
    }
}

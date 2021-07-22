package frc.robot.paths;

import frc.lib.drive.swerve.trajectory.SwerveTrajectoryGenerator;
import frc.robot.subsystems.DriveTrain;

public class CenterTrenchThreePath extends SwerveTrajectoryGenerator {

    public CenterTrenchThreePath(DriveTrain subsystem){
        // Setup
        super(subsystem.CenterTrenchThreeTrajectory);

        // Set the Start Rotation
        setStartRotation(0.0);
    
        addHeadingWaypoint(0.1, 0.0);
        addTimedHeadingWaypoint(4.0, 5.6, 58.0);
    }
}

package frc.robot.paths;

import frc.lib.drive.swerve.trajectory.SwerveTrajectoryGenerator;
import frc.robot.subsystems.DriveTrain;

public class CenterTrenchFivePath extends SwerveTrajectoryGenerator {

    public CenterTrenchFivePath(DriveTrain subsystem){
        // Setup
        super(subsystem.CenterTrenchFiveTrajectory);

        // Set the Start Rotation
        setStartRotation(0.0);
    
        addHeadingWaypoint(0.1, 8.0);
        addTimedHeadingWaypoint(4.0, 6.8, 54.0);
    }
}

package frc.robot.paths;

import frc.lib.drive.swerve.trajectory.SwerveTrajectoryGenerator;
import frc.robot.subsystems.DriveTrain;

public class FiveBallAutoPath extends SwerveTrajectoryGenerator {
    public FiveBallAutoPath(DriveTrain subsystem){
        //Setup
        super(subsystem.FiveBallTrenchRunPath);

        //Set start rotation
        setStartRotation(0.0);
    
        addHeadingWaypoint(0.01, 45);
        addHeadingWaypoint(1.75, 0);
    }
}

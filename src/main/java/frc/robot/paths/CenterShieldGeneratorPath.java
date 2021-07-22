package frc.robot.paths;

import frc.lib.drive.swerve.trajectory.SwerveTrajectoryGenerator;
import frc.robot.subsystems.DriveTrain;

public class CenterShieldGeneratorPath extends SwerveTrajectoryGenerator {

    public CenterShieldGeneratorPath(DriveTrain subsystem){
        // Setup
        super(subsystem.CenterShieldGeneratorTrajectory);

        // Set the Start Rotation
        setStartRotation(0.0);
    
        addHeadingWaypoint(0.1, 60.0);
        addTimedHeadingWaypoint(4.0, 5.5, 54.0);
    }
}

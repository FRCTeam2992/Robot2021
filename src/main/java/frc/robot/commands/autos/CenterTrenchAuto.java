// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autos;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.AutoDriveRotate;
import frc.robot.commands.AutoFollowPath;
import frc.robot.commands.HoodIsHomed;
import frc.robot.commands.SetHoodTarget;
import frc.robot.commands.SetShooterSpeed;
import frc.robot.commands.ShooterAtSpeed;
import frc.robot.commands.StartHood;
import frc.robot.commands.StartShooter;
import frc.robot.commands.groups.AutoIntake;
import frc.robot.commands.groups.AutoShoot;
import frc.robot.paths.FiveBallAutoPath;
import frc.robot.subsystems.AdjustabeHood;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Ejector;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Spindexer;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class CenterTrenchAuto extends SequentialCommandGroup {

  /** Creates a new CenterTrenchAuto. */
  public CenterTrenchAuto(Shooter shooter, AdjustabeHood adjustabeHood, DriveTrain driveTrain, Spindexer spindexer, Ejector ejector, Intake intake) {

    addCommands(
      new SetShooterSpeed(shooter, 4200),
      
      new SetHoodTarget(adjustabeHood, 6.0),
      
      new ParallelRaceGroup(
        new ParallelCommandGroup(
          new StartShooter(shooter), 
          new SequentialCommandGroup(new HoodIsHomed(adjustabeHood),new StartHood(adjustabeHood))),
          new SequentialCommandGroup(
            new ParallelCommandGroup(new AutoDriveRotate(driveTrain, 60, true, 2), new ShooterAtSpeed(shooter, 3)),
            new AutoShoot(spindexer, ejector).withTimeout(2),
            new SetHoodTarget(adjustabeHood, 0.0),
            new ParallelCommandGroup(
              new AutoFollowPath(driveTrain, new FiveBallAutoPath(driveTrain).generateSwerveTrajectory()),
              new AutoIntake(intake, spindexer, ejector).withTimeout(7.0),
              new SequentialCommandGroup(
                new WaitCommand(8.0), 
                new ParallelCommandGroup(
                  new SetHoodTarget(adjustabeHood, 7.1),
                  new SetShooterSpeed(shooter, 4600)
                )
              )
            ),
            new AutoDriveRotate(driveTrain, 60, true, 2),
            new AutoShoot(spindexer, ejector).withTimeout(5)
          )
      )
    );
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autos;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AutoDriveRotate;
import frc.robot.commands.AutoFollowPath;
import frc.robot.commands.HomeAdjustableHood;
import frc.robot.commands.SetHoodTarget;
import frc.robot.commands.SetShooterSpeed;
import frc.robot.commands.ShooterAtSpeed;
import frc.robot.commands.StartHood;
import frc.robot.commands.StartShooter;
import frc.robot.commands.groups.AutoIntake;
import frc.robot.commands.groups.AutoOverride;
import frc.robot.commands.groups.AutoShoot;
import frc.robot.paths.LeftTrenchTwoPath;
import frc.robot.paths.RightTrenchThreePath;
import frc.robot.subsystems.AdjustabeHood;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Ejector;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Spindexer;

public class LeftTrenchTwoAuto extends SequentialCommandGroup {

  public LeftTrenchTwoAuto(Shooter shooter, AdjustabeHood adjustabeHood, DriveTrain driveTrain, Spindexer spindexer,
      Ejector ejector, Intake intake) {
    addCommands(
      new SetShooterSpeed(shooter, 4500),
      
      new SetHoodTarget(adjustabeHood, 9.0),
      
      new ParallelRaceGroup(
        new ParallelCommandGroup(
          new StartShooter(shooter), 
          new SequentialCommandGroup(
            new HomeAdjustableHood(adjustabeHood),
            new StartHood(adjustabeHood)
          )
        ),
        new SequentialCommandGroup(
          // new ParallelCommandGroup(
          //   new AutoDriveRotate(driveTrain, 60, true, 2), 
          //   new ShooterAtSpeed(shooter, 2)
          // ),
          // new AutoShoot(spindexer, ejector, intake).withTimeout(1.5),
          // new SetHoodTarget(adjustabeHood, 9.0),
          // new SetShooterSpeed(shooter, 4800),
          new ParallelCommandGroup(
            new AutoFollowPath(driveTrain, new LeftTrenchTwoPath(driveTrain).generateSwerveTrajectory()),
            new SequentialCommandGroup(
              new AutoIntake(intake, spindexer, ejector).withTimeout(2.25),
              new AutoOverride(intake, spindexer, ejector).withTimeout(1)
            )
          ),
          new AutoDriveRotate(driveTrain, 60, true, 1),
          new AutoShoot(spindexer, ejector, intake).withTimeout(5)
        )
      )
    );
    
  }
}

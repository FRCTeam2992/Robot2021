// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autos;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.AutoFollowPath;
import frc.robot.commands.AutoLimeLightHood;
import frc.robot.commands.AutoLimeLightSpeed;
import frc.robot.commands.DeployIntake;
import frc.robot.commands.MoveIntake;
import frc.robot.commands.SetAdjustableHoodPosition;
import frc.robot.commands.StartShooter;
import frc.robot.commands.StopEjector;
import frc.robot.commands.StopSpindexer;
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
public class CenterTrenchAuto extends ParallelCommandGroup {

  private DriveTrain mDriveTrain;
  private Shooter mShooter;
  private Spindexer mSpindexer;
  private Ejector mEjector;
  private Intake mIntake;
  private AdjustabeHood mAdjustabeHood;

  public CenterTrenchAuto(DriveTrain driveTrain, Shooter shooter, Spindexer spindexer, Ejector ejector, Intake intake,
      AdjustabeHood adjustableHood) {
    mDriveTrain = driveTrain;
    mShooter = shooter;
    mSpindexer = spindexer;
    mEjector = ejector;
    mIntake = intake;
    mAdjustabeHood = adjustableHood;

    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new StartShooter(mShooter), new AutoLimeLightHood(mAdjustabeHood, mDriveTrain),
        new AutoLimeLightSpeed(mShooter, mDriveTrain), new DeployIntake(mIntake, true), new MoveIntake(mIntake, 1),
        new AutoFollowPath(mDriveTrain, new FiveBallAutoPath(mDriveTrain).generateSwerveTrajectory()),
        new SequentialCommandGroup(new WaitCommand(1),
            new ParallelCommandGroup(new AutoShoot(mSpindexer, mEjector),
                new SequentialCommandGroup(new WaitCommand(1.75),
                    new ParallelCommandGroup(new SetAdjustableHoodPosition(mAdjustabeHood, 0.0),
                        new StopEjector(mEjector), new StopSpindexer(mSpindexer))))));
  }
}

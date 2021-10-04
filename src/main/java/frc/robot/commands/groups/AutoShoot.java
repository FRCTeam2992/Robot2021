// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.DeployIntake;
import frc.robot.commands.MoveEjector;
import frc.robot.commands.MoveIntake;
import frc.robot.commands.MoveSpindexer;
import frc.robot.commands.StopIntake;
import frc.robot.subsystems.Ejector;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Spindexer;

public class AutoShoot extends ParallelCommandGroup {

  public AutoShoot(Spindexer mSpindexer, Ejector mEjector, Intake mIntake) {
    // Add Commands
    addCommands(
      new MoveEjector(mEjector, 1), new MoveSpindexer(mSpindexer, -0.45), 
      new SequentialCommandGroup(
          new MoveIntake(mIntake, 1), new DeployIntake(mIntake, false), new WaitCommand(2), new StopIntake(mIntake)));
  }
}

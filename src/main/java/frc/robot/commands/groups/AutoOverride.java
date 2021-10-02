// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.DeployIntake;
import frc.robot.commands.MoveIntake;
import frc.robot.commands.StopEjector;
import frc.robot.commands.StopIntake;
import frc.robot.commands.StopSpindexer;
import frc.robot.subsystems.Ejector;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Spindexer;

public class AutoOverride extends ParallelCommandGroup {

  public AutoOverride(Intake mIntake, Spindexer mSpindexer, Ejector mEjector) {
    // Add Commands
    addCommands(new MoveIntake(mIntake, 1), new StopSpindexer(mSpindexer), new StopEjector(mEjector),
        new SequentialCommandGroup(
          new DeployIntake(mIntake, false), new WaitCommand(2), new StopIntake(mIntake)));
  }
}
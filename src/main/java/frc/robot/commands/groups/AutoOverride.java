// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.DeployIntake;
import frc.robot.commands.StopEjector;
import frc.robot.commands.StopIntake;
import frc.robot.commands.StopSpindexer;
import frc.robot.subsystems.Ejector;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Spindexer;

public class AutoOverride extends ParallelCommandGroup {

  public AutoOverride(Intake mIntake, Spindexer mSpindexer, Ejector mEjector) {
    // Add Commands
    addCommands(new StopIntake(mIntake), new StopSpindexer(mSpindexer), new DeployIntake(mIntake, false),
        new StopEjector(mEjector));
  }
}
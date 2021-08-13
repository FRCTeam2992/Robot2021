// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.DeployIntake;
import frc.robot.commands.MoveEjector;
import frc.robot.commands.MoveIntake;
import frc.robot.commands.MoveSpindexer;
import frc.robot.subsystems.Ejector;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Spindexer;

public class AutoIntake extends ParallelCommandGroup {

  public AutoIntake(Intake mIntake, Spindexer mSpindexer, Ejector mEjector) {
    // Add Commands
    addCommands(new DeployIntake(mIntake, false), new MoveIntake(mIntake, 1.0), new MoveSpindexer(mSpindexer, 0.4),
        new MoveEjector(mEjector, -0.2));
  }
}

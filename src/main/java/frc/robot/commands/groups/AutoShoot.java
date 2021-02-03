// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.MoveEjector;
import frc.robot.commands.MoveSpindexer;
import frc.robot.subsystems.Ejector;
import frc.robot.subsystems.Spindexer;


public class AutoShoot extends ParallelCommandGroup {


  public AutoShoot(Spindexer mSpindexer, Ejector mEjector) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new MoveEjector(mEjector, .5), new MoveSpindexer(mSpindexer, -.5));
  }
}
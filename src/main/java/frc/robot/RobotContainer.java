/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.StopIntake;
import frc.robot.subsystems.Intake;
import frc.robot.commands.StopSpindexer;
import frc.robot.subsystems.Spindexer;

public class RobotContainer {
  // Subsystem
  private final Intake mIntake;

  private final Spindexer mSpindexer;

  public RobotContainer() {
    // Intake
    mIntake = new Intake();
    mIntake.setDefaultCommand(new StopIntake(mIntake));

    // Configure the button bindings
    mSpindexer = new Spindexer();
    mSpindexer.setDefaultCommand(new StopSpindexer(mSpindexer));
    configureButtonBindings();
  }

  private void configureButtonBindings() {

  }
}

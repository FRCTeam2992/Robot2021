/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.StopSpindexer;
import frc.robot.subsystems.Spindexer;


public class RobotContainer {
private final Spindexer mSpindexer;
 
  public RobotContainer() {
    mSpindexer = new Spindexer();
    mSpindexer.setDefaultCommand(new StopSpindexer(mSpindexer));
    configureButtonBindings();
  }

  
  private void configureButtonBindings() {

  }
}

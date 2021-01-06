/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.StopClimbSlide;
import frc.robot.commands.StopIntake;
import frc.robot.subsystems.ClimbSlide;
import frc.robot.subsystems.Intake;
import frc.robot.commands.StopSpindexer;
import frc.robot.commands.StopTurret;
import frc.robot.subsystems.Spindexer;
import frc.robot.subsystems.Turret;

public class RobotContainer {
  // Subsystems
  private final Intake mIntake;
  private final Spindexer mSpindexer;
  private final Turret mTurret;
  private final ClimbSlide mClimeSlide;

  public RobotContainer() {
    // Intake
    mIntake = new Intake();
    mIntake.setDefaultCommand(new StopIntake(mIntake));

    //Turret
    mTurret = new Turret();
    mTurret.setDefaultCommand(new StopTurret(mTurret));

    //ClimeSlide
    mClimeSlide = new ClimbSlide();
    mClimeSlide.setDefaultCommand(new StopClimbSlide(mClimeSlide));
    
    mSpindexer = new Spindexer();
    mSpindexer.setDefaultCommand(new StopSpindexer(mSpindexer));
    configureButtonBindings();
  }

  private void configureButtonBindings() {

  }
}

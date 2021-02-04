/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimbSlide;

public class MoveClimbSlide extends CommandBase {

  // Subsystem Instance
  private ClimbSlide mClimbSlide;

  // Saved Variables
  private double mClimbSpeed;

  public MoveClimbSlide(ClimbSlide subsystem, double climbSpeed) {
    // Subsystem Instance
    mClimbSlide = subsystem;

    // Set the Subsystem Requirement
    addRequirements(mClimbSlide);

    // Saved Variables
    mClimbSpeed = climbSpeed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    mClimbSlide.setSlideSpeed(mClimbSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    mClimbSlide.setSlideSpeed(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.TelescopeClimb;

public class ClimbSticks extends CommandBase {
  //Subsystem
  private TelescopeClimb mTelescopeClimb;

  private boolean mClimbMode;

  public ClimbSticks(TelescopeClimb subsystem, boolean climbMode) {

    mTelescopeClimb = subsystem;

    mClimbMode = climbMode;

    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double y1;

    y1 = -Robot.mRobotContainer.controller2.getY(Hand.kLeft);

    if (Math.abs(y1) >= Constants.joystickDeadband || !mClimbMode){
      y1 = 0.0;
    } else {
       double climbValue = y1;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

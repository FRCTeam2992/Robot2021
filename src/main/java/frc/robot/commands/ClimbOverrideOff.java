// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.TelescopeClimb;


public class ClimbOverrideOff extends CommandBase {

  private TelescopeClimb mTelescopeClimb;
  private Intake mIntake;

  public ClimbOverrideOff(TelescopeClimb subsystem) {
    mTelescopeClimb = subsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    mTelescopeClimb.climbOverride= false;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should ed.
  @Override
  public boolean isFinished() {
    return true;
  }
}

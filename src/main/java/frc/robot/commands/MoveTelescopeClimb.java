/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.TelescopeClimb;

public class MoveTelescopeClimb extends CommandBase {

  // Subsystem Intance
  private TelescopeClimb mTelescopeClimb;

  // Saved Variables
  private double mTelescopeSpeed;

  public MoveTelescopeClimb(TelescopeClimb subsystem, double telescopeSpeed) {
    // Subsystem Instance
    mTelescopeClimb = subsystem;

    // Set the Subsystem Requirement
    addRequirements(mTelescopeClimb);

    // Saved Variables
    mTelescopeSpeed = telescopeSpeed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Encoder Limit Switch
    if (mTelescopeClimb.getTelescopePosition() >= Constants.teleClimbLimit && mTelescopeSpeed > 0.0) {
      mTelescopeClimb.setTelescopeSpeed(0.0);
    } else {
      mTelescopeClimb.setTelescopeSpeed(mTelescopeSpeed);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    mTelescopeClimb.setTelescopeSpeed(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

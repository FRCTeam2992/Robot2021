// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.AdjustabeHood;

public class SetAdjustableHoodPosition extends CommandBase {

  // Subsystem Instance
  private AdjustabeHood mAdjustabeHood;

  // Saved Variables
  private double mPosition;

  // Command States
  private boolean isDone = false;

  public SetAdjustableHoodPosition(AdjustabeHood subsystem, double position) {
    // Subsystem Instance
    mAdjustabeHood = subsystem;

    // Set the Subsystem Requirement
    addRequirements(mAdjustabeHood);

    // Saved Variables
    mPosition = position;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (mAdjustabeHood.isHomed) {
      double moveDistance = mPosition - mAdjustabeHood.getHoodPosition();

      if ((moveDistance < 0 && !mAdjustabeHood.getLimitState())
          || (moveDistance > 0 && mAdjustabeHood.getHoodPosition() < Constants.maxHoodRotations)) {
        mAdjustabeHood.setHoodPosition(mPosition);
      } else {
        mAdjustabeHood.setAdjustableShooterHoodSpeed(0.0);
      }
    } else {
      isDone = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    mAdjustabeHood.setAdjustableShooterHoodSpeed(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isDone;
  }
}

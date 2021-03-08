// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.AdjustabeHood;

public class MoveAdjustableHood extends CommandBase {

  // Subsystem Instance
  private AdjustabeHood mAdjustabeHood;

  // Saved Variables
  private double mAdjustableShooterHoodSpeed;

  // Command States
  private boolean isDone = false;

  public MoveAdjustableHood(AdjustabeHood subsystem, double adjustabeShooterHoodSpeed) {
    // Subsystem Instance
    mAdjustabeHood = subsystem;

    // Set the Subsystem Requirement
    addRequirements(mAdjustabeHood);

    // Saved Variables
    mAdjustableShooterHoodSpeed = adjustabeShooterHoodSpeed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (mAdjustabeHood.isHomed) {
      if ((mAdjustableShooterHoodSpeed < 0.0 && !mAdjustabeHood.getLimitState())
          || (mAdjustableShooterHoodSpeed > 0.0 && mAdjustabeHood.getHoodPosition() < Constants.maxHoodRotations)) {
        mAdjustabeHood.setAdjustableShooterHoodSpeed(mAdjustableShooterHoodSpeed);

        if (mAdjustabeHood.getLimitState()) {
          mAdjustabeHood.zeroHoodMotor();
        }
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

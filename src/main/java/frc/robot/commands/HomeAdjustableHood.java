// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AdjustabeHood;

public class HomeAdjustableHood extends CommandBase {

  // Subsystem Instance
  private AdjustabeHood mAdjustabeHood;

  // Command States
  private boolean beginningLimitState;
  private boolean isDone = false;

  public HomeAdjustableHood(AdjustabeHood subsytem) {
    // Subsystem Instance
    mAdjustabeHood = subsytem;

    // Set the Subsystem Requirement
    addRequirements(mAdjustabeHood);
  }

  @Override
  public void initialize() {
    isDone = false;
    beginningLimitState = mAdjustabeHood.getLimitState();
  }

  @Override
  public void execute() {
    if (beginningLimitState) {
      mAdjustabeHood.setAdjustableShooterHoodSpeed(0.15);

      if (!mAdjustabeHood.getLimitState()) {
        beginningLimitState = false;
      }
    }

    else {
      mAdjustabeHood.setAdjustableShooterHoodSpeed(-0.15);

      if (mAdjustabeHood.getLimitState()) {
        mAdjustabeHood.setAdjustableShooterHoodSpeed(0.0);
        mAdjustabeHood.zeroHoodMotor();
        mAdjustabeHood.isHomed = true;

        isDone = true;
      }
    }
  }

  @Override
  public void end(boolean interrupted) {
    mAdjustabeHood.setAdjustableShooterHoodSpeed(0.0);
  }

  @Override
  public boolean isFinished() {
    return isDone;
  }
}

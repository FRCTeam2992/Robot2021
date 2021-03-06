// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AdjustabeShooterHood;

public class HomeAdjuestableShooterHood extends CommandBase {

  private AdjustabeShooterHood mAdjustabeShooterHood;

  private boolean beginningLimitState;
  private boolean isDone = false;

  public HomeAdjuestableShooterHood(AdjustabeShooterHood subsytem) {

    mAdjustabeShooterHood = subsytem;

    addRequirements(mAdjustabeShooterHood);
  }

  @Override
  public void initialize() {
    beginningLimitState = mAdjustabeShooterHood.getLimitState();
  }

  @Override
  public void execute() {
    if (beginningLimitState) {
      mAdjustabeShooterHood.setAdjustableShooterHoodSpeed(0.1);

      if (!mAdjustabeShooterHood.getLimitState()) {
        beginningLimitState = false;
      }
    }

    else {
      mAdjustabeShooterHood.setAdjustableShooterHoodSpeed(-0.1);

      if (mAdjustabeShooterHood.getLimitState()) {
        mAdjustabeShooterHood.setAdjustableShooterHoodSpeed(0.0);
        mAdjustabeShooterHood.zeroHoodMotor();

        isDone = true;
      }
    }
  }

  @Override
  public void end(boolean interrupted) {
    mAdjustabeShooterHood.setAdjustableShooterHoodSpeed(0.0);
  }

  @Override
  public boolean isFinished() {
    return isDone;
  }
}

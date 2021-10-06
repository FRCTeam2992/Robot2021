// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.AdjustabeHood;

public class HoldAdjustableHood extends CommandBase {

  // Subsystem Instance
  private AdjustabeHood mAdjustabeShooterHood;
  private double hoodPosition = 0;

  public HoldAdjustableHood(AdjustabeHood subsystem) {
    // Subsystem Instance
    mAdjustabeShooterHood = subsystem;

    // Set the Subsystem Requirement
    addRequirements(mAdjustabeShooterHood);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    hoodPosition = mAdjustabeShooterHood.getHoodPosition();
    mAdjustabeShooterHood.setHoodTarget(hoodPosition);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (mAdjustabeShooterHood.isHomed) {
      double moveDistance = mAdjustabeShooterHood.getHoodTarget() - mAdjustabeShooterHood.getHoodPosition();

      if ((moveDistance < 0 && !mAdjustabeShooterHood.getLimitState())
          || (moveDistance > 0 && mAdjustabeShooterHood.getHoodPosition() < Constants.maxHoodRotations)) {
        mAdjustabeShooterHood.startHood();
      } else {
        mAdjustabeShooterHood.setAdjustableShooterHoodSpeed(0.0);
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    mAdjustabeShooterHood.setAdjustableShooterHoodSpeed(0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;

import frc.robot.subsystems.AdjustabeHood;

public class HoodSticks extends CommandBase {
  // Subsystem Instance
  private AdjustabeHood mAdjustabeHood;

  private double mAdjustableShooterHoodSpeed;

  public HoodSticks(AdjustabeHood subsystem) {
    mAdjustabeHood = subsystem;

    addRequirements(mAdjustabeHood);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double hoodY;
    hoodY = -Robot.mRobotContainer.controller2.getY(Hand.kRight);

    if(Math.abs(hoodY) <= Constants.joystickDeadband){
      hoodY = 0.0;
    } 
    hoodY = Math.pow(hoodY, 3) / 8;


    if (mAdjustabeHood.isHomed) {
      if ((hoodY < 0.0 && !mAdjustabeHood.getLimitState())
          || (hoodY > 0.0 && mAdjustabeHood.getHoodPosition() < Constants.maxHoodRotations)) {
        mAdjustabeHood.setAdjustableShooterHoodSpeed(hoodY);

        if (mAdjustabeHood.getLimitState()) {
          mAdjustabeHood.zeroHoodMotor();
        }
      } else {
        mAdjustabeHood.setAdjustableShooterHoodSpeed(0.0);
      }
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
    return false;
  }
}

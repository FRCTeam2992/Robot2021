// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AdjustabeShooterHood;

public class HomeHood extends CommandBase {

private AdjustabeShooterHood mAdjustabeShooterHood;

  public HomeHood(AdjustabeShooterHood subsytem) {

    mAdjustabeShooterHood = subsytem;

    addRequirements(mAdjustabeShooterHood);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    if(mAdjustabeShooterHood.getLimitState()) {
      mAdjustabeShooterHood.setAdjustableShooterHoodSpeed(0.1);
      
      if(mAdjustabeShooterHood.getLimitState() == false) {
        mAdjustabeShooterHood.setAdjustableShooterHoodSpeed(-0.1);

        if(mAdjustabeShooterHood.getLimitState()){
          mAdjustabeShooterHood.setAdjustableShooterHoodSpeed(0.0);
        }
      }
    }
    else{
      mAdjustabeShooterHood.setAdjustableShooterHoodSpeed(-0.1);

      if(mAdjustabeShooterHood.getLimitState() == false) {
        mAdjustabeShooterHood.setAdjustableShooterHoodSpeed(-0.1);

        if(mAdjustabeShooterHood.getLimitState()){
          mAdjustabeShooterHood.setAdjustableShooterHoodSpeed(0.0);
        }
      }
    }
  }

  @Override
  public void end(boolean interrupted) {
    mAdjustabeShooterHood.setAdjustableShooterHoodSpeed(0.0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}

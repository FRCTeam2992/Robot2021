// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.vision.LimeLight.LedMode;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;

public class AutoLimeLightSpeed extends CommandBase {
  private Shooter mShooter;
  private DriveTrain mDriveTrain;

  public AutoLimeLightSpeed(Shooter subsystemS, DriveTrain subsystemD) {
    mShooter = subsystemS;
    mDriveTrain = subsystemD;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    mDriveTrain.limeLightCamera.setLedMode(LedMode.On);
   
    double currentDistance = mDriveTrain.limeLightCamera.getDistanceToTarget(Constants.cameraAngle, Constants.cameraHeight, Constants.targetHeight);
    int targetSpeed = mShooter.presetSpeeds.getSetpoint(currentDistance);
    mShooter.shooterSetSpeed = targetSpeed;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    mDriveTrain.limeLightCamera.setLedMode(LedMode.Off);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

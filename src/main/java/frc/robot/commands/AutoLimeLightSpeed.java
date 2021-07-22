// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.game.year2021.PowerCellInterpolator;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;

public class AutoLimeLightSpeed extends CommandBase {

  // Subsystem Instance
  private Shooter mShooter;
  private DriveTrain mDriveTrain;

  // Saved Variables
  private PowerCellInterpolator mInterpolator;

  public AutoLimeLightSpeed(Shooter subsystemS, DriveTrain subsystemD, PowerCellInterpolator interpolator) {
    // Subsystem Instances
    mShooter = subsystemS;
    mDriveTrain = subsystemD;

    // Saved Variables
    mInterpolator = interpolator;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    if (mDriveTrain.limeLightCamera.hasTarget()) {

      double currentDistance = mDriveTrain.limeLightCamera.getDistanceToTarget(Constants.cameraAngle,
          Constants.cameraHeight, Constants.targetHeight);

      double targetSpeed = mInterpolator.calculateShooterSpeed(currentDistance,
          SmartDashboard.getNumber("powerCellDamagePercentage", 0.0));

      mShooter.shooterSetSpeed = (int) Math.round(targetSpeed);
    } else {
      mShooter.shooterSetSpeed = 3800;
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}

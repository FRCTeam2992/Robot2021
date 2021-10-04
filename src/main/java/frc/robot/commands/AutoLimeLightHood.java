// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.game.year2021.PowerCellInterpolator;
import frc.lib.vision.LimeLight.LedMode;
import frc.robot.Constants;
import frc.robot.subsystems.AdjustabeHood;
import frc.robot.subsystems.DriveTrain;

public class AutoLimeLightHood extends CommandBase {

  // Subsystem Instances
  private AdjustabeHood mAdjustabeHood;
  private DriveTrain mDriveTrain;

  // Saved Variables
  private PowerCellInterpolator mInterpolator;

  public AutoLimeLightHood(AdjustabeHood subsystemA, DriveTrain subsystemD, PowerCellInterpolator interpolator) {
    // Subsystem Instances
    mAdjustabeHood = subsystemA;
    mDriveTrain = subsystemD;

    // Set the Subsystem Requirement
    addRequirements(subsystemA);

    // Saved Variables
    mInterpolator = interpolator;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    mDriveTrain.limeLightCamera.setLedMode(LedMode.On);

    if (mDriveTrain.limeLightCamera.hasTarget()) {

      double currentDistance = mDriveTrain.limeLightCamera.getDistanceToTarget(Constants.cameraAngle,
          Constants.cameraHeight, Constants.targetHeight);

      double targetPostion = mInterpolator.calculateHoodPosition(currentDistance,
          SmartDashboard.getNumber("powerCellDamagePercentage", 0.0));

      mAdjustabeHood.setHoodPosition(targetPostion);
    } else {
      mAdjustabeHood.setHoodPosition(1.8);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    mDriveTrain.limeLightCamera.setLedMode(LedMode.Off);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.drive.swerve.SwerveModuleFalconNeo;
import frc.lib.vision.LimeLight.LedMode;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

public class AutoDriveRotate extends CommandBase {

  private DriveTrain mDriveTrain;
  private double mTargetRotation;
  private boolean mUseLimeLight;
  private double mTimeout;

  private Timer timeoutTimer;

  private int atSetpointCounter = 0;

  /** Creates a new AutoDriveRotate. */
  public AutoDriveRotate(DriveTrain subsystem, double targetRotation, boolean useLimeLight, double timeout) {
    mDriveTrain = subsystem;
    mTargetRotation = targetRotation;
    mUseLimeLight = useLimeLight;
    mTimeout = timeout;

    timeoutTimer = new Timer();

    addRequirements(mDriveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timeoutTimer.reset();
    timeoutTimer.start();

    if(mUseLimeLight) {
      mDriveTrain.limeLightCamera.setLedMode(LedMode.On);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double correctionSpeed;

    if (mUseLimeLight && mDriveTrain.limeLightCamera.hasTarget()) {
      correctionSpeed = -mDriveTrain.limeLightCamera.getTargetXOffset() * (Constants.driveAimP / 2.0);
    } else {
      // Get the Gyro Value
      double gyroValue = mDriveTrain.navx.getYaw();

      // Normalize the Target Angle (-180 - 180)
      if (mTargetRotation < -180.0) {
        mTargetRotation += 360.0;
      } else if (mTargetRotation > 180) {
        mTargetRotation -= 360.0;
      }

      // Normalize the Gyro Angle (-180 - 180)
      if (gyroValue > 180.0) {
        gyroValue -= 360;
      } else if (gyroValue < -180) {
        gyroValue += 360;
      }

      // Get the Gyro Error
      double gyroError = gyroValue - mTargetRotation;

      // Normalize the Gyro Error (-180 - 180)
      if (gyroError > 180.0) {
        gyroError -= 360.0;
      } else if (gyroError < -180.0) {
        gyroError += 360.0;
      }

      // Calculate Correction Speed
      correctionSpeed = gyroError * 0.008;
    }

    // Calculate the Swerve States
    double[] swerveStates;

    // Check for Field Centric Enabled
    if (Constants.isFieldCentric) {
      swerveStates = mDriveTrain.swerveController.calculate(0.0, 0.0, correctionSpeed, mDriveTrain.navx.getYaw());
    } else {
      swerveStates = mDriveTrain.swerveController.calculate(0.0, 0.0, correctionSpeed);
    }

    // Get the Swerve Modules
    SwerveModuleFalconNeo frontLeft = mDriveTrain.frontLeftModule;
    SwerveModuleFalconNeo frontRight = mDriveTrain.frontRightModule;
    SwerveModuleFalconNeo rearLeft = mDriveTrain.rearLeftModule;
    SwerveModuleFalconNeo rearRight = mDriveTrain.rearRightModule;

    // Command the Swerve Modules
    if (Constants.isVelocityControlled) {
      frontLeft.setDriveVelocity(swerveStates[0], swerveStates[1]);
      frontRight.setDriveVelocity(swerveStates[2], swerveStates[3]);
      rearLeft.setDriveVelocity(swerveStates[4], swerveStates[5]);
      rearRight.setDriveVelocity(swerveStates[6], swerveStates[7]);
    } else {
      frontLeft.setDrive(swerveStates[0], swerveStates[1]);
      frontRight.setDrive(swerveStates[2], swerveStates[3]);
      rearLeft.setDrive(swerveStates[4], swerveStates[5]);
      rearRight.setDrive(swerveStates[6], swerveStates[7]);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    mDriveTrain.stopDrive();

    if(mUseLimeLight) {
      mDriveTrain.limeLightCamera.setLedMode(LedMode.On);
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(mUseLimeLight) {
      if(Math.abs(mDriveTrain.limeLightCamera.getTargetXOffset()) < 1.0 & mDriveTrain.limeLightCamera.hasTarget()) {
        atSetpointCounter++;
      } else {
        atSetpointCounter = 0;
      }

      return atSetpointCounter >= 3 || timeoutTimer.get() > mTimeout;
    } else {
      if(Math.abs(mDriveTrain.navx.getYaw() - mTargetRotation) < 1.0) {
        atSetpointCounter++;
      } else {
        atSetpointCounter = 0;
      }

      return atSetpointCounter >= 3 || timeoutTimer.get() > mTimeout;
    }
  }
}

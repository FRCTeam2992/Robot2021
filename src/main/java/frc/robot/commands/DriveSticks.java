// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.drive.swerve.SwerveModuleFalconNeo;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;

public class DriveSticks extends CommandBase {

  // Subsystem Instance
  private DriveTrain mDriveTrain;

  // Command States
  private double gyroTarget;
  private boolean gyroTargetRecorded = false;

  public DriveSticks(DriveTrain subsystem) {
    // Subsystem Instance
    mDriveTrain = subsystem;

    // Set the Subsystem Requirement
    addRequirements(mDriveTrain);
  }

  // Called when the command is initially scheduled./
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Joystick Inputs (x1 = Strafe, y1 = Speed, x2 = Rotation)
    double x1 = -Robot.mRobotContainer.controller.getX(Hand.kLeft);
    double y1 = -Robot.mRobotContainer.controller.getY(Hand.kLeft);
    double x2 = -Robot.mRobotContainer.controller.smoothGetRaw(4);

    // OLD JOYSTICK SMOOTHING CODE
    // // Polar Deadband
    // if ((x1 * x1 + y1 * y1) < Constants.joystickDeadband *
    // Constants.joystickDeadband) {
    // x1 = 0.0;
    // y1 = 0.0;
    // }

    // if (Math.abs(x2) < Constants.joystickDeadband) {
    // x2 = 0.0;
    // }

    // /*
    // * // Joystick smoothing double magnitude = Math.sqrt(x1*x1 + y1*y1); double
    // * angle = Math.atan(y1/x1); magnitude = Constants.joystickSmoothFactor *
    // * Math.pow(magnitude, 3.0) + (1-Constants.joystickSmoothFactor) * magnitude;
    // x1
    // * = magnitude * Math.cos(angle); y1 = magnitude * Math.sin(angle);
    // */

    // x1 = Constants.joystickXYSmoothFactor * Math.pow(x1, 3.0) + (1 -
    // Constants.joystickXYSmoothFactor) * x1;
    // y1 = Constants.joystickXYSmoothFactor * Math.pow(y1, 3.0) + (1 -
    // Constants.joystickXYSmoothFactor) * y1;
    // x2 = Constants.joystickXYSmoothFactor * Math.pow(x2, 3.0) + (1 -
    // Constants.joystickXYSmoothFactor) * x2;

    // NEW JOYSTICK SMOOTHING CODE
    // Get the Joystick Magnitude
    double xyMagnitude = Math.sqrt((x1 * x1) + (y1 * y1));

    // Check the Magnitude Deadband
    if (xyMagnitude <= Constants.joystickDeadband) {
      x1 = 0.0;
      y1 = 0.0;
    } else {
      // Get the Polar Angle
      double xyAngle = Math.atan2(y1, x1);

      // Smooth the X and Y Axis
      double smoothedXYMagnitude = (Constants.joystickXYSmoothFactor * Math.pow(xyMagnitude, 3.0))
          + ((1.0 - Constants.joystickXYSmoothFactor) * xyMagnitude);

      // Convert from Polar to X and Y Coordinates
      x1 = smoothedXYMagnitude * Math.cos(xyAngle);
      y1 = smoothedXYMagnitude * Math.sin(xyAngle);
    }

    // Check the Rotation Deadband
    if (x2 <= Constants.joystickDeadband) {
      x2 = 0.0;
    } else {
      // Smooth the Rotation Axis
      x2 = (Constants.joystickRotationSmoothFactor * Math.pow(x2, 3.0))
          + ((1.0 - Constants.joystickRotationSmoothFactor) * x2);
    }

    // Check for Movement
    if (Math.abs(x1) > 0.0 || Math.abs(y1) > 0.0 || Math.abs(x2) > 0.0) {

      // Slow the Rotation
      x2 *= (2.0 / 3.0);

      // Check for Slow Mode
      if (Robot.mRobotContainer.controller.getBumperPressed(Hand.kLeft)) {
        x1 /= 2.0;
        y1 /= 2.0;
        x2 /= 2.0;
      }

      // Gyro Input (-180 to 180)
      double gyroValue = mDriveTrain.navx.getYaw();

      // Gyro Correction
      if (Math.abs(x2) <= 0.05 && Constants.isGyroCorrected) {

        // Check for Recorded Value
        if (gyroTargetRecorded) {
          // Get the Gyro Value
          double tempGyroValue = gyroValue;

          // Normalize the Target Angle (-180 - 180)
          if (gyroTarget < -180.0) {
            gyroTarget += 360.0;
          } else if (gyroTarget > 180) {
            gyroTarget -= 360.0;
          }

          // Normalize the Gyro Angle (-180 - 180)
          if (tempGyroValue > 180.0) {
            tempGyroValue -= 360;
          } else if (tempGyroValue < -180) {
            tempGyroValue += 360;
          }

          // Get the Gyro Error
          double gyroError = gyroTarget + tempGyroValue;

          // Normalize the Gyro Error (-180 - 180)
          if (gyroError > 180.0) {
            gyroError -= 360.0;
          } else if (gyroError < -180.0) {
            gyroError += 360.0;
          }

          // Calculate Correction Speed
          x2 = gyroError * Constants.driveGyroP;
        } else {
          // Record a Gyro Value
          gyroTarget = -gyroValue;
          gyroTargetRecorded = true;
        }
      } else {
        // Reset the Target Recorded State
        gyroTargetRecorded = false;
      }

      // Calculate the Swerve States
      double[] swerveStates;

      // Check for Field Centric Enabled
      if (Constants.isFieldCentric) {
        swerveStates = mDriveTrain.swerveController.calculate(x1, y1, x2, gyroValue);
      } else {
        swerveStates = mDriveTrain.swerveController.calculate(x1, y1, x2);
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
    } else {
      mDriveTrain.stopDrive();
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    mDriveTrain.stopDrive();
  }
}

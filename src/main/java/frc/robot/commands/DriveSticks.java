// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.drive.swerve.SwerveModuleFalconNeo;
import frc.lib.util.RollingAverage;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;

public class DriveSticks extends CommandBase {

  // Subsystem Instance
  private DriveTrain mDriveTrain;

  // Joystick Rolling Averages
  private RollingAverage averageX1;
  private RollingAverage averageY1;
  private RollingAverage averageX2;

  // Command States
  private double gyroTarget;
  private boolean gyroTargetRecorded = false;

  public DriveSticks(DriveTrain subsystem) {
    // Subsystem Instance
    mDriveTrain = subsystem;

    // Set the Subsystem Requirement
    addRequirements(mDriveTrain);

    // Joystick Rolling Averages
    averageX1 = new RollingAverage(5);
    averageY1 = new RollingAverage(5);
    averageX2 = new RollingAverage(5);
  }

  // Called when the command is initially scheduled./
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Joystick Inputs (x1 = Strafe, y1 = Speed, x2 = Rotation)
    averageX1.add(-Robot.mRobotContainer.controller.getX(Hand.kLeft));
    averageY1.add(-Robot.mRobotContainer.controller.getY(Hand.kLeft));
    averageX2.add(-Robot.mRobotContainer.controller.getRawAxis(4) * (2.0 / 3.0));

    double x1 = averageX1.getAverage();
    double y1 = averageY1.getAverage();
    double x2 = averageX2.getAverage();

    // Check for Movement
    if (Math.abs(x1) >= 0.1 || Math.abs(y1) >= 0.1 || Math.abs(x2) >= 0.1) {

      // Gyro Input (-180 to 180)
      double gyroValue = mDriveTrain.navx.getYaw();

      // Gyro Correction
      if (x2 <= 0.1 && Constants.isGyroCorrected) {
        // Check for Recorded Value
        if (gyroTargetRecorded) {
          // Calculate Correction Speed
          x2 = (gyroTarget - gyroValue) * Constants.driveGyroP;
        } else {
          // Record a Gyro Value
          gyroTarget = gyroValue;
          gyroTargetRecorded = true;
        }
      } else {
        // Reset the Target Recorded State
        gyroTargetRecorded = false;
      }

      // Calculate the Swerve States
      double[] swerveStates;

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

        SmartDashboard.putNumber("Front Left Set Speed", swerveStates[0] * Constants.swerveMaxSpeed);
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

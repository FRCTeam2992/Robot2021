// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.drive.swerve.SwerveModule;
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
    if (Math.abs(x1) >= 0.05 || Math.abs(y1) >= 0.05 || Math.abs(x2) >= 0.05) {
      // Slow Mode
      // if (Robot.mRobotContainer.controller.getBumperPressed(Hand.kLeft)) {
      // x1 /= 3;
      // y1 /= 3;
      // x2 /= 2;
      // }

      // Gyro Input (-180 to 180)
      double gyroValue = mDriveTrain.navx.getYaw();

      // Swerve Variables
      double L = Constants.swerveLength / 2;
      double W = Constants.swerveWidth / 2;
      double r = Math.sqrt((L * L) + (W * W));

      // Field Centric Code from NAVX Website
      if (Constants.isFieldCentric) {
        double gyro = gyroValue * Math.PI / 180;

        double temp = x1 * Math.cos(gyro) + y1 * Math.sin(gyro);
        y1 = -x1 * Math.sin(gyro) + y1 * Math.cos(gyro);
        x1 = temp;
      }

      // --------------------------------------
      // Swerve Module Math for Speed and Angle
      // --------------------------------------
      double a = x1 - x2 * (L / r);
      double b = x1 + x2 * (L / r);
      double c = y1 - x2 * (W / r);
      double d = y1 + x2 * (W / r);

      double frontLeftSpeed = Math.sqrt((b * b) + (c * c));
      double frontRightSpeed = Math.sqrt((b * b) + (d * d));
      double rearLeftSpeed = Math.sqrt((a * a) + (c * c));
      double rearRightSpeed = Math.sqrt((a * a) + (d * d));

      double frontLeftAngle = Math.atan2(b, c) * 180 / Math.PI;
      double frontRightAngle = Math.atan2(b, d) * 180 / Math.PI;
      double rearLeftAngle = Math.atan2(a, c) * 180 / Math.PI;
      double rearRightAngle = Math.atan2(a, d) * 180 / Math.PI;

      // -------------------------------------
      // Normalize the Speed
      // -------------------------------------
      double max = frontLeftSpeed;
      max = Math.max(max, frontRightSpeed);
      max = Math.max(max, rearLeftSpeed);
      max = Math.max(max, rearRightSpeed);

      if (max > 1) {
        frontRightSpeed /= max;
        frontLeftSpeed /= max;
        rearLeftSpeed /= max;
        rearRightSpeed /= max;
      }

      // Get the Swerve Modules
      SwerveModule frontLeft = mDriveTrain.frontLeftModule;
      SwerveModule frontRight = mDriveTrain.frontRightModule;
      SwerveModule rearLeft = mDriveTrain.rearLeftModule;
      SwerveModule rearRight = mDriveTrain.rearRightModule;

      // Command the Swerve Modules
      if (Constants.isVelocityControlled) {
        frontLeft.setDriveVelocity(frontLeftSpeed, frontLeftAngle);
        frontRight.setDriveVelocity(frontRightSpeed, frontRightAngle);
        rearLeft.setDriveVelocity(rearLeftSpeed, rearLeftAngle);
        rearRight.setDriveVelocity(rearRightSpeed, rearRightAngle);
      } else {
        frontLeft.setDrive(frontLeftSpeed, frontLeftAngle);
        frontRight.setDrive(frontRightSpeed, frontRightAngle);
        rearLeft.setDrive(rearLeftSpeed, rearLeftAngle);
        rearRight.setDrive(rearRightSpeed, rearRightAngle);
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

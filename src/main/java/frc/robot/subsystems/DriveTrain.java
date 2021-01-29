// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.kinematics.SwerveDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.SwerveDriveOdometry;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.drive.swerve.SwerveModule;

public class DriveTrain extends SubsystemBase {

  //Drive Motor
  private final CANSparkMax frontLeftDrive;
  private final CANSparkMax frontLeftTurn;

  private final CANSparkMax frontRightDrive;
  private final CANSparkMax frontRightTurn;

  private final CANSparkMax rearLeftDrive;
  private final CANSparkMax rearLeftTurn;

  private final CANSparkMax rearRightDrive;
  private final CANSparkMax rearRightTurn;

  //Drive Encoders
  private final AnalogInput frontLeftEncoder;
  private final AnalogInput frontRightEncoder;
  private final AnalogInput rearLeftEncoder;
  private final AnalogInput rearRightEncoder;

  //Turn PID controller
  private final PIDController frontLeftController; 
  private final PIDController frontRightController;
  private final PIDController rearLeftController;
  private final PIDController rearRightController;

  //Swerve Modules
  private final SwerveModule frontLeftModule;
  private final SwerveModule frontRightModule;
  private final SwerveModule rearLeftModule;
  private final SwerveModule rearRightModule;

  //Robot gyro
  public AHRS navx;

  //Swerve drive kinematics
  public final SwerveDriveKinematics swerveDriveKinematics;

  //Swerve drive odometry
  public final SwerveDriveOdometry swerveDriveOdometry;

  //swerve pose
  public Pose2d latestSwervPose;


  /** Creates a new DriveTrain. */
  public DriveTrain() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project....

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.SwerveDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.SwerveDriveOdometry;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.drive.swerve.SwerveModule;
import frc.robot.Constants;

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
    //Drive motors
    //Front Left
    frontLeftDrive = new CANSparkMax(1, MotorType.kBrushless);
    frontLeftDrive.setInverted(false);
    frontLeftDrive.setIdleMode(IdleMode.kCoast);
    frontLeftDrive.setSmartCurrentLimit(30);
    frontLeftDrive.setOpenLoopRampRate(0.2);

    frontLeftTurn = new CANSparkMax(2, MotorType.kBrushless);
    frontLeftTurn.setInverted(false);
    frontLeftTurn.setIdleMode(IdleMode.kCoast);
    frontLeftTurn.setSmartCurrentLimit(30);

    //Front Right
    frontRightDrive = new CANSparkMax(3, MotorType.kBrushless);
    frontRightDrive.setInverted(false);
    frontRightDrive.setIdleMode(IdleMode.kCoast);
    frontRightDrive.setSmartCurrentLimit(30);
    frontRightDrive.setOpenLoopRampRate(0.2);

    frontRightTurn = new CANSparkMax(4, MotorType.kBrushless);
    frontRightTurn.setInverted(false);
    frontRightTurn.setIdleMode(IdleMode.kCoast);
    frontRightTurn.setSmartCurrentLimit(30);

    //Rear Left
    rearLeftDrive = new CANSparkMax(5, MotorType.kBrushless);
    rearLeftDrive.setInverted(false);
    rearLeftDrive.setIdleMode(IdleMode.kCoast);
    rearLeftDrive.setSmartCurrentLimit(30);
    rearLeftDrive.setOpenLoopRampRate(0.2);

    rearLeftTurn = new CANSparkMax(6, MotorType.kBrushless);
    rearLeftTurn.setInverted(false);
    rearLeftTurn.setIdleMode(IdleMode.kCoast);
    rearLeftTurn.setSmartCurrentLimit(30);

    //Rear Right
    rearRightDrive = new CANSparkMax(7, MotorType.kBrushless);
    rearRightDrive.setInverted(false);
    rearRightDrive.setIdleMode(IdleMode.kCoast);
    rearRightDrive.setSmartCurrentLimit(30);
    rearRightDrive.setOpenLoopRampRate(0.2);
  
    rearRightTurn = new CANSparkMax(4, MotorType.kBrushless);
    rearRightTurn.setInverted(false);
    rearRightTurn.setIdleMode(IdleMode.kCoast);
    rearRightTurn.setSmartCurrentLimit(30);

    //Drive Encoders
    frontLeftEncoder = new AnalogInput(0);
    frontRightEncoder = new AnalogInput(1);
    rearLeftEncoder = new AnalogInput(2);
    rearRightEncoder = new AnalogInput(3);

    //Turn PID controller
    frontLeftController = new PIDController(Constants.turnP, Constants.turnI, Constants.turnD);
    frontLeftController.enableContinuousInput(-180.0, 180.0);

    frontRightController = new PIDController(Constants.turnP, Constants.turnI, Constants.turnD);
    frontRightController.enableContinuousInput(-180.0, 180.0);

    rearLeftController = new PIDController(Constants.turnP, Constants.turnI, Constants.turnD);
    rearLeftController.enableContinuousInput(-180.0, 180.0);

    rearRightController = new PIDController(Constants.turnP, Constants.turnI, Constants.turnD);
    rearRightController.enableContinuousInput(-180.0, 180.0);

    //Set drive pid controllers
    CANPIDController frontLeftDrivePID = frontLeftDrive.getPIDController();
    frontLeftDrivePID.setP(Constants.driveP);
    frontLeftDrivePID.setI(Constants.driveI);
    frontLeftDrivePID.setD(Constants.driveD);
    frontLeftDrivePID.setFF(Constants.driveF);

    CANPIDController frontRightDrivePID = frontRightDrive.getPIDController();
    frontRightDrivePID.setP(Constants.driveP);
    frontRightDrivePID.setI(Constants.driveI);
    frontRightDrivePID.setD(Constants.driveD);
    frontRightDrivePID.setFF(Constants.driveF);

    CANPIDController rearLeftDrivePID = rearLeftDrive.getPIDController();
    rearLeftDrivePID.setP(Constants.driveP);
    rearLeftDrivePID.setI(Constants.driveI);
    rearLeftDrivePID.setD(Constants.driveD);
    rearLeftDrivePID.setFF(Constants.driveF);

    CANPIDController rearRightDrivePID = rearRightDrive.getPIDController();
    rearRightDrivePID.setP(Constants.driveP);
    rearRightDrivePID.setI(Constants.driveI);
    rearRightDrivePID.setD(Constants.driveD);
    rearRightDrivePID.setFF(Constants.driveF);

    //Swerve Modules
    frontLeftModule = new SwerveModule(frontLeftDrive, frontLeftTurn, frontLeftEncoder, Constants.frontLeftOffset, frontLeftController, Constants.driveWheelDiameter, Constants.driveGearRatio, Constants.swerveMaxSpeed);

    frontRightModule = new SwerveModule(frontRightDrive, frontRightTurn, frontRightEncoder, Constants.frontRightOffset, frontRightController, Constants.driveWheelDiameter, Constants.driveGearRatio, Constants.swerveMaxSpeed);

    rearLeftModule = new SwerveModule(rearLeftDrive, frontLeftTurn, rearLeftEncoder, Constants.rearLeftOffset, rearLeftController, Constants.driveWheelDiameter, Constants.driveGearRatio, Constants.swerveMaxSpeed);

    rearRightModule = new SwerveModule(rearRightDrive, frontRightTurn, rearRightEncoder, Constants.rearRightOffset, rearRightController, Constants.driveWheelDiameter, Constants.driveGearRatio, Constants.swerveMaxSpeed);

    //Robot Gyro
    navx = new AHRS(SPI.port.kMXP);

    // Swerve Drive Kinematics
    swerveDriveKinematics = new SwerveDriveKinematics(Constants.frontLeftLocation,
    Constants.frontRightLocation, Constants.rearLeftLocation,
    Constants.rearRightLocation);

    // Serve Drive Odometry
    swerveDriveOdometry = new SwerveDriveOdometry(swerveDriveKinematics,
    Rotation2d.fromDegrees(-navx.getYaw()), new Pose2d(0.0, 0.0, new Rotation2d()));

  }

  @Override
  public void periodic() {
   // Display Encoder Angle\
   SmartDashboard.putNumber("Front Left Encoder Angle", frontLeftModule.getEncoderAngle());
   SmartDashboard.putNumber("Front Right Encoder Angle", frontRightModule.getEncoderAngle());
   SmartDashboard.putNumber("Rear Left Encoder Angle", rearLeftModule.getEncoderAngle());
   SmartDashboard.putNumber("Rear Right Encoder Angle", rearRightModule.getEncoderAngle());

   // Display Wheel Velocities
   SmartDashboard.putNumber("Front Left Velocity", frontLeftModule.getWheelSpeedMeters());
   SmartDashboard.putNumber("Front Right Velocity", frontRightModule.getWheelSpeedMeters());
   SmartDashboard.putNumber("Rear Left Velocity", rearLeftModule.getWheelSpeedMeters());
   SmartDashboard.putNumber("Rear Right Velocity", rearRightModule.getWheelSpeedMeters());

  // Display Gyro Angle
  SmartDashboard.putNumber("Gyro Yaw", navx.getYaw());

  // Update the Odometry
  latestSwervPose = swerveDriveOdometry.update(Rotation2d.fromDegrees(-navx.getYaw()), frontLeftModule.getState(), frontRightModule.getState(), rearLeftModule.getState(), rearRightModule.getState());

  // Display Odometry
  SmartDashboard.putNumber("Odometry Rotation", latestSwervPose.getRotation().getDegrees());
  SmartDashboard.putNumber("Odometry X", latestSwervPose.getX());
  SmartDashboard.putNumber("Odometry Y", latestSwervPose.getY());
  }

  public void setTurnIdelMode(IdleMode mode) {
    frontLeftTurn.setIdleMode(mode);
    frontRightTurn.setIdleMode(mode);
    rearLeftTurn.setIdleMode(mode);
    rearRightTurn.setIdleMode(mode);
  }

  public void setDriveIdelMode(IdleMode mode) {
    frontLeftDrive.setIdleMode(mode);
    frontRightDrive.setIdleMode(mode);
    rearLeftDrive.setIdleMode(mode);
    rearRightDrive.setIdleMode(mode);
  }
  public void stopDrive() {
    frontLeftModule.stop();
    frontRightModule.stop();
    rearLeftModule.stop();
    rearRightModule.stop();
  }
  public void resetOdometry() {
    swerveDriveOdometry.resetPosition(new Pose2d(0.0, 0.0, new Rotation2d()), Rotation2d.fromDegrees(-navx.getYaw()));
  }
}
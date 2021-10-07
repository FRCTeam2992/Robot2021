// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project....

package frc.robot.subsystems;

import java.io.IOException;
import java.nio.file.Path;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.SwerveDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.SwerveDriveOdometry;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.drive.swerve.SwerveController;
import frc.lib.drive.swerve.SwerveModuleFalconNeo;
import frc.lib.vision.LimeLight;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {

  // Drive Motors
  private final TalonFX frontLeftDrive;
  private final CANSparkMax frontLeftTurn;

  private final TalonFX frontRightDrive;
  private final CANSparkMax frontRightTurn;

  private final TalonFX rearLeftDrive;
  private final CANSparkMax rearLeftTurn;

  private final TalonFX rearRightDrive;
  private final CANSparkMax rearRightTurn;

  // Module Angle Encoders
  private final AnalogInput frontLeftEncoder;
  private final AnalogInput frontRightEncoder;
  private final AnalogInput rearLeftEncoder;
  private final AnalogInput rearRightEncoder;

  // Turn PID Controllers
  private final PIDController frontLeftController;
  private final PIDController frontRightController;
  private final PIDController rearLeftController;
  private final PIDController rearRightController;

  // Swerve Modules
  public final SwerveModuleFalconNeo frontLeftModule;
  public final SwerveModuleFalconNeo frontRightModule;
  public final SwerveModuleFalconNeo rearLeftModule;
  public final SwerveModuleFalconNeo rearRightModule;

  // Swerve Controller
  public final SwerveController swerveController;

  // Robot Gyro
  public AHRS navx;

  // Swerve Drive Kinematics
  public final SwerveDriveKinematics swerveDriveKinematics;

  // Swerve Drive Odometry
  public final SwerveDriveOdometry swerveDriveOdometry;

  // Swerve Pose
  public Pose2d latestSwervePose;

  // Motion Trajectories
  public Trajectory SlalomTrajectory;
  public Trajectory BarrelTrajectory;
  public Trajectory BounceTrajectory;
  public Trajectory GalacticSearchARedTrajectory;
  public Trajectory GalacticSearchABlueTrajectory;
  public Trajectory GalacticSearchBRedTrajectory;
  public Trajectory GalacticSearchBBlueTrajectory;
  public Trajectory PowerPortForward;
  public Trajectory PowerPortBackward;
  public Trajectory TeamNumberPath;
  public Trajectory CenterTrenchFiveTrajectory;
  public Trajectory RightTrenchFiveTrajectory;
  public Trajectory CenterTrenchThreeTrajectory;
  public Trajectory RightTrenchThreeTrajectory;
  public Trajectory CenterShieldGeneratorTrajectory;

  // Limelight Camera
  public final LimeLight limeLightCamera;

  // DriveTrain Dashboard Update Counter
  private int dashboardCounter = 0;

  public DriveTrain() {
    // Drive Motors
    frontLeftDrive = new TalonFX(1);
    frontLeftDrive.setInverted(false);
    frontLeftDrive.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 30, 40, 0.25));

    frontLeftTurn = new CANSparkMax(2, MotorType.kBrushless);
    frontLeftTurn.setInverted(false);
    frontLeftTurn.setSmartCurrentLimit(30);

    frontRightDrive = new TalonFX(3);
    frontRightDrive.setInverted(false);
    frontRightDrive.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 30, 40, 0.25));

    frontRightTurn = new CANSparkMax(4, MotorType.kBrushless);
    frontRightTurn.setInverted(false);
    frontRightTurn.setSmartCurrentLimit(30);

    rearLeftDrive = new TalonFX(5);
    rearLeftDrive.setInverted(false);
    rearLeftDrive.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 30, 40, 0.25));

    rearLeftTurn = new CANSparkMax(6, MotorType.kBrushless);
    rearLeftTurn.setInverted(false);
    rearLeftTurn.setSmartCurrentLimit(30);

    rearRightDrive = new TalonFX(7);
    rearRightDrive.setInverted(false);
    rearRightDrive.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 30, 40, 0.25));

    rearRightTurn = new CANSparkMax(8, MotorType.kBrushless);
    rearRightTurn.setInverted(false);
    rearRightTurn.setSmartCurrentLimit(30);

    // Config the Drive Motors
    setDriveNeutralMode(NeutralMode.Coast);
    setDriveCurrentLimit(60.0);
    setDriveRampRate(0.0);

    // Config the Turn Motors
    setTurnIdleMode(IdleMode.kCoast);

    // Drive Encoders
    frontLeftEncoder = new AnalogInput(0);
    frontRightEncoder = new AnalogInput(1);
    rearLeftEncoder = new AnalogInput(2);
    rearRightEncoder = new AnalogInput(3);

    // Turn PID Controllers
    frontLeftController = new PIDController(Constants.turnP, Constants.turnI, Constants.turnD);
    frontLeftController.enableContinuousInput(-180.0, 180.0);

    frontRightController = new PIDController(Constants.turnP, Constants.turnI, Constants.turnD);
    frontRightController.enableContinuousInput(-180.0, 180.0);

    rearLeftController = new PIDController(Constants.turnP, Constants.turnI, Constants.turnD);
    rearLeftController.enableContinuousInput(-180.0, 180.0);

    rearRightController = new PIDController(Constants.turnP, Constants.turnI, Constants.turnD);
    rearRightController.enableContinuousInput(-180.0, 180.0);

    // Set the Drive PID Controllers
    frontLeftDrive.config_kP(0, Constants.driveP);
    frontLeftDrive.config_kI(0, Constants.driveI);
    frontLeftDrive.config_kD(0, Constants.driveD);
    frontLeftDrive.config_kF(0, Constants.driveF);

    frontRightDrive.config_kP(0, Constants.driveP);
    frontRightDrive.config_kI(0, Constants.driveI);
    frontRightDrive.config_kD(0, Constants.driveD);
    frontRightDrive.config_kF(0, Constants.driveF);

    rearLeftDrive.config_kP(0, Constants.driveP);
    rearLeftDrive.config_kI(0, Constants.driveI);
    rearLeftDrive.config_kD(0, Constants.driveD);
    rearLeftDrive.config_kF(0, Constants.driveF);

    rearRightDrive.config_kP(0, Constants.driveP);
    rearRightDrive.config_kI(0, Constants.driveI);
    rearRightDrive.config_kD(0, Constants.driveD);
    rearRightDrive.config_kF(0, Constants.driveF);

    // Swerve Modules
    frontLeftModule = new SwerveModuleFalconNeo(frontLeftDrive, frontLeftTurn, frontLeftEncoder,
        Constants.frontLeftOffset, frontLeftController, Constants.driveWheelDiameter, Constants.driveGearRatio,
        Constants.swerveMaxSpeed);

    frontRightModule = new SwerveModuleFalconNeo(frontRightDrive, frontRightTurn, frontRightEncoder,
        Constants.frontRightOffset, frontRightController, Constants.driveWheelDiameter, Constants.driveGearRatio,
        Constants.swerveMaxSpeed);

    rearLeftModule = new SwerveModuleFalconNeo(rearLeftDrive, rearLeftTurn, rearLeftEncoder, Constants.rearLeftOffset,
        rearLeftController, Constants.driveWheelDiameter, Constants.driveGearRatio, Constants.swerveMaxSpeed);

    rearRightModule = new SwerveModuleFalconNeo(rearRightDrive, rearRightTurn, rearRightEncoder,
        Constants.rearRightOffset, rearRightController, Constants.driveWheelDiameter, Constants.driveGearRatio,
        Constants.swerveMaxSpeed);

    // Swerve Controller
    swerveController = new SwerveController(Constants.swerveLength, Constants.swerveWidth);

    // Robot Gyro
    navx = new AHRS(SPI.Port.kMXP);

    // Swerve Drive Kinematics
    swerveDriveKinematics = new SwerveDriveKinematics(Constants.frontLeftLocation, Constants.frontRightLocation,
        Constants.rearLeftLocation, Constants.rearRightLocation);

    // Serve Drive Odometry
    swerveDriveOdometry = new SwerveDriveOdometry(swerveDriveKinematics, Rotation2d.fromDegrees(-navx.getYaw()),
        new Pose2d(0.0, 0.0, new Rotation2d()));

    // Motion Trajectories
    loadMotionPaths();

    // LimeLight Camera
    limeLightCamera = new LimeLight();
  }

  @Override
  public void periodic() {
    // Display Module Angles
    SmartDashboard.putNumber("Front Left Module Angle",
    frontLeftModule.getEncoderAngle());
    SmartDashboard.putNumber("Front Right Module Angle",
    frontRightModule.getEncoderAngle());
    SmartDashboard.putNumber("Rear Left Module Angle",
    rearLeftModule.getEncoderAngle());
    SmartDashboard.putNumber("Rear Right Module Angle",
    rearRightModule.getEncoderAngle());

    // Display Wheel Velocities
    // SmartDashboard.putNumber("Front Left Module Velocity",
    // frontLeftModule.getWheelSpeedMeters());
    // SmartDashboard.putNumber("Front Right Module Velocity",
    // frontRightModule.getWheelSpeedMeters());
    // SmartDashboard.putNumber("Rear Left Module Velocity",
    // rearLeftModule.getWheelSpeedMeters());
    // SmartDashboard.putNumber("Rear Right Module Velocity",
    // rearRightModule.getWheelSpeedMeters());

    SmartDashboard.putNumber("fl turn", frontLeftTurn.getEncoder().getPosition());
    SmartDashboard.putNumber("rl turn", rearLeftTurn.getEncoder().getPosition());
    SmartDashboard.putNumber("fr turn", frontRightTurn.getEncoder().getPosition());
    SmartDashboard.putNumber("rr turn", rearRightTurn.getEncoder().getPosition());
    
    // DriveTrain Dashboard Update
    if (dashboardCounter >= 5) {
      // Display LimeLight Distance to Target
      SmartDashboard.putNumber("Limelight Distance",
          limeLightCamera.getDistanceToTarget(Constants.cameraAngle, Constants.cameraHeight, Constants.targetHeight));

      // Display Gyro Angle
      SmartDashboard.putNumber("Gyro Yaw", navx.getYaw());

      dashboardCounter = 0;
    } else {
      dashboardCounter++;
    }
    // Display Gyro Angle
    SmartDashboard.putNumber("Gyro Yaw", navx.getYaw());

    // Update the Odometry
    latestSwervePose = swerveDriveOdometry.update(Rotation2d.fromDegrees(-navx.getYaw()), frontLeftModule.getState(),
        frontRightModule.getState(), rearLeftModule.getState(), rearRightModule.getState());

    // Display Odometry
    // SmartDashboard.putNumber("Odometry Rotation",
    // latestSwervePose.getRotation().getDegrees());
    // SmartDashboard.putNumber("Odometry X", latestSwervePose.getX());
    // SmartDashboard.putNumber("Odometry Y", latestSwervePose.getY());
  }

  public void setTurnIdleMode(IdleMode mode) {
    frontLeftTurn.setIdleMode(mode);
    frontRightTurn.setIdleMode(mode);
    rearLeftTurn.setIdleMode(mode);
    rearRightTurn.setIdleMode(mode);
  }

  public void setDriveNeutralMode(NeutralMode mode) {
    frontLeftDrive.setNeutralMode(mode);
    frontRightDrive.setNeutralMode(mode);
    rearLeftDrive.setNeutralMode(mode);
    rearRightDrive.setNeutralMode(mode);
  }

  public void setDriveCurrentLimit(double current) {
    frontLeftDrive.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, current, current, 0));
    frontRightDrive.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, current, current, 0));
    rearLeftDrive.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, current, current, 0));
    rearRightDrive.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, current, current, 0));
  }

  public void setDriveRampRate(double seconds) {
    // Open Loop Ramp
    frontLeftDrive.configOpenloopRamp(seconds);
    frontRightDrive.configOpenloopRamp(seconds);
    rearLeftDrive.configOpenloopRamp(seconds);
    rearRightDrive.configOpenloopRamp(seconds);

    // Closed Loop Ramp
    frontLeftDrive.configClosedloopRamp(seconds);
    frontRightDrive.configClosedloopRamp(seconds);
    rearLeftDrive.configClosedloopRamp(seconds);
    rearRightDrive.configClosedloopRamp(seconds);
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

  public void setOdometryPosition(Pose2d position) {
    swerveDriveOdometry.resetPosition(position, Rotation2d.fromDegrees(-navx.getYaw()));
  }

  private void loadMotionPaths() {
    // Trajectory Paths
    Path slalomPath = Filesystem.getDeployDirectory().toPath().resolve("output/Slalom.wpilib.json");
    Path barrelPath = Filesystem.getDeployDirectory().toPath().resolve("output/Barrel.wpilib.json");
    Path bouncePath = Filesystem.getDeployDirectory().toPath().resolve("output/Bounce.wpilib.json");
    Path galacticSearchARedPath = Filesystem.getDeployDirectory().toPath()
        .resolve("output/GalacticSearchARed.wpilib.json");
    Path galacticSearchABluePath = Filesystem.getDeployDirectory().toPath()
        .resolve("output/GalacticSearchABlue.wpilib.json");
    Path galacticSearchBRedPath = Filesystem.getDeployDirectory().toPath()
        .resolve("output/GalacticSearchBRed.wpilib.json");
    Path galacticSearchBBluePath = Filesystem.getDeployDirectory().toPath()
        .resolve("output/GalacticSearchBBlue.wpilib.json");
    Path powerPortForwardPath = Filesystem.getDeployDirectory().toPath().resolve("output/PowerPortForward.wpilib.json");
    Path powerPortBackwardPath = Filesystem.getDeployDirectory().toPath()
        .resolve("output/PowerPortBackward.wpilib.json");
    Path teamNumerPath = Filesystem.getDeployDirectory().toPath().resolve("output/2992LetterPath.wpilib.json");
    Path centerTrenchFivePath = Filesystem.getDeployDirectory().toPath().resolve("output/CenterTrenchFive.wpilib.json");
    Path rightTrenchFivePath = Filesystem.getDeployDirectory().toPath().resolve("output/RightTrenchFive.wpilib.json");
    Path centerTrenchThreePath = Filesystem.getDeployDirectory().toPath()
        .resolve("output/CenterTrenchThree.wpilib.json");
    Path rightTrenchThreePath = Filesystem.getDeployDirectory().toPath().resolve("output/RightTrenchThree.wpilib.json");
    Path centerShieldGeneratorPath = Filesystem.getDeployDirectory().toPath()
        .resolve("output/CenterShieldGenerator.wpilib.json");

    try {
      SlalomTrajectory = TrajectoryUtil.fromPathweaverJson(slalomPath);
      BarrelTrajectory = TrajectoryUtil.fromPathweaverJson(barrelPath);
      BounceTrajectory = TrajectoryUtil.fromPathweaverJson(bouncePath);
      GalacticSearchARedTrajectory = TrajectoryUtil.fromPathweaverJson(galacticSearchARedPath);
      GalacticSearchABlueTrajectory = TrajectoryUtil.fromPathweaverJson(galacticSearchABluePath);
      GalacticSearchBRedTrajectory = TrajectoryUtil.fromPathweaverJson(galacticSearchBRedPath);
      GalacticSearchBBlueTrajectory = TrajectoryUtil.fromPathweaverJson(galacticSearchBBluePath);
      PowerPortForward = TrajectoryUtil.fromPathweaverJson(powerPortForwardPath);
      PowerPortBackward = TrajectoryUtil.fromPathweaverJson(powerPortBackwardPath);
      TeamNumberPath = TrajectoryUtil.fromPathweaverJson(teamNumerPath);
      CenterTrenchFiveTrajectory = TrajectoryUtil.fromPathweaverJson(centerTrenchFivePath);
      RightTrenchFiveTrajectory = TrajectoryUtil.fromPathweaverJson(rightTrenchFivePath);
      CenterTrenchThreeTrajectory = TrajectoryUtil.fromPathweaverJson(centerTrenchThreePath);
      RightTrenchThreeTrajectory = TrajectoryUtil.fromPathweaverJson(rightTrenchThreePath);
      CenterShieldGeneratorTrajectory = TrajectoryUtil.fromPathweaverJson(centerShieldGeneratorPath);
    } catch (IOException e) {
      DriverStation.reportError("Unable to load motion trajectories!", e.getStackTrace());
      e.printStackTrace();
    }
     
  }
}
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.HolonomicDriveController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.drive.swerve.trajectory.SwerveTrajectory;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

public class AutoFollowPath extends CommandBase {

  // Subsystem Instance
  private DriveTrain mDriveTrain;

  // Trajectory Instance
  private SwerveTrajectory mSwerveyTrajectory;
  private Trajectory mTrajectory;

  // Drive Controller Instance
  private HolonomicDriveController controller;

  // Timer
  private Timer elapsedTimer;

  public AutoFollowPath(DriveTrain subsystem, SwerveTrajectory swerveTrajectory) {
    // Subsystem Instance
    mDriveTrain = subsystem;

    // Set the Subsystem Requirement
    addRequirements(mDriveTrain);

    // Save the Swerve Trajectory
    mSwerveyTrajectory = swerveTrajectory;

    // Get the Trajectory
    mTrajectory = mSwerveyTrajectory.getTrajectory();

    // Initialize the Drive Controller
    controller = new HolonomicDriveController(
        new PIDController(Constants.xCorrectionP, Constants.xCorrectionI, Constants.xCorrectionD),
        new PIDController(Constants.yCorrectionP, Constants.yCorrectionI, Constants.yCorrectionD),
        new ProfiledPIDController(Constants.thetaCorrectionP, Constants.thetaCorrectionI, Constants.thetaCorrectionD,
            new TrapezoidProfile.Constraints(Constants.maxThetaVelocity, Constants.maxThetaAcceleration)));

    // Timer
    elapsedTimer = new Timer();
  }

  /**
   * Wrapper for constructor that adds a NavX Yaw gyro bias for a nonzero robot setup position
   * @param theSubsystem
   * @param theSwerveTrajectory
   * @param gyroBias
   */
  public AutoFollowPath(DriveTrain theSubsystem, SwerveTrajectory theSwerveTrajectory, double gyroBias) {
    this(theSubsystem, theSwerveTrajectory);
    mDriveTrain.navx.setBias(gyroBias); // Set the gyrobias to account for starting orientation of this path
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // Set the Swerve Odometry Position to the Trajectory Start Position
    Pose2d trajectoryStartPose = mTrajectory.getInitialPose();
    mDriveTrain.setOdometryPosition(new Pose2d(trajectoryStartPose.getX(), trajectoryStartPose.getY(), Rotation2d
        .fromDegrees(mSwerveyTrajectory.getDesiredHeading(0.0, mDriveTrain.latestSwervePose.getTranslation()))));

    // Reset and Start the Elapsed Timer
    elapsedTimer.reset();
    elapsedTimer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Get the Current Time
    double currentTime = elapsedTimer.get();

    // Get the Latest State
    Trajectory.State latestState = mTrajectory.sample(currentTime);

    // Get the Desired Heading
    double heading = mSwerveyTrajectory.getDesiredHeading(currentTime, mDriveTrain.latestSwervePose.getTranslation());

    // Get the Ajusted Speeds
    ChassisSpeeds adjustSpeeds = controller.calculate(mDriveTrain.latestSwervePose, latestState,
        Rotation2d.fromDegrees(heading));

    // Get the Module States
    SwerveModuleState[] moduleStates = mDriveTrain.swerveDriveKinematics.toSwerveModuleStates(adjustSpeeds);

    // Set the Module States
    mDriveTrain.frontLeftModule.setState(moduleStates[0]);
    mDriveTrain.frontRightModule.setState(moduleStates[1]);
    mDriveTrain.rearLeftModule.setState(moduleStates[2]);
    mDriveTrain.rearRightModule.setState(moduleStates[3]);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    mDriveTrain.stopDrive();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return elapsedTimer.get() >= mTrajectory.getTotalTimeSeconds();
  }
}
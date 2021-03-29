/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.HomeAdjustableHood;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  // Saved Instances
  public static RobotContainer mRobotContainer;

  // Adjustable Hood Disabled State
  private double lastHoodPosition = -1.0;

  // Auto Instances
  private Command autoCommand;

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    /// Initialize the Robot Container
    mRobotContainer = new RobotContainer();
  }

  /**
   * This function is run every robot packet, no matter the mode.
   */
  @Override
  public void robotPeriodic() {
    // Run the Scheduler
    CommandScheduler.getInstance().run();
  }

  /**
   * Initialize Function for the Disabled Mode
   */
  @Override
  public void disabledInit() {
    // Set the Drive Train to Coast
    mRobotContainer.mDriveTrain.setDriveNeutralMode(NeutralMode.Coast);
    mRobotContainer.mDriveTrain.setTurnIdleMode(IdleMode.kCoast);

    // Save the Last Hood Position
    lastHoodPosition = mRobotContainer.mAdjustabeHood.getHoodPosition();
  }

  /**
   * Periodic Function for the Disabled Mode
   */
  @Override
  public void disabledPeriodic() {

  }

  /**
   * Initialize Function for the Autonomous Mode
   */
  @Override
  public void autonomousInit() {
    // Set the Drive Train to Brake
    mRobotContainer.mDriveTrain.setDriveNeutralMode(NeutralMode.Brake);
    mRobotContainer.mDriveTrain.setTurnIdleMode(IdleMode.kBrake);

    // Set the Drive Motors Current Limit
    mRobotContainer.mDriveTrain.setDriveCurrentLimit(60.0);

    // Set the Drive Motors Ramp Rate
    mRobotContainer.mDriveTrain.setDriveRampRate(0.0);

    // Reset the Gyro
    mRobotContainer.mDriveTrain.navx.zeroYaw();

    // Reset the Odometry
    mRobotContainer.mDriveTrain.resetOdometry();

    // Home the Ajustable Hood
    runHoodHome();

    // Get the Autonomous Command
    autoCommand = mRobotContainer.getAutoCommand();

    // Run the Auto Command
    if (autoCommand != null) {
      autoCommand.schedule();
    }
  }

  /**
   * Periodic Function for the Autonomous Mode
   */
  @Override
  public void autonomousPeriodic() {
  }

  /**
   * Initialize Function for the Teleop Mode
   */
  @Override
  public void teleopInit() {
    // Set the Drive Train to Brake
    mRobotContainer.mDriveTrain.setDriveNeutralMode(NeutralMode.Brake);
    mRobotContainer.mDriveTrain.setTurnIdleMode(IdleMode.kBrake);

    // Set the Drive Motors Current Limit
    mRobotContainer.mDriveTrain.setDriveCurrentLimit(40.0);

    // Set the Drive Motors Ramp Rate
    mRobotContainer.mDriveTrain.setDriveRampRate(0.25);

    // Reset the Gyro
    mRobotContainer.mDriveTrain.navx.zeroYaw();

    // Reset the Odometry
    mRobotContainer.mDriveTrain.resetOdometry();

    // Home the Ajustable Hood
    runHoodHome();

    // Cancel the Auto Command
    if (autoCommand != null) {
      autoCommand.cancel();
    }
  }

  /**
   * Periodic Function for the Teleop Mode
   */
  @Override
  public void teleopPeriodic() {

  }

  /**
   * Initialize Function for the Test Mode
   */
  @Override
  public void testInit() {
    // Cancel the Running Commands
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * Periodic Function for the Test Mode
   */
  @Override
  public void testPeriodic() {

  }

  public void runHoodHome() {
    if (!mRobotContainer.mAdjustabeHood.isHomed
        || lastHoodPosition != mRobotContainer.mAdjustabeHood.getHoodPosition()) {
      new HomeAdjustableHood(mRobotContainer.mAdjustabeHood).schedule(false);
    }
  }
}

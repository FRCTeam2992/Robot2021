/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.lib.oi.mhController;
import frc.robot.commands.*;
import frc.robot.paths.SlalomPath;
import frc.robot.paths.StraightPath;
import frc.robot.subsystems.*;

public class RobotContainer {

  // Subsystem Instances
  public final DriveTrain mDriveTrain;
  private final Intake mIntake;
  private final Spindexer mSpindexer;

  // Subsytem Instances (Disabled)
  // private final Turret mTurret;
  // private final ClimbSlide mClimeSlide;
  // private final TelescopeClimb mTelescopeClimb;
  // private final ColorWheel mColorWheel;
  // private final Shooter mShooter;
  // private final Ejector mEjector;
  // private final AdjustabeShooterHood mAdjustabeShooterHood;

  // Controllers
  public mhController controller;

  public RobotContainer() {

    // Subsystem Instances
    mDriveTrain = new DriveTrain();
    mDriveTrain.setDefaultCommand(new DriveSticks(mDriveTrain));

    mIntake = new Intake();
    mIntake.setDefaultCommand(new StopIntake(mIntake));

    mSpindexer = new Spindexer();
    mSpindexer.setDefaultCommand(new StopSpindexer(mSpindexer));

    // Subsystem Instances (Disabled)
    // mTurret = new Turret();
    // mTurret.setDefaultCommand(new StopTurret(mTurret));

    // mClimeSlide = new ClimbSlide();
    // mClimeSlide.setDefaultCommand(new StopClimbSlide(mClimeSlide));

    // mColorWheel = new ColorWheel();
    // mColorWheel.setDefaultCommand(new StopColorWheel(mColorWheel));

    // mTelescopeClimb = new TelescopeClimb();
    // mTelescopeClimb.setDefaultCommand(new StopTelescopeClimb(mTelescopeClimb));

    // mAdjustabeShooterHood = new AdjustabeShooterHood();
    // mAdjustabeShooterHood.setDefaultCommand(new
    // StopAdjustableShooterHood(mAdjustabeShooterHood));

    // mShooter = new Shooter();
    // mShooter.setDefaultCommand(new StopShooter(mShooter));

    // mEjector = new Ejector();
    // mEjector.setDefaultCommand(new StopEjector(mEjector));

    SmartDashboard.putData("Set Swerve 0", new SetSwerveAngle(mDriveTrain, 0));
    SmartDashboard.putData("Set Swerve 90", new SetSwerveAngle(mDriveTrain, 90));

    // SmartDashboard Auto Paths
    SmartDashboard.putData("Straight Path",
        new AutoFollowPath(mDriveTrain, new StraightPath().generateSwerveTrajectory()));

    SmartDashboard.putData("Slalom Path",
        new AutoFollowPath(mDriveTrain, new SlalomPath(mDriveTrain).generateSwerveTrajectory()));

    // Initialize the Controller
    controller = new mhController(0);

    // Configure the Buttons
    configureButtonBindings();
  }

  private void configureButtonBindings() {

  }
}

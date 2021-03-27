/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.lib.oi.mhController;
import frc.robot.commands.*;
import frc.robot.commands.autos.GalacticSearchSelector;
import frc.robot.commands.groups.AutoIntake;
import frc.robot.commands.groups.AutoOverride;
import frc.robot.commands.groups.AutoShoot;
import frc.robot.paths.BarrelPath;
import frc.robot.paths.BouncePath;
import frc.robot.paths.SlalomPath;
import frc.robot.paths.StraightPath;
import frc.robot.subsystems.*;

public class RobotContainer {

  // Subsystem Instances
  public final DriveTrain mDriveTrain;
  private final Intake mIntake;
  private final Spindexer mSpindexer;
  private final Shooter mShooter;
  public final AdjustabeHood mAdjustabeHood;
  private final Ejector mEjector;

  // Subsytem Instances (Disabled)
  // private final Turret mTurret;
  // private final ClimbSlide mClimeSlide;
  // private final TelescopeClimb mTelescopeClimb;
  // private final ColorWheel mColorWheel;

  // Auto Chooser
  SendableChooser<Command> autoChooser;

  // Controllers
  public mhController controller;

  // Controller Buttons
  JoystickButton controllerAutoIntake;
  JoystickButton controllerAutoOverride;

  public RobotContainer() {

    // Subsystem Instances
    mDriveTrain = new DriveTrain();
    mDriveTrain.setDefaultCommand(new DriveSticks(mDriveTrain));

    mIntake = new Intake();
    mIntake.setDefaultCommand(new StopIntake(mIntake));

    mSpindexer = new Spindexer();
    mSpindexer.setDefaultCommand(new StopSpindexer(mSpindexer));

    mShooter = new Shooter();
    mShooter.setDefaultCommand(new StopShooter(mShooter));

    mAdjustabeHood = new AdjustabeHood();
    mAdjustabeHood.setDefaultCommand(new StopAdjustableHood(mAdjustabeHood));

    mEjector = new Ejector();
    mEjector.setDefaultCommand(new StopEjector(mEjector));

    // Subsystem Instances (Disabled)
    // mTurret = new Turret();
    // mTurret.setDefaultCommand(new StopTurret(mTurret));

    // mClimeSlide = new ClimbSlide();
    // mClimeSlide.setDefaultCommand(new StopClimbSlide(mClimeSlide));

    // mColorWheel = new ColorWheel();
    // mColorWheel.setDefaultCommand(new StopColorWheel(mColorWheel));

    // mTelescopeClimb = new TelescopeClimb();
    // mTelescopeClimb.setDefaultCommand(new StopTelescopeClimb(mTelescopeClimb));

    // SmartDashboard Auto Data
    SmartDashboard.putData("Set Swerve 0", new SetSwerveAngle(mDriveTrain, 0.0));
    SmartDashboard.putData("Set Swerve -90", new SetSwerveAngle(mDriveTrain, -90.0));
    SmartDashboard.putData("Set Swerve 90", new SetSwerveAngle(mDriveTrain, 90.0));

    // SmartDashboard Test Data
    // SmartDashboard.putData(new StopShooter(mShooter));
    // SmartDashboard.putData(new StartShooter(mShooter));
    // SmartDashboard.putData("Set Shooter +100", new ChangeShooterSpeed(mShooter, 100));
    // SmartDashboard.putData("Set Shooter -100", new ChangeShooterSpeed(mShooter, -100));

    // SmartDashboard.putData(new StopAdjustableHood(mAdjustabeHood));
    // SmartDashboard.putData(new HomeAdjustableHood(mAdjustabeHood));
    // SmartDashboard.putData("Set Hood 2", new SetAdjustableHoodPosition(mAdjustabeHood, 1));
    // SmartDashboard.putData("Set Hood 4", new SetAdjustableHoodPosition(mAdjustabeHood, 4));
    // SmartDashboard.putData("Set Hood 6", new SetAdjustableHoodPosition(mAdjustabeHood, 6));
    // SmartDashboard.putData("Set Hood 8", new SetAdjustableHoodPosition(mAdjustabeHood, 8));
    // SmartDashboard.putData("Set Hood 10", new SetAdjustableHoodPosition(mAdjustabeHood, 10));
    // SmartDashboard.putData("Set Hood 12", new SetAdjustableHoodPosition(mAdjustabeHood, 12.0));
    // SmartDashboard.putData("Set Hood 14.5", new SetAdjustableHoodPosition(mAdjustabeHood, 14.5));
    // SmartDashboard.putData("Set Hood 15.25", new SetAdjustableHoodPosition(mAdjustabeHood, 15.25));
    // SmartDashboard.putData("Move Hood 0.05", new MoveAdjustableHood(mAdjustabeHood, 0.05));
    // SmartDashboard.putData("Move Hood -0.05", new MoveAdjustableHood(mAdjustabeHood, -0.05));

    // SmartDashboard.putData("Move Intake 1.0", new MoveIntake(mIntake, 1.0));
    // SmartDashboard.putData("Move Intake -1.0", new MoveIntake(mIntake, -1.0));

    // SmartDashboard.putData("Move Spindexer 0.6", new MoveSpindexer(mSpindexer, 0.6));
    // SmartDashboard.putData("Move Spindexer -0.75", new MoveSpindexer(mSpindexer, -0.5));

    // SmartDashboard.putData("Intake Out", new DeployIntake(mIntake, true));
    // SmartDashboard.putData("Intake In", new DeployIntake(mIntake, false));

    // SmartDashboard.putData("Move Ejector 0.8", new MoveEjector(mEjector, 0.8));
    // SmartDashboard.putData("Move Ejector -0.1", new MoveEjector(mEjector, -0.1));

    // SmartDashboard.putData("Auto Intake", new AutoIntake(mIntake, mSpindexer, mEjector));
    // SmartDashboard.putData("Auto Override", new AutoOverride(mIntake, mSpindexer, mEjector));
    // SmartDashboard.putData("Auto Shoot", new AutoShoot(mSpindexer, mEjector));

    SmartDashboard.putData("Straight Drive", new AutoFollowPath(mDriveTrain, new StraightPath().generateSwerveTrajectory()));

    // Setup the Autonomous Selector
    setupAutoSelector();

    // Initialize the Controller
    controller = new mhController(0);

    // Configure the Buttons
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    // Controller Buttons
    controllerAutoIntake = new JoystickButton(controller, 1);
    controllerAutoIntake.whenPressed(new AutoIntake(mIntake, mSpindexer, mEjector));

    controllerAutoOverride = new JoystickButton(controller, 2);
    controllerAutoOverride.whenPressed(new AutoOverride(mIntake, mSpindexer, mEjector));
  }

  private void setupAutoSelector() {
    // Auto Commands
    Command slalomAuto = new AutoFollowPath(mDriveTrain, new SlalomPath(mDriveTrain).generateSwerveTrajectory());
    Command barrelAuto = new AutoFollowPath(mDriveTrain, new BarrelPath(mDriveTrain).generateSwerveTrajectory());
    Command bounceAuto = new AutoFollowPath(mDriveTrain, new BouncePath(mDriveTrain).generateSwerveTrajectory());
    Command galacticSearchAuto = new GalacticSearchSelector(mDriveTrain, mIntake, mSpindexer, mEjector);

    // Auto Choose
    autoChooser = new SendableChooser<>();

    // Add Commands to the Chooser
    autoChooser.setDefaultOption("Slalom", slalomAuto);
    autoChooser.addOption("Barrel", barrelAuto);
    autoChooser.addOption("Bounce", bounceAuto);
    autoChooser.addOption("Galactic Search", galacticSearchAuto);

    // Display the Chooser on Dashboard
    SmartDashboard.putData("Auto Selector", autoChooser);
  }

  public Command getAutoCommand() {
    return autoChooser.getSelected();
  }
}

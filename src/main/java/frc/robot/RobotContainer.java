/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.lib.oi.mhController;
import frc.lib.oi.controller.DPadButton;
import frc.lib.oi.controller.TriggerButton;
import frc.lib.oi.controller.DPadButton.Direction;
import frc.robot.commands.*;
import frc.robot.commands.autos.GalacticSearchSelector;
import frc.robot.commands.groups.*;
import frc.robot.paths.*;
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
  private SendableChooser<Command> autoChooser;

  // Controllers
  public mhController controller1;
  public mhController controller2;

  // Controller 1 Buttons
  private JoystickButton gyroResetButton;
  public TriggerButton slowModeButton;
  public TriggerButton autoShootButton;
  public JoystickButton autoAimButton;
  private JoystickButton increaseShooterSpeedButton;
  private JoystickButton decreaseShooterSpeedButton;
  // private JoystickButton powerPortForwardButton;
  // private JoystickButton powerPortBackwardButton;

  // Controller 2 Buttons
  private JoystickButton autoOverrideButton;
  private JoystickButton autoIntakeButton;
  private JoystickButton moveHoodDownButton;
  private JoystickButton moveHoodUpButton;
  private JoystickButton shooterToggleButton;
  private TriggerButton moveSpindexerForwardButton;
  private TriggerButton moveSpindexerReverseButton;
  private DPadButton zone1Button;
  private DPadButton zone2Button;
  private DPadButton zone3Button;
  private DPadButton zone4Button;
  private JoystickButton toggleIntakeButton;

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
    // SmartDashboard.putData("Straight Drive",
    // new AutoFollowPath(mDriveTrain, new
    // StraightPath().generateSwerveTrajectory()));
    SmartDashboard.putData("Set Swerve 0", new SetSwerveAngle(mDriveTrain, 0.0));
    SmartDashboard.putData("Set Swerve -90", new SetSwerveAngle(mDriveTrain, -90.0));

    // SmartDashboard Test Data
    // SmartDashboard.putData(new StopShooter(mShooter));
    // SmartDashboard.putData(new StartShooter(mShooter));
    // SmartDashboard.putData("Set Shooter +100", new ChangeShooterSpeed(mShooter,
    // 100));
    // SmartDashboard.putData("Set Shooter -100", new ChangeShooterSpeed(mShooter,
    // -100));

    // SmartDashboard.putData(new StopAdjustableHood(mAdjustabeHood));
    // SmartDashboard.putData(new HomeAdjustableHood(mAdjustabeHood));
    // SmartDashboard.putData("Set Hood 2", new
    // SetAdjustableHoodPosition(mAdjustabeHood, 1));
    // SmartDashboard.putData("Set Hood 4", new
    // SetAdjustableHoodPosition(mAdjustabeHood, 4));
    // SmartDashboard.putData("Set Hood 6", new
    // SetAdjustableHoodPosition(mAdjustabeHood, 6));
    // SmartDashboard.putData("Set Hood 8", new
    // SetAdjustableHoodPosition(mAdjustabeHood, 8));
    // SmartDashboard.putData("Set Hood 10", new
    // SetAdjustableHoodPosition(mAdjustabeHood, 10));
    // SmartDashboard.putData("Set Hood 12", new
    // SetAdjustableHoodPosition(mAdjustabeHood, 12.0));
    // SmartDashboard.putData("Set Hood 14.5", new
    // SetAdjustableHoodPosition(mAdjustabeHood, 14.5));
    // SmartDashboard.putData("Set Hood 15.25", new
    // SetAdjustableHoodPosition(mAdjustabeHood, 15.25));
    // SmartDashboard.putData("Move Hood 0.05", new
    // MoveAdjustableHood(mAdjustabeHood, 0.05));
    // SmartDashboard.putData("Move Hood -0.05", new
    // MoveAdjustableHood(mAdjustabeHood, -0.05));

    // SmartDashboard.putData("Move Intake 1.0", new MoveIntake(mIntake, 1.0));
    // SmartDashboard.putData("Move Intake -1.0", new MoveIntake(mIntake, -1.0));

    // SmartDashboard.putData("Move Spindexer 0.6", new MoveSpindexer(mSpindexer,
    // 0.6));
    // SmartDashboard.putData("Move Spindexer -0.75", new MoveSpindexer(mSpindexer,
    // -0.5));

    // SmartDashboard.putData("Intake Out", new DeployIntake(mIntake, true));
    // SmartDashboard.putData("Intake In", new DeployIntake(mIntake, false));

    // SmartDashboard.putData("Move Ejector 0.8", new MoveEjector(mEjector, 0.8));
    // SmartDashboard.putData("Move Ejector -0.1", new MoveEjector(mEjector, -0.1));

    // SmartDashboard.putData("Auto Intake", new AutoIntake(mIntake, mSpindexer,
    // mEjector));
    // SmartDashboard.putData("Auto Override", new AutoOverride(mIntake, mSpindexer,
    // mEjector));
    // SmartDashboard.putData("Auto Shoot", new AutoShoot(mSpindexer, mEjector));

    SmartDashboard.putData("Start Hood", new StartHood(mAdjustabeHood));
    SmartDashboard.putData("Set Hood 0", new SetHoodTarget(mAdjustabeHood, 0));
    SmartDashboard.putData("Set Hood 1", new SetHoodTarget(mAdjustabeHood, 1));
    SmartDashboard.putData("Set Hood 13", new SetHoodTarget(mAdjustabeHood, 13));
    SmartDashboard.putData("Shooter At Setpoint", new ShooterAtSetSpeed(mShooter, 100));
    SmartDashboard.putData("Is Home", new HoodIsHomed(mAdjustabeHood));

    // Setup the Autonomous Selector
    setupAutoSelector();

    // Display the Joystick Settings
    SmartDashboard.putNumber("xySmoothFactor", Constants.joystickXYSmoothFactor);
    SmartDashboard.putNumber("rotationSmoothFactor", Constants.joystickRotationSmoothFactor);
    SmartDashboard.putBoolean("isLeftStrafe", true);

    // Initialize the Controllers
    controller1 = new mhController(0);
    controller2 = new mhController(1);

    // Configure the Buttons
    configureButtonBindings();

    // calls function to start camera
    initCamera();
  }

  private void configureButtonBindings() {
    // Controller 1 Buttons
    gyroResetButton = new JoystickButton(controller1, 8);
    gyroResetButton.whenPressed(new ResetGyro(mDriveTrain));

    slowModeButton = new TriggerButton(controller1, Hand.kLeft, 0.2);

    autoShootButton = new TriggerButton(controller1, Hand.kRight, 0.2);
    autoShootButton.whileActiveContinuous(new AutoShoot(mSpindexer, mEjector));
    autoShootButton.whenInactive(new StopAutoShoot(mSpindexer, mEjector));

    autoAimButton = new JoystickButton(controller1, 1);
    autoAimButton.whileHeld(new AutoLimeLightSpeed(mShooter, mDriveTrain));
    autoAimButton.whenPressed(new AutoLimeLightHood(mAdjustabeHood, mDriveTrain));
    autoAimButton.whenReleased(new StopAdjustableHood(mAdjustabeHood));

    increaseShooterSpeedButton = new JoystickButton(controller1, 6);
    increaseShooterSpeedButton.whenPressed(new ChangeShooterSpeed(mShooter, 100));

    decreaseShooterSpeedButton = new JoystickButton(controller1, 5);
    decreaseShooterSpeedButton.whenPressed(new ChangeShooterSpeed(mShooter, -100));

    // powerPortForwardButton = new JoystickButton(controller1, 2);
    // powerPortForwardButton.toggleWhenPressed(
    // new AutoFollowPath(mDriveTrain, new
    // PowerPortForward(mDriveTrain).generateSwerveTrajectory()));

    // powerPortBackwardButton = new JoystickButton(controller1, 3);
    // powerPortBackwardButton.toggleWhenPressed(
    // new AutoFollowPath(mDriveTrain, new
    // PowerPortBackward(mDriveTrain).generateSwerveTrajectory()));

    // Controller 2 Buttons
    autoOverrideButton = new JoystickButton(controller2, 2);
    autoOverrideButton.whenPressed(new AutoOverride(mIntake, mSpindexer, mEjector));

    autoIntakeButton = new JoystickButton(controller2, 1);
    autoIntakeButton.whenPressed(new AutoIntake(mIntake, mSpindexer, mEjector));

    moveHoodUpButton = new JoystickButton(controller2, 6);
    moveHoodUpButton.whenPressed(new MoveAdjustableHood(mAdjustabeHood, 0.1));
    moveHoodUpButton.whenReleased(new StopAdjustableHood(mAdjustabeHood));

    moveHoodDownButton = new JoystickButton(controller2, 5);
    moveHoodDownButton.whenPressed(new MoveAdjustableHood(mAdjustabeHood, -0.1));
    moveHoodDownButton.whenReleased(new StopAdjustableHood(mAdjustabeHood));

    shooterToggleButton = new JoystickButton(controller2, 3);
    shooterToggleButton.toggleWhenPressed(new StartShooter(mShooter));

    moveSpindexerForwardButton = new TriggerButton(controller2, Hand.kRight, 0.2);
    moveSpindexerForwardButton.whenActive(new MoveSpindexer(mSpindexer, 0.4));
    moveSpindexerForwardButton.whenInactive(new StopSpindexer(mSpindexer));

    moveSpindexerReverseButton = new TriggerButton(controller2, Hand.kLeft, 0.2);
    moveSpindexerReverseButton.whenActive(new MoveSpindexer(mSpindexer, -0.4));
    moveSpindexerReverseButton.whenInactive(new StopSpindexer(mSpindexer));

    zone4Button = new DPadButton(controller2, Direction.DOWN);
    zone4Button.whenActive(new SetAdjustableHoodPosition(mAdjustabeHood, 5.7));
    zone4Button.whenActive(new SetShooterSpeed(mShooter, 4300));

    zone1Button = new DPadButton(controller2, Direction.LEFT);
    zone1Button.whenActive(new SetAdjustableHoodPosition(mAdjustabeHood, 1.357));
    zone1Button.whenActive(new SetShooterSpeed(mShooter, 3600));

    zone2Button = new DPadButton(controller2, Direction.UP);
    zone2Button.whenActive(new SetAdjustableHoodPosition(mAdjustabeHood, 0));
    zone2Button.whenActive(new SetShooterSpeed(mShooter, 0));

    zone3Button = new DPadButton(controller2, Direction.RIGHT);
    zone3Button.whenActive(new SetAdjustableHoodPosition(mAdjustabeHood, 7.57)); // 8.71
    zone3Button.whenActive(new SetShooterSpeed(mShooter, 5100)); // 3000

    toggleIntakeButton = new JoystickButton(controller2, 4);
    toggleIntakeButton.whenPressed(new ToggleIntake(mIntake));

  }

  private void setupAutoSelector() {
    // Auto Commands
    Command slalomAuto = new AutoFollowPath(mDriveTrain, new SlalomPath(mDriveTrain).generateSwerveTrajectory());
    Command barrelAuto = new AutoFollowPath(mDriveTrain, new BarrelPath(mDriveTrain).generateSwerveTrajectory());
    Command bounceAuto = new AutoFollowPath(mDriveTrain, new BouncePath(mDriveTrain).generateSwerveTrajectory());
    Command galacticSearchAuto = new GalacticSearchSelector(mDriveTrain, mIntake, mSpindexer, mEjector);
    Command teamNumberAuto = new AutoFollowPath(mDriveTrain,
        new TeamNumberPath(mDriveTrain).generateSwerveTrajectory());

    // Auto Choose
    autoChooser = new SendableChooser<>();

    // Add Commands to the Chooser
    autoChooser.setDefaultOption("Slalom", slalomAuto);
    autoChooser.addOption("Barrel", barrelAuto);
    autoChooser.addOption("Bounce", bounceAuto);
    autoChooser.addOption("Galactic Search", galacticSearchAuto);
    autoChooser.addOption("2992 Draw", teamNumberAuto);

    // Display the Chooser on Dashboard
    SmartDashboard.putData("Auto Selector", autoChooser);
  }

  public Command getAutoCommand() {
    return autoChooser.getSelected();
  }

  public void initCamera() {
    CameraServer.getInstance().startAutomaticCapture("Driver Camera", 1);
  }
}

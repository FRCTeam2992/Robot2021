/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSource.ConnectionStrategy;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.lib.game.year2021.PowerCellDataPoint;
import frc.lib.game.year2021.PowerCellInterpolator;
import frc.lib.oi.mhController;
import frc.lib.oi.controller.DPadButton;
import frc.lib.oi.controller.TriggerButton;
import frc.lib.oi.controller.DPadButton.Direction;
import frc.robot.commands.*;
import frc.robot.commands.autos.CenterShieldGeneratorAuto;
import frc.robot.commands.autos.CenterTrenchFiveAuto;
import frc.robot.commands.autos.CenterTrenchThreeAuto;
import frc.robot.commands.autos.RightTrenchFiveAuto;
import frc.robot.commands.autos.RightTrenchThreeAuto;
import frc.robot.commands.groups.*;
import frc.robot.subsystems.*;

public class RobotContainer {

  // Subsystem Instances
  public final DriveTrain mDriveTrain;
  private final Intake mIntake;
  private final Spindexer mSpindexer;
  private final Shooter mShooter;
  public final AdjustabeHood mAdjustabeHood;
  private final Ejector mEjector;
  private final TelescopeClimb mTelescopeClimb;

  // Subsytem Instances (Disabled)
  // private final Turret mTurret;
  // private final ClimbSlide mClimeSlide;
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
  private JoystickButton lockSwerveDriveButton;

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
  private JoystickButton climbModeOnButton;
  private JoystickButton climbModeOffButton;

  private boolean toggleClimbMode;

  // Power Cell Interpolator
  private PowerCellInterpolator powerCellInterpolator;

  public RobotContainer() {
    // Initialize the Power Cell Interpolator
    initInterpolator();

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

    mTelescopeClimb = new TelescopeClimb();
    mTelescopeClimb.setDefaultCommand(new StopTelescopeClimb(mTelescopeClimb));

    // Subsystem Instances (Disabled)
    // mTurret = new Turret();
    // mTurret.setDefaultCommand(new StopTurret(mTurret));

    // mClimeSlide = new ClimbSlide();
    // mClimeSlide.setDefaultCommand(new StopClimbSlide(mClimeSlide));

    // mColorWheel = new ColorWheel();
    // mColorWheel.setDefaultCommand(new StopColorWheel(mColorWheel));


    // Setup the Autonomous Selector
    setupAutoSelector();

    // Display the Joystick and Power Cell Settings
    SmartDashboard.putNumber("xySmoothFactor", Constants.joystickXYSmoothFactor);
    SmartDashboard.putNumber("rotationSmoothFactor", Constants.joystickRotationSmoothFactor);
    SmartDashboard.putBoolean("isLeftStrafe", true);
    SmartDashboard.putNumber("powerCellDamagePercentage", 0.0);

    // Initialize the Controllers
    controller1 = new mhController(0);
    controller2 = new mhController(1);

    // Configure the Buttons
    configureButtonBindings();

    // Initialize the Camera
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
    autoAimButton.whileHeld(new AutoLimeLightSpeed(mShooter, mDriveTrain, powerCellInterpolator));
    autoAimButton.whenPressed(new AutoLimeLightHood(mAdjustabeHood, mDriveTrain, powerCellInterpolator));
    autoAimButton.whenReleased(new StopAdjustableHood(mAdjustabeHood));

    increaseShooterSpeedButton = new JoystickButton(controller1, 6);
    increaseShooterSpeedButton.whenPressed(new ChangeShooterSpeed(mShooter, 100));

    decreaseShooterSpeedButton = new JoystickButton(controller1, 5);
    decreaseShooterSpeedButton.whenPressed(new ChangeShooterSpeed(mShooter, -100));

    lockSwerveDriveButton = new JoystickButton(controller1, 3);
    lockSwerveDriveButton.whenPressed(new SetSwerveModuleAngles(mDriveTrain, 90, 135, 90, 135));
    lockSwerveDriveButton.whenReleased(new DriveSticks(mDriveTrain));

    // Controller 2 Buttons
    
    // climbModeOnButton = new JoystickButton(controller2, 7);
    // climbModeOnButton.whenPressed(ClimbModeOn());
    
    // climbModeOffButton = new JoystickButton(controller2, 8);
    // climbModeOffButton.whenPressed(ClimbModeOff());
    
    
    //Climb mode switch
   
      zone2Button = new DPadButton(controller2, Direction.UP);
      zone2Button.whenActive(new MoveTelescopeClimb(mTelescopeClimb, 0.5));
      zone2Button.whenInactive(new StopTelescopeClimb(mTelescopeClimb));

      zone4Button = new DPadButton(controller2, Direction.DOWN);

      zone4Button.whenActive(new MoveTelescopeClimb(mTelescopeClimb, -0.5));
      zone4Button.whenInactive(new StopTelescopeClimb(mTelescopeClimb));
    

    // zone2Button.whenActive(new SetAdjustableHoodPosition(mAdjustabeHood, 0.0));

    // zone4Button.whenActive(new SetAdjustableHoodPosition(mAdjustabeHood, 7.15));
    // zone4Button.whenActive(new SetShooterSpeed(mShooter, 4400));

    autoOverrideButton = new JoystickButton(controller2, 2);
    autoOverrideButton.whenPressed(new AutoOverride(mIntake, mSpindexer, mEjector));

    autoIntakeButton = new JoystickButton(controller2, 1);
    autoIntakeButton.whenPressed(new AutoIntake(mIntake, mSpindexer, mEjector));

    moveHoodUpButton = new JoystickButton(controller2, 6);
    moveHoodUpButton.whenPressed(new MoveAdjustableHood(mAdjustabeHood, 0.05));
    moveHoodUpButton.whenReleased(new StopAdjustableHood(mAdjustabeHood));

    moveHoodDownButton = new JoystickButton(controller2, 5);
    moveHoodDownButton.whenPressed(new MoveAdjustableHood(mAdjustabeHood, -0.05));
    moveHoodDownButton.whenReleased(new StopAdjustableHood(mAdjustabeHood));

    shooterToggleButton = new JoystickButton(controller2, 3);
    shooterToggleButton.toggleWhenPressed(new StartShooter(mShooter));

    moveSpindexerForwardButton = new TriggerButton(controller2, Hand.kRight, 0.2);
    moveSpindexerForwardButton.whenActive(new MoveSpindexer(mSpindexer, 0.4));
    moveSpindexerForwardButton.whenInactive(new StopSpindexer(mSpindexer));

    moveSpindexerReverseButton = new TriggerButton(controller2, Hand.kLeft, 0.2);
    moveSpindexerReverseButton.whenActive(new MoveSpindexer(mSpindexer, -0.4));
    moveSpindexerReverseButton.whenInactive(new StopSpindexer(mSpindexer));

    zone1Button = new DPadButton(controller2, Direction.LEFT);
    zone1Button.whenActive(new SetAdjustableHoodPosition(mAdjustabeHood, 1.8));
    zone1Button.whenActive(new SetShooterSpeed(mShooter, 3800));

    zone3Button = new DPadButton(controller2, Direction.RIGHT);
    zone3Button.whenActive(new SetAdjustableHoodPosition(mAdjustabeHood, 9.0));
    zone3Button.whenActive(new SetShooterSpeed(mShooter, 5300));

    toggleIntakeButton = new JoystickButton(controller2, 4);
    toggleIntakeButton.whenPressed(new ToggleIntake(mIntake));
  }

  private void setupAutoSelector() {
    // Auto Commands
    Command centerTrenchFiveAuto = new CenterTrenchFiveAuto(mShooter, mAdjustabeHood, mDriveTrain, mSpindexer, mEjector,
        mIntake, powerCellInterpolator);
    Command rightTrenchFiveAuto = new RightTrenchFiveAuto(mShooter, mAdjustabeHood, mDriveTrain, mSpindexer, mEjector,
        mIntake);
    Command centerTrenchThreeAuto = new CenterTrenchThreeAuto(mShooter, mAdjustabeHood, mDriveTrain, mSpindexer,
        mEjector, mIntake);
    Command rightTrenchThreeAuto = new RightTrenchThreeAuto(mShooter, mAdjustabeHood, mDriveTrain, mSpindexer, mEjector,
        mIntake);
    Command centerShieldGeneratorAuto = new CenterShieldGeneratorAuto(mShooter, mAdjustabeHood, mDriveTrain, mSpindexer,
        mEjector, mIntake);

    // Auto Chooser
    autoChooser = new SendableChooser<>();

    // Add Commands to the Chooser
    autoChooser.setDefaultOption("Do Nothing", null);
    autoChooser.addOption("Center Trench (5 Power Cells)", centerTrenchFiveAuto);
    autoChooser.addOption("Right Trench (5 Power Cells)", rightTrenchFiveAuto);
    autoChooser.addOption("Center Trench (3 Power Cells)", centerTrenchThreeAuto);
    autoChooser.addOption("Right Trench (3 Power Cells)", rightTrenchThreeAuto);
    autoChooser.addOption("Center Shield Generator", centerShieldGeneratorAuto);

    // Display the Chooser on Dashboard
    SmartDashboard.putData("Auto Selector", autoChooser);
  }

  public Command getAutoCommand() {
    return autoChooser.getSelected();
  }

  private void initCamera() {
    UsbCamera driveCamera = CameraServer.getInstance().startAutomaticCapture("Drive Camera", 0);
    driveCamera.setConnectionStrategy(ConnectionStrategy.kKeepOpen);
    driveCamera.setFPS(30);
    driveCamera.setResolution(240, 160);
  }

  private void initInterpolator() {
    powerCellInterpolator = new PowerCellInterpolator();

    powerCellInterpolator.addDataPoint(new PowerCellDataPoint(14.5, 3800, 3800, 1.8, 1.8));
    powerCellInterpolator.addDataPoint(new PowerCellDataPoint(22.5, 3800, 3800, 3.8, 3.8));
    powerCellInterpolator.addDataPoint(new PowerCellDataPoint(30.3, 4000, 4000, 5.2, 5.2));
    powerCellInterpolator.addDataPoint(new PowerCellDataPoint(36.5, 4000, 4000, 5.2, 5.2));
    powerCellInterpolator.addDataPoint(new PowerCellDataPoint(42.5, 4000, 4400, 6, 6));
    powerCellInterpolator.addDataPoint(new PowerCellDataPoint(51.5, 4400, 4800, 7.15, 6.7));
    powerCellInterpolator.addDataPoint(new PowerCellDataPoint(60, 4500, 5400, 7.15, 7.1));
    powerCellInterpolator.addDataPoint(new PowerCellDataPoint(65, 4700, 5700, 7.5, 7.1));
    powerCellInterpolator.addDataPoint(new PowerCellDataPoint(69.5, 5100, 5800, 8.4, 7.1));
    powerCellInterpolator.addDataPoint(new PowerCellDataPoint(76.5, 5100, 6000, 8.4, 7.1));
    powerCellInterpolator.addDataPoint(new PowerCellDataPoint(81, 5300, 6000, 9, 7.1));
    powerCellInterpolator.addDataPoint(new PowerCellDataPoint(87.5, 5600, 6000, 8, 7.1));
  }

  
}

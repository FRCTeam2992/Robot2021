/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.lib.drive.swerve.SwerveModule;
import frc.lib.oi.mhController;
import frc.robot.commands.DriveSticks;
import frc.robot.commands.StopBarClimb;
import frc.robot.commands.StopClimbSlide;
import frc.robot.commands.StopColorWheel;
import frc.robot.commands.StopEjector;
import frc.robot.commands.StopIntake;
import frc.robot.commands.StopShooter;
import frc.robot.subsystems.BarClimb;
import frc.robot.subsystems.ClimbSlide;
import frc.robot.subsystems.ColorWheel;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Ejector;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.commands.StopSpindexer;
import frc.robot.commands.StopTelescopeClimb;
import frc.robot.commands.StopTurret;
import frc.robot.subsystems.Spindexer;
import frc.robot.subsystems.TelescopeClimb;
import frc.robot.subsystems.Turret;

public class RobotContainer {
  // Subsystems
  private final Intake mIntake;
  private final Spindexer mSpindexer;
  private final Turret mTurret;
  private final ClimbSlide mClimeSlide;
  private final ColorWheel mColorWheel;
  private final BarClimb mBarClimb;
  private final Shooter mShooter;
  private final TelescopeClimb mTelescopeClimb;
  private final Ejector mEjector;
  private final DriveTrain mDriveTrain;

  // Controllers 
  public mhController controller;

  public RobotContainer() {

  // DriveTrain
  mDriveTrain = new DriveTrain();
  mDriveTrain.setDefaultCommand(new DriveSticks(mDriveTrain));
  
  SwerveModule module = mDriveTrain.frontLeftModule; 

  // Intake
    mIntake = new Intake();
    mIntake.setDefaultCommand(new StopIntake(mIntake));

  //Turret
    mTurret = new Turret();
    mTurret.setDefaultCommand(new StopTurret(mTurret));

  //ClimeSlide
    mClimeSlide = new ClimbSlide();
    mClimeSlide.setDefaultCommand(new StopClimbSlide(mClimeSlide));
    
  //Spindexer
    mSpindexer = new Spindexer();
    mSpindexer.setDefaultCommand(new StopSpindexer(mSpindexer));

  //ColorWheel
    mColorWheel = new ColorWheel();
    mColorWheel.setDefaultCommand(new StopColorWheel(mColorWheel));

  //BarClimb
    mBarClimb = new BarClimb();
    mBarClimb.setDefaultCommand(new StopBarClimb(mBarClimb));

  //Shooter
    mShooter = new Shooter();
    mShooter.setDefaultCommand(new StopShooter(mShooter));

  //TelescopeClimb
    mTelescopeClimb = new TelescopeClimb();
    mTelescopeClimb.setDefaultCommand(new StopTelescopeClimb(mTelescopeClimb));

  //Ejector
    mEjector = new Ejector();
    mEjector.setDefaultCommand(new StopEjector(mEjector));
    
    configureController();
    configureButtonBindings();

  }

  private void configureController(){
    controller = new mhController(0);
  }

  private void configureButtonBindings() {

  }

  public void setDriveTrainIdleMode(IdleMode turnMode, IdleMode driveMode){
    mDriveTrain.setTurnIdleMode(turnMode);
    mDriveTrain.setDriveIdleMode(driveMode);
    
  }

  public void zeroGyro(){
    mDriveTrain.navx.zeroYaw();

  }

  public void resetOdometry(){
    mDriveTrain.resetOdometry();

  }
}

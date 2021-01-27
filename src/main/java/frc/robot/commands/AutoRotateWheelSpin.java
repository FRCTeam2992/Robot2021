// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;
import frc.robot.subsystems.ColorWheel;

public class AutoRotateWheelSpin extends CommandBase {

  private ColorWheel mColorWheel;

  private double encoderSetValue;

  private double mRotations = 0;

  private double mTimeout = 0;
  private Timer timeoutTimer;

  /** Creates a new AutoRotateWheelSpin. */
  public AutoRotateWheelSpin(double rotations, double timeout, ColorWheel subsystem) {
    
    mColorWheel = subsystem;

    mRotations = rotations;
    mTimeout = timeout;

    timeoutTimer = new Timer();

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.setInterruptible(true);

        mColorWheel.zeroMotorPosition();
        encoderSetValue = (mRotations * Constants.colorWheelSpinRatio) * (Constants.colorWheelEncoderPulses * 4);

        timeoutTimer.reset();
        timeoutTimer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    mColorWheel.setColorWheelPostion(encoderSetValue);
  }

  @Override
  public boolean isFinished() {
    return Math.abs(encoderSetValue - mColorWheel.getMotorPostion()) <= 50 || timeoutTimer.get() >= mTimeout;
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    mColorWheel.stopColorWheel();
  }

  @Override
    protected void interrupted() {
      mColorWheel.stopColorWheel();
   }
}

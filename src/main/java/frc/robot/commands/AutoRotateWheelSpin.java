// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ColorWheel;

public class AutoRotateWheelSpin extends CommandBase {

  private double encoderSetValue;

  private double mRotations = 0;

  private double mTimeout = 0;
  private Timer timeoutTimer;
  private Constants mConstants;
  private ColorWheel mColorWheel;

  /** Creates a new AutoRotateWheelSpin. */
  public AutoRotateWheelSpin(ColorWheel subsystem, double rotations, double timeout) {
    addRequirements(mColorWheel);

    mRotations = rotations;
    mTimeout = timeout;
    mColorWheel = subsystem;
    timeoutTimer = new Timer();

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

        mColorWheel.zeroMotorPosition();
        encoderSetValue = (mRotations * mConstants.colorWheelSpinRatio) * (mConstants.colorWheelEncoderPulses * 4);

        timeoutTimer.reset();
        timeoutTimer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    mColorWheel.setColorWheelPosition(encoderSetValue);
  }

  @Override
  public boolean isFinished() {
    return Math.abs(encoderSetValue - mColorWheel.getMotorPosition()) <= 50 || timeoutTimer.get() >= mTimeout;
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    mColorWheel.stopColorWheel();
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ColorWheel;

public class AutoRotateWheelSpin extends CommandBase {

  // Subsystem Instance
  private ColorWheel mColorWheel;

  // Saved Variables
  private double mRotations;
  private double mTimeout;

  // Timer
  private Timer timeoutTimer;

  private double encoderSetValue;

  public AutoRotateWheelSpin(ColorWheel subsystem, double rotations, double timeout) {
    // Subsystem Instance
    mColorWheel = subsystem;

    // Set the Subsystem Requirement
    addRequirements(mColorWheel);

    // Saved Variables
    mRotations = rotations;
    mTimeout = timeout;

    // Timer
    timeoutTimer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    mColorWheel.zeroMotorPosition();
    encoderSetValue = (mRotations * Constants.colorWheelSpinRatio) * (Constants.colorWheelEncoderPulses * 4);

    timeoutTimer.reset();
    timeoutTimer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    mColorWheel.setColorWheelPosition(encoderSetValue);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(encoderSetValue - mColorWheel.getMotorPosition()) <= 50 || timeoutTimer.get() >= mTimeout;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    mColorWheel.setColorWheelSpeed(0.0);
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoRotateWheelSpin extends CommandBase {

  private double encoderSetValue;

  private double mRotations = 0;

  private double mTimeout = 0;
  private Timer timeoutTimer;

  /** Creates a new AutoRotateWheelSpin. */
  public AutoRotateWheelSpin(double rotations, double timeout) {
    requires(Robot.colorWheel);
    requires(Robot.topLift);

    mRotations = rotations;
    mTimeout = timeout;

    timeoutTimer = new Timer();

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.setInterruptible(true);

        Robot.colorWheel.zeroMotorPosition();
        encoderSetValue = (mRotations * Constants.colorWheelSpinRatio) * (Constants.colorWheelEncoderPulses * 4);

        timeoutTimer.reset();
        timeoutTimer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.colorWheel.setColorWheelPostion(encoderSetValue);
  }

  @Override
  public boolean isFinished() {
    return Math.abs(encoderSetValue - Robot.colorWheel.getMotorPostion()) <= 50 || timeoutTimer.get() >= mTimeout;
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.colorWheel.stopColorWheel();
  }

  @Override
    protected void interrupted() {
        Robot.colorWheel.stopColorWheel();
   }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.ColorWheel;
import frc.robot.subsystems.ColorWheel.TargetColor;

public class AutoRotateWheelColor extends CommandBase {

  private ColorWheel mColorWheel;

  private boolean isDone = false;

  private int detectedPosition = 0;
  private int targetPosition = 0;

  private int rotations = 0;
  private double encoderSetValue = 0;

  private double mTimeout = 0;
  private Timer timeoutTimer;
  /** Creates a new AutoRotateWheelColor. */
  public AutoRotateWheelColor(ColorWheel subsystem, double timeout) {
    
    mColorWheel = subsystem;

    mTimeout = timeout;
    timeoutTimer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    detectedPosition = getColorID(mColorWheel.getDetectedColor());
        targetPosition = getColorID(mColorWheel.getFMSColorData());

        if (detectedPosition == 0 || targetPosition == 0) {
            isDone = true;
        }

        rotations = targetPosition - detectedPosition;

        if (Math.abs(rotations) > 2) {
            rotations += detectedPosition;
        }

        mColorWheel.zeroMotorPosition();
        encoderSetValue = ((rotations / 8) * Constants.colorWheelSpinRatio) * (Constants.colorWheelEncoderPulses * 4);

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
    return isDone || Math.abs(encoderSetValue - mColorWheel.getMotorPosition()) <= 50
                || timeoutTimer.get() >= mTimeout;
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    mColorWheel.stopColorWheel();
  }

  // Returns true when the command should end.
  private int getColorID(TargetColor targetColor) {
    switch (targetColor) {
    case Green:
        return 1;
    case Blue:
        return 2;
    case Yellow:
        return 3;
    case Red:
        return 4;
    default:
        return 0;
  }
}
}
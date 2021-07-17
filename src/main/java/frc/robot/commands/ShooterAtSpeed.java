// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class ShooterAtSpeed extends CommandBase {
  /** Creates a new ShooterAtSpeed. */

  private Shooter mShooter;

  private double mTimeout = 0;

  private Timer timeroutTimer;

  public ShooterAtSpeed(Shooter subsystem, double timeout) {
    // Use addRequirements() here to declare subsystem dependencies.

    mShooter = subsystem;

    mTimeout = timeout;

    timeroutTimer = new Timer();
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timeroutTimer.reset();
    timeroutTimer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(mShooter.shooterSetSpeed - mShooter.getShooterRPM()) <= 50
      || timeroutTimer.get() >= mTimeout;
  }
}

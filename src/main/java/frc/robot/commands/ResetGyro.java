// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class ResetGyro extends CommandBase {

  // Subsystem Instance
  private DriveTrain mDriveTrain;

  public ResetGyro(DriveTrain subsystem) {
    // Subsystem Instance
    mDriveTrain = subsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // Reset the Gyro
    mDriveTrain.navx.zeroYaw();

    // Reset the Odometry
    mDriveTrain.resetOdometry();
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
    return true;
  }
}

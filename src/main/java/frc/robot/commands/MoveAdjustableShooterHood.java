// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AdjustabeShooterHood;

public class MoveAdjustableShooterHood extends CommandBase {

  // Subsystem Instance
  private AdjustabeShooterHood mAdjustabeShooterHood;

  // Saved Variables
  private double mAdjustableShooterHoodSpeed;

  public MoveAdjustableShooterHood(AdjustabeShooterHood subsystem, double adjustabeShooterHoodSpeed) {
    // Subsystem Instance
    mAdjustabeShooterHood = subsystem;

    // Set the Subsystem Requirement
    addRequirements(mAdjustabeShooterHood);

    // Saved Variables
    mAdjustableShooterHoodSpeed = adjustabeShooterHoodSpeed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    mAdjustabeShooterHood.setAdjustableShooterHoodSpeed(mAdjustableShooterHoodSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    mAdjustabeShooterHood.setAdjustableShooterHoodSpeed(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

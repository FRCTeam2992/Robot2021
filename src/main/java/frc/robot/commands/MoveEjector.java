// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Ejector;

public class MoveEjector extends CommandBase {

  // Subsystem Instance
  private Ejector mEjector;

  // Saved Variables
  private double mEjectorSpeed;

  public MoveEjector(Ejector subsystem, double ejectorSpeed) {
    // Subsystem Instance
    mEjector = subsystem;

    // Set the Subsytem Requirement
    addRequirements(mEjector);

    // Saved Variables
    mEjectorSpeed = ejectorSpeed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    mEjector.setEjectorSpeed(mEjectorSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    mEjector.setEjectorSpeed(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

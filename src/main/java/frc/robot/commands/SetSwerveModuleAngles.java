// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class SetSwerveModuleAngles extends CommandBase {

  // Subsystem Instance
  private DriveTrain mDriveTrain;

  // Saved Variables
  private double flAngle;
  private double frAngle;
  private double rlAngle;
  private double rrAngle;

  public SetSwerveModuleAngles(DriveTrain subsystem, double flAngle, double frAngle, double rlAngle, double rrAngle) {
    // Subsystem Instance
    mDriveTrain = subsystem;

    // Set the Subsystem Requirements
    addRequirements(subsystem);

    // Saved Variables
    this.flAngle = flAngle;
    this.frAngle = frAngle;
    this.rlAngle = rlAngle;
    this.rrAngle = rrAngle;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    mDriveTrain.frontLeftModule.setTurnAngle(flAngle);
    mDriveTrain.frontRightModule.setTurnAngle(frAngle);
    mDriveTrain.rearLeftModule.setTurnAngle(rlAngle);
    mDriveTrain.rearRightModule.setTurnAngle(rrAngle);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class MoveIntake extends CommandBase {

  // Subsystem Instance
  private Intake mIntake;

  // Saved Variables
  private double mIntakeSpeed;

  public MoveIntake(Intake subsystem, double IntakeSpeed) {
    // Subsystem Instance
    mIntake = subsystem;

    // Set the Subsystem Requirements
    addRequirements(mIntake);

    // Saved Variables
    mIntakeSpeed = IntakeSpeed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    mIntake.setIntakeSpeed(mIntakeSpeed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    mIntake.setIntakeSpeed(mIntakeSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //mIntake.setIntakeSpeed(0.0);
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Spindexer;

public class MoveSpindexer extends CommandBase {

  // Subsystem Instance
  private Spindexer mSpindexer;

  // Varibles
  private double mSpinnerSpeed;

  public MoveSpindexer(Spindexer mSpindexer2, double spinnerSpeed) {
    // Subsystem
    mSpindexer = mSpindexer2;
    // Varibles
    mSpinnerSpeed = spinnerSpeed;

    addRequirements(mSpindexer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    mSpindexer.setSpinnerSpeed(mSpinnerSpeed);
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

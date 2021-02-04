/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BarClimb;

public class MoveBarClimb extends CommandBase {

  // Subsystem Instance
  private BarClimb mBarClimb;

  // Saved Variables
  private double mBarSpeed;
  private boolean mToggleClimb;
  private boolean mToggleLock;

  public MoveBarClimb(BarClimb subsystem, double barSpeed, boolean toggleClimb, boolean toggleLock) {
    // Subsystem Instance
    mBarClimb = subsystem;

    // Set the Subsystem Requirement
    addRequirements(mBarClimb);

    // Saved Variables
    mBarSpeed = barSpeed;
    mToggleClimb = toggleClimb;
    mToggleLock = toggleLock;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    mBarClimb.setBarSpeed(mBarSpeed);
    mBarClimb.deployClimb(mToggleClimb);
    mBarClimb.deployLock(mToggleLock);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    mBarClimb.setBarSpeed(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

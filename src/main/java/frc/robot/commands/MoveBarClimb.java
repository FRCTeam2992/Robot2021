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
   //subsystem instance
   private BarClimb mBarClimb;

   // varibles
   private double mBarSpeed;
   private boolean mToggleClimb;
   private boolean mToggleLock;
  public MoveBarClimb(BarClimb subsystem, double barSpeed, boolean toogleClimb, boolean toggleLock) {
    mBarClimb = subsystem;
    mBarSpeed = barSpeed;

    addRequirements(mBarClimb);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    mBarClimb.barClimbSpeed(mBarSpeed);
    mBarClimb.deployClimb(mToggleClimb);
    mBarClimb.deployLock(mToggleLock);
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

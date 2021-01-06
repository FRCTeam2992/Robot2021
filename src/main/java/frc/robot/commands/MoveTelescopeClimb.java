/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.TelescopeClimb;

public class MoveTelescopeClimb extends CommandBase {
  //Subsystem Intance
  private TelescopeClimb mTelescopeClimb;

  //Subsystem
  private double mTeleSpeed;

  public MoveTelescopeClimb(TelescopeClimb subsystem, double teleSpeed) {
    //subsystem
    mTelescopeClimb = subsystem;
    //varibles
    mTeleSpeed = teleSpeed;

    addRequirements(mTelescopeClimb);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    mTelescopeClimb.setClimbSpeed(mTeleSpeed);

    //stop motor if going up and past the climb limit
    if(mTelescopeClimb.getTelePosition() >= Constants.teleClimbLimit && mTeleSpeed > 0.0){
      mTelescopeClimb.setClimbSpeed(0.0);
    }
    else{
      mTelescopeClimb.setClimbSpeed(mTeleSpeed);
    }
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

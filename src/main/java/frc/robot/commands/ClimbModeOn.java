// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.TelescopeClimb;


public class ClimbModeOn extends CommandBase {

  private TelescopeClimb mTelescopeClimb;

<<<<<<< Updated upstream
  public ClimbModeOn(TelescopeClimb subsystem) {
=======
  private boolean climbToggle = false;

   
>>>>>>> Stashed changes

  // public ClimbModeOn(TelescopeClimb subsystem) {

  //   mTelescopeClimb = subsystem;
  // }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
<<<<<<< Updated upstream
    mTelescopeClimb.toggleClimbMode= !mTelescopeClimb.toggleClimbMode;
=======
    climbToggle = !climbToggle;
    
>>>>>>> Stashed changes
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

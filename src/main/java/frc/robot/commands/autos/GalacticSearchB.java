// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autos;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.AutoFollowPath;
import frc.robot.commands.groups.AutoIntake;
import frc.robot.paths.GalacticSearchBBlue;
import frc.robot.paths.GalacticSearchBRed;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Ejector;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Spindexer;

public class GalacticSearchB extends CommandBase {

  // Saved Commands
  private Command autoIntake;
  private Command redPath;
  private Command bluePath;

  // Network Table Entry
  private NetworkTableEntry isRedEntry;

  public GalacticSearchB(DriveTrain driveTrain, Intake intake, Spindexer spindexer, Ejector ejector) {
    // Initialize the Auto Intake Command
    autoIntake = new AutoIntake(intake, spindexer, ejector);

    // Initialize the Path Commands
    redPath = new AutoFollowPath(driveTrain, new GalacticSearchBRed(driveTrain).generateSwerveTrajectory());
    bluePath = new AutoFollowPath(driveTrain, new GalacticSearchBBlue(driveTrain).generateSwerveTrajectory());

    // Network Table Entry
    isRedEntry = NetworkTableInstance.getDefault().getTable("tracker").getEntry("isRed");
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // Schedule the Auto Intake Command
    autoIntake.schedule();

    // Schedule the Path Command
    if (isRedEntry.getBoolean(false)) {
      redPath.schedule();
    } else {
      bluePath.schedule();
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Cancel the Commands
    autoIntake.cancel();
    redPath.cancel();
    bluePath.cancel();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}


package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class DeployIntake extends CommandBase {

  // Subsystem Instance
  private Intake mIntake;

  // Vaeiables
  private boolean mToggle;

  public DeployIntake(Intake subsystem, boolean toggle) {
    // Subsystem Instance
    mIntake = subsystem;

    // Variables
    mToggle = toggle;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    mIntake.deployIntake(mToggle);
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

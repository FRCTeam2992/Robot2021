package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class ShooterAtSetSpeed extends CommandBase {

  // Subsystem Instance
  private Shooter mShooter;

  // Saved Variables
  private int mToleranceValue;

  public ShooterAtSetSpeed(Shooter subsystem, int toleranceValue) {
    // Subsystem Instance
    mShooter = subsystem;

    // Saved Variables
    mToleranceValue = toleranceValue;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(mShooter.shooterSetSpeed - mShooter.getShooterRPM()) <= mToleranceValue;
  }
}

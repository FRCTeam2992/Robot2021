/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {

  // Intake Motor
  private CANSparkMax intakeMotor;

  // Intake Solenod
  private Solenoid intakeDeploySol;

  /**
   * Creates a new Intake.
   */
  public Intake() {
    // Intake Motor
    intakeMotor = new CANSparkMax(16, MotorType.kBrushless);
    intakeMotor.setInverted(false);
    intakeMotor.setIdleMode(IdleMode.kCoast);

    // Intake Solenoid
    intakeDeploySol = new Solenoid(1);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setIntakeSpeed(double Speed) {
    // Intake Speed
    intakeMotor.set(Speed);

  }

  public void deployIntake(Boolean toggle) {
    // Intake Deploy
    intakeDeploySol.set(toggle);

  }
}

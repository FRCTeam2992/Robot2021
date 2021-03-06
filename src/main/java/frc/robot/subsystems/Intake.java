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

  // Intake Motors
  private CANSparkMax intakeMotor;

  // Intake Solenoids
  private Solenoid intakeDeploySolenoid;

  public Intake() {
    // Intake Motors
    intakeMotor = new CANSparkMax(10, MotorType.kBrushless);
    intakeMotor.setInverted(false);
    intakeMotor.setIdleMode(IdleMode.kCoast);

    // Intake Solenoids
    intakeDeploySolenoid = new Solenoid(0);
  }

  @Override
  public void periodic() {

  }

  public void setIntakeSpeed(double speed) {
    intakeMotor.set(speed);
  }

  public void deployIntake(boolean toggle) {
    intakeDeploySolenoid.set(toggle);
  }
}

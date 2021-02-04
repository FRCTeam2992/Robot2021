// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class AdjustabeShooterHood extends SubsystemBase {

  // Motors
  private CANSparkMax hoodMotor;

  public AdjustabeShooterHood() {
    hoodMotor = new CANSparkMax(69, MotorType.kBrushless);
    hoodMotor.setInverted(false);
    hoodMotor.setIdleMode(IdleMode.kCoast);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setAdjustableShooterHoodSpeed(double speed) {
    hoodMotor.set(speed);
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class AdjustabeShooterHood extends SubsystemBase {

  // Shooter Hood Motors
  private CANSparkMax hoodMotor;

  public AdjustabeShooterHood() {
    // Shooter Hood Motors
    hoodMotor = new CANSparkMax(69, MotorType.kBrushless);
    hoodMotor.setInverted(false);
    hoodMotor.setIdleMode(IdleMode.kBrake);
  }

  @Override
  public void periodic() {

  }

  public void setAdjustableShooterHoodSpeed(double speed) {
    hoodMotor.set(speed);
  }

  public void hoodPosition(int position) {
    hoodMotor.getPIDController().setReference(position, ControlType.kPosition);
  }
  
}

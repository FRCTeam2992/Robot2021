// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Ejector extends SubsystemBase {

  // Ejector Motors
  private VictorSPX ejectorMotor;

  public Ejector() {
    // Ejector Motors
    ejectorMotor = new VictorSPX(79);
    ejectorMotor.setInverted(false);
    ejectorMotor.setNeutralMode(NeutralMode.Coast);
  }

  @Override
  public void periodic() {

  }

  public void setEjectorSpeed(double speed) {
    ejectorMotor.set(ControlMode.PercentOutput, speed);
  }
}

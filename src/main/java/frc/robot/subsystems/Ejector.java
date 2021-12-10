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
    ejectorMotor = new VictorSPX(11);
    ejectorMotor.setInverted(true);
    ejectorMotor.setNeutralMode(NeutralMode.Coast);
    ejectorMotor.setControlFramePeriod(1, 255);
    ejectorMotor.setControlFramePeriod(2, 255);
    ejectorMotor.setControlFramePeriod(3, 255);
    ejectorMotor.setControlFramePeriod(4, 255);
    ejectorMotor.setControlFramePeriod(8, 255);
    ejectorMotor.setControlFramePeriod(10, 255);
    ejectorMotor.setControlFramePeriod(12, 255);
    ejectorMotor.setControlFramePeriod(13, 255);
    ejectorMotor.setControlFramePeriod(14, 255);
  }

  @Override
  public void periodic() {

  }

  public void setEjectorSpeed(double speed) {
    ejectorMotor.set(ControlMode.PercentOutput, speed);
  }
}

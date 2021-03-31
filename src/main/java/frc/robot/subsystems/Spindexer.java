/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Spindexer extends SubsystemBase {

  // Spindexer Motors
  private VictorSPX spinnerMotor;

  public Spindexer() {
    // Spindexer Motors
    spinnerMotor = new VictorSPX(10);
    spinnerMotor.setInverted(true);
    spinnerMotor.setNeutralMode(NeutralMode.Coast);
  }

  @Override
  public void periodic() {

  }

  public void setSpinnerSpeed(double speed) {
    spinnerMotor.set(ControlMode.PercentOutput, speed);
  }
}

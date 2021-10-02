/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TelescopeClimb extends SubsystemBase {

  // Telescope Climb Motors
  private TalonFX teleClimbMotor;

  public TelescopeClimb() {
    // Telescope Climb Motors
    teleClimbMotor = new TalonFX(15);
    teleClimbMotor.setNeutralMode(NeutralMode.Brake);
    teleClimbMotor.setInverted(false);
  }

  @Override
  public void periodic() {

  }

  public void setTelescopeSpeed(double speed) {
    teleClimbMotor.set(ControlMode.PercentOutput, speed);
  }

  public double getTelescopePosition() {
    return teleClimbMotor.getSelectedSensorPosition();
  }

}

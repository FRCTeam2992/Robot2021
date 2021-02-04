/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Turret extends SubsystemBase {

  // Turret Motors
  private TalonSRX turretMotor;

  public Turret() {
    // Turret Motors
    turretMotor = new TalonSRX(17);
    turretMotor.setInverted(false);
    turretMotor.setNeutralMode(NeutralMode.Coast);
  }

  @Override
  public void periodic() {

  }

  public void setTurretSpeed(double speed) {
    turretMotor.set(ControlMode.PercentOutput, speed);
  }
}

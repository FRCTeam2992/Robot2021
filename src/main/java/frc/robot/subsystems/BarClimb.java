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

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BarClimb extends SubsystemBase {

  // Motors
  private VictorSPX barClimbMotor;

  // Solenoid
  private Solenoid lockSolenoid;
  private Solenoid deploySolenoid;

  public BarClimb() {
    barClimbMotor = new VictorSPX(16);
    barClimbMotor.setInverted(false);
    barClimbMotor.setNeutralMode(NeutralMode.Brake);

    lockSolenoid = new Solenoid(2);
    deploySolenoid = new Solenoid(3);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void barClimbSpeed(double speed) {
    barClimbMotor.set(ControlMode.PercentOutput, speed);
  }

  public void deployClimb(boolean toggle) {
    deploySolenoid.set(toggle);
  }

  public void deployLock(boolean toggle) {
    lockSolenoid.set(toggle);
  }
}

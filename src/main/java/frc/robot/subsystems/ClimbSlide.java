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

public class ClimbSlide extends SubsystemBase {

  private VictorSPX climbMotor;
  /**
   * Creates a new ClimbSlide.
   */
  public ClimbSlide() {
    climbMotor = new VictorSPX(12);
    climbMotor.setInverted(false);
    climbMotor.setNeutralMode(NeutralMode.Coast);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void SetClimbSpeed(double speed) {

    climbMotor.set(ControlMode.PercentOutput, speed);

  }
}
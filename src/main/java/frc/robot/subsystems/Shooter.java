/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {

  // Shooter Motors
  private TalonSRX leadShooter;
  private VictorSPX followShooter;

  // Shooter Set Speed
  public int shooterSetSpeed = Constants.defaultShooterSpeed;

  // Shooter Dashboard Update Counter
  private int dashboardCounter = 0;

  public Shooter() {
    // Shooter Motors
    leadShooter = new TalonSRX(12);
    leadShooter.setInverted(false);
    leadShooter.setNeutralMode(NeutralMode.Coast);
    leadShooter.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 60, 60, 0));

    followShooter = new VictorSPX(13);
    followShooter.setInverted(true);
    followShooter.follow(leadShooter);
  }

  @Override
  public void periodic() {
    // Display the Shooter Set Speed and Current RPM
    if (dashboardCounter >= 4) {
      SmartDashboard.putNumber("Shooter Set Speed", shooterSetSpeed);
      SmartDashboard.putNumber("Shooter Current RPM", getShooterRPM());

      dashboardCounter = 0;
    } else {
      dashboardCounter++;
    }
  }

  public void setShooterSpeed(double speed) {
    leadShooter.set(ControlMode.PercentOutput, speed);
  }

  public void setShooterVelocity(double velocity) {
    leadShooter.set(ControlMode.Velocity, velocity);
  }

  public double getShooterRPM() {
    return (leadShooter.getSelectedSensorVelocity() * 600) / (Constants.shooterEncoderPulses * 4);
  }
}
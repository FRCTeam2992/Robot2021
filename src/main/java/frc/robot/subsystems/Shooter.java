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
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
 //motors
  private TalonSRX leadShooter;
  private VictorSPX followShooter;

  //shooter set speed
  public int shooterSetSpeed = Constants.defaultShooterSpeed;

  public Shooter() {
    leadShooter = new TalonSRX(13);
    leadShooter.setInverted(false);
    leadShooter.setNeutralMode(NeutralMode.Coast);

    followShooter = new VictorSPX(14);
    followShooter.setInverted(true);
    followShooter.follow(leadShooter);
  }

  @Override
  public void periodic() {


    SmartDashboard.putNumber("Shooter Set Speed", shooterSetSpeed);
  }

  public void setShooterSpeed(double speed) {
    leadShooter.set(ControlMode.PercentOutput, speed);
  }

  public void setShooterVelocity(double velocity){
    leadShooter.set(ControlMode.Velocity, velocity);
  }

  public double getShooterRPM() {
    return (leadShooter.getSelectedSensorVelocity() * 600) / (Constants.shooterEncoderPulses * 4);
  }
}
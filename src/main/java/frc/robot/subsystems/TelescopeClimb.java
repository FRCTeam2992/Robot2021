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

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TelescopeClimb extends SubsystemBase {

  // Telescope Climb Motors
  private TalonFX teleClimbMotor;

  public boolean toggleClimbMode = false;
  public boolean climbOverride = false;

  private int dashboardCounter = 0;
  private double encoderValue = 0;

  public TelescopeClimb() {
    // Telescope Climb Motors
    teleClimbMotor = new TalonFX(15);
    teleClimbMotor.setNeutralMode(NeutralMode.Brake);
    teleClimbMotor.setInverted(false);
    teleClimbMotor.getSensorCollection().setIntegratedSensorPosition(0.0, 100);
    teleClimbMotor.setControlFramePeriod(1, 255);
    teleClimbMotor.setControlFramePeriod(4, 255);
    teleClimbMotor.setControlFramePeriod(8, 255);
    teleClimbMotor.setControlFramePeriod(10, 255);
    teleClimbMotor.setControlFramePeriod(12, 255);
    teleClimbMotor.setControlFramePeriod(13, 255);
    teleClimbMotor.setControlFramePeriod(14, 255);
    teleClimbMotor.setControlFramePeriod(22, 255);

  }

  @Override
  public void periodic() {
    encoderValue = teleClimbMotor.getSensorCollection().getIntegratedSensorPosition();
    if (++dashboardCounter >= 5) {

      SmartDashboard.putBoolean("Climb Mode", toggleClimbMode);
      SmartDashboard.putNumber("Climb Encoder", teleClimbMotor.getSensorCollection().getIntegratedSensorPosition());

      dashboardCounter = 0;
    }
  }

  public void setTelescopeSpeed(double speed){ 
    if (climbOverride && toggleClimbMode){
        teleClimbMotor.set(ControlMode.PercentOutput, speed);
      }

    else if (toggleClimbMode && encoderValue < Constants.topTeleClimbLimit && encoderValue > 0) {
      if (encoderValue > Constants.topTeleCimbSlow && speed > 0) {
        teleClimbMotor.set(ControlMode.PercentOutput, speed * Constants.teleClimbSlowModifier);
      } else if (encoderValue < Constants.bottomTeleClimbSlow && speed < 0) {
        teleClimbMotor.set(ControlMode.PercentOutput, speed * Constants.teleClimbSlowModifier);
      } else {
        teleClimbMotor.set(ControlMode.PercentOutput, speed);
      }
    }

    else {
      teleClimbMotor.set(ControlMode.PercentOutput, 0);
    }
  }

  public double getTelescopePosition() {
    return teleClimbMotor.getSelectedSensorPosition();
  }

}

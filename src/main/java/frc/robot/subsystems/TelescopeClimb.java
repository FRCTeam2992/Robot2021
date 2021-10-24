/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
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
    teleClimbMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 0);
    teleClimbMotor.setSelectedSensorPosition(0.0);
    // teleClimbMotor.getSensorCollection().setIntegratedSensorPosition(0.0, 0);
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
    encoderValue = getTelescopePosition();
    if (++dashboardCounter >= 5) {

      SmartDashboard.putBoolean("Climb Mode", toggleClimbMode);
      // SmartDashboard.putNumber("Climb Encoder", teleClimbMotor.getSensorCollection().getIntegratedSensorPosition());
      SmartDashboard.putNumber("Climb Encoder", encoderValue);
      dashboardCounter = 0;
    }
  }

  public void setTelescopeSpeed(double speed){ 

    if (!toggleClimbMode) {
      // Not in climb mode, so just stop the climber
      teleClimbMotor.set(ControlMode.PercentOutput, 0);
      return;
    }

    // All code from here down only runs if in climb mode
    if (climbOverride){
        // Climb mode is on and override button pressed to let climber move no matter what
        teleClimbMotor.set(ControlMode.PercentOutput, speed);
        return;
      }

    // All code from here down only runs if in climb mode and not using override
    if (encoderValue < Constants.topTeleClimbLimit && encoderValue > 0) {
      // We are within the climb range of 0 to topTeleClimbLimit
      if (encoderValue > Constants.topTeleCimbSlow && speed > 0) {
        // Nearing the top so slow down if moving up
        teleClimbMotor.set(ControlMode.PercentOutput, speed * Constants.teleClimbSlowModifier);
      } else if (encoderValue < Constants.bottomTeleClimbSlow && speed < 0) {
        // Nearing the bottom so slow down if moving down
        teleClimbMotor.set(ControlMode.PercentOutput, speed * Constants.teleClimbSlowModifier);
      } else {
        // We are not near the top or bottom so full speed is OK
        teleClimbMotor.set(ControlMode.PercentOutput, speed);
      }
    }

    else {
      // We are outside the climb range.  Only allow motion in proper direction
      if (encoderValue <= 0 && speed > 0) {
        // We are below the "bottom" of climb range but moving up so its OK
        teleClimbMotor.set(ControlMode.PercentOutput, speed);
      }
      else if (encoderValue >= Constants.topTeleClimbLimit && speed < 0) {
        // We are above the "top" of climb range but moving down so its OK
        teleClimbMotor.set(ControlMode.PercentOutput, speed);
      }
      else {
        // Outside climb range and not moving towards climb range so stop
        teleClimbMotor.set(ControlMode.PercentOutput, 0);
      }
    }

    
  }

  public double getTelescopePosition() {
    return teleClimbMotor.getSelectedSensorPosition();
  }

}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.lang.annotation.Target;

import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.util.DistanceDatabase;
import frc.robot.Constants;

public class AdjustabeHood extends SubsystemBase {

  // Adjustable Hood Motors
  private CANSparkMax hoodMotor;

  // Adjustable Hood Limit Switch
  private DigitalInput limitSwitch;

  // Homed State
  public boolean isHomed = false;

  // Adjustable Hood Dashboard Update Counter
  private int dashboardCounter = 0;

<<<<<<< Updated upstream
  //Preset Positions
  public DistanceDatabase presetPostions;
=======
  public double hoodTarget = 0;
>>>>>>> Stashed changes

  public AdjustabeHood() {
    // Adjustables Hood Motors
    hoodMotor = new CANSparkMax(14, MotorType.kBrushless);
    hoodMotor.setInverted(false);
    hoodMotor.setIdleMode(IdleMode.kBrake);

    // Set the Hood PID Controllers
    CANPIDController hoodController = hoodMotor.getPIDController();
    hoodController.setOutputRange(Constants.lowerHoodSpeedLimit, Constants.upperHoodSpeedLimit);
    hoodController.setP(Constants.hoodP);
    hoodController.setI(Constants.hoodI);
    hoodController.setD(Constants.hoodD);

    // Adjustable Hood Limit Switch
    limitSwitch = new DigitalInput(0);

    presetPostions = new DistanceDatabase();
    presetPostions.addSetpoint(17.5, 1.375);
    presetPostions.addSetpoint(30.5, 4.452);
    presetPostions.addSetpoint(44.5, 5.119);
    presetPostions.addSetpoint(54, 5.7);
    presetPostions.addSetpoint(65.4, 6.9);
    presetPostions.addSetpoint(72, 7.57);
  }

  @Override
  public void periodic() {
    // Display Hood Position and Limit Switch State
    if (dashboardCounter >= 4) {
      SmartDashboard.putNumber("Current Hood Position", hoodMotor.getEncoder().getPosition());
      SmartDashboard.putBoolean("Hood Limit Switch", limitSwitch.get());


      dashboardCounter = 0;
    } else {
      dashboardCounter++;
    }
  }

  public void setAdjustableShooterHoodSpeed(double speed) {
    hoodMotor.set(speed);
  }

  public void setHoodPosition(double position) {
    hoodMotor.getPIDController().setReference(position, ControlType.kPosition);
    hoodTarget = position;
  }

  public void startHood() {
    hoodMotor.getPIDController().setReference(hoodTarget, ControlType.kPosition);
  }

  public boolean getLimitState() {
    return limitSwitch.get();
  }

  public double getHoodPosition() {
    return hoodMotor.getEncoder().getPosition();
  }

  public double getHoodTarget() {
    return hoodTarget;
  }

  public void setHoodTarget(double target) {
    hoodTarget = target;
  }

  public void zeroHoodMotor() {
    hoodMotor.getEncoder().setPosition(0.0);
  }
}

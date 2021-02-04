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
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ColorWheel extends SubsystemBase {

  // Color Wheel Motors
  private TalonSRX colorWheelMotor;

  // Color Wheel Soleniods
  private Solenoid colorWheelSolenoid;

  // Color Sensor
  private final ColorSensorV3 colorSensor;
  private final I2C.Port sensor = I2C.Port.kOnboard;
  private final ColorMatch colorMatcher = new ColorMatch();

  // Target Color States
  public enum TargetColor {
    Blue, Green, Red, Yellow, Unknown, Corrupt
  }

  public ColorWheel() {
    // Color Wheel Motors
    colorWheelMotor = new TalonSRX(15);
    colorWheelMotor.setInverted(false);
    colorWheelMotor.setNeutralMode(NeutralMode.Coast);

    // Color Wheel Solenoids
    colorWheelSolenoid = new Solenoid(3);

    // Color Sensor
    colorSensor = new ColorSensorV3(sensor);

    // Add Colors to the Matcher
    colorMatcher.addColorMatch(Constants.kBlueTarget);
    colorMatcher.addColorMatch(Constants.kGreenTarget);
    colorMatcher.addColorMatch(Constants.kRedTarget);
    colorMatcher.addColorMatch(Constants.kYellowTarget);
  }

  @Override
  public void periodic() {

  }

  public void stopColorWheel() {
    colorWheelMotor.set(ControlMode.PercentOutput, 0);
  }

  public void setColorWheelSpeed(double speed) {
    colorWheelMotor.set(ControlMode.PercentOutput, speed);
  }

  public void setColorWheelPosition(double position) {
    colorWheelMotor.set(ControlMode.PercentOutput, position);
  }

  public double getMotorPosition() {
    return colorWheelMotor.getSelectedSensorPosition();
  }

  public void zeroMotorPosition() {
    colorWheelMotor.setSelectedSensorPosition(0);
  }

  public void deployColorWheel(boolean toggle) {
    colorWheelSolenoid.set(toggle);
  }

  public TargetColor getDetectedColor() {
    Color detectedColor = colorSensor.getColor();

    ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);

    if (match.color == Constants.kBlueTarget) {
      return TargetColor.Blue;
    } else if (match.color == Constants.kRedTarget) {
      return TargetColor.Red;
    } else if (match.color == Constants.kGreenTarget) {
      return TargetColor.Green;
    } else if (match.color == Constants.kYellowTarget) {
      return TargetColor.Yellow;
    } else {
      return TargetColor.Unknown;
    }
  }

  public TargetColor getFMSColorData() {
    String gameData = DriverStation.getInstance().getGameSpecificMessage();

    if (gameData.length() > 0) {
      switch (gameData.charAt(0)) {
        case 'B':
          return TargetColor.Blue;
        case 'G':
          return TargetColor.Green;
        case 'R':
          return TargetColor.Red;
        case 'Y':
          return TargetColor.Yellow;
        default:
          return TargetColor.Corrupt;
      }
    } else {
      return TargetColor.Unknown;
    }
  }
}

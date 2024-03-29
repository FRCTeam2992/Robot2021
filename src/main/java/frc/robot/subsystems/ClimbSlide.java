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

  // Climb Slide Motors
  private VictorSPX slideMotor;

  
  public ClimbSlide() {
    // Climb Slide Motors
    slideMotor = new VictorSPX(16);
    slideMotor.setInverted(false);
    slideMotor.setNeutralMode(NeutralMode.Coast);
  }

  @Override
  public void periodic() {

  }

  public void setSlideSpeed(double speed) {
      slideMotor.set(ControlMode.PercentOutput, speed);
    }
  }


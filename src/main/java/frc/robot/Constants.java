/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.util.Color;

public final class Constants {
    //teleclimb
    public static final int teleClimbLimit = 4000;

    //shooter
    public static final int defaultShooterSpeed = 5200;
    public static final int shooterEncoderPulses = 2048;
    
// Color Wheel Target Colors
public static final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
public static final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
public static final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
public static final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
}

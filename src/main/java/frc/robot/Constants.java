/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.util.Color;

public final class Constants {

    // Telescope Climb Variables
    public static final int teleClimbLimit = 4000;

    // Shooter Variables
    public static final int defaultShooterSpeed = 5200;
    public static final int shooterEncoderPulses = 2048;

    // Color Wheel Variables
    public static final int colorWheelEncoderPulses = 1024;
    public static final double colorWheelSpinRatio = 8.0;
    public static final double colorWheelSpinRotations = 4.0;

    // Color Wheel Target Colors
    public static final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    public static final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    public static final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    public static final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

    // Drive Variables
    public static boolean isFieldCentric = true;
    public static boolean isVelocityControlled = false;

    // Length and Width of the Robot in Meters (Inches: 22.0 x 24.5)
    public static final double swerveWidth = 0.5588;
    public static final double swerveLength = 0.6223;

    // Max Swerve Speed (Velocity Control)
    public static final double swerveMaxSpeed = 3.5; // (Meters per Second)

    // Max Path Following Drive Speeds
    public static final double maxPathFollowingVelocity = 1.5; // (Meters per Second)
    public static final double maxPathFollowingAcceleration = 0.5; // (Meters per Second Squared)

    // Max Path Following Turn Speeds
    public static final double maxThetaVelocity = 3.14; // (Radians per Second)
    public static final double maxThetaAcceleration = 1.57; // (Radians per Second Squared)

    // Swerve Module Translations
    public static final Translation2d frontLeftLocation = new Translation2d(0.31115, 0.2794);
    public static final Translation2d frontRightLocation = new Translation2d(0.31115, -0.2794);
    public static final Translation2d rearLeftLocation = new Translation2d(-0.31115, 0.2794);
    public static final Translation2d rearRightLocation = new Translation2d(-0.31115, -0.2794);

    // Swerve Wheels and Gear Ratio
    public static final double driveGearRatio = (40.0 / 16.0) * (18.0 / 26.0) * (60.0 / 15.0);
    public static final double driveWheelDiameter = 0.1016;

    // Analog Encoder Offsets (Degrees) - Opposite of Reading
    public static final double frontLeftOffset = -179.0; // -111.7
    public static final double frontRightOffset = 16.9; // 0.8
    public static final double rearLeftOffset = 91.4; // 98.0
    public static final double rearRightOffset = 34.7; // 94.8

    // Swerve Drive PID (Velocity Control)
    public static final double driveP = 0.00025;
    public static final double driveI = 0.0;
    public static final double driveD = 0.0;
    public static final double driveF = 0.00018;

    // Swerve Turn PID
    public static final double turnP = 0.005;
    public static final double turnI = 0.0;
    public static final double turnD = 0.0;

    // Swerve X Axis Correction PID (Path Following)
    public static final double xCorrectionP = 1.0;
    public static final double xCorrectionI = 0.0;
    public static final double xCorrectionD = 0.0;

    // Swerve Y Axis Correction PID (Path Following)
    public static final double yCorrectionP = 1.0;
    public static final double yCorrectionI = 0.0;
    public static final double yCorrectionD = 0.0;

    // Swerve Theta Axis Correction PID (Path Following)
    public static final double thetaCorrectionP = 1.0;
    public static final double thetaCorrectionI = 0.0;
    public static final double thetaCorrectionD = 0.0;
}

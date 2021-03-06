package frc.lib.drive.swerve;

import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.SwerveModuleState;

public class SwerveController {

    // Saved Variables
    private double length;
    private double width;

    public SwerveController(double length, double width) {
        // Saved Variables
        this.length = length;
        this.width = width;
    }

    /**
     * @param x1 Joystick Strafe
     * @param y1 Joystick Speed
     * @param x2 Joystick Rotation
     */
    public SwerveModuleState[] calculate(double x1, double y1, double x2) {
        // Swerve Variables
        double L = length / 2.0;
        double W = width / 2.0;
        double r = Math.sqrt((L * L) + (W * W));

        // --------------------------------------
        // Swerve Module Math for Speed and Angle
        // --------------------------------------
        double a = x1 - x2 * (L / r);
        double b = x1 + x2 * (L / r);
        double c = y1 - x2 * (W / r);
        double d = y1 + x2 * (W / r);

        double frontLeftSpeed = Math.sqrt((b * b) + (c * c));
        double frontRightSpeed = Math.sqrt((b * b) + (d * d));
        double rearLeftSpeed = Math.sqrt((a * a) + (c * c));
        double rearRightSpeed = Math.sqrt((a * a) + (d * d));

        double frontLeftAngle = Math.atan2(b, c) * 180.0 / Math.PI;
        double frontRightAngle = Math.atan2(b, d) * 180.0 / Math.PI;
        double rearLeftAngle = Math.atan2(a, c) * 180.0 / Math.PI;
        double rearRightAngle = Math.atan2(a, d) * 180.0 / Math.PI;

        // -------------------------------------
        // Normalize the Speed
        // -------------------------------------
        double max = frontLeftSpeed;
        max = Math.max(max, frontRightSpeed);
        max = Math.max(max, rearLeftSpeed);
        max = Math.max(max, rearRightSpeed);

        if (max > 1) {
            frontRightSpeed /= max;
            frontLeftSpeed /= max;
            rearLeftSpeed /= max;
            rearRightSpeed /= max;
        }

        SwerveModuleState[] stateArray = new SwerveModuleState[4];

        stateArray[0] = new SwerveModuleState(frontLeftSpeed, Rotation2d.fromDegrees(frontLeftAngle));
        stateArray[1] = new SwerveModuleState(frontRightSpeed, Rotation2d.fromDegrees(frontRightAngle));
        stateArray[2] = new SwerveModuleState(rearLeftSpeed, Rotation2d.fromDegrees(rearLeftAngle));
        stateArray[3] = new SwerveModuleState(rearRightSpeed, Rotation2d.fromDegrees(rearRightAngle));

        return stateArray;
    }

    /**
     * @param x1   Joystick Strafe
     * @param y1   Joystick Speed
     * @param x2   Joystick Rotation
     * @param gyro Gyro Input (-180 to 180)
     */
    public SwerveModuleState[] calculate(double x1, double y1, double x2, double gyroInput) {
        // Swerve Variables
        double L = length / 2.0;
        double W = width / 2.0;
        double r = Math.sqrt((L * L) + (W * W));

        // Field Centric Code from NAVX Website
        double gyro = gyroInput * Math.PI / 180.0;

        double temp = x1 * Math.cos(gyro) + y1 * Math.sin(gyro);
        y1 = -x1 * Math.sin(gyro) + y1 * Math.cos(gyro);
        x1 = temp;

        // --------------------------------------
        // Swerve Module Math for Speed and Angle
        // --------------------------------------
        double a = x1 - x2 * (L / r);
        double b = x1 + x2 * (L / r);
        double c = y1 - x2 * (W / r);
        double d = y1 + x2 * (W / r);

        double frontLeftSpeed = Math.sqrt((b * b) + (c * c));
        double frontRightSpeed = Math.sqrt((b * b) + (d * d));
        double rearLeftSpeed = Math.sqrt((a * a) + (c * c));
        double rearRightSpeed = Math.sqrt((a * a) + (d * d));

        double frontLeftAngle = Math.atan2(b, c) * 180.0 / Math.PI;
        double frontRightAngle = Math.atan2(b, d) * 180.0 / Math.PI;
        double rearLeftAngle = Math.atan2(a, c) * 180.0 / Math.PI;
        double rearRightAngle = Math.atan2(a, d) * 180.0 / Math.PI;

        // -------------------------------------
        // Normalize the Speed
        // -------------------------------------
        double max = frontLeftSpeed;
        max = Math.max(max, frontRightSpeed);
        max = Math.max(max, rearLeftSpeed);
        max = Math.max(max, rearRightSpeed);

        if (max > 1) {
            frontRightSpeed /= max;
            frontLeftSpeed /= max;
            rearLeftSpeed /= max;
            rearRightSpeed /= max;
        }

        SwerveModuleState[] stateArray = new SwerveModuleState[4];

        stateArray[0] = new SwerveModuleState(frontLeftSpeed, Rotation2d.fromDegrees(frontLeftAngle));
        stateArray[1] = new SwerveModuleState(frontRightSpeed, Rotation2d.fromDegrees(frontRightAngle));
        stateArray[2] = new SwerveModuleState(rearLeftSpeed, Rotation2d.fromDegrees(rearLeftAngle));
        stateArray[3] = new SwerveModuleState(rearRightSpeed, Rotation2d.fromDegrees(rearRightAngle));

        return stateArray;
    }
}

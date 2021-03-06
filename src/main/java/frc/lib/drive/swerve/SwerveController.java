package frc.lib.drive.swerve;

public class SwerveController {

    // Saved Variables
    private double length;
    private double width;
    private boolean isFieldCentric;

    public SwerveController(double length, double width, boolean isFieldCentric) {
        // Saved Variables
        this.length = length;
        this.width = width;
        this.isFieldCentric = isFieldCentric;
    }

    /**
     * @param x1   Joystick Strafe
     * @param y1   Joystick Speed
     * @param x2   Joystick Rotation
     * @param gyro Gyro Input (-180 to 180)
     */
    public void calculate(double x1, double y1, double x2, double gyroInput) {
        // Check for Movement
        if (Math.abs(x1) >= 0.1 || Math.abs(y1) >= 0.1 || Math.abs(x2) >= 0.1) {

            // Swerve Variables
            double L = length / 2.0;
            double W = width / 2.0;
            double r = Math.sqrt((L * L) + (W * W));

            // Field Centric Code from NAVX Website
            if (isFieldCentric) {
                double gyro = gyroInput * Math.PI / 180.0;

                double temp = x1 * Math.cos(gyro) + y1 * Math.sin(gyro);
                y1 = -x1 * Math.sin(gyro) + y1 * Math.cos(gyro);
                x1 = temp;
            }

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
        }
    }
}

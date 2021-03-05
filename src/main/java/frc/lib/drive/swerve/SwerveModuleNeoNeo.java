package frc.lib.drive.swerve;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.SwerveModuleState;

public class SwerveModuleNeoNeo {

    // Saved Variables
    private CANSparkMax driveMotor;
    private CANSparkMax turnMotor;
    private AnalogInput encoderInput;
    private double encoderOffset;
    private PIDController turnPID;
    private double wheelDiameter;
    private double wheelGearRatio;
    private double maxDriveSpeed;

    public SwerveModuleNeoNeo(CANSparkMax driveMotor, CANSparkMax turnMotor, AnalogInput encoderInput, double encoderOffset,
            PIDController turnPID, double wheelDiameter, double wheelGearRatio, double maxDriveSpeed) {
        // Saved Variables
        this.driveMotor = driveMotor;
        this.turnMotor = turnMotor;
        this.encoderInput = encoderInput;
        this.encoderOffset = encoderOffset;
        this.turnPID = turnPID;
        this.wheelDiameter = wheelDiameter;
        this.wheelGearRatio = wheelGearRatio;
        this.maxDriveSpeed = maxDriveSpeed;
    }

    public void setDriveSpeed(double speed) {
        driveMotor.set(speed);
    }

    public void setTurnSpeed(double speed) {
        turnMotor.set(speed);
    }

    public void stop() {
        setDriveSpeed(0.0);
        setTurnSpeed(0.0);
    }

    public void setTurnAngle(double degrees) {
        degrees = Math.min(Math.max(degrees, -180.0), 180.0);
        setTurnSpeed(turnPID.calculate(getEncoderAngle(), degrees));
    }

    public void setVelocityMeters(double speed) {
        double RPM = (speed * wheelGearRatio * 60) / (wheelDiameter * Math.PI);

        driveMotor.getPIDController().setReference(RPM, ControlType.kVelocity);
    }

    public void setDrive(double speed, double angle) {
        if (Math.abs(getEncoderAngle() - angle) > 90.0) {
            if (angle > 0) {
                angle -= 180.0;
            } else {
                angle += 180.0;
            }

            speed = -speed;
        }

        setDriveSpeed(speed);
        setTurnAngle(angle);
    }

    public void setDriveVelocity(double speedPercent, double angle) {
        double speed = speedPercent * maxDriveSpeed;

        if (Math.abs(getEncoderAngle() - angle) > 90.0) {
            if (angle > 0) {
                angle -= 180.0;
            } else {
                angle += 180.0;
            }

            speed = -speed;
        }

        setVelocityMeters(speed);
        setTurnAngle(angle);
    }

    public boolean atAngle() {
        return turnPID.atSetpoint();
    }

    public double getEncoderAngle() {
        double tempAngle = (encoderInput.getVoltage() * (360.0 / 5.0)) - encoderOffset;

        tempAngle -= 180.0;

        if (tempAngle < -180.0) {
            tempAngle += 360.0;
        } else if (tempAngle > 180.0) {
            tempAngle -= 360.0;
        }

        return -tempAngle;
    }

    public double getWheelSpeedMeters() {
        double RPM = driveMotor.getEncoder().getVelocity();

        double speed = (RPM * wheelDiameter * Math.PI) / (wheelGearRatio * 60);

        return speed;
    }

    public SwerveModuleState getState() {
        return new SwerveModuleState(getWheelSpeedMeters(), Rotation2d.fromDegrees(getEncoderAngle()));
    }

    public void setState(SwerveModuleState state) {
        double angle = state.angle.getDegrees();
        double speed = state.speedMetersPerSecond;

        setVelocityMeters(speed);
        setTurnAngle(angle);
    }
}

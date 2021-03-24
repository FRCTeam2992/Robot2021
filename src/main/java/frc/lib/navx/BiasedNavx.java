package frc.lib.navx;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

/**
 * Wrapper class for navX gyro adding a manually set bias to gyro reset/zero Yaw
 */
public class BiasedNavx extends AHRS {

    /**
     * Call NavX super class constructor
     * 
     * @param port
     */
    public BiasedNavx (SPI.Port port) {
        super(port);
    }

    private double mYawBias = 0.0;

    public void setBias(double bias) {
        mYawBias = bias;
    }

    /**
     * Overrides getYaw to add bias factor.  Makes sure to unwrap to -180 - 180
     */
    public float getYaw() {
        double yaw = super.getYaw() + mYawBias;

        // Insure it's in range -180 to 180
        while (yaw < -180.0) {
            yaw += 360.0;
        }
        while (yaw > 180.0) {
            yaw -= 360.0;
        }

        return ((float) yaw);
    
    }
}

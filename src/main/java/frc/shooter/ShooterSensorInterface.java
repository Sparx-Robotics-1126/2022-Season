package frc.shooter;

/**
 * Any and all sensor data required for operation of the Shooter subsystem
 */
public interface ShooterSensorInterface {
    double getShooterSpeed();

    double getAngleToTarget();
    double getDistanceToTarget();

    void enableLimelight(boolean enable);
}

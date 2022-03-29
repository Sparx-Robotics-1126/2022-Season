package frc.shooter;

import com.revrobotics.RelativeEncoder;

/**
 * Any and all sensor data required for operation of the Shooter subsystem
 */
public interface ShooterSensorInterface 
{
    void addEncoders(RelativeEncoder shooterSpark);
    double getSpeed();
    double getAngleToTarget();
    double getDistanceToTarget();
    double getMotorDistance();
    double percentageToRPM(double percentage);
}
package frc.storage;

import com.revrobotics.RelativeEncoder;

/**
 * Any and all sensor data required for operation of the Storage subsystem such as encoders, limit switches, etc..
 */
public interface StorageSensorInterface
{
    //Sensors
    double getStorageEncoderDistance();
    void addEncoders(RelativeEncoder encoder);
    boolean getStorageIRSensor();
}

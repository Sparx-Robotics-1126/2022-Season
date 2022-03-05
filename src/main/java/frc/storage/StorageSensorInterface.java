package frc.storage;

/**
 * Any and all sensor data required for operation of the Storage subsystem such as encoders, limit switches, etc..
 */
public interface StorageSensorInterface
{
    //Sensors
    double getStorageEncoderDistance();

    //Operator input
    boolean getStorageButton();
    void setStorageButton(boolean value);
}
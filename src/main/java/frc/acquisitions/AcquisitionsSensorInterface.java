package frc.acquisitions;

/**
 * Any and all sensor data required for operation of the Aquisitions subsystem such as encoders, limit switches, etc..
 */
public interface AcquisitionsSensorInterface
{
    //Sensors
    double getArmEncoderDistance();
    boolean ballInRange();
    boolean isCorrectColor();
}
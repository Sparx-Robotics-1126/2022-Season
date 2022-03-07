package frc.storage;

public interface StorageSensorInterface {
    
     //Sensors
     double getArmEncoderDistance();
 
     //Operator input
     boolean getArmButton();
     void setArmButton(boolean value);
}

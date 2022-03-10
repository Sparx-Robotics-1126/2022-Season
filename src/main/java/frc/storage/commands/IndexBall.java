package frc.storage.commands;

import frc.storage.StorageCommand;
import frc.storage.StorageOutput;
import frc.storage.StorageSensorInterface;
import frc.subsystem.Storage;

public class IndexBall extends StorageCommand {
    
    private boolean pastStorageSensorValue;
    final double STORAGE_MOTOR_SPEED = .25;     //placeholder
    public IndexBall(StorageSensorInterface sensors){
        super(sensors);
        pastStorageSensorValue = sensors.getStorageIRSensor();
    }

    @Override
    public StorageOutput execute() {
        if(!pastStorageSensorValue && getSensors().getStorageIRSensor()){
            pastStorageSensorValue = true;
            return new StorageOutput(STORAGE_MOTOR_SPEED, false);
        }else if(pastStorageSensorValue && !getSensors().getStorageIRSensor()){
            pastStorageSensorValue = false;
        }
        return new StorageOutput(0, true);
    }
}

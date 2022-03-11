package frc.storage.commands;

import frc.storage.StorageCommand;
import frc.storage.StorageOutput;
import frc.storage.StorageSensorInterface;

public class IndexBall extends StorageCommand 
{
    private final double STORAGE_MOTOR_SPEED = 0.05;

    private boolean pastStorageSensorValue;

    public IndexBall(StorageSensorInterface sensors)
    {
        super(sensors);
        pastStorageSensorValue = sensors.getStorageIRSensor();
    }

    @Override
    public StorageOutput execute() {
        /*if (!pastStorageSensorValue && getSensors().getStorageIRSensor())
        {
            pastStorageSensorValue = true;
            return new StorageOutput(STORAGE_MOTOR_SPEED, false);
        }
        else if(pastStorageSensorValue && !getSensors().getStorageIRSensor())
            pastStorageSensorValue = false;*/

        return new StorageOutput(0.5, true);
    }
}
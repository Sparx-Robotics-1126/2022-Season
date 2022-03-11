package frc.storage.commands;

import frc.storage.StorageCommand;
import frc.storage.StorageOutput;
import frc.storage.StorageSensorInterface;
import frc.subsystem.Storage;

public class CheckForBalls extends StorageCommand
{
    // create a counter that stops at 2 and does not let any balls in afterwards.
    //each time a sensor senses a ball, increase ball counter by 1. 
    private boolean pastStorageSensorValue;
    
    public CheckForBalls(StorageSensorInterface sensors)
    {
        super(sensors);

        pastStorageSensorValue = sensors.getStorageIRSensor();
    } 

    @Override
    public StorageOutput execute() 
    {
        //!pastStorageSensorValue &&
        if (getSensors().getStorageIRSensor())
        {
            if (!pastStorageSensorValue && Storage.getNumBalls() < 2)
                Storage.setNumBalls(Storage.getNumBalls() + 1);

            pastStorageSensorValue = true;

            return new StorageOutput(0.25, false);
        }
        else //if (pastStorageSensorValue) //&& !getSensors().getStorageIRSensor()
        {
            pastStorageSensorValue = false;
            
            return new StorageOutput(0, true);
        }
    }
}
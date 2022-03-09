package frc.storage.commands;

import frc.storage.StorageCommand;
import frc.storage.StorageOutput;
import frc.storage.StorageSensorInterface;

public class CheckForBalls extends StorageCommand
{
    // create a counter that stops at 2 and does not let any balls in afterwards.
    //each time a sensor senses a ball, increase ball counter by 1. 
    private short amountOfBalls;
    private boolean pastStorageSensorValue;
    
    public CheckForBalls(StorageSensorInterface sensors, short amountOfBalls)
    {
        super(sensors);
        this.amountOfBalls = amountOfBalls;
        pastStorageSensorValue = sensors.getStorageIRSensor();
    }

    @Override
    public StorageOutput execute() 
    {
        if(!pastStorageSensorValue && getSensors().getStorageIRSensor()){
            pastStorageSensorValue = true;
            if (amountOfBalls < 2)
                amountOfBalls += 1;
        }else if(pastStorageSensorValue && !getSensors().getStorageIRSensor()){
            pastStorageSensorValue = false;
        }

        return new StorageOutput(amountOfBalls, true);
    }
}
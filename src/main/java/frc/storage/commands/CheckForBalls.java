package frc.storage.commands;

import frc.storage.StorageCommand;
import frc.storage.StorageOutput;
import frc.storage.StorageSensorInterface;

public class CheckForBalls extends StorageCommand
{
    // create a counter that stops at 2 and does not let any balls in afterwards.
    //each time a sensor senses a ball, increase ball counter by 1. 
    
    public CheckForBalls(StorageSensorInterface sensors)
    {
        super(sensors);
    }

    @Override
    public StorageOutput execute() 
    {
        // TODO Auto-generated method stub
        return null;
    }
}
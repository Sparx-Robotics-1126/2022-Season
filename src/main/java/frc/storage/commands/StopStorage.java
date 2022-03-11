package frc.storage.commands;

import frc.storage.StorageCommand;
import frc.storage.StorageOutput;
import frc.storage.StorageSensorInterface;

public class StopStorage extends StorageCommand
{
    public StopStorage(StorageSensorInterface sensors)
    {
        super(sensors);
    }

    @Override
    public StorageOutput execute()
    {
        return new StorageOutput(0, true);
    }
}
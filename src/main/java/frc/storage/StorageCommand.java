package frc.storage;

/**
 * Abstract class allowing the creation of commands for use in the Storage subsystem.
 */
public abstract class StorageCommand 
{
    private StorageSensorInterface sensors;

    /**
     * Create a new command for use in coordination with the Storage subsystem.
     * @param sensors The StorageSensorInterface for use within the command.
     */
    public StorageCommand(StorageSensorInterface sensors)
    {
        this.sensors = sensors;
    }

    /**
     * @return The StorageSensorInterface being used for this StorageCommand.
     */
    public StorageSensorInterface getSensors()
    {
        return sensors;
    }

    /**
     * Called every update of the Storage thread when this StorageCommand is being run.
     * @return An StorageOutput object containing the requested motor speeds.
     */
    public abstract StorageOutput execute(); 
}
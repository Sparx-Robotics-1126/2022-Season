package frc.drives;

/**
 * Abstract class allowing the creation of commands for use in the Drives subsystem.
 */
public abstract class DrivesCommand
{
    private DrivesSensorInterface sensors;

    /**
     * Create a new command for use in coordination with the Drives subsystem.
     * @param sensors The DrivesSensorInterface for use within the command.
     */
    public DrivesCommand(DrivesSensorInterface sensors)
    {
        this.sensors = sensors;
    }

    /**
     * @return The DrivesSensorInterface being used for this DrivesCommand.
     */
    public DrivesSensorInterface getSensors()
    {
        return sensors;
    }

    /**
     * Called every update of the Drives thread when this DrivesCommand is being run.
     * @return A DrivesOutput object containing the requested motor speeds.
     */
    public abstract DrivesOutput execute();
}
package frc.acquisitions;

/**
 * Abstract class allowing the creation of commands for use in the Acquisitions subsystem.
 */
public abstract class AcquisitionsCommand 
{
    private AcquisitionsSensorInterface sensors;

    /**
     * Create a new command for use in coordination with the Acquisitions subsystem.
     * @param sensors The AcquisitionsSensorInterface for use within the command.
     */
    public AcquisitionsCommand(AcquisitionsSensorInterface sensors)
    {
        this.sensors = sensors;
    }

    /**
     * @return The AcquisitionsSensorInterface being used for this AcquisitionsCommand.
     */
    public AcquisitionsSensorInterface getSensors()
    {
        return sensors;
    }

    /**
     * Called every update of the Acquisitions thread when this AcquisitionsCommand is being run.
     * @return An AcquisitionsOutput object containing the requested motor speeds.
     */
    public abstract AcquisitionsOutput execute();
}
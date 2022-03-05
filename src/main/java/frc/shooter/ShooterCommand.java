package frc.shooter;

/**
 * Abstract class allowing the creation of commands for use in the Shooter subsystem.
 */
public abstract class ShooterCommand 
{
    private ShooterSensorInterface sensors;

    /**
     * Create a new command for use in coordination with the Shooter subsystem.
     * @param sensors The ShooterSensorInterface for use within the command.
     */
    public ShooterCommand(ShooterSensorInterface sensors)
    {
        this.sensors = sensors;
    }

    /**
     * Returns the ShooterSensorInterface sensors
     */
    public ShooterSensorInterface getSensors()
    {
        return sensors;
    }

    /**
     * Called every update of the Shooter thread when this ShooterCommand is being run.
     * @return A ShooterOutput object containing the requested motor speeds.
     */
    public abstract ShooterOutput execute();
}

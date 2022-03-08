package frc.storage;

/**
 * Class allowing the storage of speed values for motors. Used when setting motor speeds in the Storage subsystem.
 */
public class StorageOutput 
{
    /**
     * Output for the motor. Value between -1 and 1.
     */
    private double motor;

    /**
     * True if the command has finished executing.
     */
    private boolean isDone;

    /**
     * Used to set and store new speed values for the motors.
     * @param motor The speed that the storage motor should be set to.
     */
    public StorageOutput(double motor)
    {
        this.motor = motor;
        this.isDone = false;
    }

    /**
     * Used to set and store new speed values for the motors.
     * @param motor The speed that the motor should be set to.
     * @param isDone True if the command has finished executing.
     */
    public StorageOutput(double motor, boolean isDone)
    {
        this.motor = motor;
        this.isDone = isDone;
    }

    /**
     * @return The set output speed of the motor.
     */
    public double get() 
    {
        return motor;
    }

    /**
     * @return Whether or not the command has finished executing and should no longer be ran in the subsystem thread.
     */
    public boolean isDone() 
    {
        return isDone;
    }
}
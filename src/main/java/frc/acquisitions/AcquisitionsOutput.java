package frc.acquisitions;

/**
 * Class allowing the storage of speed values for motors. Used when setting motor speeds in the Acquisitions subsystem.
 */
public class AcquisitionsOutput 
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
     * @param armMotor The speed that the arm motor should be set to.
     * @param intakeMotor The speed that the intake motor should be set to.
     */
    public AcquisitionsOutput(double motor)
    {
        this.motor = motor;
        this.isDone = false;
    }

    /**
     * Used to set and store new speed values for the motors.
     * @param motor The speed that the motor should be set to.
     * @param isDone True if the command has finished executing.
     */
    public AcquisitionsOutput(double motor, boolean isDone)
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
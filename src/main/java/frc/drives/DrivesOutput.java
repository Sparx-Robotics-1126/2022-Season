package frc.drives;

/**
 * Class allowing the storage of speed values for motors. Used when setting motor speeds in the Drives subsystem.
 */
public class DrivesOutput 
{
    /**
     * Output for the left motors. Value between -1 and 1.
     */
    private double leftMotor;

    /**
     * Output for the right motors. Value between -1 and 1.
     */
    private double rightMotor;

    /**
     * True if the command has finished executing.
     */
    private boolean isDone;

    /**
     * Used to set and store new speed values for the motors.
     * @param leftMotor The speed that the left motor should be set to.
     * @param rightMotor The speed that the right motor should be set to.
     */
    public DrivesOutput(double leftMotor, double rightMotor)
    {
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;
        this.isDone = false;
    }

    /**
     * Used to set and store new speed values for the motors.
     * @param leftMotor The speed that the left motor should be set to.
     * @param rightMotor The speed that the right motor should be set to.
     * @param isDone True if the command has finished executing.
     */
    public DrivesOutput(double leftMotor, double rightMotor, boolean isDone)
    {
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;
        this.isDone = isDone;
    }

    /**
     * @return The set output speed of the left motor.
     */
    public double getLeftMotor() 
    {
        return leftMotor;
    }

    /**
     * @return The set output speed of the right motor.
     */
    public double getRightMotor() 
    {
        return rightMotor;
    }

    /**
     * @return Whether or not the command has finished executing and should no longer be ran in the subsystem thread.
     */
    public boolean isDone() 
    {
        return isDone;
    }
}

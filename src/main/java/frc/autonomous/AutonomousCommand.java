package frc.autonomous;

public abstract class AutonomousCommand 
{
    /**
     * Called when this AutonomousCommand should be run.
     */
    public abstract void execute();

    /**
     * @return A boolean indicating whether or not this AutonomousCommand has finished executing.
     */
    public abstract boolean isDone();
}
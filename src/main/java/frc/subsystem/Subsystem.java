package frc.subsystem;

/**
 * An abstract class that provides the framework for all active systems on the robot.
 */
public abstract class Subsystem implements Runnable 
{
    /**
     * Determines how this Subsystem should behave (dependent on subclass implementation).
     */
    public enum SubsystemState
    {
        NORMAL,
        OVERRIDE
    }

    private SubsystemState state;

    Subsystem()
    {
        state = SubsystemState.NORMAL;
    }

    @Override
    public void run()
    {
    	while (true) execute();
    }

    /**
     * @return The current SubsystemState attached to this Subsystem.
     */
    public final SubsystemState getState()
    {
        return state;
    }

    /**
     * Sets the state of this Subsystem to the specified newState.
     * @param newState The SubsystemState to set this Subsystem to.
     */
    public final void setState(SubsystemState newState)
    {
        state = newState;
    }

    /**
     * Called every update/tick of this subsystem.
     */
    abstract void execute();

    /**
     * @return A boolean indicating whether or not the Subsystem is idle.
     */
    public abstract boolean isDone();
}
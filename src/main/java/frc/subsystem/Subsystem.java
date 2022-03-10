package frc.subsystem;

/**
 * An abstract class that provides the framework for all active systems on the robot.
 */
public abstract class Subsystem implements Runnable 
{
    @Override
    public void run()
    {
    	while (true) execute();
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
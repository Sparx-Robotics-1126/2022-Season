package frc.subsystem;

public abstract class Subsystem implements Runnable 
{
    /**
     * Called every update/tick of the main loop.
     */
    @Override
    public void run()
    {
    	while (true)
        {
    		execute();
    	}
    }

    /**
     * Called every update/tick of this subsystem.
     */
    abstract void execute();

    /**
     * Used during autonomous to detect when a task is completed.
     */
    public abstract boolean isDone();
}

package frc.subsystem;

public abstract class Subsystem implements Runnable 
{
    /**
     * This is called continuously by the Thread created in Robot.java.
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
     * Called by run(). This is where the code for the subsystem goes.
     */
    abstract void execute();

    /**
     * Used during autonomous to see what a task (such as drives a certain distance forward) is completed.
     */
    public abstract boolean isDone();
}

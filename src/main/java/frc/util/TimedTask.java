package frc.util;

public abstract class TimedTask implements Runnable
{
    private int delay;

    /**
     * Creates a new TimedTask to be executed every certain amount of milliseconds.
     * @param delay The amount of milliseconds between executions of this TimedTask.
     */
    public TimedTask(int delay)
    {
        this.delay = delay;
    }

    /**
     * The code that should be executed after each delay.
     */
    public abstract void execute();

    /**
     * Starts this TimedTask.
     */
    public void start()
    {
        new Thread(this).start();
    }

    @Override
    public void run()
    {
        while (true)
        {
            execute();
            try { Thread.sleep(delay); } catch (InterruptedException e) {}
        }
    }
}
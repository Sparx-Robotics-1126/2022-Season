package frc.autonomous;

public abstract class AutonomousCommand 
{
    private boolean ran;

    public AutonomousCommand()
    {
        ran = false;
    }

    public final void run()
    {
        ran = true;
        execute();
    }

    public final boolean hasRan()
    {
        return ran;
    }

    protected abstract void execute();
    public abstract boolean isDone();
}
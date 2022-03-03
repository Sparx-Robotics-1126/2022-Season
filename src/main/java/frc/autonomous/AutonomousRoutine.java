package frc.autonomous;

public abstract class AutonomousRoutine implements Runnable
{
    private AutonomousCommand[] commands;

    private int current;

    public AutonomousRoutine(AutonomousCommand[] commands)
    {
        this.commands = commands;
        this.current = 0;
    }

    public void step()
    {
        commands[current].execute();

        current = (current + 1) % commands.length;
    }

    @Override
    public void run()
    {
        
    }
}
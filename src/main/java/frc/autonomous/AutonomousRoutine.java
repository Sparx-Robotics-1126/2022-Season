package frc.autonomous;

public abstract class AutonomousRoutine 
{
    private AutonomousCommand[] commands;

    private int position;

    public AutonomousRoutine(AutonomousCommand[] commands)
    {
        this.commands = commands;
        position = 0;
    }

    public void execute()
    {
        if (position > commands.length - 1)
            return;

        AutonomousCommand current = commands[position];

        if (!current.hasRan())
            current.run();
        else if (current.isDone())
            position++;
    }

    public abstract String getName();
}
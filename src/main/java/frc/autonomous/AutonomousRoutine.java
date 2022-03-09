package frc.autonomous;

public abstract class AutonomousRoutine 
{
    private AutonomousCommand[] commands;

    private int position;

    public AutonomousRoutine(AutonomousCommand[] commands)
    {
        if (commands.length == 0)
            throw new IllegalArgumentException("The passed command array must contain at least one AutonomousCommand.");

        this.commands = commands;
        position = 0;
    }

    public void execute()
    {
        
    }

    public abstract String getName();
}
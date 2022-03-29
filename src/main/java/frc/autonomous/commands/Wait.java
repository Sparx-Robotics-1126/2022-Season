package frc.autonomous.commands;

import frc.autonomous.AutonomousCommand;

public class Wait extends AutonomousCommand
{
    private int time;

    public Wait(int time)
    {
        this.time = time;
    }

    @Override
    protected void execute() 
    {
        try { Thread.sleep(time); } catch (InterruptedException e) {}
    }

    @Override
    public boolean isDone() 
    {
        return true;
    }
}
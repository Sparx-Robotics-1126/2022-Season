package frc.autonomous.commands;

import frc.robot.Robot;

import frc.autonomous.AutonomousCommand;

public class SetStorageSpeed extends AutonomousCommand
{
    private double speed;

    public SetStorageSpeed(double speed)
    {
        this.speed = speed;
    }

    @Override
    public void execute() 
    {
        Robot.getStorage().setSpeed(speed);
    }

    @Override
    public boolean isDone() 
    {
        return true;
    }
}
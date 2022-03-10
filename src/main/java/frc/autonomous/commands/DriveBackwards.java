package frc.autonomous.commands;

import frc.robot.Robot;

import frc.autonomous.AutonomousCommand;

public class DriveBackwards extends AutonomousCommand
{
    private double distance;

    public DriveBackwards(double distance)
    {
        this.distance = distance;
    }

    @Override
    public void execute() 
    {
        Robot.getDrives().moveBackward(0.5, distance);
    }

    @Override
    public boolean isDone() 
    {
        return Robot.getDrives().isDone();
    }
}
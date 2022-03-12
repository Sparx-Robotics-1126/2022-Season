package frc.autonomous.commands;

import frc.robot.Robot;

import frc.autonomous.AutonomousCommand;

public class SetShooterSpeed extends AutonomousCommand
{
    private double speed;

    public SetShooterSpeed(double speed)
    {
        this.speed = speed;
    }

    @Override
    public void execute() 
    {
        Robot.getShooter().setSpeed(speed);
    }

    @Override
    public boolean isDone() 
    {
        return Robot.getShooter().shooterAtSpeed();
    }
}
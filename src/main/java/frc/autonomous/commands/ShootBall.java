package frc.autonomous.commands;

import frc.robot.Robot;

import frc.autonomous.AutonomousCommand;

public class ShootBall extends AutonomousCommand
{
    private double speed;

    public ShootBall(double speed)
    {
        this.speed = speed;
    }

    @Override
    public void execute() 
    {
        Robot.getShooter().shootBall(speed);
    }

    @Override
    public boolean isDone() 
    {
        return Robot.getShooter().isDone();
    }
}
package frc.autonomous.routines;

import frc.autonomous.AutonomousCommand;
import frc.autonomous.AutonomousRoutine;

import frc.autonomous.commands.DriveBackwards;
import frc.autonomous.commands.ShootBall;

public class ShootOneBall extends AutonomousRoutine
{
    public ShootOneBall() 
    {
        super(new AutonomousCommand[] {
            new ShootBall(0.2),
            new DriveBackwards(75),
        });
    }

    @Override
    public String getName() 
    {
        return "Shoot 1 Ball";
    }   
}
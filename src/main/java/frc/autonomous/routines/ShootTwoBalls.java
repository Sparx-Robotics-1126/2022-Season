package frc.autonomous.routines;

import frc.autonomous.AutonomousCommand;
import frc.autonomous.AutonomousRoutine;

import frc.autonomous.commands.DriveBackwards;

public class ShootTwoBalls extends AutonomousRoutine
{
    public ShootTwoBalls() 
    {
        super(new AutonomousCommand[] {
            new DriveBackwards(50)
        });
    }

    @Override
    public String getName() 
    {
        return "Shoot 2 Balls";
    }   
}
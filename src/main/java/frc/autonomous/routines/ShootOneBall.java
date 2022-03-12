package frc.autonomous.routines;

import frc.autonomous.AutonomousCommand;
import frc.autonomous.AutonomousRoutine;

import frc.autonomous.commands.DriveBackwards;
import frc.autonomous.commands.SetShooterSpeed;
import frc.autonomous.commands.SetStorageSpeed;
import frc.autonomous.commands.Wait;

public class ShootOneBall extends AutonomousRoutine
{
    public ShootOneBall() 
    {
        super(new AutonomousCommand[] {
            new SetShooterSpeed(0.15),
            new SetStorageSpeed(0.25),
            new Wait(5000),
            new DriveBackwards(75),
            new SetShooterSpeed(0),
            new SetStorageSpeed(0)
        });
    }

    @Override
    public String getName() 
    {
        return "Shoot 1 Ball";
    }   
}
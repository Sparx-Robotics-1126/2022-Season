package frc.shooter.commands;

import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensorInterface;

public class SingleSpeed extends ShooterCommand
{
    private final double TURN_SPEED = 0.5;

    public SingleSpeed(ShooterSensorInterface sensors)
    {
        super(sensors);
    }

    @Override
    public ShooterOutput execute() 
    {
        return new ShooterOutput(TURN_SPEED, true);
    }
}

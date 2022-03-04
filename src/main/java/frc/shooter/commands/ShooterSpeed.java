package frc.shooter.commands;

import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensorInterface;
import frc.subsystem.Shooter;

public class ShooterSpeed extends ShooterCommand
{
    public ShooterSpeed(ShooterSensorInterface sensors)
    {
        super(sensors);
    }

    @Override
    public ShooterOutput execute() {
        double distance = sensors.getDistanceToTarget();
        double actualSpeed = sensors.getShooterSpeed();
        double wantedSpeed; //TODO: Add mathematics to determine the correct initial velocity for the shooter.
        return null;
    }
}

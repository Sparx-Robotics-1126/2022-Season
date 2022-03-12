package frc.shooter.commands;

import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensorInterface;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class StaticSpeed extends ShooterCommand
{
    private double speed;

    static
    {
        SmartDashboard.putNumber("SHOOTER_SPEED", 0.15);
    }

    public StaticSpeed(ShooterSensorInterface sensors)
    {
        super(sensors);

        speed = SmartDashboard.getNumber("SHOOTER_SPEED", 0.15);
    }

    public StaticSpeed(ShooterSensorInterface sensors, double speed)
    {
        super(sensors);
        
        this.speed = speed;
    }

    @Override
    public ShooterOutput execute() 
    {
        return new ShooterOutput(speed, false);
    }
}

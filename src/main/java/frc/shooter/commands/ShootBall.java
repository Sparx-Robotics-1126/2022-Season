package frc.shooter.commands;

import frc.robot.Robot;

import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensorInterface;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShootBall extends ShooterCommand
{
    private double speed;

    static
    {
        SmartDashboard.putNumber("SHOOTER_SPEED", 0.35);
    }

    public ShootBall(ShooterSensorInterface sensors)
    {
        super(sensors);

        speed = SmartDashboard.getNumber("SHOOTER_SPEED", 0.35);
    }

    public ShootBall(ShooterSensorInterface sensors, double speed)
    {
        super(sensors);

        this.speed = speed;
    }

    @Override
    public ShooterOutput execute() 
    {
        if (getSensors().getSpeed() != 0 && Robot.getShooter().shooterAtSpeed())
            Robot.getStorage().setSpeed();

        return new ShooterOutput(speed, false);
    }
}
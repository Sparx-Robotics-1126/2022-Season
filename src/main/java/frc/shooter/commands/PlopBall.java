package frc.shooter.commands;

import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensorInterface;

public class PlopBall extends ShooterCommand
{
    private final double TURN_DISTANCE = 1;
    private final double TURN_SPEED = 0.5;

    public PlopBall(ShooterSensorInterface sensors)
    {
        super(sensors);
    }

    @Override
    public ShooterOutput execute() 
    {
        if(getSensors().getMotorDistance() < TURN_DISTANCE){
            return new ShooterOutput(TURN_SPEED, false);
        }
        return new ShooterOutput(0, true);
    }
}

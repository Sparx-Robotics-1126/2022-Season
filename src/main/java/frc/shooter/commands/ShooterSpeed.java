package frc.shooter.commands;

import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensorInterface;

public class ShooterSpeed extends ShooterCommand
{
    private final double ACCEPTABLE_ERROR = 1;
    private final double HEIGHT_OF_PORT = 2.4955; //In meters

    public ShooterSpeed(ShooterSensorInterface sensors)
    {
        super(sensors);
    }

    @Override
    public ShooterOutput execute() 
    {
        double distance = getSensors().getDistanceToTarget();
        double wantedSpeed = calcShootingSpeed(distance);

        double actualSpeed = getSensors().getShooterSpeed();
        
        return new ShooterOutput(wantedSpeed, (Math.abs(actualSpeed - wantedSpeed) < ACCEPTABLE_ERROR));
    }

    private double calcShootingSpeed(double distanceFromTarget)
    {
        //return .136*distanceFromTarget+44.8; //Using trendline
		return Math.sqrt((distanceFromTarget*distanceFromTarget*9.8)/(2*Math.cos(35)*HEIGHT_OF_PORT-Math.tan(35))); //Using math
    }
}

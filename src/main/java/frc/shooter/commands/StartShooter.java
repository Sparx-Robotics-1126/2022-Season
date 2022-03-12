package frc.shooter.commands;

import frc.robot.Robot;
import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensorInterface;

public class StartShooter extends ShooterCommand
{
    private final double ACCEPTABLE_ERROR = 1;
    private final double HEIGHT_OF_PORT = 2.4955; //In meters

    private final int MINIMUM_PIXEL = 10;
    private final int MAXIMUM_PIXEL = 230;

    /*
    DISTANCE (Y VALUE): 
    SPEED: 
    */

    private double[] trendline = {0.2,.31,.42,.53,.64,.75};

    public StartShooter(ShooterSensorInterface sensors)
    {
        super(sensors);
    }

    @Override
    public ShooterOutput execute() 
    {
        double distance = Robot.getLimelight().getTargetHeight();
        double wantedSpeed = calcShootingSpeed(distance);

        double actualSpeed = getSensors().getShooterSpeed();
        
        return new ShooterOutput(wantedSpeed, (Math.abs(actualSpeed - wantedSpeed) < ACCEPTABLE_ERROR));
    }

    private double calcShootingSpeed(double distance)
    {
        int baseIndex = (int) getScaledIndex(distance);
        return trendline[baseIndex] + (distance - baseIndex*getTrendlineStep()) * (( trendline[baseIndex + 1] - trendline[baseIndex]) / ((baseIndex + 1) * getTrendlineStep() - baseIndex * getTrendlineStep()));//*HEIGHT_OF_PORT-Math.tan(35))); //Using math
    }

    private double getTrendlineStep() {
        return (MAXIMUM_PIXEL-MINIMUM_PIXEL)/trendline.length;
    }

    private double getScaledIndex(double distance) {
        return distance/getTrendlineStep();
    }




}

package frc.shooter.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

    private final double SMALL_ADJUST = 0.01;
    
    private final double LARGE_ADJUST = 0.05;

    private final String DASHBOARD_KEY = "TRENDLINE_EDIT";

    private double[] trendline = {0.2,.31,.42,.53,.64,.75};
    private double shotDistance;
    private double shotSpeed;

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
        
        int editTrendlineValue = (int) SmartDashboard.getNumber(DASHBOARD_KEY, 0);

        if(Math.abs(actualSpeed - wantedSpeed) < ACCEPTABLE_ERROR) {
            shotDistance = distance;
            shotSpeed = actualSpeed;
        }
        if(editTrendlineValue != 0) {
            if(shotDistance != 0 && shotSpeed != 0) {
                updateTrendline(editTrendlineValue,shotDistance, shotSpeed);
            }
            SmartDashboard.putNumber(DASHBOARD_KEY, 0);
        }

        return new ShooterOutput(wantedSpeed, (Math.abs(actualSpeed - wantedSpeed) < ACCEPTABLE_ERROR));
    }

    private void updateTrendline(int editValue,double distance, double speed) {
        int baseIndex = (int) getScaledIndex(distance);
        double percentage = getScaledIndex(distance - baseIndex);
        switch(editValue) {
            
            case -2:
            trendline[baseIndex] -= LARGE_ADJUST * percentage;
            trendline[baseIndex + 1] -= LARGE_ADJUST * (1-percentage);
            break;
            case -1:
            trendline[baseIndex] -= SMALL_ADJUST * percentage;
            trendline[baseIndex + 1] -= SMALL_ADJUST * (1-percentage);
            break;
            case 1:
            trendline[baseIndex] += SMALL_ADJUST * percentage;
            trendline[baseIndex + 1] += SMALL_ADJUST * (1-percentage);
            break;
            case 2:
            trendline[baseIndex] += LARGE_ADJUST * percentage;
            trendline[baseIndex + 1] += LARGE_ADJUST * (1-percentage);
            break;
            default:
            break;
        }

        for(int i = 0; i < trendline.length; i++) {
            System.out.print(i + " " + trendline[i] + ", ");
        }
        System.out.println("");
    }

    private double calcShootingSpeed(double distance)
    {
        int baseIndex = (int) getScaledIndex(distance);
        return trendline[baseIndex] + (distance - baseIndex*getTrendlineStep()) * (( trendline[baseIndex + 1] - trendline[baseIndex]) / ((baseIndex + 1) * getTrendlineStep() - baseIndex * getTrendlineStep()));//*HEIGHT_OF_PORT-Math.tan(35))); //Using math
    }

    private double getTrendlineStep() {
        return MAXIMUM_PIXEL/MINIMUM_PIXEL;
    }

    private double getScaledIndex(double distance) {
        return distance/getTrendlineStep();
    }




}

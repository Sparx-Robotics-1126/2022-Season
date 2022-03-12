package frc.subsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.IO;
import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensorInterface;
import frc.shooter.commands.StaticSpeed;
import frc.shooter.commands.StartShooter;
import frc.shooter.commands.StopShooter;

public class Shooter extends Subsystem
{
    private CANSparkMax shooterMotor;

    /*
     * Class containing all sensor data for Shooter.
     */
    private ShooterSensorInterface shooterSensors;

    //private ShooterData[] trendline;

    /**
     * The current ShooterCommand being ran.
     */
    private ShooterCommand shooterCommand;

    private double requestedSpeed;

    // private class ShooterData
    // {
    //     public double distance;
    //     public double speed;

    //     /*
    //     HEIGHT: 
    //     SPEED: 
    //     */

    //     public ShooterData(double distance, double speed)
    //     {
    //         this.distance = distance;
    //         this.speed = speed;
    //     }
    // }

    /**
     * Main initializer for the acquisitions subsystem. Called in Robot.java.
     */
    public Shooter(ShooterSensorInterface shooterSensors) 
    {
        shooterMotor = new CANSparkMax(IO.SHOOTER_MOTOR, MotorType.kBrushless);
        this.shooterSensors = shooterSensors;
        shooterSensors.addEncoders(shooterMotor.getEncoder());
        requestedSpeed = 0;

       // trendline = new ShooterData[5];

        //Input values into trendline

        //trendline[0] = new ShooterData(distance, speed);
    }

    @Override
    void execute() 
    {
        if (shooterCommand != null)
        {
            ShooterOutput shooterOutput = shooterCommand.execute();

            shooterMotor.set(-shooterOutput.get());

            requestedSpeed = -shooterOutput.get();

            SmartDashboard.putBoolean("SHOOTER_AT_SPEED", shooterAtSpeed());
            SmartDashboard.putNumber("SHOOTER_ACTUAL_SPEED", shooterSensors.getShooterSpeed());
            SmartDashboard.putNumber("SHOOTER_REQUESTED_SPEED", shooterSensors.percentageToRPM(requestedSpeed));
            
            if (shooterOutput.isDone())
                shooterCommand = null;
        }
    }

    // public double getTrendlineValue(double distance)
    // {
    //     int closestIndexMax = -1;
    //     int closestIndexMin;

    //     ShooterData pointMin, pointMax;

    //     for (int i = 0; i < trendline.length; i ++)
    //     {
    //         closestIndexMax = i;
    //         if (trendline[i].distance >= distance)
    //         {
    //             break;
    //         }
    //     }
    //     closestIndexMin = closestIndexMax - 1;

    //     pointMin = trendline[closestIndexMin];
    //     pointMax = trendline[closestIndexMax];

    //     return pointMin.speed + (distance - pointMin.distance) * ((pointMax.speed - pointMin.speed) / (pointMax.distance - pointMin.distance));
    // }

    public boolean shooterAtSpeed()
    {
        return Math.abs(shooterSensors.getShooterSpeed() - shooterSensors.percentageToRPM(requestedSpeed)) <= 20;
    }

    /**
     * Sets the shooter motor speed to 0
     */
    public void stopShooter() 
    {
        shooterCommand = new StopShooter(shooterSensors);
    }

    /**
     * Adjusts the shooter motor speed to shoot correctly
     */
    public void startShooter()
    {
        shooterCommand = new StartShooter(shooterSensors);
    }

    public void findTarget()
    {
        double targetAngle = shooterSensors.getAngleToTarget();
        
    }

    public void setSpeed()
    {
        shooterCommand = new StaticSpeed(shooterSensors);
    }

    public void setSpeed(double speed)
    {
        shooterCommand = new StaticSpeed(shooterSensors, speed);
    }

    @Override
    public boolean isDone() 
    {
        return shooterCommand == null;
    }
}

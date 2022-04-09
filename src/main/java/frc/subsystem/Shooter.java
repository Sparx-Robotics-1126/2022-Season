package frc.subsystem;

import frc.robot.IO;

import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensorInterface;

import frc.shooter.commands.StopShooter;
import frc.shooter.commands.ShootBall;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends Subsystem
{
    private static final double MAX_RPM = 4000;

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

    private SparkMaxPIDController pid;

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

    static
    {
        SmartDashboard.putNumber("PID_PROPORTIONAL_COEFFICIENT", 0.0005);
        SmartDashboard.putNumber("PID_INTEGRAL_COEFFICIENT", 0);
        SmartDashboard.putNumber("PID_DERIVATIVE_COEFFICIENT", 0);
    }

    /**
     * Main initializer for the acquisitions subsystem. Called in Robot.java.
     */
    public Shooter(ShooterSensorInterface shooterSensors) 
    {
        shooterMotor = new CANSparkMax(IO.SHOOTER_MOTOR, MotorType.kBrushless);

        shooterMotor.setIdleMode(IdleMode.kBrake);

        pid = shooterMotor.getPIDController();
        pid.setOutputRange(-1, 1);
        pid.setP(0.0005);
        pid.setI(0);
        pid.setD(0);
        
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
        pid.setP(SmartDashboard.getNumber("PID_PROPORTIONAL_COEFFICIENT", 0.0005));
        pid.setI(SmartDashboard.getNumber("PID_INTEGRAL_COEFFICIENT", 0));
        pid.setD(SmartDashboard.getNumber("PID_DERIVATIVE_COEFFICIENT", 0));

        if (shooterCommand != null)
        {
            ShooterOutput shooterOutput = shooterCommand.execute();
            
            requestedSpeed = -shooterOutput.get() * MAX_RPM;
            pid.setReference(requestedSpeed, ControlType.kVelocity);
            
            SmartDashboard.putBoolean("SHOOTER_AT_SPEED", shooterAtSpeed());
            SmartDashboard.putNumber("SHOOTER_ACTUAL_SPEED", shooterSensors.getSpeed());
            SmartDashboard.putNumber("SHOOTER_REQUESTED_SPEED", requestedSpeed);
            
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
        return Math.abs(requestedSpeed - shooterSensors.getSpeed()) <= 20;
    }

    /**
     * Sets the shooter motor speed to 0
     */
    public void stopShooter() 
    {
        shooterCommand = new StopShooter(shooterSensors);
    }

    public void findTarget()
    {

    }

    public void shootBall()
    {
        shooterCommand = new ShootBall(shooterSensors);
    }

    public void shootBall(double speed)
    {
        shooterCommand = new ShootBall(shooterSensors, speed);
    }

    @Override
    public boolean isDone() 
    {
        return shooterCommand == null;
    }
}

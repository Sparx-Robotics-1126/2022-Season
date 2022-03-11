package frc.subsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.IO;
import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensorInterface;
import frc.shooter.commands.SingleSpeed;
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

    /**
     * Main initializer for the acquisitions subsystem. Called in Robot.java.
     */
    public Shooter(ShooterSensorInterface shooterSensors) 
    {
        shooterMotor = new CANSparkMax(IO.SHOOTER_MOTOR, MotorType.kBrushless);
        shooterSensors.addEncoders(shooterMotor.getEncoder());
    }


    @Override
    void execute() 
    {
        if (shooterCommand != null)
        {
            ShooterOutput shooterOutput = shooterCommand.execute();

            shooterMotor.set(-shooterOutput.get());
                
            if (shooterOutput.isDone())
                shooterCommand = null;
        }
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

    public void singleSpeed()
    {
        shooterCommand = new SingleSpeed(shooterSensors);
    }

    @Override
    public boolean isDone() 
    {
        return shooterCommand == null;
    }
}

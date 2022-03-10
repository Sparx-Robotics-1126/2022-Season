package frc.subsystem;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.ControlMode;

import java.util.HashMap;

import frc.drives.commands.TurnLeft;
import frc.robot.IO;
import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensorInterface;
import frc.shooter.commands.StartShooter;
import frc.shooter.commands.StopShooter;

public class Shooter extends Subsystem
{
    private CANSparkMax shooterMotor;

    //private HashMap<Double, Double> map;

    /*
     * Class containing all sensor data for Shooter.
     */
    private ShooterSensorInterface shooterSensors;

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
        //map = new HashMap<>();

        //Input values into map

        //map.put(input, return value);
    }

    @Override
    void execute() 
    {
        if (shooterCommand != null)
        {
            ShooterOutput shooterOutput = shooterCommand.execute();

            shooterMotor.set(shooterOutput.get());
                
            if (shooterOutput.isDone())
                shooterCommand = null;
        }
    }

    // public double getHashMapValue(double inputValue)
    // {
    //     double closestValueMin = -1;

    //     for(int i = 0; i < map.size(); i ++){
    //         double mapX = map.get((double)i);
    //     }
    // }

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

    @Override
    public boolean isDone() 
    {
        return shooterCommand == null;
    }
}

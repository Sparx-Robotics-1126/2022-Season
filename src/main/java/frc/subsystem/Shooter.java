package frc.subsystem;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import frc.robot.IO;
import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensorInterface;
import frc.shooter.commands.StopShooter;

public class Shooter extends Subsystem
{
    private TalonSRX shooterMotor;

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
        shooterMotor = new TalonSRX(IO.SHOOTER_MOTOR);
    }

    @Override
    void execute() {
        if (shooterCommand != null)
        {
            ShooterOutput shooterOutput = shooterCommand.execute();

            shooterMotor.set(ControlMode.PercentOutput, shooterOutput.get());
                
            if (shooterOutput.isDone())
                shooterCommand = null;
        }
    }

    public void stopShooter() 
    {
        shooterCommand = new StopShooter(shooterSensors);
    }

    @Override
    public boolean isDone() 
    {
        return shooterCommand == null;
    }
}

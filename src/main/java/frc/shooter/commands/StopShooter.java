package frc.shooter.commands;

import frc.shooter.ShooterCommand;
import frc.shooter.ShooterOutput;
import frc.shooter.ShooterSensorInterface;

public class StopShooter extends ShooterCommand{

    /**
     * Create a new command for use in coordination with the Shooter subsystem.
     * @param sensors The ShooterSensorInterface for use within the command.
     */
    public StopShooter(ShooterSensorInterface sensors)
    {
        super(sensors);
    }

    /**
     * Called every update of the Shooter thread when this ShooterCommand is being run.
     * @return A ShooterOutput object containing the requested motor speeds.
     */
    public ShooterOutput execute() 
    {   
        return new ShooterOutput(0, true);
    }
}

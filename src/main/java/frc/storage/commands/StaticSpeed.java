package frc.storage.commands;

import frc.storage.StorageCommand;
import frc.storage.StorageOutput;
import frc.storage.StorageSensorInterface;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class StaticSpeed extends StorageCommand
{
    private double speed;

    static
    {
        SmartDashboard.putNumber("STORAGE_SPEED", 0.15);
    }

    public StaticSpeed(StorageSensorInterface sensors)
    {
        super(sensors);

        speed = SmartDashboard.getNumber("STORAGE_SPEED", 0.15);
    }

    public StaticSpeed(StorageSensorInterface sensors, double speed)
    {
        super(sensors);
        
        this.speed = speed;
    }

    @Override
    public StorageOutput execute() 
    {
        return new StorageOutput(speed, false);
    }
}

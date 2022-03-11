package frc.storage.commands;

import frc.storage.StorageCommand;
import frc.storage.StorageOutput;
import frc.storage.StorageSensorInterface;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class StaticSpeed extends StorageCommand
{
    static
    {
        SmartDashboard.putNumber("STORAGE_SPEED", 0.25);
    }

    public StaticSpeed(StorageSensorInterface sensors)
    {
        super(sensors);
        System.out.println("new static speed command");
    }

    @Override
    public StorageOutput execute() 
    {
        return new StorageOutput(SmartDashboard.getNumber("STORAGE_SPEED", 0.25), false);
    }
}
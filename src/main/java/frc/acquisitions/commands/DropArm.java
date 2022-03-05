package frc.acquisitions.commands;

import frc.acquisitions.AcquisitionsCommand;
import frc.acquisitions.AcquisitionsOutput;
import frc.acquisitions.AcquisitionsSensorInterface;

public class DropArm extends AcquisitionsCommand
{
    final double ARM_SPEED = 0.25;
    final double ARM_STOPPING_POINT = 0;

    public DropArm(AcquisitionsSensorInterface sensors)
    {
        super(sensors);
    }

    public AcquisitionsOutput execute() 
    {   
        if (getSensors().getArmEncoderDistance() <= ARM_STOPPING_POINT)
            return new AcquisitionsOutput(0, true);
        
        return new AcquisitionsOutput(-ARM_SPEED, false);
    }
}
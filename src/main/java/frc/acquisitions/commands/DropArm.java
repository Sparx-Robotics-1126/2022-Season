package frc.acquisitions.commands;

import frc.acquisitions.AcquisitionsCommand;
import frc.acquisitions.AcquisitionsOutput;
import frc.acquisitions.AcquisitionsSensorInterface;

public class DropArm extends AcquisitionsCommand
{
    public DropArm(AcquisitionsSensorInterface sensors)
    {
        super(sensors);
    }

    public AcquisitionsOutput execute() 
    {
        return new AcquisitionsOutput(-0.8, true);
    }
}
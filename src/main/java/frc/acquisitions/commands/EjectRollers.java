package frc.acquisitions.commands;

import frc.acquisitions.AcquisitionsCommand;
import frc.acquisitions.AcquisitionsOutput;
import frc.acquisitions.AcquisitionsSensorInterface;

public class EjectRollers extends AcquisitionsCommand
{
    public EjectRollers(AcquisitionsSensorInterface sensors)
    {
        super(sensors);
    }

    public AcquisitionsOutput execute() 
    {   
        return new AcquisitionsOutput(-0.25, true);
    }
}
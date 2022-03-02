package frc.acquisitions.commands;

import frc.acquisitions.AcquisitionsCommand;
import frc.acquisitions.AcquisitionsOutput;
import frc.acquisitions.AcquisitionsSensorInterface;

public class IntakeRollers extends AcquisitionsCommand
{
    public IntakeRollers(AcquisitionsSensorInterface sensors)
    {
        super(sensors);
    }

    public AcquisitionsOutput execute() 
    {   
        return new AcquisitionsOutput(0.25, false);
    }
}
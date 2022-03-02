package frc.acquisitions.commands;

import frc.acquisitions.AcquisitionsCommand;
import frc.acquisitions.AcquisitionsOutput;
import frc.acquisitions.AcquisitionsSensorInterface;

public class StopRollers extends AcquisitionsCommand
{   
    /**
     * Create a new command for use in coordination with the Acquisitions subsystem.
     * @param sensors The AcquisitionsSensorInterface for use within the command.
     */
    public StopRollers(AcquisitionsSensorInterface sensors)
    {
        super(sensors);
    }

    /**
     * Called every update of the Acquisitions thread when this AcquisitionsCommand is being run.
     * @return An AcquisitionsOutput object containing the requested motor speeds.
     */
    public AcquisitionsOutput execute() 
    {   
        return new AcquisitionsOutput(0, true);
    }
}
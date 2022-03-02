package frc.controllers;

import frc.subsystem.Drives;
import frc.subsystem.Acquisitions;

/**
 * Controller for testing.
 */
public class TestControls extends Controller 
{
    private long startTimeStamp;
    private final long RUNTIME;

    public TestControls(Drives drives, Acquisitions acquisitions) 
    {
        super(drives, acquisitions);
        RUNTIME = 10000; //In milliseconds.
        reset();
    }

    public void reset()
    {
        startTimeStamp = 0;
    }

    @Override
    public void execute() 
    {
        if (startTimeStamp == 0)
        {
            startTimeStamp = System.currentTimeMillis();
            drives.moveBackward(9999);
        }
        else if (startTimeStamp != Long.MAX_VALUE && System.currentTimeMillis() > startTimeStamp + RUNTIME)
        {
            startTimeStamp = Long.MAX_VALUE;
            drives.setJoysticks(0, 0);
            drives.startDriverControlled();
        }
    }
}

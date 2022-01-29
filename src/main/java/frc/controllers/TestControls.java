/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.controllers;

import frc.subsystem.Drives;

/**
 * Add your docs here.
 */
public class TestControls extends Controller 
{
    private long startTimeStamp;
    private final long RUNTIME;

    public TestControls(Drives drives) 
    {
        super(drives);
        RUNTIME = 10000;//10 seconds
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
        
        
        }else if (startTimeStamp != Long.MAX_VALUE && System.currentTimeMillis() > startTimeStamp + RUNTIME)
        {
            startTimeStamp = Long.MAX_VALUE;
            drives.setJoysticks(0, 0);
            drives.startDriverControlled();
        }
    }
}

package frc.drives.commands;

import frc.drives.DrivesCommand;
import frc.drives.DrivesOutput;
import frc.drives.DrivesSensorInterface;

/**
 * DrivesCommand to switch the overall Drives control mode to being driver controlled (for use in teleoperated).
 */
public class DriverControlled extends DrivesCommand
{	
	public DriverControlled(DrivesSensorInterface sensor) 
	{
		super(sensor);
	}
	
	@Override
	public DrivesOutput execute()
	{
		return new DrivesOutput(getSensors().getLeftJoyStick(), getSensors().getRightJoyStick());
	}
}
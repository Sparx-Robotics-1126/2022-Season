/**
 * 
 */
package frc.drives.commands;

import frc.drives.DrivesCommand;
import frc.drives.DrivesOutput;
import frc.drives.DrivesSensorInterface;



/**
 * @author Aidan Cheeseman
 *
 */
public class DriverControlled extends DrivesCommand{
	
	
	/**
	 * constructor
	 * @param sensor
	 */
	public DriverControlled(DrivesSensorInterface sensor) {
		super(sensor);
	}
	
	/**
	 * creates a DrivesOutput with the values of the Left and Right JoySticks
	 */
	@Override
	public DrivesOutput execute(){
		return new DrivesOutput(sensors.getLeftJoyStick() ,sensors.getRightJoyStick());
	}

}

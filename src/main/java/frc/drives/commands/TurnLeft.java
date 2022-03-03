package frc.drives.commands;

import frc.drives.DrivesCommand;
import frc.drives.DrivesOutput;
import frc.drives.DrivesSensorInterface;

/**
 * DrivesCommand to manually instruct the robot to turn left.
 */
public class TurnLeft extends DrivesCommand 
{
	private final double SPEED;
	private final double ANGLE;

	private double minSpeed = 0.25;
	private double finalAngle = 0;
	private double stopAngle = 180;
	private double speedToStop = 0;
	private double distanceToStop = 0;

	/**
	 * Creates a DrivesCommand instructing the robot to turn left by the specified angle.
	 * @param sensors A DrivesSensorInterface containing the sensors that should be used with this DrivesCommand.
	 * @param speed The speed at which to turn by.
	 * @param angle The amount of degrees that the robot should turn.
	 */
	public TurnLeft(DrivesSensorInterface sensors, double speed, double angle)
	{
		super(sensors);

		this.SPEED = speed;
		this.ANGLE = angle;

		finalAngle = sensors.getGyroAngle() - ANGLE;
	}

	@Override
	public DrivesOutput execute() 
	{
		distanceToStop = Math.abs(finalAngle - sensors.getGyroAngle());

		if (sensors.getGyroAngle() <= finalAngle) 
			return new DrivesOutput(0, 0, true);
		else if (distanceToStop <= stopAngle)
		{
			speedToStop = distanceToStop / stopAngle;

			//Slow down the robot when we near the desired angle to avoid overshooting it.
			if (speedToStop < minSpeed)
				speedToStop = minSpeed;

			return new DrivesOutput(-speedToStop, speedToStop); 
		} 

		return new DrivesOutput(-SPEED, SPEED);
	}
}

package frc.drives.commands;

import frc.drives.DrivesCommand;
import frc.drives.DrivesOutput;
import frc.drives.DrivesSensorInterface;

public class TurnRight extends DrivesCommand
{
	private double minSpeed = 0.3;
	private final double SPEED;
	private double finalAngle = 0;
	private double stopAngle = 90;
	private double speedToStop = 0;
	private double distanceToStop = 0;

	public TurnRight(DrivesSensorInterface sensor, double speed, double angle) 
	{
		super(sensor);
		this.SPEED = speed;
		finalAngle = sensors.getGyroAngle() + angle;
	}

	@Override
	public DrivesOutput execute() 
	{
		distanceToStop = Math.abs(finalAngle - sensors.getGyroAngle());
		if (sensors.getGyroAngle() >= finalAngle) 
		{
			//Stop motors, let drives know we are finished
			return new DrivesOutput(0, 0, true); //LeftMotor: 0, RightMotor: 0, isDone: true

		}
		else if (distanceToStop <= stopAngle)
		{ // slows down robot based on distance before stopping
			speedToStop = distanceToStop / stopAngle;
			if(speedToStop < minSpeed) // sets minimum speed if the robot is close to 
				// desired angle so the speed doesnt go to 0
			{
				speedToStop = minSpeed;
			}

			return new DrivesOutput(speedToStop,-speedToStop); 
		} else {
			//If we still need to spin
			return new DrivesOutput(SPEED, -SPEED);//LeftMotor: -SPEED, RightMotor: SPEED, isDone: false
			//Can also use new DrivesOutput(SPEED, -SPEED, false)
		}
	}
}
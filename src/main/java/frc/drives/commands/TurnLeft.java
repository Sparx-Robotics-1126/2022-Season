package frc.drives.commands;

import frc.drives.DrivesCommand;
import frc.drives.DrivesOutput;
import frc.drives.DrivesSensorInterface;

public class TurnLeft extends DrivesCommand 
{
	private final double SPEED;
	private final double ANGLE;

	private double minSpeed = 0.25;
	private double finalAngle = 0;
	private double stopAngle = 90;
	private double speedToStop = 0;
	private double distanceToStop = 0;

	public TurnLeft(DrivesSensorInterface sensors, double speed, double angle)
	{
		super(sensors); //Superclass will store sensor for you!
		this.SPEED = speed;
		this.ANGLE = angle;
		finalAngle = sensors.getGyroAngle() - ANGLE; // finds angle that the robot is trying to move to 
	}

	/**
	 * Main Method, will be called constantly until isDone is set true
	 */
	@Override
	public DrivesOutput execute() 
	{
		distanceToStop = Math.abs(finalAngle - sensors.getGyroAngle());
		if (sensors.getGyroAngle() <= finalAngle) 
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
			
			return new DrivesOutput(-speedToStop,speedToStop); 
		} 
		else 
		{
			//If we still need to spin
			return new DrivesOutput(-SPEED, SPEED); //LeftMotor: -SPEED, RightMotor: SPEED, isDone: false
		}
	}
}

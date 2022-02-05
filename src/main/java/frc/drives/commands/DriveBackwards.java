package frc.drives.commands;

import frc.drives.DrivesCommand;
import frc.drives.DrivesOutput;
import frc.drives.DrivesSensorInterface;

/**
 * DrivesCommand to manually instruct the robot to move backward.
 */
public class DriveBackwards extends DrivesCommand 
{
	private final double DISTANCE_kP;
	private final double GYRO_kP;
	private final double DISTANCE_DEADBAND;

	private final double TARGET_DISTANCE;
	private final double TARGET_ANGLE;

	/**
	 * Creates a DrivesCommand instructing the robot to move backward by the specified distance.
	 * @param sensors A DrivesSensorInterface containing the sensors that should be used with this DrivesCommand.
	 * @param distance The distance to move by.
	 */
	public DriveBackwards(DrivesSensorInterface sensors, double distance) 
	{
		super(sensors);
		
		DISTANCE_kP = 0.03;
		GYRO_kP = 0.06;
		DISTANCE_DEADBAND = 1; //2 inches.

		TARGET_DISTANCE = sensors.getAverageEncoderDistance() - distance;
		TARGET_ANGLE = sensors.getGyroAngle();
	}

	public DrivesOutput execute() 
	{
		double distanceError = TARGET_DISTANCE - sensors.getAverageEncoderDistance();
		double angleError = TARGET_ANGLE - sensors.getGyroAngle(); //Negative means too far right.

		double leftSpeed, rightSpeed;

		leftSpeed = rightSpeed = distanceError * DISTANCE_kP;

		if (leftSpeed < -1)
		{
			leftSpeed = -1;
			rightSpeed = -1;
		}

		double gyroOffset = angleError * GYRO_kP;

		if (gyroOffset > 0) //Too far left.
			leftSpeed -= gyroOffset;
		else
			rightSpeed += gyroOffset;

		if (sensors.getAverageEncoderDistance() < TARGET_DISTANCE + DISTANCE_DEADBAND)
			return new DrivesOutput(0, 0, true);

		return new DrivesOutput(leftSpeed, rightSpeed);
	}
}
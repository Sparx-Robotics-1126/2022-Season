package frc.drives.commands;

import frc.drives.DrivesCommand;
import frc.drives.DrivesOutput;
import frc.drives.DrivesSensorInterface;

/**
 * DrivesCommand to manually instruct the robot to move forward.
 */
public class DriveForward extends DrivesCommand 
{
	private static final double DISTANCE_kP = 0.03;
	private static final double DISTANCE_DEADBAND = 1; //2 inches.

	private final double TARGET_DISTANCE;

	private final double REQUESTED_SPEED;

	/**
	 * Creates a DrivesCommand instructing the robot to move forward by the specified distance.
	 * @param sensors A DrivesSensorInterface containing the sensors that should be used with this DrivesCommand.
	 * @param speed The speed at which to drive forwards.
	 * @param distance The distance to move by.
	 */
	public DriveForward(DrivesSensorInterface sensors, double speed, double distance) 
	{
		super(sensors);

		TARGET_DISTANCE = sensors.getAverageEncoderDistance() + distance;
		REQUESTED_SPEED = speed;
	}

	public DrivesOutput execute() 
	{
		double distanceError = TARGET_DISTANCE - getSensors().getAverageEncoderDistance();
		//double angleError = TARGET_ANGLE - getSensors().getGyroAngle();

		double leftSpeed, rightSpeed;
		leftSpeed = rightSpeed = distanceError * DISTANCE_kP * REQUESTED_SPEED;

		if (leftSpeed > 1)
		{
			leftSpeed = 1;
			rightSpeed = 1;
		}

		/*double gyroOffset = angleError * GYRO_kP;

		if (gyroOffset < 0) //Too far left.
			leftSpeed -= gyroOffset;
		else
			rightSpeed += gyroOffset;*/

		if (getSensors().getAverageEncoderDistance() > TARGET_DISTANCE - DISTANCE_DEADBAND)
			return new DrivesOutput(0, 0, true);

		return new DrivesOutput(leftSpeed, rightSpeed);
	}
}
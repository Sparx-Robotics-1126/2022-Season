package frc.drives;

import frc.robot.IO;

import com.revrobotics.RelativeEncoder;

import com.kauailabs.navx.frc.AHRS;

/**
 * Class for interfacing with the various sensors of the Drives subsystem (e.g. encoders).
 */
public class DrivesSensors implements DrivesSensorInterface 
{
	private final double MULTIPLIER = 1.2;

	private AHRS gyro;

	private RelativeEncoder rightEncoder;
	private RelativeEncoder leftEncoder;

	private double rightJoystick;
	private double leftJoystick;
	
	/**
	 * Creates a new object for interfacing with the various sensors of the Drives subsystem (e.g. encoders).
	 */
	public DrivesSensors() 
	{
		gyro = new AHRS(IO.USB_ONBOARD);
		rightJoystick = 0;
		leftJoystick = 0;
	}
	
	/**
	 * Adds the left and right encoders of the motor to this DrivesSensors object.
	 * @param leftSpark The left encoder of the motor.
	 * @param rightSpark The right encoder of the motor.
	 */
	@Override
	public void addEncoders(RelativeEncoder leftSpark, RelativeEncoder rightSpark) 
	{
		this.leftEncoder = leftSpark;
		this.rightEncoder = rightSpark;
	}
	
	/**
	 * @return The current distance that the left encoder is reporting.
	 */
	@Override
	public double getLeftEncoderDistance() 
	{
		return leftEncoder.getPosition() * MULTIPLIER;
	}

	/**
	 * @return The current speed that the left encoder is reporting.
	 */
	@Override
	public double getLeftEncoderSpeed() 
	{
		return leftEncoder.getVelocity();
	}

	/**
	 * @return The current distance that the right encoder is reporting.
	 */
	@Override
	public double getRightEncoderDistance() 
	{
		return rightEncoder.getPosition() * -MULTIPLIER;
	}

	/**
	 * @return The current speed that the right encoder is reporting.
	 */
	@Override
	public double getRightEncoderSpeed() 
	{
		return -rightEncoder.getVelocity();
	}

	/**
	 * @return The average of both the right and left encoder distances.
	 */
	@Override
	public double getAverageEncoderDistance() 
	{
		return (getRightEncoderDistance() + getLeftEncoderDistance()) / 2;
	}

	/**
	 * @return The average of both the right and left encoder speeds.
	 */
	@Override
	public double getAverageEncoderSpeed() 
	{
		return (getRightEncoderSpeed() + getLeftEncoderSpeed()) / 2;
	}

	/**
	 * @return The current angle that the gyro is reporting.
	 */
	@Override
	public double getGyroAngle() 
	{
		return gyro.getAngle();
	}

	/**
	 * @return The right joystick's set value.
	 */
	@Override
	public double getRightJoyStick() 
	{
		return rightJoystick;
	}

	/**
	 * @return The left joystick's set value.
	 */
	@Override
	public double getLeftJoyStick() 
	{
		return leftJoystick;
	}

	/**
	 * Sets the value of the right joystick.
	 * @param value The value to set.
	 */
	@Override
	public void setRightJoystick(double value) 
	{
		rightJoystick = value;
	}

	/**
	 * Sets the value of the left joystick.
	 * @param value The value to set.
	 */
	@Override
	public void setLeftJoystick(double value) 
	{
		leftJoystick = value;
	}
}
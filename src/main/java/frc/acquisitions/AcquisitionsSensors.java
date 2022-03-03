package frc.acquisitions;

import frc.robot.IO;

import frc.sensors.ColorSensor;
import frc.util.SDUtil;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.Encoder;

public class AcquisitionsSensors implements AcquisitionsSensorInterface
{
	private static final int PROXIMITY_MINIMUM = 1048;

    private ColorSensor colorSensor;

	private Encoder armEncoder;

    //Create Joystick
	private boolean armButton;
	private boolean intakeButton;

    /**
	 * Creates a new object for interfacing with the various sensors of the Aquisitions subsystem (e.g. encoders).
	 */
	public AcquisitionsSensors() 
	{
        colorSensor = new ColorSensor(100);
		colorSensor.start();

		armEncoder = new Encoder(IO.ACQUISITIONS_ARM_ENCODER_A, IO.ACQUISITIONS_ARM_ENCODER_B);
		armEncoder.setDistancePerPulse(0.314789);

		armButton = false;
		intakeButton = false;
	}

	/**
	 * Checks if there is an object in range of the color sensor.
	 */
	public boolean ballInRange()
	{
		return colorSensor.getProximity() > PROXIMITY_MINIMUM;
	}

	/**
	 * Checks if the ball color matches the team color.
	 */
	public boolean isCorrectColor()
	{
		Color curr = colorSensor.getColor();
		boolean red = SDUtil.isRedAlliance();

		return curr.red > curr.blue && red || curr.blue > curr.red && !red;
	}

	/**
	 * Gets the distance the arm motor has rotated.
	 */
	@Override
	public double getArmEncoderDistance() 
	{
		return armEncoder.getDistance();
	}

	/**
	 * @return The arm button's set value.
	 */
	@Override
	public boolean getArmButton() 
	{
		return armButton;
	}

	/**
	 * @return The intake button's set value.
	 */
	@Override
	public boolean getIntakeButton() 
	{
		return intakeButton;
	}

	/**
	 * Sets the value of the arm button.
	 * @param value The value to set.
	 */
	@Override
	public void setArmButton(boolean value) 
	{
		armButton = value;
	}

	/**
	 * Sets the value of the intake button.
	 * @param value The value to set.
	 */
	@Override
	public void setIntakeButton(boolean value) 
	{
		intakeButton = value;
	}
}
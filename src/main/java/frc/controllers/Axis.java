package frc.controllers;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Class for interfacing with the 2 axes on an XBOX controller.
 */
public class Axis 
{
	private Joystick joystick;

	/**
	 * The point on the axis relative to the origin in which input 
	 * should start to be registered.
	 */
	private double deadband;

	/**
	 * The value that the final output value will be multiplied by.
	 */
	private double sensitivity;

	private int axis;
	private boolean invert;

	/**
	 * Create a new axis on the specified Joystick.
	 * @param joystick The Joystick to derive the axis from.
	 * @param axis The index of the axis to read from the Joystick.
	 * @param invert A boolean indicating whether or not the sign of the input should be changed.
	 */
	public Axis(Joystick joystick, int axis, boolean invert) 
	{
		this.joystick = joystick;
		this.axis = axis;
		this.invert = invert;
		this.sensitivity = 1;
		this.deadband = 0.2;
	}

	/**
	 * @return The value of the axis that was specified during the creation of this Axis.
	 */
	public double get()
	{
		if (deadband == 1 || sensitivity == 0)
			return 0;

		double value = joystick.getRawAxis(axis);

		if (Math.abs(value) < deadband) 
			return 0;
		
		//Apply deadband constraint and assess the -1 to 1 output limit.
		double outputValue = Math.min(Math.max(-1, (value - deadband) / (1 - deadband) * sensitivity), 1);
		
		if (this.invert)
			return -outputValue;
		
		return outputValue;
	}

	public void setSensitivity(double newSensitivity)
	{
		sensitivity = newSensitivity;
	}
}
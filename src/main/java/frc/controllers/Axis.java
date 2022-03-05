package frc.controllers;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Class for interfacing with the 2 axes on an XBOX controller.
 */
public class Axis 
{
	/**
	 * The point on the axis relative to the origin in which input 
	 * should start to be registered.
	 */
	private static double DEADBAND = 0.4;

	/**
	 * The value that the final output value will be multiplied by.
	 */
	private static double SENSITIVITY = 1;

	private Joystick joystick;
	private int axis;
	private boolean invert;
	
	static
	{
		SmartDashboard.putNumber("CONTROLLER_AXIS_DEADBAND", DEADBAND);
		SmartDashboard.putNumber("CONTROLLER_AXIS_SENSITIVITY", SENSITIVITY);
	}

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
	}

	/**
	 * @return The value of the axis that was specified during the creation of this Axis.
	 */
	public double get()
	{
		DEADBAND = SmartDashboard.getNumber("CONTROLLER_AXIS_DEADBAND", DEADBAND);
		SENSITIVITY = SmartDashboard.getNumber("CONTROLLER_AXIS_SENSITIVITY", SENSITIVITY);

		if (DEADBAND == 1 || SENSITIVITY == 0)
			return 0;

		double value = joystick.getRawAxis(axis);

		if (Math.abs(value) < DEADBAND) 
			return 0;
		
		//Apply deadband constraint and assess the -1 to 1 output limit.
		double outputValue = Math.min(Math.max(-1, (value - DEADBAND) / (1 - DEADBAND) * SENSITIVITY), 1);
		
		if (this.invert)
			return -outputValue;
		
		return outputValue;
	}
}
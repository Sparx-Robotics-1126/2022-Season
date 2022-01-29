package frc.controllers;

import edu.wpi.first.wpilibj.Joystick;


public class Axis 
{
	/**
	 * The point on the axis relative to the origin in which input 
	 * should start to be registered.
	 */
	private final double DEADBAND = 0.1;

	private Joystick joystick;
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
	}

	/**
	 * @return The value of the axis that was specified during the creation of this Axis.
	 */
	public double get()
	{
		double value = joystick.getRawAxis(axis);

		if (Math.abs(value) < DEADBAND) 
			return 0;
		
		//Apply deadband constraint: begin input after the deadband.
		value = (value - DEADBAND) / DEADBAND;
		
		if (this.invert)
			return -value;
		
		return value;
	}
}
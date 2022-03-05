package frc.controllers;

import edu.wpi.first.wpilibj.Joystick;

public class AxisButton extends Button
{
	/**
	 * The "deadband" of the button; the extent to which it must be pressed in order to register 
	 * as actually being pressed.
	 */
	private final double SENSITIVITY;

	private Axis axis;
	private boolean buttonPreviouslyPressed;
	
	public AxisButton(Joystick joystick, int axisNum, ButtonType type) 
	{
		super(joystick, axisNum, type);
		SENSITIVITY = 0.25;
		axis = new Axis(joystick, axisNum, false);
	}
	
	/**
	 * @return A boolean indicating whether or not the button should be considered as pressed.
	 */
	@Override
	public boolean get() 
	{
		double value = axis.get();
		boolean currentlyPressed = value > SENSITIVITY ? true : false;
		boolean trigger = isTriggered(currentlyPressed, buttonPreviouslyPressed);
		buttonPreviouslyPressed = currentlyPressed;
		return trigger;
	}
}
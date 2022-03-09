package frc.controllers;

import edu.wpi.first.wpilibj.Joystick;

public class Button 
{
	enum ButtonType
	{
		RISING_EDGE, //Initial compression.
		FALLING_EDGE, //When released.
		PRESSED
	}
	
	private Joystick joystick;

	private ButtonType buttonType;

	private int button;

	private boolean buttonAlreadyPressed;
	private boolean buttonPreviouslyPressed;
	
	public Button(Joystick joystick, int button) 
	{
		this.joystick = joystick;
		this.button = button;
		this.buttonType = ButtonType.RISING_EDGE;
		buttonAlreadyPressed = false;
		buttonPreviouslyPressed = true;
	}
	
	public Button(Joystick joystick, int button, ButtonType type) 
	{
		this.joystick = joystick;
		this.button = button;
		this.buttonType = type;
		buttonAlreadyPressed = false;
		buttonPreviouslyPressed = true;
	}
	
	/**
	 * @return A boolean indicating whether the button should be considered as pressed or not.
	 */
	public boolean get() 
	{
		boolean isCurrentlyPressed = joystick.getRawButton(button);
		boolean trigger = isTriggered(isCurrentlyPressed, buttonAlreadyPressed);
		
		buttonAlreadyPressed = isCurrentlyPressed;

		if (trigger)
			buttonPreviouslyPressed = !buttonPreviouslyPressed;

		return trigger;
	}
	
	/**
	 * Indicates whether or not the button should be considered pressed based on its ButtonType.
	 * @param isCurrentlyPressed Whether or not the button is currently pressed.
	 * @param buttonAlreadyPressed If the button is already pressed.
	 * @return A boolean indicating whether or not the button is triggered.
	 */
	protected boolean isTriggered(boolean isCurrentlyPressed, boolean buttonAlreadyPressed) 
	{
		if (buttonType == ButtonType.RISING_EDGE)
			return isCurrentlyPressed && !buttonAlreadyPressed; //Pressed now, wasn't pressed before.
		else if (buttonType == ButtonType.FALLING_EDGE)
			return !isCurrentlyPressed && buttonAlreadyPressed; //Not pressed now, was pressed before.
		
		return isCurrentlyPressed;
	}

	/**
	 * @return True if this button has been pressed an even number of times.
	 */
	public boolean previouslyPressed()
	{
		return buttonPreviouslyPressed;
	}
}
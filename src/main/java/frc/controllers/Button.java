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
	private int button;
	private ButtonType buttonType;
	private boolean buttonPreviouslyPressed;
	
	public Button(Joystick joystick, int button) 
	{
		this.joystick = joystick;
		this.button = button;
		this.buttonType = ButtonType.RISING_EDGE;
		buttonPreviouslyPressed = false;
	}
	
	public Button(Joystick joystick, int button, ButtonType type) 
	{
		this.joystick = joystick;
		this.button = button;
		this.buttonType = type;
		buttonPreviouslyPressed = false;
	}
	
	/**
	 * @return A boolean indicating whether the button should be considered as pressed or not.
	 */
	public boolean get() 
	{
		boolean isCurrentlyPressed = joystick.getRawButton(button);
		boolean trigger = isTriggered(isCurrentlyPressed, buttonPreviouslyPressed);
		buttonPreviouslyPressed = isCurrentlyPressed;
		return trigger;
	}
	
	/**
	 * Indicates whether or not the button should be considered pressed based on its ButtonType.
	 * @param isCurrentlyPressed Whether or not the button is currently pressed.
	 * @param buttonPreviouslyPressed If the button was pressed before now.
	 * @return A boolean indicating whether or not the button is triggered.
	 */
	protected boolean isTriggered(boolean isCurrentlyPressed, boolean buttonPreviouslyPressed) 
	{
		if (buttonType == ButtonType.RISING_EDGE)
			return isCurrentlyPressed && !buttonPreviouslyPressed; //Pressed now, wasn't pressed before.
		else if (buttonType == ButtonType.FALLING_EDGE)
			return !isCurrentlyPressed && buttonPreviouslyPressed; //Not pressed now, was pressed before.
		
		return isCurrentlyPressed;
	}

	/**
	 * @return A boolean indicating whether or not the button was pressed before this call.
	 */
	public boolean previouslyPressed()
	{
		return buttonPreviouslyPressed;
	}
}

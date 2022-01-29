package frc.controllers;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick;
import frc.controllers.Button.ButtonType;
import frc.subsystem.Drives;

public class TeleopControls extends Controller 
{
	private Joystick driverJoystick;
	private Axis driverLeftAxis;
	private Axis driverRightAxis;
	
	/**
	 * Creates the Controller manager for teleoperated.
	 * @param drives The Drives subsystem to associate with this Controller.
	 */
  public TeleopControls(Drives drives)
  {
    super(drives);
    
    driverJoystick = new Joystick(0);

    //For drives.
    driverLeftAxis = new Axis(driverJoystick, ControllerMappings.XBOX_LEFT_Y, true);
    driverRightAxis = new Axis(driverJoystick, ControllerMappings.XBOX_RIGHT_Y, true);

    //Add controls for additional subsystems here.
  }

  @Override
  public void execute() 
  {
    //Driver
    drives.setJoysticks(driverLeftAxis.get(), driverRightAxis.get());

    //Rumble
  }
  
  private void setRumble(Joystick joy, double value) 
  {
    joy.setRumble(RumbleType.kLeftRumble, value);
    joy.setRumble(RumbleType.kRightRumble, value);
  }
}
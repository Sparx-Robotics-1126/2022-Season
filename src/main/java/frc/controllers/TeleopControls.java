package frc.controllers;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick;
import frc.subsystem.Drives;
import frc.subsystem.Subsystem.SubsystemState;
import frc.subsystem.Acquisitions;

public class TeleopControls extends Controller 
{
	private Joystick joystick;
	private Axis driverLeftAxis;
	private Axis driverRightAxis;

  private Button acquisitionsArmButton;
  private Button acquisitionsIntakeButton;
  private Button acquisitionsOverrideButton;
	
	/**
	 * Creates the Controller manager for teleoperated.
	 * @param drives The Drives subsystem to associate with this Controller.
	 */
  public TeleopControls(Drives drives, Acquisitions acquisitions)
  {
    super(drives, acquisitions);
    
    joystick = new Joystick(0);

    //For drives.
    driverLeftAxis = new Axis(joystick, ControllerMappings.XBOX_LEFT_Y, true);
    driverRightAxis = new Axis(joystick, ControllerMappings.XBOX_RIGHT_Y, true);

    //For Acquisitions
    acquisitionsArmButton = new Button(joystick, ControllerMappings.XBOX_B);
    acquisitionsIntakeButton = new Button(joystick, ControllerMappings.XBOX_A);
    acquisitionsOverrideButton = new Button(joystick, ControllerMappings.XBOX_A);

    //Add controls for additional subsystems here.
  }

  @Override
  public void execute() 
  {
    //Driver
    drives.setJoysticks(driverLeftAxis.get(), driverRightAxis.get());

    //Acquisitions
    if (acquisitionsArmButton.get())
      if (acquisitionsArmButton.previouslyPressed())
        acquisitions.raiseArm();
      else
        acquisitions.dropArm();

    if (acquisitionsIntakeButton.get())
      if (acquisitionsIntakeButton.previouslyPressed())
        acquisitions.intakeRollers();
      else
        acquisitions.stopRollers();
    
    if (acquisitionsOverrideButton.get())
      if (acquisitionsIntakeButton.previouslyPressed())
          acquisitions.setState(SubsystemState.NORMAL);
        else
          acquisitions.setState(SubsystemState.OVERRIDE);

    //Rumble
  }
  
  private void setRumble(Joystick joy, double value) 
  {
    joy.setRumble(RumbleType.kLeftRumble, value);
    joy.setRumble(RumbleType.kRightRumble, value);
  }
}
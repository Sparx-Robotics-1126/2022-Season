package frc.controllers;

import frc.subsystem.Drives;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleoperatedController extends Controller 
{
	private Joystick driverJoystick;

	private Axis driverLeftAxisY;
  private Axis driverLeftAxisX;
	private Axis driverRightAxis;

  private Axis driverRightTrigger;
  private Axis driverLeftTrigger;
	
  static 
  {
    SmartDashboard.putBoolean("USE_BOTH_JOYSTICKS", true);
  }

	/**
	 * Creates the Controller manager for teleoperated.
	 * @param drives The Drives subsystem to associate with this Controller.
	 */
  public TeleoperatedController(Drives drives)
  {
    super(drives);
    
    driverJoystick = new Joystick(0);

    //For drives.
    driverLeftAxisY = new Axis(driverJoystick, ControllerMappings.XBOX_LEFT_Y, true);
    driverLeftAxisX = new Axis(driverJoystick, ControllerMappings.XBOX_LEFT_X, true);
    driverRightAxis = new Axis(driverJoystick, ControllerMappings.XBOX_RIGHT_Y, true);
    
    //Add controls for additional subsystems here.
  }

  @Override
  public void execute() 
  {
    //Driver
    if (SmartDashboard.getBoolean("USE_BOTH_JOYSTICKS", true))
      if (driverRightTrigger.get() >= 0.5 && driverLeftTrigger.get() >= 0.5)
        drives.setJoysticks(driverLeftAxisY.get() * 0.25, driverRightAxis.get() * 0.25);
      else
        drives.setJoysticks(driverLeftAxisY.get(), driverRightAxis.get());
    else
    {
      double leftAxisY = driverLeftAxisY.get();
      double leftAxisX = driverLeftAxisX.get() * 0.5;

      if (Math.abs(leftAxisY) >= 0.15 && leftAxisY < 0)
        leftAxisX = -leftAxisX;

      drives.setJoysticks(leftAxisY - leftAxisX, leftAxisY + leftAxisX);
    }

    //Rumble
  }
  
  private void setRumble(Joystick joy, double value) 
  {
    joy.setRumble(RumbleType.kLeftRumble, value);
    joy.setRumble(RumbleType.kRightRumble, value);
  }
}
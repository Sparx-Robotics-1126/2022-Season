package frc.controllers;

import frc.subsystem.Drives;
import frc.subsystem.Acquisitions;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleoperatedController extends Controller 
{
	private Joystick driverJoystick;

	private Axis driverLeftAxisY;
  private Axis driverLeftAxisX;
	private Axis driverRightAxis;
  
  static 
  {
    SmartDashboard.putBoolean("USE_BOTH_JOYSTICKS", true);
  }

	/**
	 * Creates the Controller manager for teleoperated.
	 * @param drives The Drives subsystem to associate with this Controller.
	 */
  public TeleoperatedController(Drives drives, Acquisitions acquisitions)
  {
    super(drives, acquisitions);
    
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
        drives.setJoysticks(driverLeftAxisY.get(), driverRightAxis.get());
    else
    {
      double leftAxisY = driverLeftAxisY.get();
      double leftAxisX = driverLeftAxisX.get() * 0.5;

      if (Math.abs(leftAxisY) >= 0.15 && leftAxisY < 0)
        leftAxisX = -leftAxisX;

      drives.setJoysticks(leftAxisY - leftAxisX, leftAxisY + leftAxisX);
    }
  }
}
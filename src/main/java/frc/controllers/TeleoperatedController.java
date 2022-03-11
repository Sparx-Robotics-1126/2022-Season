package frc.controllers;

import frc.robot.Robot;

import frc.controllers.Button.ButtonType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleoperatedController extends Controller 
{
	private Joystick driverJoystick;
  private Joystick operatorJoystick;

  private Axis driverLeftAxisY;
  private Axis driverLeftAxisX;
	private Axis driverRightAxis;
  private Axis driverRightTrigger;

  private Button acquisitionsArmUp;
  private Button acquisitionsArmDown;
  private Button acquisitionsIntake;

  private Button shooterToggleButton;

  private Button storageButton;

  static 
  {
    SmartDashboard.putBoolean("USE_BOTH_JOYSTICKS", true);
  }
	
	/**
	 * Creates the Controller manager for teleoperated.
	 * @param drives The Drives subsystem to associate with this Controller.
	 */
  public TeleoperatedController()
  {
    driverJoystick = new Joystick(0);
    operatorJoystick = new Joystick(1);

    //DRIVES
    driverLeftAxisY = new Axis(driverJoystick, ControllerMappings.XBOX_LEFT_Y, true);
    driverLeftAxisX = new Axis(driverJoystick, ControllerMappings.XBOX_LEFT_X, true);
    driverRightAxis = new Axis(driverJoystick, ControllerMappings.XBOX_RIGHT_Y, true);
    driverRightTrigger = new Axis(driverJoystick, ControllerMappings.XBOX_R2, true);
   
    //ACQUISITIONS
    acquisitionsArmUp = new Button(operatorJoystick, ControllerMappings.XBOX_Y, ButtonType.PRESSED);
    acquisitionsArmDown = new Button(operatorJoystick, ControllerMappings.XBOX_A, ButtonType.PRESSED);
    acquisitionsIntake = new Button(operatorJoystick, ControllerMappings.XBOX_B);

    //SHOOTER
    shooterToggleButton = new Button(driverJoystick, ControllerMappings.XBOX_A);

    //STORAGE
    storageButton = new Button(driverJoystick, ControllerMappings.XBOX_X);
    //Add additional controls here.
  }

  @Override
  public void execute() 
  {
    //DRIVES
    if (SmartDashboard.getBoolean("USE_BOTH_JOYSTICKS", true))
        Robot.getDrives().setJoysticks(driverLeftAxisY.get(), driverRightAxis.get());
    else
    {
      double leftAxisY = driverLeftAxisY.get();
      double leftAxisX = driverLeftAxisX.get() * 0.5;

      if (Math.abs(leftAxisY) >= 0.15 && leftAxisY < 0)
        leftAxisX = -leftAxisX;

      Robot.getDrives().setJoysticks(leftAxisY - leftAxisX, leftAxisY + leftAxisX);
    }

    //SHOOTER
    if (shooterToggleButton.get())
    {
      int timesPressed = shooterToggleButton.timesPressed();

      if (timesPressed % 2 == 1) {
        Robot.getShooter().singleSpeed();
      }
      else
        Robot.getShooter().stopShooter();
    }

    //ACQUISITIONS
    if (acquisitionsArmUp.get())
      Robot.getAcquisitions().raiseArm();

    if (acquisitionsArmDown.get())
      Robot.getAcquisitions().dropArm();

    if (!acquisitionsArmDown.get() && !acquisitionsArmUp.get())
      Robot.getAcquisitions().stopArm();

    if (acquisitionsIntake.get())
    {
      int timesPressed = acquisitionsIntake.timesPressed();

      if (timesPressed % 2 == 1)
        Robot.getAcquisitions().intakeRollers();
      else
        Robot.getAcquisitions().stopRollers();
    }

    //STORAGE
    if (storageButton.get())
    {
      int timesPressed = storageButton.timesPressed();
      System.out.println("storage button has been pressed " + timesPressed);
      
      if (timesPressed % 2 == 1) {
        Robot.getStorage().staticSpeed();
        System.out.println("storage button start static speed");
      } else
        Robot.getStorage().stopStorage();
    }

    //Trigger Sensitivity Control
    if (driverRightTrigger.get() <= -0.8)
    {
      driverLeftAxisX.setSensitivity(0.4);
      driverLeftAxisY.setSensitivity(0.4);
      driverRightAxis.setSensitivity(0.4);
    }
    else
    {
      driverLeftAxisX.setSensitivity(1);
      driverLeftAxisY.setSensitivity(1);
      driverRightAxis.setSensitivity(1);
    }
  }
}
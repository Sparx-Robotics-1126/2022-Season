package frc.robot;

import edu.wpi.first.hal.HAL;

import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.controllers.AutoControl;
import frc.controllers.Controller;
import frc.controllers.TeleopControls;
import frc.controllers.TestControls;

import frc.drives.DrivesSensorInterface;
import frc.drives.DrivesSensors;

import frc.subsystem.Drives;

/**
 * Controls when subsystems are engaged and grants control to the correct Controller.
 */
public class Robot extends RobotBase
{
    //Every possible state of control for the robot.
    public enum RobotState
    {
		STANDBY,
		AUTO,
        TELE,
        TEST;
	}

    //Possible controllers.
    private TeleopControls teleopControls;
    private AutoControl autoControls;
    private TestControls testControls;
    
    //The robot subsystems.
    private Drives drives;

    //The acting Controller of the robot.
    private Controller currentController;

    //Sensors.
    private DrivesSensorInterface drivesSensors;

    //Keeps track of the current state of the robot.
    private RobotState state;

    private void robotInit()
    {
        state = RobotState.STANDBY; //When robot turns on, we don't want anything running in the background.
        
        //Initialize sensors.
        drivesSensors = new DrivesSensors();
        
        //Initialize Subsystems.
        drives = new Drives(drivesSensors);
        
        //Initialize Controllers.
        teleopControls = new TeleopControls(drives);
        autoControls = new AutoControl(drives);
        testControls = new TestControls(drives);

        //Start subsystem threads.
        new Thread(drives).start();
    }

    private void disabledStarted()
    {
        state = RobotState.STANDBY;
    }

    /**
     * Called when autonomous begins.
     */
    private void autoStarted()
    {
    	currentController = autoControls;
        state = RobotState.AUTO;
    }

    /**
     * Called when teleoperated begins.
     */
    private void teleopStarted()
    {
    	drives.startDriverControlled();
    	currentController = teleopControls;
    	state = RobotState.TELE;
    }

    /**
     * Called when test mode is activated.
     */
    private void testStarted()
    {
        currentController = testControls;
        state = RobotState.TEST;
    }

    /**
     * The main loop of the robot. Ran every update/tick of the RIO.
     */
    private void mainLoop() 
    {
        switch (state)
        {
            case STANDBY:
                return;
            case AUTO:
                //This can be used to give grive control after done
            	//Also used if semi-auto things are happening
            	//NOTICE THERE IS NO BREAK HERE
            case TELE:
            case TEST:
                currentController.execute(); //Calls the current controller (auto/teleop/test)
        }
    }

    @Override
    public void startCompetition() 
    {
        System.out.println("********ROBOT INIT********");
        HAL.observeUserProgramStarting();
        robotInit();
        System.out.println("************ENGAGING MAIN LOOP************");
        while (true)
        {
            if (!isDisabled())
            {
                if (isAutonomous() && state != RobotState.AUTO)
                {
                    System.out.println("**********AUTO STARTED************");
                    autoStarted();
                    HAL.observeUserProgramAutonomous();
                }
                else if (isTeleop() && state != RobotState.TELE)
                {
                    teleopStarted();
                    System.out.println("**********TELEOP STARTED************");
                    HAL.observeUserProgramTeleop();
                }
                else if (isTest() && state != RobotState.TEST)
                {
                    System.out.println("**********TEST STARTED************");
                    testStarted();
                    HAL.observeUserProgramTest();
                }
            }
            else if (state != RobotState.STANDBY)
            {
                System.out.println("**********ROBOT DISABLED************");
                disabledStarted();
                HAL.observeUserProgramDisabled();
            }
            SmartDashboard.updateValues();
            mainLoop();
        }
    }

    @Override
    public void endCompetition() 
    {
    
    }
}

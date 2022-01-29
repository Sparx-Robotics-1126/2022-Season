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
 * Controls when subsystems are engadged as well as gives control to correct controller (auto/teleop/test)
 */
public class Robot extends RobotBase
{
    //States robot can be in
    public enum RobotState
    {
		STANDBY,
		AUTO,
        TELE,
        TEST;
	}

    //Controllers
    private TeleopControls teleopControls;
    private AutoControl autoControls;
    private TestControls testControls;
    
    //Robot Subsystems
    private Drives drives;

    //Acting Controller (Auto/Teleop/Test)
    private Controller currentController;

    //Keeps track of current state
    private RobotState state;

    private void robotInit()
    {
        state = RobotState.STANDBY; //When robot turns on, we don't want anything running in the background
        
        //Sensors
        DrivesSensorInterface drivesSensors = new DrivesSensors();
        
        //Subsystems
        drives = new Drives(drivesSensors); // Creates drives instance
        
        //Controls
        teleopControls = new TeleopControls(drives); //Creates controller instance, passes in drives subsystem
        autoControls = new AutoControl(drives);
        testControls = new TestControls(drives);

        //Starting Subsystems
        new Thread(drives).start();
    }

    private void disabledStarted()
    {
        state = RobotState.STANDBY;
        autoControls.resetAuto();
    }

    /**
     * Called by Robot.java when auto has been started
     */
    private void autoStarted()
    {
    	currentController = autoControls;
        state = RobotState.AUTO;
    }

    /**
     * Called by Robot.java when teleop has been started
     */
    private void teleopStarted()
    {
    	drives.startDriverControlled();
    	currentController = teleopControls;
    	state = RobotState.TELE;
    }

    private void testStarted()
    {
        testControls.reset();
        currentController = testControls;
        state = RobotState.TEST;
    }

    //Main Method
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

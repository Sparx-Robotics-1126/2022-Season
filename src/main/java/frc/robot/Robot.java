package frc.robot;

import edu.wpi.first.hal.HAL;

import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.controllers.Controller;
import frc.controllers.TeleoperatedController;
import frc.controllers.AutonomousController;
import frc.controllers.TestController;

import frc.subsystem.Acquisitions;
import frc.subsystem.Drives;
import frc.subsystem.Shooter;
import frc.drives.DrivesSensorInterface;
import frc.drives.DrivesSensors;
import frc.sensors.Limelight;
import frc.shooter.ShooterSensorInterface;
import frc.shooter.ShooterSensors;
import frc.acquisitions.AcquisitionsSensorInterface;
import frc.acquisitions.AcquisitionsSensors;

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
    private TeleoperatedController teleopControls;
    private AutonomousController autoControls;
    private TestController testControls;
    
    //The robot subsystems.
    public Drives drives; //TODO probably not a good idea to keep this public (Accessed by Shooter)
    private Acquisitions acquisitions;
    private Shooter shooter;

    //The acting Controller of the robot.
    private Controller currentController;

    //Sensors.
    private DrivesSensorInterface drivesSensors;
    private AcquisitionsSensorInterface acquisitionsSensors;
    private ShooterSensorInterface shooterSensors;

    //Limelight
    private Limelight limelight;

    //Keeps track of the current state of the robot.
    private RobotState state;

    private void robotInit()
    {
        state = RobotState.STANDBY; //When robot turns on, we don't want anything running in the background.
        
        //Initialize sensors.
        drivesSensors = new DrivesSensors();
        acquisitionsSensors = new AcquisitionsSensors();
        shooterSensors = new ShooterSensors();
        
        //Initialize Subsystems.
        drives = new Drives(drivesSensors);
        acquisitions = new Acquisitions(acquisitionsSensors);
        shooter = new Shooter(shooterSensors);
        
        //Initialize Controllers.
        teleopControls = new TeleoperatedController(drives, acquisitions);
        autoControls = new AutonomousController(drives, acquisitions);
        testControls = new TestController(drives, acquisitions);

        //Limelight
        limelight = new Limelight();

        //Start subsystem threads.
        new Thread(drives).start();
        new Thread(acquisitions).start();
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

    public Drives getDrives()
    {
        return drives;
    }

    public Acquisitions getAcquisitions()
    {
        return acquisitions;
    }

    public Shooter getShooter()
    {
        return shooter;
    }

    public static Limelight getLimelight()
    {
        return limelight;
    }
}

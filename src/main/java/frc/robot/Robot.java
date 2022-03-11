package frc.robot;

import frc.controllers.Controller;
import frc.controllers.TeleoperatedController;
import frc.controllers.AutonomousController;
import frc.controllers.TestController;

import frc.subsystem.Acquisitions;
import frc.subsystem.Drives;
import frc.subsystem.Shooter;
import frc.subsystem.Storage;
import frc.drives.DrivesSensorInterface;
import frc.drives.DrivesSensors;
import frc.sensors.Limelight;
import frc.shooter.ShooterSensorInterface;
import frc.shooter.ShooterSensors;
import frc.storage.StorageSensorInterface;
import frc.storage.StorageSensors;
import frc.acquisitions.AcquisitionsSensorInterface;
import frc.acquisitions.AcquisitionsSensors;

import edu.wpi.first.hal.HAL;

import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.kauailabs.navx.frc.AHRS;

/**
 * The main controlling class of the Robot. Controls all subsystems via specialized Controllers.
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
    private static Drives drives;
    private static Acquisitions acquisitions;
    private static Shooter shooter;
    private static Storage storage;

    //The acting Controller of the robot.
    private Controller currentController;

    //Sensors.
    private DrivesSensorInterface drivesSensors;
    private AcquisitionsSensorInterface acquisitionsSensors;
    private ShooterSensorInterface shooterSensors;
    private StorageSensorInterface storageSensors;

    private static AHRS gyro;
    private static Limelight limelight;

    //Keeps track of the current state of the robot.
    private RobotState state;

    private void robotInit()
    {
        state = RobotState.STANDBY; //When robot turns on, we don't want anything running in the background.
        
        //Initialize sensors.
        drivesSensors = new DrivesSensors();
        acquisitionsSensors = new AcquisitionsSensors();
        shooterSensors = new ShooterSensors();
        storageSensors = new StorageSensors();
        
        gyro = new AHRS(IO.USB_ONBOARD);
        limelight = new Limelight();
        
        //Initialize Subsystems.
        drives = new Drives(drivesSensors);
        acquisitions = new Acquisitions(acquisitionsSensors);
        shooter = new Shooter(shooterSensors);
        storage = new Storage(storageSensors);
        
        //Initialize Controllers.
        teleopControls = new TeleoperatedController();
        autoControls = new AutonomousController();
        testControls = new TestController();

        //Start subsystem threads.
        new Thread(drives).start();
        new Thread(acquisitions).start();
        new Thread(shooter).start();
        new Thread(storage).start();
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
     * Called when test begins.
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
        SmartDashboard.putNumber("LIMELIGHT_Y", limelight.getHorizontalPixelsFromTarget());

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
        System.out.println("******** ROBOT INIT ********");

        HAL.observeUserProgramStarting();
        robotInit();

        System.out.println("************ ENGAGING MAIN LOOP ************");

        while (true)
        {
            if (!isDisabled())
            {
                if (isAutonomous() && state != RobotState.AUTO)
                {
                    System.out.println("********** AUTONOMOUS STARTED ************");
                    autoStarted();
                    HAL.observeUserProgramAutonomous();
                }
                else if (isTeleop() && state != RobotState.TELE)
                {
                    teleopStarted();
                    System.out.println("********** TELEOPERATED STARTED ************");
                    HAL.observeUserProgramTeleop();
                }
                else if (isTest() && state != RobotState.TEST)
                {
                    System.out.println("********** TEST STARTED ************");
                    testStarted();
                    HAL.observeUserProgramTest();
                }
            }
            else if (state != RobotState.STANDBY)
            {
                System.out.println("********** ROBOT DISABLED ************");
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
    
    /**
     * @return The instance of the Drives subsystem currently in use by the robot. Null if the robot has not been initialized yet.
     */
    public static Drives getDrives()
    {
        return drives;
    }

    /**
     * @return The instance of the Acquisitions subsystem currently in use by the robot. Null if the robot has not been initialized yet.
     */
    public static Acquisitions getAcquisitions()
    {
        return acquisitions;
    }

    public static Shooter getShooter()
    {
        return shooter;
    }
    
    public static Storage getStorage() {
        return storage;
    }

    /**
     * @return The AHRS interface for the navX sensor currently in use by the robot.
     */
    public static AHRS getGyro()
    {
        return gyro;
    }

    /**
     * @return The Limelight currently in use by the robot.
     */
    public static Limelight getLimelight()
    {
        return limelight;
    }
}

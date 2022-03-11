package frc.subsystem;

import frc.robot.IO;

import frc.storage.StorageCommand;
import frc.storage.StorageOutput;
import frc.storage.StorageSensorInterface;
import frc.storage.commands.*;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Storage extends Subsystem
{
    private StorageCommand storageCommand;
	private StorageSensorInterface sensors;
    private CANSparkMax storageMotor;
    
	private static short numBalls;

    public Storage(StorageSensorInterface sensors)
    {
        numBalls = 1;
        this.sensors = sensors;

		storageMotor = new CANSparkMax(IO.STORAGE_MOTOR, MotorType.kBrushless);
    }

    /**
     * @return number of balls
     */
    public static short getNumBalls()
    {
        return numBalls;
    }

    /**
     * Sets the number of balls
     */
    public static void setNumBalls(int newBallCount)
    {
        numBalls = (short) newBallCount;
    }   

    @Override
    void execute() 
    {
        SmartDashboard.putBoolean("STORAGE_IR_SENSOR", sensors.getStorageIRSensor());

        if (storageCommand == null)
            checkForBalls();

        SmartDashboard.putString("STORAGE_COMMAND", storageCommand.getClass().getName());

        if (storageCommand != null) 
        {
			StorageOutput output = storageCommand.execute();
            SmartDashboard.putNumber("STORAGE_OUTPUT", output.get());
            storageMotor.set(output.get());

			if (output.isDone()) 
            {
                storageMotor.set(0);
				storageCommand = null;
			}
		}
    }

    @Override
    public boolean isDone() 
    {
        return storageCommand == null;
    }

    public void checkForBalls() 
    {
        storageCommand = new CheckForBalls(sensors);
    }

    public void indexBall() 
    {
        storageCommand = new IndexBall(sensors);
    }

    public void shootBall() 
    {
        storageCommand = new ShootBall(sensors);
    }
}

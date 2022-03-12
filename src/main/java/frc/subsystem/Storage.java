package frc.subsystem;

import frc.robot.IO;

import frc.storage.StorageCommand;
import frc.storage.StorageOutput;
import frc.storage.StorageSensorInterface;
import frc.storage.commands.*;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

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
        if (storageCommand != null) 
        {
			StorageOutput output = storageCommand.execute();
            storageMotor.set(output.get());

			if (output.isDone()) 
            {
                storageMotor.set(0);
				storageCommand = null;
			}
		}
        else
            storageMotor.set(new CheckForBalls(sensors).execute().get());
    }

    @Override
    public boolean isDone() 
    {
        return storageCommand == null;
    }

    public void setSpeed()
    {
        storageCommand = new StaticSpeed(sensors);
    }

    public void setSpeed(double speed)
    {
        storageCommand = new StaticSpeed(sensors, speed);
    }

    public void stopStorage()
    {
        storageCommand = new StopStorage(sensors);
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

package frc.storage;

import frc.robot.IO;

import edu.wpi.first.wpilibj.DigitalInput;

import com.revrobotics.RelativeEncoder;

public class StorageSensors implements StorageSensorInterface
{
	private RelativeEncoder storageEncoder;

    private DigitalInput storageIR;

    /**
	 * Creates a new object for interfacing with the various sensors of the Storage subsystem (e.g. encoders).
	 */
	public StorageSensors() 
	{
		storageIR = new DigitalInput(IO.STORAGE_IR);
	}

	public void addEncoders(RelativeEncoder encoder)
	{
		storageEncoder = encoder;
	}

	/**
	 * Gets the distance the storage motor has rotated.
	 */
	@Override
	public double getStorageEncoderDistance() 
	{
		return storageEncoder.getPosition();
	}

	@Override
	public boolean getStorageIRSensor() 
	{
        return !storageIR.get();
	}
}
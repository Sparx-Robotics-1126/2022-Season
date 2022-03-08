package frc.storage;

import frc.robot.IO;

import edu.wpi.first.wpilibj.Encoder;

public class StorageSensors implements StorageSensorInterface
{
	private Encoder storageEncoder;

    /**
	 * Creates a new object for interfacing with the various sensors of the Storage subsystem (e.g. encoders).
	 */
	public StorageSensors() 
	{
		storageEncoder = new Encoder(IO.STORAGE_ENCODER_A, IO.STORAGE_ENCODER_B);
		storageEncoder.setDistancePerPulse(0.314789);
	}

	/**
	 * Gets the distance the storage motor has rotated.
	 */
	@Override
	public double getStorageEncoderDistance() 
	{
		return storageEncoder.getDistance();
	}
}
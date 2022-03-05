package frc.storage;

import frc.robot.IO;

import edu.wpi.first.wpilibj.Encoder;

public class StorageSensors implements StorageSensorInterface
{
	private Encoder storageEncoder;

    //Create Joystick
	private boolean storageButton;

    /**
	 * Creates a new object for interfacing with the various sensors of the Storage subsystem (e.g. encoders).
	 */
	public StorageSensors() 
	{
		storageEncoder = new Encoder(IO.STORAGE_ENCODER_A, IO.STORAGE_ENCODER_B);
		storageEncoder.setDistancePerPulse(0.314789);

		storageButton = false;
	}

	/**
	 * Gets the distance the storage motor has rotated.
	 */
	@Override
	public double getStorageEncoderDistance() 
	{
		return storageEncoder.getDistance();
	}

	/**
	 * @return The storage button's set value.
	 */
	@Override
	public boolean getStorageButton() 
	{
		return storageButton;
	}

	/**
	 * Sets the value of the storage button.
	 * @param value The value to set.
	 */
	@Override
	public void setStorageButton(boolean value) 
	{
		storageButton = value;
	}
}
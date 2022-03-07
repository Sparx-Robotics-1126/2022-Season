package frc.storage;

import edu.wpi.first.wpilibj.Encoder;
import frc.robot.IO;

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
	 * Gets the distance the Storage motor has rotated.
	 */
	@Override
	public double getArmEncoderDistance() 
	{
		return storageEncoder.getDistance();
	}

	/**
	 * @return The Storage button's set value.
	 */
	@Override
	public boolean getArmButton() 
	{
		return storageButton;
	}

	/**
	 * Sets the value of the Storage button.
	 * @param value The value to set.
	 */
	@Override
	public void setArmButton(boolean value) 
	{
		storageButton = value;
	}
}

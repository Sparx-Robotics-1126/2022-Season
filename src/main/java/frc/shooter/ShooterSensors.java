package frc.shooter;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Encoder;

import frc.robot.IO;
import frc.sensors.Limelight;

public class ShooterSensors implements ShooterSensorInterface
{
    private RelativeEncoder shooterEncoder;
	private Limelight limeSensor;

    /**
	 * Creates a new object for interfacing with the various sensors of the Shooter subsystem (e.g. encoders).
	 */
	public ShooterSensors() 
	{
		shooterEncoder = new Encoder(IO.SHOOTER_ENCODER_A, IO.SHOOTER_ENCODER_B);
		shooterEncoder.setDistancePerPulse(0.314789); //TODO Change this or something idk
		limeSensor = new Limelight();
	}

	/**
	 * Gets the distance from the limelight to the target
	 */
	public double getDistanceToTarget() 
	{
		return limeSensor.getDistanceFromTarget();
	}

	/**
	 * Gets the angle of the target compared to the limelight
	 */
	public double getAngleToTarget() 
	{
		return limeSensor.getAngleFromTarget();
	}

	@Override
	public void enableLimelight(boolean enable) 
	{
		limeSensor.enable(enable);
	}

    /**
	 * Gets the speed that the motor is rotating.
	 */
	@Override
	public double getShooterSpeed() 
	{
		return shooterEncoder.getRate();
	}

	public Encoder getEncoder()
	{
		return shooterEncoder;
	}

	public double getMotorDistance()
	{
		return shooterEncoder.getDistance();
	}
}

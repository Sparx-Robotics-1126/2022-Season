package frc.shooter;

import com.revrobotics.RelativeEncoder;

import frc.robot.Robot;

public class ShooterSensors implements ShooterSensorInterface
{
    private RelativeEncoder shooterEncoder;

    /**
	 * Creates a new object for interfacing with the various sensors of the Shooter subsystem (e.g. encoders).
	 */
	public ShooterSensors() 
	{

	}

	@Override
	public void addEncoders(RelativeEncoder shooterSpark) {
		this.shooterEncoder = shooterSpark;
	}

	/**
	 * Gets the distance from the limelight to the target
	 */
	public double getDistanceToTarget() 
	{
		return Robot.getLimelight().getDistanceFromTarget();
	}

	/**
	 * Gets the angle of the target compared to the limelight
	 */
	public double getAngleToTarget() 
	{
		return Robot.getLimelight().getAngleFromTarget();
	}

	@Override
	public void enableLimelight(boolean enable) 
	{
		Robot.getLimelight().enable(enable);
	}

    /**
	 * Gets the speed that the motor is rotating.
	 */
	@Override
	public double getShooterSpeed() 
	{
		return shooterEncoder.getVelocity();
	}

	public double getMotorDistance()
	{
		return shooterEncoder.getPosition();
	}
}
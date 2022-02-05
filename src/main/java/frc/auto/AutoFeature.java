package frc.auto;

/**
 * Specifies all potential types of features used during autonomous.
 */
public enum AutoFeature 
{
	/**
	 * End the current autonomous routine.
	 */
	STOP("Stopping Auto"),
	
	/**
	 * Wait the specified number of milliseconds.
	 * Value one specifies the amount of milliseconds to wait.
	 */
	WAIT("Waiting..."),
	
	/**
	 * Wait for the current drives command to finish.
	 */
	DRIVE_DONE("Drive: Waiting for Complete..."),
	
	/**
	 * Drive the robot straight forward the specified distance at the specified speed.
	 * Value one dictates speed.
	 * Value two dictates the desired distance.
	 */
	DRIVE_FORWARD("Drive: Forward"),
	
	/**
	 * Drive the robot straight backward the specified distance at the specified speed.
	 * Value one dictates speed.
	 * Value two dictates the desired distance.
	 */
	DRIVE_BACKWARDS("Drive: Backward"),
	
	/**
	 * Turn the robot right by the specified speed and degrees.
	 * Value one dictates speed.
	 * Value two dictates the desired angle.
	 */
	DRIVE_TURN_RIGHT("Drive: Turn Right"),
	
	/**
	 * Turn the robot left by the specified speed and degrees.
	 * Value one dictates speed.
	 * Value two dictates the desired angle.
	 */
	DRIVE_TURN_LEFT("Drive: Turn Left");
	
	private String name;

	private AutoFeature(String name)
	{
		this.name = name;
	}
	
	@Override
	public String toString() 
	{
		return name;
	}
}

package frc.auto;

public enum AutoFeature {
	/**
	 * End Autonomous Routine
	 */
	STOP("Stopping Auto"),
	
	/**
	 * Wait number of milliseconds
	 * Value1: milliseconds to wait
	 */
	WAIT("Waiting..."),
	
	/**
	 * Wait for last drives command to complete
	 */
	DRIVE_DONE("Drive: Waiting for Complete..."),
	
	/**
	 * Have robot drive straight
	 * Value 1: Speed
	 * Value 2: Distance
	 */
	DRIVE_FORWARD("Drive: Forward"),
	
	/**
	 * Have robot drive backwards
	 * Value1: Speed
	 * Value2: Distance
	 */
	DRIVE_BACKWARDS("Drive: Backward"),
	
	/**
	 * Have robot turn right
	 * Value1: Speed
	 * Value2: Degrees
	 */
	DRIVE_TURN_RIGHT("Drive: Turn Right"),
	
	/**
	 * HAve robot turn left
	 * Value1: Speed
	 * Value2: Degrees
	 */
	DRIVE_TURN_LEFT("Drive: Turn Left"),
	
	/**
	 * Wait for shooter to complete last shooter command
	 */
	SHOOTER_DONE("Shooter: Waiting for Complete..."),
	
	/**
	 * Wait for the shooter to be ready to fire
	 */
	SHOOTER_READY_TO_FIRE("Shooter: Ready to Fire"),
	
	/**
	 * Active Limelight targeting system
	 * Will enable both active turreting and flywheel control
	 */
	SHOOTER_ACTIVATE_LIMELIGHT("Shooter: Limelight Enabled"),
	
	/**
	 * Deactivate Limelight targeting system
	 * Will turn off limelight and spin down flywheel
	 */
	SHOOTER_DEACTIVATE_LIMELIGHT("Shooter: Limelight Disabled"),
	
	/**
	 * Will shoot ONE power cell when ready
	 */
	STORAGE_SHOOT("Storage: Shooting"),
	
	/**
	 * Wait for last Storage command to complete
	 */
	STORAGE_DONE("Storage: Waiting for Complete..."),
	
	/**
	 * Will bring highest ball to top of shooter ready for launch
	 * WILL NOT SHOOT BALL
	 */
	STORAGE_PREPARE_FOR_SHOOTING("Storage: Preparing to Shoot"),
	
	/**
	 * Wait for last Acq command to complete
	 */
	ACQ_DONE("Acq: Waiting for Complete..."),
	
	/**
	 * Turn on Acq Rollers
	 * Will also deal with the storage indexing
	 */
	ACQ_ACQUIRE("Acq: Acquiring"),
	
	/**
	 * Turn off Acq Rollers
	 */
	ACQ_STOP_ACQUIRING("Acq: Stop Acquiring");
	
	private String name;
	private AutoFeature(String name){
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}

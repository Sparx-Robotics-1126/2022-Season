package frc.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SerialPort;

/**
 * Enumeration for all motor and sensor IO ports from the RIO.
 */
public final class IO 
{
	private IO() {}

	//MOTORS
	public static final int DRIVES_RIGHT_MOTOR_1			= 24;
	public static final int DRIVES_RIGHT_MOTOR_2			= 26;
	public static final int DRIVES_LEFT_MOTOR_1				= 27;
	public static final int DRIVES_LEFT_MOTOR_2				= 25;

	public static final int ACQUISITIONS_ARM_MOTOR			= 3;
	public static final int ACQUISITIONS_INTAKE_MOTOR		= 11;

	public static final int SHOOTER_MOTOR					= 1;
	public static final int STORAGE_MOTOR					= 2;
	
	//SENSORS
	public static final I2C.Port I2C_ONBOARD				= I2C.Port.kOnboard;
	public static final SerialPort.Port USB_ONBOARD 		= SerialPort.Port.kUSB;

	public static final int STORAGE_IR						= 18;
}
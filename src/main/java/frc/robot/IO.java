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

	public static final int ACQUISITIONS_ARM_MOTOR			= 28; //PLACEHOLDER
	public static final int ACQUISITIONS_INTAKE_MOTOR		= 29; //PLACEHOLDER

	public static final int SHOOTER_MOTOR					= 1;
	
	//SENSORS
	public static final I2C.Port I2C_ONBOARD				= I2C.Port.kOnboard;
	public static final SerialPort.Port USB_ONBOARD 		= SerialPort.Port.kUSB;

	public static final int ACQUISITIONS_ARM_ENCODER_A		= 31; //PLACEHOLDER
	public static final int ACQUISITIONS_ARM_ENCODER_B		= 1; //PLACEHOLDER

	public static final int SHOOTER_ENCODER_A				= 2; //PLACEHOLDER
	public static final int SHOOTER_ENCODER_B				= 3; //PLACEHOLDER
}
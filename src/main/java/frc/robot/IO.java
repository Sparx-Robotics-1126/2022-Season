package frc.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SerialPort;

/**
 * Enumeration for all motor and sensor IO ports from the RIO.
 */
public final class IO 
{
	//MOTORS
	public static final int DRIVES_RIGHT_MOTOR_1	= 24;
	public static final int DRIVES_RIGHT_MOTOR_2	= 26; 
	public static final int DRIVES_LEFT_MOTOR_1		= 27;
	public static final int DRIVES_LEFT_MOTOR_2		= 25; 
	
	//SENSORS
	public static final I2C.Port I2C_ONBOARD		= I2C.Port.kOnboard;
	public static final SerialPort.Port USB_ONBOARD = SerialPort.Port.kUSB;
}
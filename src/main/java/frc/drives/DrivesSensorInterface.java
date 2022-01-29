package frc.drives;

import com.revrobotics.RelativeEncoder;

/**
 * Any and all sensor data required for operation of the Drives subsystem, including encoders, limit switches, 
 * vision, joysitck input, etc..
 */
public interface DrivesSensorInterface 
{
	void addEncoders(RelativeEncoder leftSpark, RelativeEncoder rightSpark);
	
    //Sensors.
    double getLeftEncoderDistance();
    double getLeftEncoderSpeed();
    double getRightEncoderDistance();
    double getRightEncoderSpeed();
    double getAverageEncoderDistance();
    double getAverageEncoderSpeed();
    double getGyroAngle();

    //Operator input.
    double getRightJoyStick();
    double getLeftJoyStick();
    void setRightJoystick(double value);
    void setLeftJoystick(double value);
}
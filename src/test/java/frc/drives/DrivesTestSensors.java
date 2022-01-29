package frc.drives;

import com.revrobotics.CANEncoder;

public class DrivesTestSensors implements DrivesSensorInterface{

	private double rightEncoderDistance;
	private double rightEncoderSpeed;
	private double leftEncoderDistance;
	private double leftEncoderSpeed;
	private double gyroAngle;
	
	private double leftJoystick;
	private double rightJoystick;
	
	@Override
	public double getLeftEncoderDistance() { return leftEncoderDistance; }

	@Override
	public double getLeftEncoderSpeed() { return leftEncoderSpeed; }

	@Override
	public double getRightEncoderDistance() { return rightEncoderDistance; }

	@Override
	public double getRightEncoderSpeed() { return rightEncoderSpeed; }

	@Override
	public double getAverageEncoderDistance() {return (leftEncoderDistance + rightEncoderDistance) / 2; }

	@Override
	public double getAverageEncoderSpeed() { return (leftEncoderSpeed + rightEncoderSpeed) / 2; }

	@Override
	public double getGyroAngle() { return gyroAngle; }

	@Override
	public double getRightJoyStick() { return rightJoystick; }

	@Override
	public double getLeftJoyStick() { return leftJoystick; }

	
	//------------------SETTERS----------------------------
	public void setRightEncoderDistance(double rightEncoderDistance) {
		this.rightEncoderDistance = rightEncoderDistance;
	}

	public void setRightEncoderSpeed(double rightEncoderSpeed) {
		this.rightEncoderSpeed = rightEncoderSpeed;
	}

	public void setLeftEncoderDistance(double leftEncoderDistance) {
		this.leftEncoderDistance = leftEncoderDistance;
	}

	public void setLeftEncoderSpeed(double leftEncoderSpeed) {
		this.leftEncoderSpeed = leftEncoderSpeed;
	}

	public void setGyroAngle(double gyroAngle) {
		this.gyroAngle = gyroAngle;
	}

	public void setLeftJoystick(double leftJoystick) {
		this.leftJoystick = leftJoystick;
	}

	public void setRightJoystick(double rightJoystick) {
		this.rightJoystick = rightJoystick;
	}

	//NOT USED
	@Override
	public void addEncoders(CANEncoder leftSpark, CANEncoder rightSpark) {}
}

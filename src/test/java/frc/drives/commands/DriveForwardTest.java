package frc.drives.commands;

import static org.junit.Assert.*;

import org.junit.Test;

import frc.drives.DrivesOutput;
import frc.drives.DrivesTestSensors;

public class DriveForwardTest 
{
	private DrivesTestSensors sensors;

	@Test
	public void motorsAreEqual() 
	{
		sensors = new DrivesTestSensors();
		DriveForward command = new DriveForward(sensors, 1, 100);
		sensors.setLeftEncoderDistance(20);
		sensors.setRightEncoderDistance(20);
		DrivesOutput output = command.execute();
		assertEquals("Motors are not the same speed.", output.getRightMotor(), output.getLeftMotor(), 0.001);
	}

	@Test
	public void pointedRight_ShouldSlowLeft() 
	{
		sensors = new DrivesTestSensors();
		DriveForward command = new DriveForward(sensors, 1, 100);
		sensors.setLeftEncoderDistance(20);
		sensors.setRightEncoderDistance(20);
		sensors.setGyroAngle(5);
		DrivesOutput output = command.execute();

		assertTrue("Front pointed right should slow down left", output.getRightMotor() < output.getLeftMotor()); //Negative numbers.
	}

	@Test
	public void pointedLeft_ShouldSlowRight() 
	{
		sensors = new DrivesTestSensors();
		DriveForward command = new DriveForward(sensors, 1, 100);
		sensors.setLeftEncoderDistance(20);
		sensors.setRightEncoderDistance(20);
		sensors.setGyroAngle(-5);
		DrivesOutput output = command.execute();

		assertTrue("Front pointed right should slow down right", output.getRightMotor() > output.getLeftMotor()); //Negative numbers.
	}

	@Test
	public void atDistance_ShouldStop()
	{
		sensors = new DrivesTestSensors();
		DriveForward command = new DriveForward(sensors, 1, 10);
		DrivesOutput firstOutput = command.execute();
		assertTrue(firstOutput.getLeftMotor() > 0);
		assertTrue(firstOutput.getRightMotor() > 0);

		sensors.setLeftEncoderDistance(5);
		sensors.setRightEncoderDistance(5);
		DrivesOutput secondOutput = command.execute();
		assertTrue(secondOutput.getLeftMotor() > 0);
		assertTrue(secondOutput.getRightMotor() > 0);
		assertTrue(firstOutput.getLeftMotor() > secondOutput.getLeftMotor()); //Negative numbers.
		assertTrue(firstOutput.getRightMotor() > secondOutput.getRightMotor());//Negative numbers.

		sensors.setLeftEncoderDistance(10);
		sensors.setRightEncoderDistance(10);
		DrivesOutput lastOutput = command.execute();
		assertEquals(0, lastOutput.getLeftMotor(), 0);
		assertEquals(0, lastOutput.getRightMotor(), 0);
	}
} 
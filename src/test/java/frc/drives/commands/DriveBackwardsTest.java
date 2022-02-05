package frc.drives.commands;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import frc.drives.DrivesOutput;
import frc.drives.DrivesTestSensors;

public class DriveBackwardsTest 
{
	private DrivesTestSensors sensors;

	@Before
	public void setup() 
	{
		sensors = new DrivesTestSensors();
	}

	@Test
	public void motorsAreEqual() 
	{
		DriveBackwards backwardC = new DriveBackwards(sensors, 10);
		sensors.setLeftEncoderDistance(20);
		sensors.setRightEncoderDistance(20);
		DrivesOutput output = backwardC.execute();
		assertEquals("Motors are not the same speed.", output.getRightMotor(), output.getLeftMotor(), 0.001);
	}

	@Test
	public void pointedRight_ShouldSlowLeft() 
	{
		DriveBackwards backwardC = new DriveBackwards(sensors, 10);
		sensors.setLeftEncoderDistance(20);
		sensors.setRightEncoderDistance(20);
		sensors.setGyroAngle(5);
		DrivesOutput output = backwardC.execute();

		assertTrue("Front pointed right should slow down right", output.getRightMotor() < output.getLeftMotor()); //Negative numbers.
	}

	@Test
	public void pointedLeft_ShouldSlowRight() 
	{
		DriveBackwards backwardC = new DriveBackwards(sensors, 10);
		sensors.setLeftEncoderDistance(20);
		sensors.setRightEncoderDistance(20);
		sensors.setGyroAngle(-5);
		DrivesOutput output = backwardC.execute();

		assertTrue("Front pointed right should slow down right", output.getRightMotor() > output.getLeftMotor()); //Negative numbers.
	}

	@Test
	public void atDistance_ShouldStop()
	{
		sensors.setLeftEncoderDistance(0);
		sensors.setRightEncoderDistance(0);
		DriveBackwards backwardC = new DriveBackwards(sensors, 10);
		DrivesOutput firstOutput = backwardC.execute();
		assertTrue(firstOutput.getLeftMotor() < 0);
		assertTrue(firstOutput.getRightMotor() < 0);

		sensors.setLeftEncoderDistance(-5);
		sensors.setRightEncoderDistance(-5);
		DrivesOutput secondOutput = backwardC.execute();
		assertTrue(secondOutput.getLeftMotor() < 0);
		assertTrue(secondOutput.getRightMotor() < 0);
		assertTrue(firstOutput.getLeftMotor() < secondOutput.getLeftMotor());//Negative Numbers
		assertTrue(firstOutput.getRightMotor() < secondOutput.getRightMotor());//Negative Nubmers

		sensors.setLeftEncoderDistance(-10);
		sensors.setRightEncoderDistance(-10);
		DrivesOutput lastOutput = backwardC.execute();
		assertEquals(0, lastOutput.getLeftMotor(), 0);
		assertEquals(0, lastOutput.getRightMotor(), 0);
	}
} 
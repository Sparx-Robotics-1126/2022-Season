package frc.drives.commands;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import frc.drives.DrivesOutput;
import frc.drives.DrivesTestSensors;

public class SpinLeftTests 
{	
	private DrivesTestSensors sensors;
	
	@Before
	public void setup() 
	{
		sensors = new DrivesTestSensors();
	}
	
	@Test
	public void spinRight_ShouldTurnLeft() 
	{
		//Set simulated sensor data.
		sensors.setGyroAngle(0);
		
		TurnLeft spinCommand = new TurnLeft(sensors, 0.5, 180);
		DrivesOutput output = spinCommand.execute();
		
		//Make sure the left motor is moving backwards at the requested speed.
		assertEquals("Left motor should have been moving backward.", -0.5, output.getLeftMotor(), 0.001);
		//Make sure the right motor is moving forward at the requested speed.
		assertEquals("Right motor should have been moving forward.", 0.5, output.getRightMotor(), 0.001);
		//Make sure isDone is false, as the sensor only reads 0 not 180.
		assertEquals("Left turn was not completed.", false, output.isDone());
	}
	
	@Test
	public void completedSpinLeft_ShouldStop() 
	{
		//Set simulated sensor data.
		sensors.setGyroAngle(180);
		
		TurnLeft spinCommand = new TurnLeft(sensors, 0.5, -180);
		DrivesOutput output = spinCommand.execute();
		
		//Make sure the left motor is stopped.
		assertEquals("The left motor should be stopped.", 0, output.getLeftMotor(), 0.001);
		//Make sure the right motor is stopped.
		assertEquals("The right motor should be stopped.", 0, output.getRightMotor(), 0.001);
		//Make sure isDone is false, as the sensor only reads 0 not 180.
		assertEquals("Left turn failed to stop.", true, output.isDone());
	}
}
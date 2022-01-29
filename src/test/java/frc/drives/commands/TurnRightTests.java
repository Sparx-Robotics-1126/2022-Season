package frc.drives.commands;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import frc.drives.DrivesOutput;
import frc.drives.DrivesSensorInterface;
import frc.drives.DrivesTestSensors;

public class TurnRightTests {
	
	private DrivesTestSensors sensors;
	
	@Before
	public void setup() {
		sensors = new DrivesTestSensors();
	}
	
	@Test
	public void turnRight_ShouldTurnRight() {
		//Set fake sensor data
		sensors.setGyroAngle(0);
		
		TurnRight turnCommand = new TurnRight(sensors, 0.5, 180);
		DrivesOutput output = turnCommand.execute();
		
		//Make sure left motor is moving forward at requested speed
		assertEquals("Left Motor should be moving forward", 0.5, output.getLeftMotor(), 0.001);
		//Make sure right motor is moving backwards at requested speed
		assertEquals("Right Motor should be moving backwards", -0.5, output.getRightMotor(), 0.001);
		//Make sure isDone is false, as the sensor only reads 0 not 180
		assertEquals("Turn not completed!!", false, output.isDone());
	}
	
	@Test
	public void completedTurnRight_ShouldStop() {
		//Set fake sensor data
		sensors.setGyroAngle(180);
		
		TurnRight turnCommand = new TurnRight(sensors, 0.5, 180);
		sensors.setGyroAngle(360);
		DrivesOutput output = turnCommand.execute();
		
		//Make sure left motor is moving forward at requested speed
		assertEquals("Left Motor should be stopped", 0, output.getLeftMotor(), 0.001);
		//Make sure right motor is moving backwards at requested speed
		assertEquals("Right Motor should be stopped", 0, output.getRightMotor(), 0.001);
		//Make sure isDone is false, as the sensor only reads 0 not 180
		assertEquals("Turn not stopping!!", true, output.isDone());
	}
		
	
	@Test
	public void TurnRight_ShouldStopWhenCurrentAngleIsGreaterThanDesiredAngle() {
		//Set fake sensor data
		sensors.setGyroAngle(180);
		
		TurnRight turnCommand = new TurnRight(sensors, 0.5, 180);
		sensors.setGyroAngle(361);
		DrivesOutput output = turnCommand.execute();
		
		//Make sure left motor is moving forward at requested speed
		assertEquals("Left Motor should be stopped", 0, output.getLeftMotor(), 0.001);
		//Make sure right motor is moving backwards at requested speed
		assertEquals("Right Motor should be stopped", 0, output.getRightMotor(), 0.001);
		//Make sure isDone is false, as the sensor only reads 0 not 180
		assertEquals("Turn not stopping!!", true, output.isDone());
	}

}

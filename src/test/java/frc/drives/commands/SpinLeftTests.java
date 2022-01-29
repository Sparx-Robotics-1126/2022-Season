package frc.drives.commands;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import frc.drives.DrivesOutput;
import frc.drives.DrivesSensorInterface;
import frc.drives.DrivesTestSensors;

public class SpinLeftTests {
	
	private DrivesTestSensors sensors;
	
	@Before
	public void setup() {
		sensors = new DrivesTestSensors();
	}
	
	@Test
	public void spinRight_ShouldTurnLeft() {
		//Set fake sensor data
		sensors.setGyroAngle(0);
		
		SpinLeft spinCommand = new SpinLeft(sensors, 0.5, 180);
		DrivesOutput output = spinCommand.execute();
		
		//Make sure left motor is moving forward at requested speed
		assertEquals("Left Motor should be moving backwards", -0.5, output.getLeftMotor(), 0.001);
		//Make sure right motor is moving backwards at requested speed
		assertEquals("Right Motor should be moving forwards", 0.5, output.getRightMotor(), 0.001);
		//Make sure isDone is false, as the sensor only reads 0 not 180
		assertEquals("Turn not completed!!", false, output.isDone());
	}
	
	@Test
	public void completedSpinLeft_ShouldStop() {
		//Set fake sensor data
		sensors.setGyroAngle(180);
		
		SpinLeft spinCommand = new SpinLeft(sensors, 0.5, -180);
		DrivesOutput output = spinCommand.execute();
		
		//Make sure left motor is moving forward at requested speed
		assertEquals("Left Motor should be stopped", 0, output.getLeftMotor(), 0.001);
		//Make sure right motor is moving backwards at requested speed
		assertEquals("Right Motor should be stopped", 0, output.getRightMotor(), 0.001);
		//Make sure isDone is false, as the sensor only reads 0 not 180
		assertEquals("Turn not stopping!!", true, output.isDone());
	}

}

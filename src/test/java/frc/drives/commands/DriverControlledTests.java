package frc.drives.commands;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import frc.drives.DrivesCommand;
import frc.drives.DrivesOutput;
import frc.drives.DrivesSensorInterface;
import frc.drives.DrivesTestSensors;

public class DriverControlledTests {
	
	private DrivesTestSensors sensors;
	
	@Before
	public void setup() {
		sensors = new DrivesTestSensors();
	}
	
	@Test
	public void command_shouldReturnJoystickValue() {
		DrivesCommand command = new DriverControlled(sensors);
		sensors.setLeftJoystick(0);
		sensors.setRightJoystick(0);
		DrivesOutput output = command.execute();
		assertEquals(0, output.getLeftMotor(), 0.000001);
		assertEquals(0, output.getRightMotor(), 0.000001);
		
		sensors.setLeftJoystick(0.5);
		sensors.setRightJoystick(-0.5);
		output = command.execute();
		assertEquals(0.5, output.getLeftMotor(), 0.000001);
		assertEquals(-0.5, output.getRightMotor(), 0.000001);
		
		sensors.setLeftJoystick(-0.5);
		sensors.setRightJoystick(0.5);
		output = command.execute();
		assertEquals(-0.5, output.getLeftMotor(), 0.000001);
		assertEquals(0.5, output.getRightMotor(), 0.000001);
	}

}

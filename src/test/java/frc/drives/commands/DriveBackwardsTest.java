package frc.drives.commands;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import frc.drives.DrivesCommand;
import frc.drives.DrivesOutput;
import frc.drives.DrivesTestSensors;
import frc.health.HealthReport;

public class DriveBackwardsTest {

	private DrivesTestSensors sensors;

	@Before
	public void setup() {
		sensors = new DrivesTestSensors();
	}

	@Test
	public void motorsAreEqual() {
		DriveBackwards backwardC = new DriveBackwards(sensors, 10);
		sensors.setLeftEncoderDistance(20);
		sensors.setRightEncoderDistance(20);
		DrivesOutput output = backwardC.execute();
		assertEquals("motors are not the same speed", output.getRightMotor(), output.getLeftMotor(), 0.001);
	}

	@Test
	public void pointedRight_ShouldSlowLeft() {
		DriveBackwards backwardC = new DriveBackwards(sensors, 10);
		sensors.setLeftEncoderDistance(20);
		sensors.setRightEncoderDistance(20);
		sensors.setGyroAngle(5);
		DrivesOutput output = backwardC.execute();

		assertTrue("Front pointed right should slow down right", output.getRightMotor() < output.getLeftMotor());//Negative Numbers
	}

	@Test
	public void pointedLeft_ShouldSlowRight() {
		DriveBackwards backwardC = new DriveBackwards(sensors, 10);
		sensors.setLeftEncoderDistance(20);
		sensors.setRightEncoderDistance(20);
		sensors.setGyroAngle(-5);
		DrivesOutput output = backwardC.execute();

		assertTrue("Front pointed right should slow down right", output.getRightMotor() > output.getLeftMotor());//Negative Numbers
	}

	@Test
	public void atDistance_ShouldStop(){
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
	
	@Test
	public void neitherSideMoves_ShouldCreateError() {
		sensors.setRightEncoderSpeed(0);
		sensors.setRightEncoderSpeed(0);
		HealthReport report = new DriveBackwards(sensors, 10).checkHealth();
		assertEquals(true, report.isError());
		assertEquals("Both L/R not moving!", report.getMessage());
	}
	
	@Test
	public void leftSideNoMoves_ShouldCreateError() {
		sensors.setRightEncoderSpeed(-1);
		sensors.setLeftEncoderSpeed(0);
		HealthReport report = new DriveBackwards(sensors, 10).checkHealth();
		assertEquals(true, report.isError());
		assertEquals("RIO side not moving!", report.getMessage());
	}
	
	@Test
	public void rightSideNoMoves_ShouldCreateError() {
		sensors.setRightEncoderSpeed(0);
		sensors.setLeftEncoderSpeed(-1);
		HealthReport report = new DriveBackwards(sensors, 10).checkHealth();
		assertEquals(true, report.isError());
		assertEquals("Scissor Lift side not moving!", report.getMessage());
	}
	
	@Test
	public void leftSideMovingWrongWay_ShouldCreateError() {
		sensors.setRightEncoderSpeed(0);
		sensors.setRightEncoderDistance(0);
		sensors.setLeftEncoderSpeed(0);
		sensors.setLeftEncoderDistance(0);
		DrivesCommand command = new DriveBackwards(sensors, 10);
		sensors.setRightEncoderSpeed(-1);
		sensors.setRightEncoderDistance(-2);
		sensors.setLeftEncoderSpeed(1);
		sensors.setLeftEncoderDistance(2);
		HealthReport report = command.checkHealth();
		assertEquals(true, report.isError());
		assertEquals("RIO Side encoder is moving in the wrong direction!", report.getMessage());
	}
	
	@Test
	public void rightSideMovingWrongWay_ShouldCreateError() {
		sensors.setRightEncoderSpeed(0);
		sensors.setRightEncoderDistance(0);
		sensors.setLeftEncoderSpeed(0);
		sensors.setLeftEncoderDistance(0);
		DrivesCommand command = new DriveBackwards(sensors, 10);
		sensors.setRightEncoderSpeed(1);
		sensors.setRightEncoderDistance(2);
		sensors.setLeftEncoderSpeed(-1);
		sensors.setLeftEncoderDistance(-2);
		HealthReport report = command.checkHealth();
		assertEquals(true, report.isError());
		assertEquals("Scissor Side encoder is moving in the wrong direction!", report.getMessage());
	}
	
	@Test
	public void drivesGood_ShouldntCreateError() {
		sensors.setRightEncoderSpeed(0);
		sensors.setRightEncoderDistance(0);
		sensors.setLeftEncoderSpeed(0);
		sensors.setLeftEncoderDistance(0);
		DrivesCommand command = new DriveBackwards(sensors, 10);
		sensors.setRightEncoderSpeed(-1);
		sensors.setRightEncoderDistance(-2);
		sensors.setLeftEncoderSpeed(-1);
		sensors.setLeftEncoderDistance(-2);
		HealthReport report = command.checkHealth();
		assertEquals(false, report.isError());
	}

} 
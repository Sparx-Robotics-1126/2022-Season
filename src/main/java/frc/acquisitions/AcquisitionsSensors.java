package frc.acquisitions;

//import frc.robot.IO;

//import frc.sensors.ColorSensor;

//import edu.wpi.first.wpilibj.util.Color;
//import edu.wpi.first.wpilibj.Encoder;

//import edu.wpi.first.wpilibj.DriverStation;

public class AcquisitionsSensors implements AcquisitionsSensorInterface
{
	//private static final int PROXIMITY_MINIMUM = 100;

    //private ColorSensor colorSensor;

	//private Encoder armEncoder;

    /**
	 * Creates a new object for interfacing with the various sensors of the Aquisitions subsystem (e.g. encoders).
	 */
	public AcquisitionsSensors() 
	{
        //colorSensor = new ColorSensor(100);
		//colorSensor.start();
	}

	/**
	 * @return True if there is an object within range of the color sensor.
	 */
	/*public boolean ballInRange()
	{
		return colorSensor.getProximity() >= PROXIMITY_MINIMUM;
	}*/

	/**
	 * @return True if the ball color matches our current alliance color.
	 */
	/*public boolean isCorrectColor()
	{
		Color ballColor = colorSensor.getColor();

		return ballColor.red > ballColor.blue && 
			ballColor.red > ballColor.green && 
			DriverStation.getAlliance() == 
			DriverStation.Alliance.Red;
	}*/

	/**
	 * @return The distance the arm motor has rotated.
	 */
	/*@Override
	public double getArmEncoderDistance() 
	{
		return armEncoder.getDistance();
	}*/
}
package frc.sensors;

import frc.robot.IO;

import frc.util.TimedTask;

import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorSensorV3;

public class ColorSensor extends TimedTask
{
    private static final Color DEFAULT_COLOR = new Color(0, 0, 0);

    private ColorSensorV3 sensor;

    private Color currentColor;

    private double currentProximity;

    /**
     * Creates a new color sensor.
     * @param updateDelay The amount of milliseconds between when the cached color and proximity values update.
     */
    public ColorSensor(int updateDelay)
    {
        super(updateDelay);

        sensor = new ColorSensorV3(IO.I2C_ONBOARD);
    }

    /**
     * Start collecting data from this ColorSensor and begin updating the current color and proximity values.
     */
    @Override
    public void start()
    {
        super.start();
    }

    public Color getColor()
    {
        //Prevent a race condition NullPointerException if an entity attempts to read the sensor's color before it has been received.
        if (currentColor == null)
            return DEFAULT_COLOR;
        
        return currentColor;
    }

    public double getProximity()
    {
        return currentProximity;
    }

    @Override
    public void execute()
    {
        currentColor = sensor.getColor();
        currentProximity = sensor.getProximity();
    }
}
package frc.sensors;

import frc.robot.IO;

import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorSensorV3;

public class ColorSensor
{
    private ColorSensorV3 sensor;

    public ColorSensor() 
    {
        sensor = new ColorSensorV3(IO.I2C_ONBOARD);
    }

    public Color get()
    {
        return sensor.getColor();
    }
}
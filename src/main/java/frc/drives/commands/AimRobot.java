package frc.drives.commands;

import frc.drives.DrivesCommand;
import frc.drives.DrivesOutput;
import frc.drives.DrivesSensorInterface;

public class AimRobot extends DrivesCommand{

    double leftSpeed, rightSpeed;

    public AimRobot(DrivesSensorInterface sensors)
    {
        super(sensors);
    }

    @Override
    public DrivesOutput execute(){

        return new DrivesOutput(leftSpeed, rightSpeed);
    }
}
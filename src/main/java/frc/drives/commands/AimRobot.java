package frc.drives.commands;

import frc.drives.DrivesCommand;
import frc.drives.DrivesOutput;
import frc.drives.DrivesSensorInterface;
import frc.robot.Robot;
import frc.sensors.Limelight;

public class AimRobot extends DrivesCommand
{
    private final double ACCEPTED_ERROR = 0.5; //TODO PLACEHOLDER

    public AimRobot(DrivesSensorInterface sensors)
    {
        super(sensors);
    }

    @Override
    public DrivesOutput execute(){
        double offset = Robot.getGyro().getAngle() - Robot.getLimelight().getHorizontalFromTarget();
        
        if (offset > ACCEPTED_ERROR)
            Robot.getDrives().turnRight(offset);
        else if (offset < -ACCEPTED_ERROR)
            Robot.getDrives().turnLeft(offset);

        return new DrivesOutput(0, 0, true);
    }
}
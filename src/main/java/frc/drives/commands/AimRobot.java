package frc.drives.commands;

import frc.drives.DrivesCommand;
import frc.drives.DrivesOutput;
import frc.drives.DrivesSensorInterface;

public class AimRobot extends DrivesCommand{
    public AimRobot(DrivesSensorInterface sensors)
    {

    }

    @Override
    public DrivesOutput execute(){
        double tx = getSensors().getAngleToTarget();
        double speed = 0; 
        if(Math.abs(tx) > DEADBAND) {
              speed = tx*p; 
        }
        
        double shooterAngle = sensors.getShooterAngleToRobot();

        if(speed>0) {
        	if(shooterAngle > MAX_ANGLE) {
        		speed = 0;
        	}
        }else{
        	if(shooterAngle < -MAX_ANGLE) {
        		speed = 0;
        	}
        }
        return new ShooterOutput(speed, Math.abs(tx) < 1);
    }
}
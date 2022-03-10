package frc.storage.commands;

import frc.storage.StorageCommand;
import frc.storage.StorageOutput;
import frc.storage.StorageSensorInterface;
import frc.subsystem.Storage;

public class ShootBall extends StorageCommand{
    final double STORAGE_MOTOR_SPEED = .25;     //placeholder
    final double STORAGE_MOTOR_STOPPING_POINT = 1;    //placeholder

    public ShootBall(StorageSensorInterface sensors) {
		super(sensors);
		
	}

    @Override
    public StorageOutput execute() {
        // TODO Auto-generated method stub
        if(getSensors().getStorageEncoderDistance() >= STORAGE_MOTOR_STOPPING_POINT) {
            //Storage.setNumBalls(Storage.getNumBalls() - 1);
            return new StorageOutput(STORAGE_MOTOR_SPEED, false);
        }
        Storage.setNumBalls((Storage.getNumBalls()-1));;
        return new StorageOutput(0, true);
    }
}

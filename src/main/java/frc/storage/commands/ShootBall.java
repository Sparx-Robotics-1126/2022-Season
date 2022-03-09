package frc.storage.commands;

import frc.storage.StorageCommand;
import frc.storage.StorageOutput;
import frc.storage.StorageSensorInterface;

public class ShootBall extends StorageCommand{
    private short numBalls;

    public ShootBall(StorageSensorInterface sensors, short numballs) {
		super(sensors);
		this.numBalls = numballs;
	}

    @Override
    public StorageOutput execute() {
        // TODO Auto-generated method stub
        return null;
    }
}

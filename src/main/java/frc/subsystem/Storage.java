package frc.subsystem;

import frc.robot.IO;
import frc.storage.StorageCommand;
import frc.storage.StorageOutput;
import frc.storage.StorageSensorInterface;
import frc.storage.StorageSensors;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Storage extends Subsystem{
    private StorageCommand storageCommand;
	private StorageSensorInterface sensors;

	private short numOfBallsAquired;

	private CANSparkMax storageMotor;

    /**
     * The maximum amount of current in amps that should be permitted during motor operation.
     */
    private static final int MAX_CURRENT = 30;

    public Storage()
    {
        numOfBallsAquired = 1;
        sensors = new StorageSensors();
		storageMotor = new CANSparkMax(IO.STORAGE_MOTOR, MotorType.kBrushless);

        storageMotor.setSmartCurrentLimit(MAX_CURRENT);
    }

    @Override
    void execute() {
        if (storageCommand != null) {
			StorageOutput output = storageCommand.execute();
			numOfBallsAquired = output.getNumOfBalls();
			if (output.isDone()) {
				// TURN OFF All MOTORS
                storageMotor.set(0);
				storageCommand = null;
			}
		}
		SmartDashboard.putNumber("Balls Aquired", numOfBallsAquired);
    }

    @Override
    public boolean isDone() {
        // TODO Auto-generated method stub
        return false;
    }
}

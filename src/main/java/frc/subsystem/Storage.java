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

	private static short numBalls;
	private CANSparkMax storageMotor;

    /**
     * The maximum amount of current in amps that should be permitted during motor operation.
     */
    private static final int MAX_CURRENT = 30;

    public Storage()
    {
        numBalls = 1;
        sensors = new StorageSensors();
		storageMotor = new CANSparkMax(IO.STORAGE_MOTOR, MotorType.kBrushless);

        storageMotor.setSmartCurrentLimit(MAX_CURRENT);
    }

    /**
     * @return number of balls
     */
    public static short getNumBalls(){
        return numBalls;
    }

    /**
     * Sets the number of balls
     */
    public static void setNumBalls(int newBallCount){
        numBalls = (short) newBallCount;
    }   

    @Override
    void execute() {
        if (storageCommand != null) {
			StorageOutput output = storageCommand.execute();
            numBalls = getNumBalls();
            storageMotor.set(output.get());
			if (output.isDone()) {
				// TURN OFF All MOTORS
                storageMotor.set(0);
				storageCommand = null;
			}
		}
		//   SmartDashboard.putNumber("Balls Aquired", numBalls);
    }

    @Override
    public boolean isDone() {
        // TODO Auto-generated method stub
        return false;
    }
}

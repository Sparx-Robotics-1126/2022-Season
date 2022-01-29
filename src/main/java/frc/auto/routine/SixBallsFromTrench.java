package frc.auto.routine;

import frc.auto.AutoFeature;
import frc.auto.AutoRoutine;
import frc.auto.AutoTask;

public class SixBallsFromTrench extends AutoRoutine {
	public SixBallsFromTrench() {
		super("Shoot Six", auto);
	}
	private static final AutoTask[] auto = {
			// shoots initial 3 balls from line
			// AutoTask.createTask(AutoFeature.STORAGE_PREPARE_FOR_SHOOTING),
			AutoTask.createTask(AutoFeature.SHOOTER_ACTIVATE_LIMELIGHT),
			AutoTask.createTask(AutoFeature.SHOOTER_READY_TO_FIRE),
			AutoTask.createTask(AutoFeature.STORAGE_SHOOT),
			AutoTask.createTask(AutoFeature.STORAGE_DONE),
			AutoTask.createTask(AutoFeature.SHOOTER_READY_TO_FIRE),
			AutoTask.createTask(AutoFeature.STORAGE_SHOOT),
			AutoTask.createTask(AutoFeature.STORAGE_DONE),
			AutoTask.createTask(AutoFeature.SHOOTER_READY_TO_FIRE),
			AutoTask.createTask(AutoFeature.STORAGE_SHOOT),
			AutoTask.createTask(AutoFeature.STORAGE_DONE),
			AutoTask.createTask(AutoFeature.SHOOTER_DEACTIVATE_LIMELIGHT),
			AutoTask.createTask(AutoFeature.ACQ_ACQUIRE),
			AutoTask.createTask(AutoFeature.DRIVE_BACKWARDS, 165),
			AutoTask.createTask(AutoFeature.DRIVE_DONE),
			AutoTask.createTask(AutoFeature.ACQ_STOP_ACQUIRING),
			// shoots the 3 acquired balls
			AutoTask.createTask(AutoFeature.STORAGE_PREPARE_FOR_SHOOTING),
			AutoTask.createTask(AutoFeature.SHOOTER_ACTIVATE_LIMELIGHT),
			AutoTask.createTask(AutoFeature.SHOOTER_READY_TO_FIRE),
			AutoTask.createTask(AutoFeature.STORAGE_SHOOT),
			AutoTask.createTask(AutoFeature.STORAGE_DONE),
			AutoTask.createTask(AutoFeature.SHOOTER_READY_TO_FIRE),
			AutoTask.createTask(AutoFeature.STORAGE_SHOOT),
			AutoTask.createTask(AutoFeature.STORAGE_DONE),
			AutoTask.createTask(AutoFeature.SHOOTER_READY_TO_FIRE),
			AutoTask.createTask(AutoFeature.STORAGE_SHOOT),
			AutoTask.createTask(AutoFeature.STORAGE_DONE),
			AutoTask.createTask(AutoFeature.SHOOTER_DEACTIVATE_LIMELIGHT),
			AutoTask.createTask(AutoFeature.STOP)
	};
}

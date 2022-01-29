package frc.auto.routine;

import frc.auto.AutoFeature;
import frc.auto.AutoRoutine;
import frc.auto.AutoTask;

public class DriveBackwards extends AutoRoutine {
	
	public DriveBackwards() {
		super("Drive Backwards", auto);

	}

	public static final AutoTask[] auto = {
			AutoTask.createTask(AutoFeature.DRIVE_BACKWARDS, 24),
			AutoTask.createTask(AutoFeature.DRIVE_DONE),
			AutoTask.createTask(AutoFeature.STOP)
	};
}

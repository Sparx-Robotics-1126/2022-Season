package frc.auto.routine;

import frc.auto.AutoRoutine;
import frc.auto.AutoTask;
import frc.auto.AutoFeature;

public class DoNothing extends AutoRoutine{

	private static final AutoTask[] auto = {
			AutoTask.createTask(AutoFeature.STOP),
			AutoTask.createTask(AutoFeature.STOP, 254),
			AutoTask.createTask(AutoFeature.STOP, 1114, 2054)
	};
	
	public DoNothing() {
		super("Do Nothing", auto);
	}

}

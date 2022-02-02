package frc.auto.routine;

import frc.auto.AutoRoutine;
import frc.auto.AutoTask;
import frc.auto.AutoFeature;

/**
 * Autonomous routine to sit still ("do nothing").
 */
public class DoNothing extends AutoRoutine
{
	private static final AutoTask[] auto = {
			AutoTask.createTask(AutoFeature.STOP),
			AutoTask.createTask(AutoFeature.STOP, 254),
			AutoTask.createTask(AutoFeature.STOP, 1114, 2054)
	};
	
	/**
	 * Create the DoNothing autonomous instruction set.
	 */
	public DoNothing() 
	{
		super("Do Nothing", auto);
	}
}
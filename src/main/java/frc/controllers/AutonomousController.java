package frc.controllers;

import frc.autonomous.AutonomousRoutine;

import frc.autonomous.routines.DoNothing;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonomousController extends Controller
{
	private AutonomousRoutine routine;

	private SendableChooser<AutonomousRoutine> selector;

	//Index 0 should contain the default autonomous routine.
	private AutonomousRoutine[] routines = {
		new DoNothing()
	};

	public AutonomousController() 
	{
		selector = new SendableChooser<>();

		for (AutonomousRoutine routine : routines)
			selector.addOption(routine.getName(), routine);
		
		selector.setDefaultOption(routines[0].getName(), routines[0]);

		SmartDashboard.putData("AUTONOMOUS_ROUTINE", selector);
	}

	@Override
	public void execute() 
	{
		if (routine == null)
			routine = selector.getSelected();

		routine.execute();
	}
}
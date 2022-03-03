package frc.controllers;

import frc.subsystem.Drives;

import frc.autonomous.AutonomousRoutine;

public class AutonomousController extends Controller
{
	private AutonomousRoutine currentRoutine;

	public AutonomousController(Drives drives) 
	{
		super(drives);
	}

	public void setRoutine(AutonomousRoutine routine)
	{
		currentRoutine = routine;
	}

	public Drives getDrives()
	{
		return drives;
	}

	@Override
	public void execute() 
	{
		
	}
}


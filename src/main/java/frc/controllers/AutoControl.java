package frc.controllers;

import frc.subsystem.Drives;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.auto.AutoFeature;
import frc.auto.AutoRoutine;
import frc.auto.AutoTask;
import frc.auto.routine.DoNothing;
import frc.auto.routine.SixBallsFromTrench;
import frc.auto.routine.DriveBackwards;
import frc.auto.routine.ShootBallsOnly;

public class AutoControl extends Controller{

	private SendableChooser<AutoRoutine> autoSelector;
	private AutoRoutine[] possibleAutos = {
			new DoNothing(),
			new DriveBackwards(),
			new ShootBallsOnly(),
			new SixBallsFromTrench()
			//Add new Auto Routines here
	};
	private AutoTask[] currentAuto;
	private int autoStep = 0;

	public AutoControl(Drives drives) {
		super(drives);
		createDashboard();
	}

	private void createDashboard() {
		autoSelector = new SendableChooser<AutoRoutine>();
		for(AutoRoutine auto: possibleAutos) {
			autoSelector.addOption(auto.getAutoName(), auto);
		}
		autoSelector.setDefaultOption("Default: Shoot 3", new ShootBallsOnly());
		SmartDashboard.putData("Auto Selector", autoSelector);
	}

	private AutoTask[] getSelectedAuto() {
		AutoRoutine auto = autoSelector.getSelected();
		System.out.println("Auto Selected: " + auto.getAutoName());
		return auto.getAutoSequence();
	}
	
	public void resetAuto() {
		currentAuto = null;
		autoStep = 0;
	}

	@Override
	public void execute() {
		if(currentAuto == null) { //First time auto has run
			autoStep = 0;
			currentAuto = getSelectedAuto();
		}
		AutoTask currentTask = currentAuto[autoStep];
		AutoFeature currentFeature = currentTask.getFeature();
		switch(currentFeature) {
		//Commands go here
		case STOP:
			autoStep--;//This offsets the autostep increment at the bottom causing the step to remain stuck here
			break;

		//DRIVES
		case DRIVE_FORWARD:
			drives.moveForward(currentTask.value1);
			break;
		case DRIVE_BACKWARDS:
			drives.moveBackward(currentTask.value1);
			break;
		case DRIVE_TURN_LEFT:
			drives.turnLeft(currentTask.value1);
			break;
		case DRIVE_TURN_RIGHT:
			drives.turnRight(currentTask.value1);
			break;
		case DRIVE_DONE:
			if(!drives.isDone()){
				autoStep--;
			}
			break;

		default:
			System.out.println("Unimplemented Feature: " + currentTask);
		}
		autoStep++;
	}
}


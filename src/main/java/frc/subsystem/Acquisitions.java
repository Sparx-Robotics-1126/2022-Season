package frc.subsystem;

import frc.robot.IO;

import frc.acquisitions.commands.IntakeRollers;
import frc.acquisitions.commands.StopRollers;
import frc.acquisitions.commands.RaiseArm;
import frc.acquisitions.commands.DropArm;
import frc.acquisitions.commands.EjectRollers;

import frc.acquisitions.AcquisitionsCommand;
import frc.acquisitions.AcquisitionsOutput;
import frc.acquisitions.AcquisitionsSensorInterface;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

/**
 * Controls all acquisitions behavior.
 */
public class Acquisitions extends Subsystem
{
    private TalonSRX armMotor;
    private TalonSRX intakeMotor;

    /*
     * Class containing all sensor data for Acquisitions.
     */
    private AcquisitionsSensorInterface acquisitionsSensors;
    
    /**
     * The current AcquisitionsCommand being ran.
     */
    private AcquisitionsCommand armCommand;
    private AcquisitionsCommand intakeCommand;

    /**
     * Main initializer for the acquisitions subsystem. Called in Robot.java.
     */
    public Acquisitions(AcquisitionsSensorInterface acquisitionsSensors) 
    {
        armMotor = new TalonSRX(IO.ACQUISITIONS_ARM_MOTOR);
        intakeMotor = new TalonSRX(IO.ACQUISITIONS_INTAKE_MOTOR);
    }

    @Override
    void execute()
    {
        if (getState() == SubsystemState.NORMAL)
        {
            if (acquisitionsSensors.ballInRange())
            {
                if (acquisitionsSensors.isCorrectColor())
                    intakeRollers();
                else
                    ejectRollers();
            }
            else
                stopRollers();
        }

        if (intakeCommand != null)
        {
            AcquisitionsOutput intakeOutput = intakeCommand.execute();

            intakeMotor.set(ControlMode.PercentOutput, intakeOutput.get());
                
            if (intakeOutput.isDone())
                intakeCommand = null;
        }

        if (armCommand != null)
        {   
            AcquisitionsOutput armOutput = armCommand.execute();

            armMotor.set(ControlMode.PercentOutput, armOutput.get());

            if (armOutput.isDone())
                armCommand = null;
        }
    }

    /**
     * Configures the buttons that control the arm and rollers.
     * @param arm The button for the arm.
     * @param intake The button for the rollers.
     */
    public void setButtons(boolean arm, boolean intake) 
    {
        acquisitionsSensors.setArmButton(arm);
        acquisitionsSensors.setIntakeButton(intake);
    }

    public void dropArm() 
    {
        armCommand = new DropArm(acquisitionsSensors);
    }

    public void raiseArm() 
    {
        armCommand = new RaiseArm(acquisitionsSensors);
    }

    public synchronized void intakeRollers() 
    {
        intakeCommand = new IntakeRollers(acquisitionsSensors);
    }

    public synchronized void stopRollers() 
    {
        intakeCommand = new StopRollers(acquisitionsSensors);
    }

    public synchronized void ejectRollers() 
    {
        intakeCommand = new EjectRollers(acquisitionsSensors);
    }

    @Override
    public boolean isDone() 
    {
        return armCommand == null && intakeCommand == null;
    }
} 
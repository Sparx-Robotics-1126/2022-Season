package frc.subsystem;

import frc.robot.IO;

import frc.acquisitions.commands.IntakeRollers;
import frc.acquisitions.commands.StopRollers;
import frc.acquisitions.commands.RaiseArm;
import frc.acquisitions.commands.DropArm;
import frc.acquisitions.commands.StopArm;
import frc.acquisitions.commands.EjectRollers;

import frc.acquisitions.AcquisitionsCommand;
import frc.acquisitions.AcquisitionsOutput;
import frc.acquisitions.AcquisitionsSensorInterface;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import javax.swing.text.StyleContext.SmallAttributeSet;

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
        
        //intakeMotor.overrideLimitSwitchesEnable(false);

        this.acquisitionsSensors = acquisitionsSensors;
    }

    @Override
    void execute()
    {
        /*if (getState() == SubsystemState.NORMAL)
            if (acquisitionsSensors.ballInRange())
                if (acquisitionsSensors.isCorrectColor())
                    intakeRollers();
                else
                    ejectRollers();
            else
                stopRollers();*/

        if (intakeCommand != null)
        {
            AcquisitionsOutput intakeOutput = intakeCommand.execute();
            SmartDashboard.putNumber("INTAKE_COMMAND_OUTPUT", intakeOutput.get());
            intakeMotor.set(ControlMode.PercentOutput, intakeOutput.get());
                
            if (intakeOutput.isDone())
                intakeCommand = null;
        }

        if (armCommand != null)
        {   
            AcquisitionsOutput armOutput = armCommand.execute();
            SmartDashboard.putNumber("ARM_COMMAND_OUTPUT", -armOutput.get());
            armMotor.set(ControlMode.PercentOutput, -armOutput.get());

            if (armOutput.isDone())
                armCommand = null;
        }
    }

    public void stopArm()
    {
        armCommand = new StopArm(acquisitionsSensors);
    }

    public void dropArm() 
    {
        armCommand = new DropArm(acquisitionsSensors);
    }

    public void raiseArm() 
    {
        armCommand = new RaiseArm(acquisitionsSensors);
    }

    public void intakeRollers() 
    {
        intakeCommand = new IntakeRollers(acquisitionsSensors);
    }

    public void stopRollers() 
    {
        intakeCommand = new StopRollers(acquisitionsSensors);
    }

    public void ejectRollers() 
    {
        intakeCommand = new EjectRollers(acquisitionsSensors);
    }

    @Override
    public boolean isDone() 
    {
        return armCommand == null && intakeCommand == null;
    }
} 
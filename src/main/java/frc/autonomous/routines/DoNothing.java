package frc.autonomous.routines;

import frc.autonomous.AutonomousRoutine;
import frc.autonomous.AutonomousCommand;

public class DoNothing extends AutonomousRoutine
{
    public DoNothing()
    {
        super(new AutonomousCommand[] {});
    }

    @Override
    public String getName()
    {
        return "Do Nothing";
    }
}
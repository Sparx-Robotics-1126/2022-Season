package frc.controllers;

import frc.subsystem.Acquisitions;
import frc.subsystem.Drives;

/**
 * Main abstract class for defining new Controllers.
 */
public abstract class Controller 
{
    private Drives drives;
    private Acquisitions acquisitions;

    public Controller(Drives drives, Acquisitions acquisitions)
    {
    	this.drives = drives;
        this.acquisitions = acquisitions;
    }

    /**
     * @return The Drives subsystem instance associated with this Controller.
     */
    public Drives getDrives()
    {
        return drives;
    }

    /**
     * @return The Acquisitions subsystem instance associated with this Controller.
     */
    public Acquisitions getAcquisitions()
    {
        return acquisitions;
    }
    
    public abstract void execute();
}
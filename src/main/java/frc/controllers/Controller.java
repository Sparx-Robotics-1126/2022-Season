package frc.controllers;

import frc.subsystem.Acquisitions;
import frc.subsystem.Drives;

/**
 * Main abstract class for defining new Controllers.
 */
public abstract class Controller 
{
    protected Drives drives;
    protected Acquisitions acquisitions;

    public Controller(Drives drives, Acquisitions acquisitions)
    {
    	this.drives = drives;
        this.acquisitions = acquisitions;
    }
    
    public abstract void execute();
}
package frc.controllers;

import frc.subsystem.Drives;

/**
 * Main abstract class for defining new Controllers.
 */
public abstract class Controller 
{
    protected Drives drives;

    public Controller(Drives drives)
    {
    	this.drives = drives;
    }
    
    public abstract void execute();
}
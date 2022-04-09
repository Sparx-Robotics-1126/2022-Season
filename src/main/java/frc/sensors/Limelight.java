package frc.sensors;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Limelight 
{
	final double CAMERA_ANGLE = 13.35;
	final double ROBOT_HEIGHT = 37.5;
	final double TARGET_HEIGHT = 90; // 83.25  89.5

	final int VIEWPORT_X_SIZE = 320;

	NetworkTableEntry tx;
	NetworkTableEntry tv;
	NetworkTableEntry ty;
	NetworkTableEntry ledMode;
	
	public Limelight() 
	{
		//SmartDashboard.putNumber("Camera Angle", CAMERA_ANGLE);
		NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
		tv = table.getEntry("tv"); //tells whether or not a target is present; 1 for a target, 0 for none.

		//Screen coordinates of the target.
		tx = table.getEntry("tx");
		ty = table.getEntry("ty");

		ledMode = table.getEntry("ledMode");
	}
	
	public double getDistanceFromTarget()
	{
		double a2 = ty.getDouble(0);
		double distance = (TARGET_HEIGHT - ROBOT_HEIGHT) / Math.tan(Math.toRadians(CAMERA_ANGLE + a2));
		return distance;
	}

	public double getTargetHeight()
	{
		return ty.getDouble(0);
	}

	public double getHorizontalPixelsFromTarget()
	{
		return tx.getDouble(0) - (VIEWPORT_X_SIZE / 2);
	}

	public double getHorizontalDegreesFromTarget()
	{
		return getHorizontalPixelsFromTarget() * SmartDashboard.getNumber("PIXELS_CONSTANT", 0);
	}
	
	public boolean getLock() 
	{
		if (tv.getDouble(0) > 0) 
			return true;
		
		return false;
	}
	
	public void enable(boolean enable) 
	{
		int ledModeNum = enable ? 3 : 1;
		ledMode.setNumber(ledModeNum);
	}
}
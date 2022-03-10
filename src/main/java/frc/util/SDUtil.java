package frc.util;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Utilities and key abstraction for the SmartDashboard.
 */
public final class SDUtil
{
    static
    {
        SmartDashboard.putBoolean("IS_RED_ALLIANCE", true);
    }

    private SDUtil() {}

    /**
     * Returns a boolean indicating the color of the alliance we are on.
     * @return True if we are on the red alliance. False if we are on the blue alliance.
     */
    public static boolean isRedAlliance()
    {
        return SmartDashboard.getBoolean("IS_RED_ALLIANCE", true);
    }
}
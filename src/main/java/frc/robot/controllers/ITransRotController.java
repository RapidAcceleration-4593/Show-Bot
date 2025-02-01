package frc.robot.controllers;

import edu.wpi.first.math.geometry.Translation2d;

/**
 * A drivebase controller that returns arcade style differential steering inputs: forward backward and turning.
 */
public interface ITransRotController {
    
    /**
     * 
     * @return A {@code Translation2d} which describes the forward backward translation (positive is forward) in the x component, and the yaw in the y component (positive is counterclockwise).
     */
    public Translation2d getTransRot();
}

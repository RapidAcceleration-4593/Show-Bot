package frc.robot.controllers;

import edu.wpi.first.math.geometry.Translation2d;

/**
 * A drivebase controller that returns tank style differential steering inputs.
 */
public interface ITankController {
    
    /**
     * 
     * @return A {@code Translation2d} which describes the left motor speed in the x component, and the right motor speed in the y component (positive is forward).
     */
    public Translation2d getLeftRightSpeeds();
}

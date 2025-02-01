package frc.robot.controllers;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class MultiXboxController extends CommandXboxController implements IMultiController {

    public MultiXboxController(int port) {
        super(port);
    }

    @Override
    public Translation2d getLeftRightSpeeds() {
        return new Translation2d(-this.getLeftY(), -this.getRightY());
    }

    @Override
    public Translation2d getTransRot() {
        return new Translation2d(-this.getLeftY(), -this.getRightX());
    }
    
}

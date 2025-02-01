package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.controllers.ITransRotController;
import frc.robot.subsystems.DrivebaseSubsystem;

public class CurvatureDriveCommand extends Command {
    private final DrivebaseSubsystem drivebase;
    private final ITransRotController controller;

    public CurvatureDriveCommand(DrivebaseSubsystem drivebase, ITransRotController controller) {
        this.drivebase = drivebase;
        this.controller = controller;

        addRequirements(drivebase);
    }

    @Override
    public void execute() {
        drivebase.curveDrive(controller.getTransRot().getX(), controller.getTransRot().getY());
    }

    @Override
    public void end(boolean interrupted) {
        drivebase.stop();
    }
}

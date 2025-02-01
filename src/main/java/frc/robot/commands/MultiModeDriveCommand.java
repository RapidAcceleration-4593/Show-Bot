package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.controllers.IMultiController;
import frc.robot.subsystems.DrivebaseSubsystem;

public class MultiModeDriveCommand extends Command {
    private final DrivebaseSubsystem drivebase;
    private final IMultiController controller;

    public MultiModeDriveCommand(DrivebaseSubsystem drivebase, IMultiController controller) {
        this.drivebase = drivebase;
        this.controller = controller;

        addRequirements(drivebase);
    }

    @Override
    public void execute() {
        drivebase.multiDrive(controller);
    }

    @Override
    public void end(boolean interrupted) {
        drivebase.stop();
    }
}

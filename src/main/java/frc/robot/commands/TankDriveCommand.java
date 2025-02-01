package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.controllers.ITankController;
import frc.robot.subsystems.DrivebaseSubsystem;

public class TankDriveCommand extends Command {
    private final DrivebaseSubsystem drivebase;
    private final ITankController controller;

    public TankDriveCommand(DrivebaseSubsystem drivebase, ITankController controller) {
        this.drivebase = drivebase;
        this.controller = controller;

        addRequirements(drivebase);
    }

    @Override
    public void execute() {
        drivebase.tankDrive(controller.getLeftRightSpeeds().getX(), controller.getLeftRightSpeeds().getY());
    }

    @Override
    public void end(boolean interrupted) {
        drivebase.stop();
    }
}

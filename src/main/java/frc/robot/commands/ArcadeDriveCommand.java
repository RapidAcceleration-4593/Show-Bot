package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DrivebaseSubsystem;

public class ArcadeDriveCommand extends Command {
    
    private final DrivebaseSubsystem drivebase;
    private final DoubleSupplier translationSupplier;
    private final DoubleSupplier headingSupplier;

    public ArcadeDriveCommand(DrivebaseSubsystem drivebase, DoubleSupplier translation, DoubleSupplier heading) {
        this.drivebase = drivebase;
        this.translationSupplier = translation;
        this.headingSupplier = heading;

        addRequirements(drivebase);
    }

    @Override
    public void execute() {
        drivebase.arcadeDrive(translationSupplier.getAsDouble(), headingSupplier.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        drivebase.stop();
    }
}

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class RunIntakeCommand extends Command {
    
    private final IntakeSubsystem intakeSubsystem;
    private final boolean isReversed;

    public RunIntakeCommand(IntakeSubsystem subsystem, boolean reversed) {
        this.intakeSubsystem = subsystem;
        this.isReversed = reversed;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        intakeSubsystem.runIntake(isReversed);
    }

    @Override
    public void end(boolean interrupted) {
        intakeSubsystem.stopIntake();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}

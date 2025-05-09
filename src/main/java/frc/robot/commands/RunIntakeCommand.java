package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DifferentialWristSubsystem;

public class RunIntakeCommand extends Command {
    
    private final DifferentialWristSubsystem wristSubsystem;
    private boolean inverted;

    public RunIntakeCommand(DifferentialWristSubsystem subsystem, boolean inverted) {
        this.wristSubsystem = subsystem;
        this.inverted = inverted;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        wristSubsystem.runIntake(inverted);
    }

    @Override
    public void end(boolean interrupted) {
        wristSubsystem.stopIntake();
    }
}

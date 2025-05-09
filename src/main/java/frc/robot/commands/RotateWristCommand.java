package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DifferentialWristSubsystem;

public class RotateWristCommand extends Command {
    
    public final DifferentialWristSubsystem wristSubsystem;
    private boolean inverted;

    public RotateWristCommand(DifferentialWristSubsystem subsystem, boolean inverted) {
        this.wristSubsystem = subsystem;
        this.inverted = inverted;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        wristSubsystem.rotateWrist(inverted);
    }

    @Override
    public void end(boolean interrupted) {
        wristSubsystem.stopWrist();
    }
}

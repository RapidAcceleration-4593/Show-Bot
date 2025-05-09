package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DifferentialWristSubsystem;

public class RotateWristTogetherCommand extends Command {
    
    public final DifferentialWristSubsystem wristSubsystem;
    private boolean inverted;

    public RotateWristTogetherCommand(DifferentialWristSubsystem subsystem, boolean inverted) {
        this.wristSubsystem = subsystem;
        this.inverted = inverted;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        wristSubsystem.rotateWristTogether(inverted);
    }

    @Override
    public void end(boolean interrupted) {
        wristSubsystem.stopWrist();
    }
}

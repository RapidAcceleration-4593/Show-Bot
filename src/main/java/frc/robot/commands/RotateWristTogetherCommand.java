package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.WristSubsystem;

public class RotateWristTogetherCommand extends Command {
    
    public final WristSubsystem wristSubsystem;
    private boolean inverted;

    public RotateWristTogetherCommand(WristSubsystem subsystem, boolean inverted) {
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

    @Override
    public boolean isFinished() {
        return false;
    }
}

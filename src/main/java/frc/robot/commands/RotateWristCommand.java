package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.WristSubsystem;

public class RotateWristCommand extends Command {
    
    public final WristSubsystem wristSubsystem;
    private final boolean isReversed;

    public RotateWristCommand(WristSubsystem subsystem, boolean reversed) {
        this.wristSubsystem = subsystem;
        this.isReversed = reversed;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        wristSubsystem.rotateWrist(isReversed);
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

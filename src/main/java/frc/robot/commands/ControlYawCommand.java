package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.RobotStates.WristYawStates;
import frc.robot.subsystems.WristSubsystem;

public class ControlYawCommand extends Command {
    
    private final WristSubsystem wristSubsystem;
    private final WristYawStates yawState;

    public ControlYawCommand(WristSubsystem subsystem, WristYawStates state) {
        this.wristSubsystem = subsystem;
        this.yawState = state;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        wristSubsystem.setYawState(yawState);
    }

    @Override
    public void execute() {
        wristSubsystem.controlYawPID();
    }

    @Override
    public void end(boolean interrupted) {
        wristSubsystem.stopWrist();
    }

    @Override
    public boolean isFinished() {
        return wristSubsystem.yawAtSetpoint();
    }
}

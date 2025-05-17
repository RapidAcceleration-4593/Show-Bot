package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.RobotStates.WristPitchStates;
import frc.robot.subsystems.WristSubsystem;

public class ControlPitchCommand extends Command {
    
    private final WristSubsystem wristSubsystem;
    private final WristPitchStates pitchState;

    public ControlPitchCommand(WristSubsystem subsystem, WristPitchStates state) {
        this.wristSubsystem = subsystem;
        this.pitchState = state;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        wristSubsystem.setPitchState(pitchState);
    }

    @Override
    public void execute() {
        wristSubsystem.controlPitchPID();
    }

    @Override
    public void end(boolean interrupted) {
        wristSubsystem.stopWrist();
    }

    @Override
    public boolean isFinished() {
        return wristSubsystem.pitchAtSetpoint();
    }
}

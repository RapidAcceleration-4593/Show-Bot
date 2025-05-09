package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class RunIntakeCommand extends Command {
    private final IntakeSubsystem intake;
    private final boolean inverted;
    public RunIntakeCommand(IntakeSubsystem intake, boolean inverted){
        this.intake = intake;
        this.inverted = inverted;
        addRequirements(intake);
    }
    @Override 
    public void initialize(){
        intake.runintake(inverted);

    }
    @Override
    public void end(boolean interrupted){
        intake.stopintake();
    }
}

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.shooter.ShooterSubsystem;

public class ShootCommand extends Command {
    ShooterSubsystem subsystem;
    public ShootCommand(ShooterSubsystem subsystem){
        this.subsystem = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void initialize(){
        subsystem.startSpinner.run();
    }

    @Override
    public void execute(){
        if(subsystem.atSpeed.get()){
            subsystem.startFeeder.run();
        }
    }

    @Override
    public void end(boolean interrupted){
        subsystem.stopFeeder.run();
        subsystem.stopSpinner.run();
    }
}

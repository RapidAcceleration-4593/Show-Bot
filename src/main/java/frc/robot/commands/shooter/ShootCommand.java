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
        subsystem.runShooter();
    }

    @Override
    public void execute(){
        if(subsystem.atSpeed()){
            subsystem.runFeeder();
        }
    }

    @Override
    public void end(boolean interrupted){
        subsystem.stopFeeder();
        subsystem.stopShooter();
    }
}

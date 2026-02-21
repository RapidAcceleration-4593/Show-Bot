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
        System.out.println("Start");
        subsystem.startSpinner.run();
    }

    @Override
    public void execute(){
        if(subsystem.atSpeed.get()){
            System.out.println("At Speed");
            subsystem.startFeeder.run();
        } else {
            System.out.println("Spinning up");
        }
    }

    @Override
    public void end(boolean interrupted){
        System.out.println("Stopped");
        subsystem.stopFeeder.run();
        subsystem.stopSpinner.run();
    }
}

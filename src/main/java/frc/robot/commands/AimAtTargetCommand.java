package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import frc.robot.subsystems.vision.VisionSubsystem;

public class AimAtTargetCommand extends Command {
    
    private final SwerveSubsystem swerve;
    private final VisionSubsystem vision;

    public AimAtTargetCommand(SwerveSubsystem swerve, VisionSubsystem vision) {
        this.swerve = swerve;
        this.vision = vision;
    }

    @Override
    public void initialize() {
        
    }

    @Override
    public void execute() {
        
    }
}

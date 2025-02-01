package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.PuckShooterSubsystem;

public class ShooterCommand extends Command {
    private final PuckShooterSubsystem shooterSubsystem;

    /**
     * Shooter Command Constructor
     * @param subsystem Puck Shooter Subsystem
     */
    public ShooterCommand(PuckShooterSubsystem subsystem) {
        shooterSubsystem = subsystem;
        addRequirements(subsystem);
    }

    /**
     * Start shooter motors when binding is pressed
     */
    @Override
    public void initialize() {
        shooterSubsystem.runShooter(1.0);
    }

    /**
     * While binding is held, check shooter velocities
     * @return Run spinner wheel when shooter motors at 5500 RPMs
     */
    @Override
    public void execute() {
        if (shooterSubsystem.IsAtShootingSpeed()) {
            shooterSubsystem.runSpinnerWheel(Constants.PuckShooterConstants.spinnerSpeed);
        } else {
            shooterSubsystem.stopSpinnerWheel();
        }
    }

    /**
     * Stop all shooter motors when binding is released
     */
    @Override
    public void end(boolean interrupted) {
        shooterSubsystem.stopShooter();
        shooterSubsystem.stopSpinnerWheel();
    }

    /**
     * Cancel on release
     */
    @Override
    public boolean isFinished() {
        return false;
    }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.ArcadeDriveCommand;
import frc.robot.commands.ShooterCommand;
import frc.robot.subsystems.DrivebaseSubsystem;
import frc.robot.subsystems.PuckShooterSubsystem;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
  // Subsystem(s)
  private final DrivebaseSubsystem drivebase = new DrivebaseSubsystem();
  private final PuckShooterSubsystem shooterSubsystem = new PuckShooterSubsystem();

  // Controller(s)
  private final CommandXboxController driverController = new CommandXboxController(OperatorConstants.driverControllerPort);

  /**
   * Robot Container Constructor
   * @return Xbox Controller Bindings and Default Arcade Drive Command
   */
  public RobotContainer() {
    configureBindings();

    drivebase.setDefaultCommand(new ArcadeDriveCommand(drivebase, driverController::getLeftY, driverController::getRightX));

    DriverStation.silenceJoystickConnectionWarning(true);
  }

  /** Configure Xbox Controller Bindings. */
  private void configureBindings() {
    driverController.rightTrigger().whileTrue(new ShooterCommand(shooterSubsystem));
  }

  /**
   * Retrieve Autonomous Command
   * @return An empty command.
   */
  public Command getAutonomousCommand() {
    return Commands.none();
  }
}

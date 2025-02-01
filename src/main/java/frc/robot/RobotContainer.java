// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.ShooterCommand;
import frc.robot.commands.MultiModeDriveCommand;
import frc.robot.subsystems.DrivebaseSubsystem;
import frc.robot.subsystems.PuckShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.controllers.*;

public class RobotContainer {
  // Declare and Assign Subsystems
  private final DrivebaseSubsystem drivebase = new DrivebaseSubsystem();
  private final PuckShooterSubsystem shooterSubsystem = new PuckShooterSubsystem();

  // Declare and Assign Controller(s)
  private final MultiXboxController driverController =
    new MultiXboxController(OperatorConstants.driverControllerPort);

  /**
   * Robot Container Constructor
   * @return Xbox Controller Bindings and Default Arcade Drive Command
   */
  public RobotContainer() {
    configureBindings();
    drivebase.setDefaultCommand(new MultiModeDriveCommand(drivebase, driverController));
  }

  /**
   * Configure Xbox Controller Bindings
   */
  private void configureBindings() {
    driverController.rightTrigger().whileTrue(new ShooterCommand(shooterSubsystem));
    driverController.x().onTrue(drivebase.getToNextDriveModeCommand());
  }

  /**
   * Retrieve Autonomous Command
   * @return null, absence of autons
   */
  public Command getAutonomousCommand() {
    return null;
  }
}

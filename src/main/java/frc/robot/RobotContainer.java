// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.DifferentialDriveSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
  // Declare and Assign Subsystems
  private final DifferentialDriveSubsystem drivebase = new DifferentialDriveSubsystem();

  // Declare and Assign Controller(s)
  private final CommandXboxController driverController = new CommandXboxController(OperatorConstants.driverControllerPort);

  /**
   * Robot Container Constructor
   * @return Xbox Controller Bindings and Default Arcade Drive Command
   */
  public RobotContainer() {
    configureBindings();
  }

  /**
   * Configure Xbox Controller Bindings
   */
  private void configureBindings() {
    driverController.x().onTrue(drivebase.goToPositionCommand(0, 0));
    driverController.y().onTrue(drivebase.goToPositionCommand(0, 1));

    driverController.b().onTrue(drivebase.goToPositionCommand(1, 0));
    driverController.a().onTrue(drivebase.runSingleMotorCommand(driverController::getLeftY));
  }

  /**
   * Retrieve Autonomous Command
   * @return null, absence of autons
   */
  public Command getAutonomousCommand() {
    return null;
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.RobotStates.WristPitchStates;
import frc.robot.Constants.RobotStates.WristYawStates;
import frc.robot.commands.ControlPitchCommand;
import frc.robot.commands.ControlYawCommand;
import frc.robot.commands.RotateWristCommand;
import frc.robot.commands.RotateWristTogetherCommand;
import frc.robot.commands.RunIntakeCommand;
import frc.robot.subsystems.WristSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {

  // Declare and Assign Controller(s)
  private final CommandXboxController driverController = new CommandXboxController(OperatorConstants.driverControllerPort);

  private final WristSubsystem wristSubsystem = new WristSubsystem();
  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();

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
    driverController.leftTrigger().whileTrue(new RunIntakeCommand(intakeSubsystem, true));
    driverController.rightTrigger().whileTrue(new RunIntakeCommand(intakeSubsystem, false));

    driverController.x().whileTrue(new RotateWristCommand(wristSubsystem, false));
    driverController.b().whileTrue(new RotateWristCommand(wristSubsystem, true));
    driverController.y().whileTrue(new RotateWristTogetherCommand(wristSubsystem, false));
    driverController.a().whileTrue(new RotateWristTogetherCommand(wristSubsystem, true));

    driverController.povUp().onTrue(new ControlPitchCommand(wristSubsystem, WristPitchStates.UP));
    driverController.povDown().onTrue(new ControlPitchCommand(wristSubsystem, WristPitchStates.OUT));

    driverController.leftBumper().onTrue(new ControlYawCommand(wristSubsystem, WristYawStates.LEFT));
    driverController.rightBumper().onTrue(new ControlYawCommand(wristSubsystem, WristYawStates.RIGHT));

    driverController.povLeft().onTrue(new ControlYawCommand(wristSubsystem, WristYawStates.CENTER));
  }

  /**
   * Retrieve Autonomous Command
   * @return Empty command, absence of autons
   */
  public Command getAutonomousCommand() {
    return Commands.none();
  }
}

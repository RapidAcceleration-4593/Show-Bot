// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.ArcadeDriveCommand;
import frc.robot.commands.RunIntakeCommand;
import frc.robot.subsystems.DifferentialDriveSubsystem;
import frc.robot.subsystems.DrivebaseSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;


public class RobotContainer {
  // Declare and Assign Subsystems
 //.. private final DifferentialDriveSubsystem drivebase = new DifferentialDriveSubsystem();

  // Declare and Assign Controller(s)
  private final CommandXboxController driverController = new CommandXboxController(OperatorConstants.driverControllerPort);

  private final DrivebaseSubsystem drivebase = new DrivebaseSubsystem();
  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();

  /**
   * Robot Container Constructor
   * @return Xbox Controller Bindings and Default Arcade Drive Command
   */
  public RobotContainer() {
    configureBindings();
    drivebase.setDefaultCommand(new ArcadeDriveCommand(drivebase, () -> driverController.getLeftY() / 3, () -> driverController.getRightX() / 3));
  }



  /**
   * Configure Xbox Controller Bindings
   */
  private void configureBindings() {
      driverController.rightTrigger().whileTrue(new RunIntakeCommand(intakeSubsystem, false));
      driverController.leftTrigger().whileTrue(new RunIntakeCommand(intakeSubsystem, true));
      
      
  }


  /**
   * Retrieve Autonomous Command
   * @return null, absence of autons
   */
  public Command getAutonomousCommand() {
    return null;
  }
}

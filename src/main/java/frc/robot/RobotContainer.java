// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.shooter.ShootCommand;
import frc.robot.subsystems.shooter.ShooterSubsystem;
import frc.robot.subsystems.sicckleds.LEDSubsystem;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import java.io.File;
import swervelib.SwerveInputStream;

public class RobotContainer {

	// Replace with CommandPS4Controller or CommandJoystick if needed
	final         CommandXboxController driverXbox = new CommandXboxController(0);
	// The robot's subsystems and commands are defined here...
	// private final SwerveSubsystem       drivebase  = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(), "swerve"));
	private final ShooterSubsystem shooter = new ShooterSubsystem();
	private final LEDSubsystem leds = new LEDSubsystem();
	// SwerveInputStream driveAngularVelocity = SwerveInputStream.of(drivebase.getSwerveDrive(),
	// 																() -> driverXbox.getLeftY(),
	// 																() -> driverXbox.getLeftX() * -1)
	// 															.withControllerRotationAxis(() -> driverXbox.getRightX() * -1)
	// 															.deadband(OperatorConstants.DEADBAND)
	// 															.scaleTranslation(0.8)
	// 															.allianceRelativeControl(true);

	// SwerveInputStream driveRobotOriented = driveAngularVelocity.copy().robotRelative(true)
																// .allianceRelativeControl(false);

	// Command driveFieldOrientedAngularVelocity = drivebase.driveFieldOriented(driveAngularVelocity);
	// Command driveRobotOrientedAngularVelocity = drivebase.driveFieldOriented(driveAngularVelocity);

	public RobotContainer() {
		// Configure the trigger bindings
		configureBindings();
		DriverStation.silenceJoystickConnectionWarning(true);
	}

	private void configureBindings() {
		// drivebase.setDefaultCommand(driveFieldOrientedAngularVelocity);

		// driverXbox.a().onTrue((Commands.runOnce(drivebase::zeroGyro)));
		driverXbox.rightTrigger().onTrue(new ShootCommand(shooter));

		driverXbox.b().onTrue(leds.nextPatternCommand());
	}

	public Command getAutonomousCommand() {
		// An example command will be run in autonomous
		return Commands.none();
	}

	public void setMotorBrake(boolean brake) {
		// drivebase.setMotorBrake(brake);
	}
}
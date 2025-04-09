package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivebaseConstants;

public class DifferentialDriveSubsystem extends SubsystemBase {

    private SparkMax leftMotor = DrivebaseConstants.frontLeftMotor;
    private SparkMax rightMotor = DrivebaseConstants.frontRightMotor;

    private PIDController pitchController = new PIDController(0.3, 0, 0);
    private static final double MAXIUM_OUTPUT = 0.1;

    private PIDController yawController = new PIDController(0.3, 0, 0);

    public DifferentialDriveSubsystem() {
        SparkMaxConfig motorConfig = new SparkMaxConfig();
        motorConfig.idleMode(IdleMode.kBrake);
        motorConfig.disableFollowerMode();
        
        rightMotor.configure(motorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        leftMotor.configure(motorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        pitchController.setTolerance(5/360); // Error tolerance of 5 degrees of motor rotation
    }

    public void runPitchPID(double pitch) {
        var initial = pitchController.calculate(leftMotor.getEncoder().getPosition());
        var limited = Math.max(initial, -MAXIUM_OUTPUT);
        limited = Math.min(limited, MAXIUM_OUTPUT);

        leftMotor.set(limited);
        rightMotor.set(-limited);
    }

    public void runYawPID(double pitch) {
        var initial = yawController.calculate(getCurrentYaw());
        var limited = Math.max(initial, -MAXIUM_OUTPUT);
        limited = Math.min(limited, MAXIUM_OUTPUT);

        leftMotor.set(limited);
        rightMotor.set(limited);
    }

    public Command goToPitchCommand(double pitch) {
        return run(() -> runPitchPID(pitch))
            .until(pitchController::atSetpoint)
            .beforeStarting(() -> pitchController.setSetpoint(pitch));
    }

    public Command goToYawCommand(double yaw) {
        return run(() -> runYawPID(yaw))
            .until(yawController::atSetpoint)
            .beforeStarting(() -> yawController.setSetpoint(yaw));
    }

    @Override
    public void periodic() {
        System.out.println(leftMotor.getEncoder().getPosition());
        System.out.println(leftMotor.get());
    }

    private double getCurrentYaw() {
        return leftMotor.getEncoder().getPosition() + rightMotor.getEncoder().getPosition();
    }
}

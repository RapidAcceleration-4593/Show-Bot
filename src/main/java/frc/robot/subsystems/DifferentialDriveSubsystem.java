package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.config.ClosedLoopConfig;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivebaseConstants;

public class DifferentialDriveSubsystem extends SubsystemBase {

    private SparkMax leftMotor = DrivebaseConstants.frontLeftMotor;
    private SparkMax rightMotor = DrivebaseConstants.frontRightMotor;

    private PIDController pitchController = new PIDController(0.001, 0, 0);
    private PIDController yawController;

    public DifferentialDriveSubsystem() {
        SparkMaxConfig followConfig = new SparkMaxConfig();
        followConfig.follow(leftMotor, true);
        rightMotor.configure(followConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void runPID(double pitch) {
        leftMotor.set(pitchController.calculate(leftMotor.getEncoder().getPosition()));
    }

    public Command goToPitchCommand(double pitch) {
        return run(() -> runPID(pitch))
            .until(pitchController::atSetpoint)
            .beforeStarting(() -> pitchController.setSetpoint(pitch));
    }

    @Override
    public void periodic() {
        System.out.println(leftMotor.getEncoder().getPosition());
        System.out.println(leftMotor.get());
    }
}

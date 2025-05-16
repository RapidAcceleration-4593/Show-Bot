package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DifferentialWristConstants;

public class WristSubsystem extends SubsystemBase {

    private final SparkMax leftMotor = DifferentialWristConstants.leftMotor;
    private final SparkMax rightMotor = DifferentialWristConstants.rightMotor;

    public final Encoder leftEncoder = DifferentialWristConstants.leftEncoder;
    public final Encoder rightEncoder = DifferentialWristConstants.rightEncoder;

    private final PIDController pitchController = new PIDController(0.05, 0, 0);
    private final PIDController yawController = new PIDController(0.05, 0, 0);

    // private final int[] PITCH_SETPOINTS = {500, 1600}; // Up, Out
    // private final int[] YAW_SETPOINTS = {2400, 0, -2400}; // Left, Middle, Right
    private final double MAXIUM_OUTPUT = 0.15;

    private final SparkMaxConfig motorConfig = new SparkMaxConfig();

    public WristSubsystem() {
        motorConfig.idleMode(IdleMode.kBrake);
        
        leftMotor.configure(motorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        rightMotor.configure(motorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void runPitchPID(double pitch) {
        double initial = pitchController.calculate(getCurrentPitch());
        double limited = Math.max(initial, -MAXIUM_OUTPUT);
        limited = Math.min(limited, MAXIUM_OUTPUT);

        leftMotor.set(limited);
        rightMotor.set(-limited);
    }

    public void runYawPID(double pitch) {
        double initial = yawController.calculate(getCurrentYaw());
        double limited = Math.max(initial, -MAXIUM_OUTPUT);
        limited = Math.min(limited, MAXIUM_OUTPUT);

        leftMotor.set(-limited);
        rightMotor.set(-limited);
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
    
    private double getCurrentYaw() {
        return leftEncoder.get() + rightEncoder.get();
    }

    private double getCurrentPitch() {
        return (rightEncoder.get() - leftEncoder.get()) / 2;
    }

    public void rotateWristTogether(boolean inverted) {
        leftMotor.set(inverted ? 0.7 : -0.7);
        rightMotor.set(inverted ? -0.7 : 0.7);
    }

    public void rotateWrist(boolean inverted) {
        leftMotor.set(inverted ? 0.7 : -0.7);
        rightMotor.set(inverted ? 0.7 : -0.7);
    }

    public void stopWrist() {
        leftMotor.stopMotor();
        rightMotor.stopMotor();
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Pitch", getCurrentPitch());
        SmartDashboard.putNumber("Yaw", getCurrentYaw());
    }
}

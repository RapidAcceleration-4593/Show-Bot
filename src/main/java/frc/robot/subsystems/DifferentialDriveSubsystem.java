package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivebaseConstants;

public class DifferentialDriveSubsystem extends SubsystemBase {

    private SparkMax leftMotor = DrivebaseConstants.frontLeftMotor;
    private SparkMax rightMotor = DrivebaseConstants.frontRightMotor;

    private PIDController leftPID = new PIDController(0.5, 0, 0);
    private PIDController rightPID = new PIDController(0.5, 0, 0);

    private static final double MAXIUM_OUTPUT = 0.15;

    public DifferentialDriveSubsystem() {
        SparkMaxConfig motorConfig = new SparkMaxConfig();
        motorConfig.idleMode(IdleMode.kBrake);
        motorConfig.disableFollowerMode();
        
        rightMotor.configure(motorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        leftMotor.configure(motorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        leftPID.setTolerance(15/360); // Error tolerance of 10 degrees of motor rotation
        rightPID.setTolerance(15/360);
    }

    public void setSetpoint(double pitch, double yaw) {
        leftPID.setSetpoint(pitch - yaw / 2);
        rightPID.setSetpoint(-pitch - yaw / 2);
    }

    public void updatePID() {
        leftMotor.set(limit(leftPID.calculate(leftMotor.getEncoder().getPosition())));
        rightMotor.set(limit(rightPID.calculate(rightMotor.getEncoder().getPosition())));
    }

    /** Returns value to +/- MAXIUMUM_OUTPUT */
    private double limit(double value) {
        var out = Math.max(value, -MAXIUM_OUTPUT);
        return Math.min(out, MAXIUM_OUTPUT);
    }

    public void runSingleMotor(double speed) {
        rightMotor.set(-speed);
    }

    public Command runSingleMotorCommand(DoubleSupplier input) {
        return run(() -> runSingleMotor(input.getAsDouble()));
    }

    @Override
    public void periodic() {
        System.out.println("Left: "+leftPID.getError());
        System.out.println("Rigth: "+rightPID.getError());
        //System.out.println(leftMotor.get());
    }

    public Command goToPositionCommand(double pitch, double yaw) {
        return run(this::updatePID)
            .until(() -> leftPID.atSetpoint() && rightPID.atSetpoint())
            .beforeStarting(() -> setSetpoint(pitch, yaw))
            .finallyDo(() -> {leftMotor.stopMotor(); rightMotor.stopMotor();});
    }
}

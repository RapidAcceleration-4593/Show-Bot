package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DifferentialWristConstants;
import frc.robot.Constants.RobotStates.WristPitchStates;
import frc.robot.Constants.RobotStates.WristYawStates;

public class WristSubsystem extends SubsystemBase {

    private final SparkMax leftMotor = DifferentialWristConstants.leftMotor;
    private final SparkMax rightMotor = DifferentialWristConstants.rightMotor;

    public final Encoder leftEncoder = DifferentialWristConstants.leftEncoder;
    public final Encoder rightEncoder = DifferentialWristConstants.rightEncoder;

    private final PIDController pitchController = new PIDController(0.05, 0, 0);
    private final PIDController yawController = new PIDController(0.05, 0, 0);

    private final int[] PITCH_SETPOINTS = {500, 1600};
    private final int[] YAW_SETPOINTS = {2400, 0, -2400};
    private final double MAXIUM_OUTPUT = 0.25;

    private final SparkMaxConfig motorConfig = new SparkMaxConfig();

    public WristSubsystem() {
        motorConfig.idleMode(IdleMode.kBrake);
        
        leftMotor.configure(motorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        rightMotor.configure(motorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    
        pitchController.setTolerance(20);
        yawController.setTolerance(20);
    }

    public void setPitchState(WristPitchStates state) {
        pitchController.setSetpoint(getPitchSetpoint(state));
    }

    public void setYawState(WristYawStates state) {
        yawController.setSetpoint(getYawSetpoint(state));
    }

    public double getPitchSetpoint(WristPitchStates state) {
        return switch (state) {
            case UP -> PITCH_SETPOINTS[0];
            case OUT -> PITCH_SETPOINTS[1];
            default -> throw new IllegalStateException("Passed in an WristPitchState that does not have an associated setpoint!");
        };
    }

    public double getYawSetpoint(WristYawStates state) {
        return switch (state) {
            case LEFT -> YAW_SETPOINTS[0];
            case CENTER -> YAW_SETPOINTS[1];
            case RIGHT -> YAW_SETPOINTS[2];
            default -> throw new IllegalStateException("Passed in an WristYawState that does not have an associated setpoint!");
        };
    }

    public void controlPitchPID() {
        double initial = pitchController.calculate(getCurrentPitch());
        double limited = Math.max(initial, -MAXIUM_OUTPUT);
        limited = Math.min(limited, MAXIUM_OUTPUT);

        leftMotor.set(limited);
        rightMotor.set(-limited);
    }

    public void controlYawPID() {
        double initial = yawController.calculate(getCurrentYaw());
        double limited = Math.max(initial, -MAXIUM_OUTPUT);
        limited = Math.min(limited, MAXIUM_OUTPUT);

        leftMotor.set(-limited);
        rightMotor.set(-limited);
    }

    public boolean pitchAtSetpoint() {
        return pitchController.atSetpoint();
    }

    public boolean yawAtSetpoint() {
        return yawController.atSetpoint();
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

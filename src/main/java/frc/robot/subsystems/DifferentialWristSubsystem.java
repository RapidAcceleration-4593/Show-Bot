package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DifferentialWristConstants;

public class DifferentialWristSubsystem extends SubsystemBase {

    private final SparkMax leftMotor = DifferentialWristConstants.leftMotor;
    private final SparkMax rightMotor = DifferentialWristConstants.rightMotor;

    private final SparkMax intakeMotor = DifferentialWristConstants.intakeMotor;

    private final SparkMaxConfig motorConfig = new SparkMaxConfig();

    public DifferentialWristSubsystem() {
        motorConfig.idleMode(IdleMode.kBrake);
        
        leftMotor.configure(motorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        rightMotor.configure(motorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        intakeMotor.configure(motorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
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

    public void runIntake(boolean inverted) {
        intakeMotor.set(inverted ? 1.0 : -1.0);
    }

    public void stopIntake() {
        intakeMotor.stopMotor();
    }
}

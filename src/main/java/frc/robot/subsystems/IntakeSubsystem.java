package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {
    
    private final SparkMax intakeMotor = IntakeConstants.intakeMotor;

    private final SparkMaxConfig motorConfig = new SparkMaxConfig();

    public IntakeSubsystem() {
        motorConfig.idleMode(IdleMode.kBrake);

        intakeMotor.configure(motorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void runIntake(boolean inverted) {
        intakeMotor.set(inverted ? 1.0 : -1.0);
    }

    public void stopIntake() {
        intakeMotor.stopMotor();
    }
}

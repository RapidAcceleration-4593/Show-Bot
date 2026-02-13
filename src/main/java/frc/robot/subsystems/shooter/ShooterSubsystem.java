package frc.robot.subsystems.shooter;

import com.revrobotics.spark.SparkMax;

import java.util.function.Supplier;

import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
    
    SparkMax spinnerA, spinnerB, feeder;
    
    public ShooterSubsystem(){
        spinnerA = new SparkMax(Constants.ShooterConstants.SHOOT_SPINNER_A_ID, MotorType.kBrushless);
        spinnerB = new SparkMax(Constants.ShooterConstants.SHOOT_SPINNER_B_ID, MotorType.kBrushless);
        feeder = new SparkMax(Constants.ShooterConstants.SHOOT_FEEDER_ID, MotorType.kBrushless);
        SparkMaxConfig bConfig = new SparkMaxConfig();
        bConfig.follow(spinnerA, true);
        spinnerB.configure(bConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
    }

    public Runnable startSpinner = () -> spinnerA.setVoltage(12.0);
    public Runnable startFeeder = () -> feeder.setVoltage(3.0);
    public Runnable stopSpinner = () -> spinnerA.stopMotor();
    public Runnable stopFeeder = () -> feeder.stopMotor();
    public Supplier<Boolean> atSpeed = () -> spinnerA.getEncoder().getVelocity() >= Constants.ShooterConstants.SHOOT_RPM;

}

package frc.robot.subsystems.shooter;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import frc.robot.Constants;

public class ShooterSubsystem {
    SparkMax spinnerA;
    SparkMax spinnerB;
    SparkMax feeder;
    public ShooterSubsystem(){
        spinnerA = new SparkMax(Constants.ShooterConstants.SHOOT_SPINNER_A_ID, MotorType.kBrushless);
        spinnerB = new SparkMax(Constants.ShooterConstants.SHOOT_SPINNER_B_ID, MotorType.kBrushless);
        feeder = new SparkMax(Constants.ShooterConstants.SHOOT_FEEDER_ID, MotorType.kBrushless);
        SparkMaxConfig bConfig = new SparkMaxConfig();
        bConfig.follow(spinnerA, true);
        spinnerB.configure(bConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
    }
    public void startSpinner(){
        spinnerA.setVoltage(12.0);
    }
    public void startFeeder(){
        feeder.setVoltage(3.0);
    }
}

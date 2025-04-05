package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivebaseConstants;

public class DrivebaseSubsystem extends SubsystemBase {

    // Declare and Assign IDs to CANSparkMax
    private final SparkMax frontLeftMotor = DrivebaseConstants.frontLeftMotor;
    private final SparkMax frontRightMotor = DrivebaseConstants.frontRightMotor;

    // Construct a Differential Drive object
    private final DifferentialDrive drive = new DifferentialDrive(frontLeftMotor, frontRightMotor);

    private final SparkMaxConfig brakeModeConfig = new SparkMaxConfig();
    private final SparkMaxConfig followLeftMotorConfig = new SparkMaxConfig();
    private final SparkMaxConfig followRightMotorConfig = new SparkMaxConfig();

    /**
     * Drivebase Subsystem Constructor
     * @return Establishes motor controller groups between left and right side.
     */
    public DrivebaseSubsystem() {
        brakeModeConfig.idleMode(IdleMode.kBrake);
        followLeftMotorConfig.follow(frontLeftMotor);
        followRightMotorConfig.follow(frontRightMotor);

        frontLeftMotor.configure(brakeModeConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        frontRightMotor.configure(brakeModeConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }


    /**
     * Arcade Drive Command to control binding inputs
     * @param translation Forward/Backward Movement with 
     * @param rotation Rotational Movement with Right Joystick
     */
    public void arcadeDrive(double translation, double rotation) {
        drive.arcadeDrive(rotation, translation);
    }

    /**
     * Stop all drivebase motors
     */
    public void stop() {
        drive.stopMotor();
    }
}

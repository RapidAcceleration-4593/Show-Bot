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

    // Declare and Assign IDs to CANSparkMax.
    private final SparkMax frontLeftMotor = DrivebaseConstants.frontLeftMotor;
    private final SparkMax frontRightMotor = DrivebaseConstants.frontRightMotor;
    private final SparkMax rearLeftMotor = DrivebaseConstants.rearLeftMotor;
    private final SparkMax rearRightMotor = DrivebaseConstants.rearRightMotor;

    // Construct a Differential Drive object.
    private final DifferentialDrive drive = new DifferentialDrive(frontLeftMotor, frontRightMotor);

    private final SparkMaxConfig brakeModeConfig = new SparkMaxConfig();
    private final SparkMaxConfig followLeftMotorConfig = new SparkMaxConfig();
    private final SparkMaxConfig followRightMotorConfig = new SparkMaxConfig();

    /**
     * Drivebase Subsystem Constructor.
     * @return Establishes motor controller groups between left and right side.
     */
    public DrivebaseSubsystem() {
        brakeModeConfig.idleMode(IdleMode.kBrake);
        followLeftMotorConfig.follow(frontLeftMotor);
        followRightMotorConfig.follow(frontRightMotor);

        frontLeftMotor.configure(brakeModeConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        frontRightMotor.configure(brakeModeConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        rearLeftMotor.configure(brakeModeConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        rearRightMotor.configure(brakeModeConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        rearLeftMotor.configure(followLeftMotorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        rearRightMotor.configure(followRightMotorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    /**
     * Arcade Drive Command to control binding inputs.
     * @param translation Forward/Backward Movement with Left Joystick.
     * @param rotation Rotational Movement with Right Joystick.
     */
    public void arcadeDrive(double translation, double rotation) {
        drive.arcadeDrive(rotation, translation);
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        drive.tankDrive(leftSpeed, -rightSpeed);
    }

    public void curveDrive(double translation, double rotation) {
        drive.curvatureDrive(translation, rotation, false);
    }

    /** Stop all drivebase motors. */
    public void stop() {
        drive.stopMotor();
    }
}

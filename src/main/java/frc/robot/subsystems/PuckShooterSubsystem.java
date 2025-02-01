package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkRelativeEncoder;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PuckShooterConstants;

public class PuckShooterSubsystem extends SubsystemBase {

    // Declare and Assign IDs to Motors and Encoders
    private final SparkMax leftShooter = PuckShooterConstants.leftShooter;
    private final SparkMax rightShooter = PuckShooterConstants.rightShooter;

    private final SparkMax spinner = PuckShooterConstants.spinner;

    private final SparkRelativeEncoder leftShooterEncoder = PuckShooterConstants.leftShooterEncoder;
    private final SparkRelativeEncoder rightShooterEncoder = PuckShooterConstants.rightShooterEncoder;

    private final SparkMaxConfig brakeModeConfig = new SparkMaxConfig();
    private final SparkMaxConfig followShooterConfig = new SparkMaxConfig();

    /**
     * Puck Shooter Subsystem Constructor
     * @return Connect leftShooter and rightShooter ouputs, inverting the left side
     */
    public PuckShooterSubsystem () {
        brakeModeConfig.idleMode(IdleMode.kBrake);
        followShooterConfig.follow(rightShooter, true);

        spinner.configure(brakeModeConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        leftShooter.configure(followShooterConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    /**
     * Run both shooter motors
     * @param speed Motor speed from -1 to 1
     */
    public void runShooter(double speed) {
        rightShooter.set(speed);
    }

    /**
     * Stop both shooter motors
     * @return Sets shooter motor speeds to zero
     */
    public void stopShooter() {
        rightShooter.set(0);
    }

    /**
     * Run spinner wheel motor
     * @param speed Motor speed from -1 to 1
     */
    //TODO Implement with new motor
    public void runSpinnerWheel(double speed) {
        spinner.set(speed);
    }

    /**
     * Stop spinner wheel motor
     * @return Sets spinner wheel motor speed to zero
     */
    //TODO Implement with new motor
    public void stopSpinnerWheel() {
        spinner.set(0);
    }

    /**
     * Checks absolute value of the average velocity from each shooting motor's controller
     * @return boolean of average encoder velocity greater than 5500 RPMs
     */
    //TODO optimize so it shoots at maximum speed
    public boolean IsAtShootingSpeed()
    {   
        // Shooter encoders use different measurement systems currently
        return Math.abs(leftShooterEncoder.getVelocity() + rightShooterEncoder.getVelocity()*1000) /2 > 4000;
    }
}

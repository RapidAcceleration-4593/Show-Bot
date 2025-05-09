package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivebaseConstants;

public class IntakeSubsystem extends SubsystemBase {
    private final SparkMax intakeMotor = DrivebaseConstants.intakeMotor;
    
    public void runintake(boolean inverted){
        intakeMotor.set(inverted? -0.5: 0.5);
    }
    public void stopintake(){
        intakeMotor.stopMotor();
    }
}

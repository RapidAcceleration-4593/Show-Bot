// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkRelativeEncoder;

import frc.robot.subsystems.DrivebaseSubsystem;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int driverControllerPort = 0;
  }

  public static final class DrivebaseConstants {
    public static final SparkMax frontLeftMotor = new SparkMax(3, MotorType.kBrushless);
    public static final SparkMax frontRightMotor = new SparkMax(4, MotorType.kBrushless);
    public static final SparkMax rearLeftMotor = new SparkMax(5, MotorType.kBrushless);
    public static final SparkMax rearRightMotor = new SparkMax(7, MotorType.kBrushless);
    
    public static final DrivebaseSubsystem.DriveMode defaultDriveMode = DrivebaseSubsystem.DriveMode.TANK;
  }

  public static final class PuckShooterConstants {
    public static final SparkMax leftShooter = new SparkMax(2, MotorType.kBrushless);
    public static final SparkMax rightShooter = new SparkMax(6, MotorType.kBrushless);

    public static final SparkMax spinner = new SparkMax(1, MotorType.kBrushed);
    public static final double spinnerSpeed = 0.25;

    public static final SparkRelativeEncoder leftShooterEncoder = (SparkRelativeEncoder) leftShooter.getEncoder();
    public static final SparkRelativeEncoder rightShooterEncoder = (SparkRelativeEncoder) rightShooter.getEncoder();
  }
}

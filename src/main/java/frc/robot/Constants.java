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
    public static final SparkMax frontLeftMotor = new SparkMax(1, MotorType.kBrushless);
    public static final SparkMax frontRightMotor = new SparkMax(2, MotorType.kBrushless);
  }
}
